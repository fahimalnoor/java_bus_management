import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class ChangePasswordM extends JFrame implements ActionListener
{
	JLabel t1,t2;
	JPasswordField currentText,newText;
	JButton back,logout,confirm;
	JPanel panel;
	String UserId;
	public ChangePasswordM(String UserId)
	 {
		super(" Change Password ");
		this.UserId=UserId;
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		t1=new JLabel("Enter Current Pasword");
		t1.setBounds(100,130,150,50);
		panel.add(t1);
		
		t2=new JLabel("Enter New Pasword: ");
		t2.setBounds(100,250,150,50);
		panel.add(t2);
		
		currentText=new JPasswordField();
		currentText.setBounds(270,130,200,50);
		panel.add(currentText);
		
		newText=new JPasswordField();
		newText.setBounds(270,250,200,50);
		panel.add(newText);
		
		logout=new JButton("Log Out");
		logout.setBounds(500,10,250,50);
		logout.setBackground(Color.red);
		logout.addActionListener(this);
		panel.add(logout);

		back=new JButton("Back");
		back.setBounds(500,380,250,50);
		back.setBackground(Color.blue);
		back.setForeground(Color.white);
		back.addActionListener(this);
		panel.add(back);
		
		confirm=new JButton("Confirm");
		confirm.setBounds(250,380,250,50);
		confirm.setBackground(Color.green);
		confirm.addActionListener(this);
		panel.add(confirm);
		
		this.add(panel);
	 }
	 public void  actionPerformed(ActionEvent ae)
	 {
		 String ac=ae.getActionCommand();
		if(ac.equals(logout.getText()))
		{
			 LogIn ln=new LogIn();
			 ln.setVisible(true);
			 this.setVisible(false);
		}
        else if(ac.equals(back.getText()))
		{
			DriverInfo ed=new DriverInfo(UserId);
			ed.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.equals(confirm.getText()))
		{
			newPassword(UserId);
		}
	 }
	 public void newPassword(String UserId)
	 {
		 String newId = UserId;
		 String newpass= newText.getText();
		 String currentpassword=currentText.getText();
		 String change;
		 String query2 = "UPDATE login SET Password = '"+newpass+"' WHERE userId='"+newId+"'";
	     String query = "SELECT `Password` FROM `login` WHERE userId='"+newId+"'";
         try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			boolean flag=false;
			while(rs.next())
			{
			   change=rs.getString("Password");
			if(change.equals(currentText.getText()))
			{
				st.executeUpdate(query2);
				flag=true;
				JOptionPane.showMessageDialog(this,"Password Change");
				break;
			}
			}
			if(!flag)
			{
				currentText.setText("");
				newText.setText("");			
				JOptionPane.showMessageDialog(this,"Invalid Current Password");
		    }
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
			ManagerInfo mi=new ManagerInfo(UserId);
			mi.setVisible(true);
			this.setVisible(false);
		}		
	 
	 }
}
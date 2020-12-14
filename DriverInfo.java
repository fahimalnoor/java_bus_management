import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class DriverInfo extends JFrame implements ActionListener
{
	 JLabel userid,name,phoneno,address;
	 JTextField useridText,nameText,phonenoText,phoneno2Text,addressText;
	 JButton logout,back,update,delete,changepassword;
	 JPanel panel;
	 String UserId;
	
	 public  DriverInfo(String UserId)
	 {
		super(" Driver Information ");
		this.UserId=UserId;
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		userid=new JLabel(" ID : ");
		userid.setBounds(100,130,100,50);
		panel.add(userid);
		
		useridText=new JTextField();
		useridText.setBounds(230,130,200,50);
		panel.add(useridText);
		
		
		name=new JLabel(" Name : ");
		name.setBounds(100,190,100,50);
		panel.add(name);
		
		nameText=new JTextField();
		nameText.setBounds(230,190,200,50);
		panel.add(nameText);
		
		phonenoText=new JTextField("+880");
		phonenoText.setBounds(230,250,50,50);
		phonenoText.setEnabled(false);
		panel.add(phonenoText);
		
		phoneno=new JLabel("Phone Number :");
		phoneno.setBounds(100,250,100,50);
		panel.add(phoneno);
		
		phoneno2Text=new JTextField();
		phoneno2Text.setBounds(280,250,150,50);
		panel.add(phoneno2Text);
		
		address=new JLabel("ADDRESS :");
		address.setBounds(100,310,100,50);
		panel.add(address);
		
		addressText = new JTextField();
		addressText.setBounds(230,310,200,50);
		panel.add(addressText);
		
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
		
		delete=new JButton("DELETE");
		delete.setBounds(500,300,250,50);
		delete.setBackground(Color.red);
		delete.addActionListener(this);
		panel.add(delete);
		
		update=new JButton("update");
		update.setBounds(500,220,250,50);
		update.setBackground(Color.green);
		update.addActionListener(this);
		panel.add(update);
		
		changepassword = new JButton("Change Password");
        changepassword.setBounds(120,10,250,50);
        changepassword.setBackground(Color.orange);	
        changepassword.addActionListener(this);		
		panel.add(changepassword);
		
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
			DriverHome ed=new DriverHome(UserId);
			ed.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.equals(update.getText()))
		 {
			 myInfoUpdate();
		 }
		 else if(ac.equals(delete.getText()))
		 {
			 myInfoDelete();
		 }
		 else if(ac.equals(changepassword.getText()))
		 {
			 ChangePasswordD pd=new ChangePasswordD(UserId);
			 pd.setVisible(true);
			 this.setVisible(false);
		 }
		
	 }
	 public void autoInfoD(String UserId)
	 {
		 String load=UserId;
		 String query = "SELECT `Drivername`, `Phonenumber`, `Address` FROM `driver` WHERE `UserId`='"+load+"';";
         	     
	     try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			
			boolean flag=false;
			while(rs.next())
			{
				useridText.setText(UserId+"");
				nameText.setText(rs.getString("Drivername"));
				phonenoText.setText("+880");
				phoneno2Text.setText(rs.getString("Phonenumber"));
				addressText.setText(rs.getString("Address"));
				flag=true;
				useridText.setEnabled(false);
				update.setEnabled(true);
				delete.setEnabled(true);
			}
			if(!flag)
			{
				nameText.setText("");
				phoneno2Text.setText("");
				addressText.setText("");
				JOptionPane.showMessageDialog(this,"Invalid ID");
		    }
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	 }
	 public void myInfoUpdate()
	 {
	
		 String newId = useridText.getText();
		 String newName= nameText.getText();
		 String newphone=phoneno2Text.getText();
		 String address=addressText.getText();
		 
		 String query = "UPDATE driver SET Drivername='"+newName+"',Phonenumber = '"+newphone+"',Address = '"+address+"' WHERE userId='"+newId+"'";
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			 Statement st=con.createStatement();
			 st.executeUpdate(query);
			 st.close();
			 con.close();
			 JOptionPane.showMessageDialog(this, "Update Done");
		 }
         catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Update Fail");
		}
	 }
	 public void myInfoDelete()
	 {
		 String newId=UserId;
		 String query = "DELETE from driver WHERE userId='"+newId+"';";
		 String query2 = "DELETE from login WHERE userId='"+newId+"';";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			Statement st=con.createStatement();
			st.execute(query);
			st.execute(query2);
			st.close();
			con.close();
			
			JOptionPane.showMessageDialog(this, "Delete Done");
			
			LogIn ln=new LogIn();
			ln.setVisible(true);
			this.setVisible(false);
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
	 }
}
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewDriver extends JFrame implements ActionListener
{
	 JLabel userid,name,phoneno,address;
	 JTextField useridText,nameText,phonenoText,phoneno2Text,addressText;
	 JButton logout,load,update,delete,back,refresh;
	 JPanel panel;
	 String UserId;
	
	 public  ViewDriver(String UserId)
	 {
		super(" View Driver ");
		this.UserId=UserId;
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		refresh=new JButton(" Refresh ");
		refresh.setBounds(200,20,250,50);
		refresh.setBackground(Color.orange);
		refresh.addActionListener(this);
		panel.add(refresh);
		
		userid=new JLabel("User ID : ");
		userid.setBounds(100,130,100,50);
		panel.add(userid);
		
		useridText=new JTextField();
		useridText.setBounds(230,130,200,50);
		panel.add(useridText);
		
		
		name=new JLabel("Driver Name : ");
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
		
		address=new JLabel("Address :");
		address.setBounds(100,310,100,50);
		panel.add(address);
		
		addressText=new JTextField();
		addressText.setBounds(230,310,200,50);
		panel.add(addressText);
		
		logout=new JButton("Log Out");
		logout.setBounds(500,10,250,50);
		logout.setBackground(Color.red);
		logout.addActionListener(this);
		panel.add(logout);
		
		delete=new JButton("DELETE");
		delete.setBounds(500,300,250,50);
		delete.setBackground(Color.red);
		delete.addActionListener(this);
		delete.setEnabled(false);
		panel.add(delete);
		
		update=new JButton("update");
		update.setBounds(500,220,250,50);
		update.setBackground(Color.green);
		update.addActionListener(this);
		update.setEnabled(false);
		panel.add(update);
		
		back=new JButton("Back");
		back.setBounds(500,380,250,50);
		back.setBackground(Color.blue);
		back.setForeground(Color.white);
		back.addActionListener(this);
		panel.add(back);
		
		load=new JButton(" Load ");
		load.setBounds(450,130,250,50);
		load.setBackground(Color.yellow);
		load.addActionListener(this);
		panel.add(load);
		
		
		
		this.add(panel);
	 }
	 public void actionPerformed(ActionEvent ae)
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
			 ManageDriver md=new ManageDriver(UserId);
			 md.setVisible(true);
			this.setVisible(false);
		 }
		 if(ac.equals(refresh.getText()))
		 {
			update.setEnabled(false);
			delete.setEnabled(false);
			useridText.setEnabled(true);
			useridText.setText("");
			nameText.setText("");
			phoneno2Text.setText("");
			addressText.setText("");
		 }
		 else if(ac.equals(update.getText()))
		 {
			 driverUpdate();
		 }
		  else if(ac.equals(delete.getText()))
		 {
			 driverDelete();
		 }
		  else if(ac.equals(load.getText()))
		 {
			 driverLoad();
		 }
	 }
	 public void driverLoad()
	 {
		 String load = useridText.getText();
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
				nameText.setText(rs.getString("Drivername"));
				phonenoText.setText("+880");
				phoneno2Text.setText(rs.getString("phonenumber"));
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
	public void driverUpdate()
	 {
		 String newId = useridText.getText();
		 String newName= nameText.getText();
		 String newphone=phoneno2Text.getText();
		 String newaddress=addressText.getText();
		 
		 String query = "UPDATE driver SET Drivername='"+newName+"',Phonenumber = '"+newphone+"',Address = '"+newaddress+"' WHERE UserId='"+newId+"'";
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			 Statement st=con.createStatement();
			 st.executeUpdate(query);
			 st.close();
			 con.close();
			 JOptionPane.showMessageDialog(this, "Update Done");
			 update.setEnabled(false);
			 delete.setEnabled(false);
			 useridText.setEnabled(true);
			 useridText.setText("");
			 nameText.setText("");
			 phoneno2Text.setText("");
			 addressText.setText("");
		 }
         catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Update Fail");
		}		
    }
	public void driverDelete()
	 {
		 String newId=useridText.getText();
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
			
			update.setEnabled(false);
			delete.setEnabled(false);
			useridText.setEnabled(true);
			useridText.setText("");
			nameText.setText("");
			phoneno2Text.setText("");
			addressText.setText("");
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
	}
}
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class Signup extends JFrame implements ActionListener
{
	 JLabel userid,password,name,phoneno,address;
	 JTextField useridText,nameText,phonenoText,addressText,phoneno2Text,passwordField;
	 JButton generate,signup,back;
	 JPanel panel;
	
	 public  Signup()
	 {
		super(" Sign Up ");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		userid=new JLabel("User ID : ");
		userid.setBounds(100,50,100,50);
		panel.add(userid);
		
		useridText=new JTextField();
		useridText.setBounds(230,50,200,50);
		panel.add(useridText);
		
		password=new JLabel("Password :");
		password.setBounds(100,120,100,50);
		panel.add(password);
		
		passwordField=new JTextField();
		passwordField.setBounds(230,120,200,50);
		passwordField.setEnabled(false);
		panel.add(passwordField);
		
		name=new JLabel("Your Name : ");
		name.setBounds(100,180,100,50);
		panel.add(name);
		
		nameText=new JTextField();
		nameText.setBounds(230,180,200,50);
		panel.add(nameText);
		
		phonenoText=new JTextField("+880");
		phonenoText.setBounds(230,240,50,50);
		phonenoText.setEnabled(false);
		panel.add(phonenoText);
		
		phoneno=new JLabel("Phone Number :");
		phoneno.setBounds(100,240,100,50);
		panel.add(phoneno);
		
		phoneno2Text=new JTextField();
		phoneno2Text.setBounds(280,240,150,50);
		panel.add(phoneno2Text);
		
		signup=new JButton("Sign up");
		signup.setBounds(350,380,250,50);
		signup.setBackground(Color.green);
		signup.addActionListener(this);
		panel.add(signup);
		
		back=new JButton("Back");
		back.setBounds(50,380,250,50);
		back.setBackground(Color.blue);
		back.addActionListener(this);
		panel.add(back);
		
		generate=new JButton("Generate");
		generate.setBounds(450,120,250,50);
		generate.setBackground(Color.yellow);
		generate.addActionListener(this);
		panel.add(generate);
		
		address=new JLabel("Address :");
		address.setBounds(100,300,100,50);
		panel.add(address);
		
		addressText= new JTextField();
		addressText.setBounds(230,300,200,50);
		panel.add(addressText);
		
		this.add(panel);
	 }
	 public void actionPerformed(ActionEvent ae)
	 {
		 String ac=ae.getActionCommand();
		 if(ac.equals(back.getText()))
		 {
			 LogIn ln=new LogIn();
			 ln.setVisible(true);
			 this.setVisible(false);
		 }
		 else if(ac.equals(signup.getText()))
		 {
			 signup();
		 }
		 else if(ac.equals(generate.getText()))
		 {
			 Random r = new Random();
			 passwordField.setText(r.nextInt(9999)+"");
			 generate.setEnabled(false);
		 }
	 }
	  public void signup()
	 {
		 String newuserId= useridText.getText();
		 String newname= nameText.getText();
		 String newphoneno =phoneno2Text.getText();
		 String newpass=passwordField.getText();
		 String newaddress=addressText.getText();
		 int status=2;
		 
		String query1 = "INSERT INTO driver VALUES ('"+newuserId+"','"+newname+"','"+newphoneno+"','"+newaddress+"');";
		String query2 = "INSERT INTO login VALUES ('"+newuserId+"','"+newpass+"','"+status+"');";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement st= con.createStatement();
			st.execute(query1);
			st.execute(query2);
			st.close();
			con.close();
			JOptionPane.showMessageDialog(this, "Sign Up Done");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Error!!");
		}
	 }
}
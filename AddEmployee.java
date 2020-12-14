import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
public class AddEmployee extends JFrame implements ActionListener
{
	 JLabel userid,password,name,phoneno,role,salary;
	 JTextField useridText,nameText,phonenoText,phoneno2Text,salaryText,passwordField,roleText;
	 JButton logout,generate,add,back;
	 JPanel panel;
	 String UserId;
	
	 public  AddEmployee(String UserId)
	 {
		super(" Add Employee ");
		this.UserId=UserId;
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
		
		name=new JLabel("Employee Name : ");
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
		
		role=new JLabel("Role :");
		role.setBounds(100,300,100,50);
		panel.add(role);
		
		roleText = new JTextField("Employee");
		roleText.setBounds(230,300,200,50);
		roleText.setEnabled(false);
		panel.add(roleText);
		
		salary=new JLabel("Salary :");
		salary.setBounds(100,360,100,50);
		panel.add(salary);
		
		salaryText=new JTextField();
		salaryText.setBounds(230,360,200,50);
		panel.add(salaryText);
		
		logout=new JButton("Log Out");
		logout.setBounds(500,10,250,50);
		logout.setBackground(Color.red);
		logout.addActionListener(this);
		panel.add(logout);
		
		add=new JButton("ADD");
		add.setBounds(500,300,250,50);
		add.setBackground(Color.green);
		add.addActionListener(this);
		panel.add(add);
		
		back=new JButton("Back");
		back.setBounds(500,380,250,50);
		back.setBackground(Color.blue);
		back.addActionListener(this);
		panel.add(back);
		
		generate=new JButton("Generate");
		generate.setBounds(450,120,250,50);
		generate.setBackground(Color.yellow);
		generate.addActionListener(this);
		panel.add(generate);
		
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
			 ManageEmployee em=new ManageEmployee(UserId);
			 em.setVisible(true);
			 this.setVisible(false);
		 }
		 else if(ac.equals(add.getText()))
		 {
			 employeeInsert();
		 }
		 else if(ac.equals(generate.getText()))
		 {
			 Random r = new Random();
			 passwordField.setText(r.nextInt(9999)+"");
			 generate.setEnabled(false);
		 }
	 }
	 public void employeeInsert()
	 {
		 String newuserId= useridText.getText();
		 String newname= nameText.getText();
		 String newphoneno =phoneno2Text.getText();
		 double newsalary= Double.parseDouble(salaryText.getText());
		 String newpass=passwordField.getText();
		 String newroleText=roleText.getText();
		 int status=0;
		 
		String query1 = "INSERT INTO Employee VALUES ('"+newuserId+"','"+newname+"','"+newphoneno+"','"+newroleText+"',"+newsalary+");";
		String query2 = "INSERT INTO Login VALUES ('"+newuserId+"','"+newpass+"',"+status+");";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement st= con.createStatement();
			st.execute(query1);
			st.execute(query2);
			st.close();
			con.close();
			JOptionPane.showMessageDialog(this, "New Employee Added");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Not Inserted!!");
		}
	 }
}
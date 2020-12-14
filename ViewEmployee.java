import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class ViewEmployee extends JFrame implements ActionListener
{
	 JLabel userid,name,phoneno,role,salary;
	 JTextField useridText,nameText,phonenoText,phoneno2Text,salaryText,roleText;
	 JButton logout,load,update,delete,back,refresh;
	 JPanel panel;
	 String UserId;
	
	 public  ViewEmployee(String UserId)
	 {
		super(" View Employee ");
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
		
		
		name=new JLabel("Employee Name : ");
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
		
		role=new JLabel("Role :");
		role.setBounds(100,310,100,50);
		panel.add(role);
		
		roleText = new JTextField();
		roleText.setBounds(230,310,200,50);
		panel.add(roleText);
		
		salary=new JLabel("Salary :");
		salary.setBounds(100,370,100,50);
		panel.add(salary);
		
		salaryText=new JTextField();
		salaryText.setBounds(230,370,200,50);
		panel.add(salaryText);
		
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
			 ManageEmployee me= new ManageEmployee(UserId);
			 me.setVisible(true);
			 this.setVisible(false);
		 }
		 if(ac.equals(refresh.getText()))
		{
			update.setEnabled(false);
			delete.setEnabled(false);
			salaryText.setEnabled(true);
			useridText.setEnabled(true);
			useridText.setText("");
			nameText.setText("");
			phoneno2Text.setText("");
			roleText.setText("");
			salaryText.setText("");
		}
		 else if(ac.equals(update.getText()))
		 {
			 employeeUpdate();
		 }
		  else if(ac.equals(delete.getText()))
		 {
			 employeeDelete();
		 }
		  else if(ac.equals(load.getText()))
		 {
			 employeeLoad();
			 salarylock();
		 }
	 }
	 public void employeeLoad()
	 {
		 String load = useridText.getText();
		 String query = "SELECT `Employeename`, `Phonenumber`, `Role`, `Salary` FROM `employee` WHERE `UserId`='"+load+"';";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			
			boolean flag=false;
			while(rs.next())
			{
				nameText.setText(rs.getString("Employeename"));
				phonenoText.setText("+880");
				phoneno2Text.setText(rs.getString("phonenumber"));
				roleText.setText(rs.getString("Role"));
				salaryText.setText(rs.getString("Salary"));
				flag=true;
				useridText.setEnabled(false);
				update.setEnabled(true);
				delete.setEnabled(true);
			}
			if(!flag)
			{
				nameText.setText("");
				phoneno2Text.setText("");
				roleText.setText("");
				salaryText.setText("");
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
		finally
		{

		}
		
	 }
	 public void salarylock()
	 {
		 String load = useridText.getText();
		 String query ="SELECT `Status`FROM `login` WHERE userId='"+load+"'";
		 
		 try
		 {
			 //salary=Double.parseDouble(salaryText.getText());
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			 Statement st=con.createStatement();
			 st.executeQuery(query);
			 ResultSet rs=st.executeQuery(query);
			 while(rs.next())
			 {
				 int Status=rs.getInt("Status");
				 
				 if(Status==1)
				 {
					 salaryText.setEnabled(false);
				 }
				 else{
					 salaryText.setEnabled(true);
				 }
			 }
	     }
		 catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Update Fail");
		}
	 }
	 
	 public void employeeUpdate()
	 {
		 salaryText.setEnabled(true);
		 String newId = useridText.getText();
		 String newName= nameText.getText();
		 String newphone=phoneno2Text.getText();
		 String newrole=roleText.getText();
		 String salary=salaryText.getText();
		 
		 String query = "UPDATE employee SET Employeename='"+newName+"',Phonenumber = '"+newphone+"',Role = '"+newrole+"',Salary = "+salary+" WHERE userId='"+newId+"'";
		 try
		 {
			 //salary=Double.parseDouble(salaryText.getText());
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
			 roleText.setText("");
			 salaryText.setText("");
		 }
         catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Update Fail");
		}		 
	 }
	 public void employeeDelete()
	 {
		 String newId=useridText.getText();
		 String query = "DELETE from employee WHERE userId='"+newId+"';";
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
			roleText.setText("");
			salaryText.setText("");
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
	}
}
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BorrowE extends JFrame implements ActionListener
{
	 JLabel borrowid,busid,userid,borrowdate,returndate;
	 JTextField borrowidText,busidText,useridText,borrowdateText,returndateText;
	 JButton logout,back,refresh,search;
	 JPanel panel;
	 String UserId;
	
	 public BorrowE(String UserId)
	 {
		super(" Borrow Information ");
		this.UserId=UserId;
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		borrowid=new JLabel(" Borrow ID : ");
		borrowid.setBounds(100,190,100,50);
		panel.add(borrowid);
		
		borrowidText=new JTextField();
		borrowidText.setBounds(230,190,200,50);
		panel.add(borrowidText);
		
		
		busid=new JLabel(" Bus ID : ");
		busid.setBounds(100,130,100,50);
		panel.add(busid);
		
		busidText=new JTextField();
		busidText.setBounds(230,130,200,50);
		panel.add(busidText);
		
		search=new JButton("Search");
		search.setBounds(450,130,100,50);
		search.setBackground(Color.green);
		search.addActionListener(this);
		panel.add(search);

		userid=new JLabel("User ID :");
		userid.setBounds(100,250,100,50);
		panel.add(userid);
		
		useridText=new JTextField();
		useridText.setBounds(280,250,150,50);
		panel.add(useridText);
		
		borrowdate=new JLabel("Borrow Date :");
		borrowdate.setBounds(100,310,100,50);
		panel.add(borrowdate);
		
		borrowdateText = new JTextField();
		borrowdateText.setBounds(230,310,200,50);
		panel.add(borrowdateText);
		
		returndate=new JLabel("Return Date :");
		returndate.setBounds(100,370,100,50);
		panel.add(returndate);
		
		returndateText = new JTextField();
	    returndateText.setBounds(230,370,200,50);
		panel.add(returndateText);
		
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
		
		refresh = new JButton("Change Password");
        refresh.setBounds(120,10,250,50);
        refresh.setBackground(Color.orange);	
        refresh.addActionListener(this);		
		panel.add(refresh);
		
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
			EmployeeHome ed=new EmployeeHome(UserId);
			ed.setVisible(true);
			this.setVisible(false);
		}
		if(ac.equals(refresh.getText()))
		{
			busidText.setEnabled(true);
			useridText.setText("");
			borrowidText.setText("");
			busidText.setText("");
			borrowdateText.setText("");
			returndateText.setText("");
		}
		else if(ac.equals(search.getText()))
		 {
			 employeeSearch();
		 }
	 }
	 public void employeeSearch()
	 {
		 String load = busidText.getText();
		 String query = "SELECT `BorrowId`,`UserId`,`BorrowDate`,`ReturnDate` FROM `borrowinfo` WHERE `BusId`='"+load+"';";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			
			boolean flag=false;
			while(rs.next())
			{
				borrowidText.setText(rs.getString("BorrowId"));
				useridText.setText(rs.getString("UserId"));
				borrowdateText.setText(rs.getString("BorrowDate"));
				returndateText.setText(rs.getString("ReturnDate"));
				flag=true;
				busidText.setEnabled(false);
			}
			if(!flag)
			{
				borrowidText.setText("");
				useridText.setText("");
				borrowdateText.setText("");
				returndateText.setText("");
				JOptionPane.showMessageDialog(this,"Invalid BUS ID");
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
}
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class EmployeeHome extends JFrame implements ActionListener
{
	 JLabel title;
	 JButton changepass,managedriver,myinfo,logout,businfo,back,borrowinfo;
	 JLabel imgLabel;
	 JPanel panel;
	 ImageIcon img;
	 String UserId;
	 public  EmployeeHome(String UserId)
	 {
		super("Employee Home ");
		this.UserId=UserId;
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color myColor3=new Color(50,210,210);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		img = new ImageIcon("emp.png");
		imgLabel = new JLabel(img);
		imgLabel.setBounds(50,20,300,300);
		panel.add(imgLabel);
		
		logout=new JButton(" Log out ");
		logout.setBounds(50,380,280,50);
		logout.setBackground(Color.red);
		logout.addActionListener(this);
		panel.add(logout);
		
	    title= new JLabel("Welcome, "+ UserId);
		title.setBounds(400,20,200,50);
		panel.add(title);
		
		myinfo = new JButton("My Information");
		myinfo.setBounds(450,100,280,50);
		myinfo.setBackground(myColor3);
		myinfo.addActionListener(this);
		panel.add(myinfo);
		
		managedriver=new JButton("Manage Driver");
		managedriver.setBounds(450,200,280,50);
		managedriver.setBackground(Color.green);
		managedriver.addActionListener(this);
		panel.add(managedriver);
		
		businfo=new JButton("Bus Information");
		businfo.setBounds(450,300,280,50);
		businfo.setBackground(Color.yellow);
		businfo.addActionListener(this);
		panel.add(businfo);
		
		borrowinfo=new JButton("Borrow Information");
		borrowinfo.setBounds(450,400,280,50);
	    borrowinfo.setBackground(Color.orange);
		borrowinfo.addActionListener(this);
		panel.add(borrowinfo);
		
		back=new JButton("Back");
		back.setBounds(650,10,80,40);
		back.setBackground(Color.blue);
		back.setForeground(Color.white);
		back.addActionListener(this);
		panel.add(back);
		
		this.add(panel);
	 }
	 public void actionPerformed(ActionEvent ae)
	 {
		 String text=ae.getActionCommand();
		 if(text.equals(logout.getText()))
		 {
			 LogIn ln=new LogIn();
			 ln.setVisible(true);
			 this.setVisible(false);
		 }
		 else if(text.equals(back.getText()))
		 {
			 LogIn ln=new LogIn();
			 ln.setVisible(true);
			 this.setVisible(false);
		 }
		 else if(text.equals(myinfo.getText()))
		 {
			 EmployeeInfo ei=new EmployeeInfo(UserId);
			 ei.setVisible(true);
			 this.setVisible(false);
			 
			 ei.autoInfoE(UserId);
		 }
		 else if(text.equals(managedriver.getText()))
		 {
			 ManageDriver md=new ManageDriver(UserId);
			 md.setVisible(true);
			 this.setVisible(false);
		 }
		 else if(text.equals(businfo.getText()))
		 {
			 BusInfo bi=new BusInfo(UserId);
			 bi.setVisible(true);
			 this.setVisible(false);
		 }
		 else if(text.equals(borrowinfo.getText()))
		 {
			 BorrowE be=new BorrowE(UserId);
			 be.setVisible(true);
			 this.setVisible(false);
		 }
		 
		 
	 }
}
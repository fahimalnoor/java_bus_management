import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class DriverHome extends JFrame implements ActionListener
{
	 JLabel title;
	 JButton changepass,borrowinfo,myinfo,logout,businfo,back;
	 JLabel imgLabel;
	 JPanel panel;
	 ImageIcon img;
	 String UserId;
	 public  DriverHome(String UserId)
	 {
		super("Driver Home ");
		this.UserId=UserId;
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color myColor3=new Color(50,210,210);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		img = new ImageIcon("driver.png");
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
		
		businfo=new JButton("Bus Information");
		businfo.setBounds(450,200,280,50);
		businfo.setBackground(Color.green);
		businfo.addActionListener(this);
		panel.add(businfo);
		
		borrowinfo=new JButton("Borrow Information");
		borrowinfo.setBounds(450,300,280,50);
		borrowinfo.setBackground(Color.yellow);
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
			 DriverInfo ei=new DriverInfo(UserId);
			 ei.setVisible(true);
			 this.setVisible(false);
			 
			 ei.autoInfoD(UserId);
		 }
		 else if(text.equals(businfo.getText()))
		 {
			 BusInfoD md=new BusInfoD(UserId);
			 md.setVisible(true);
			 this.setVisible(false);
		 }
		 
		 else if(text.equals(borrowinfo.getText()))
		 {
			 BorrowInfo md=new BorrowInfo(UserId);
			 md.setVisible(true);
			 this.setVisible(false);
		 }
		  
	 }
}
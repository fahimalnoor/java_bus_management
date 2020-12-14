import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class ManagerHome extends JFrame implements ActionListener
{
	 JLabel title;
	 String UserId;
	 JButton myinfo,logout,manageemployee;
	 JLabel imgLabel;
	 JPanel panel;
	 ImageIcon img;
	 public  ManagerHome(String UserId)
	 {
		super("Manager Home ");
		this.UserId=UserId;
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color myColor3=new Color(50,210,210);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		img = new ImageIcon("manager.png");
		imgLabel = new JLabel(img);
		imgLabel.setBounds(50,20,300,300);
		panel.add(imgLabel);
		
		logout=new JButton(" Log out ");
		logout.setBounds(50,380,280,50);
		logout.setBackground(Color.red);
		logout.addActionListener(this);
		panel.add(logout);
		
	    title= new JLabel("Welcome,"+UserId);
		title.setBounds(500,50,200,50);
		title.setForeground(Color.green);
		panel.add(title);
		
		myinfo = new JButton("My Information");
		myinfo.setBounds(450,100,280,50);
		myinfo.setBackground(myColor3);
		myinfo.addActionListener(this);
		panel.add(myinfo);
		
		manageemployee=new JButton("Manage Employee");
        manageemployee.setBounds(450,200,280,50);
		manageemployee.setBackground(Color.blue);
		manageemployee.addActionListener(this);
		panel.add(manageemployee);
		
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
		 else if(text.equals(myinfo.getText()))
		 {
			 ManagerInfo mi=new ManagerInfo(UserId);
			 mi.setVisible(true);
			 this.setVisible(false);
			 
			 mi.autoInfoM(UserId);
		 }
		 else if(text.equals(manageemployee.getText()))
		 {
			 ManageEmployee me=new ManageEmployee(UserId);
			 me.setVisible(true);
			 this.setVisible(false);
		 }
	 }
}
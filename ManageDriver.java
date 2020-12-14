import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ManageDriver extends JFrame implements ActionListener
{
	 String UserId; 
	 JButton back,logout,add,view;
	 JLabel imgLabel;
	 JPanel panel;
	 ImageIcon img;
	 public  ManageDriver(String UserId)
	 {
		super(" Manage Driver ");
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
		
		
		add = new JButton("Add Driver");
		add.setBounds(450,100,280,50);
		add.setBackground(myColor3);
		add.addActionListener(this);
		panel.add(add);
		
		
		view=new JButton("View Driver");
        view.setBounds(450,200,280,50);
		view.setBackground(Color.blue);
		view.addActionListener(this);
		panel.add(view);
		
		back=new JButton("Back");
		back.setBounds(400,380,280,50);
		back.setBackground(Color.green);
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
			 EmployeeHome em=new EmployeeHome(UserId);
			 em.setVisible(true);
			this.setVisible(false);
		 }
		 else if(text.equals(view.getText()))
		 {
			 ViewDriver vd=new ViewDriver(UserId);
			 vd.setVisible(true);
			this.setVisible(false);
		 }
		 else if(text.equals(add.getText()))
		 {
			 AddDriver ad=new AddDriver(UserId);
			 ad.setVisible(true);
			this.setVisible(false);
		 }
		 
	 }
}
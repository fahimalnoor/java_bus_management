import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class BusInfoD extends JFrame implements ActionListener
{
	 JLabel busid,model,price,quentity,route;
	 JTextField busidText,modelText,priceText,routeText,quentityText;
	 JButton logout,back,refresh,load,borrow;
	 JPanel panel;
	 String UserId;
	
	 public  BusInfoD(String UserId)
	 {
		super(" Bus Information ");
		this.UserId=UserId;
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		busid=new JLabel(" ID : ");
		busid.setBounds(100,130,100,50);
		panel.add(busid);
		
		busidText=new JTextField();
		busidText.setBounds(230,130,200,50);
		panel.add(busidText);
		
		load=new JButton("Search");
		load.setBounds(450,130,200,50);
		load.setBackground(Color.yellow);
		load.addActionListener(this);
		panel.add(load);
		
		borrow=new JButton("Borrow");
		borrow.setBounds(500,220,200,50);
		borrow.setBackground(Color.green);
		borrow.addActionListener(this);
		borrow.setEnabled(false);
		panel.add(borrow);
		
		model=new JLabel(" MODEL : ");
		model.setBounds(100,190,100,50);
		panel.add(model);
		
		modelText=new JTextField();
		modelText.setBounds(230,190,200,50);
		panel.add(modelText);
	
		price=new JLabel("Price :");
		price.setBounds(100,250,100,50);
		panel.add(price);
		
		priceText=new JTextField();
		priceText.setBounds(280,250,150,50);
		panel.add(priceText);
		
		quentity=new JLabel("QUANTITY :");
		quentity.setBounds(100,310,100,50);
		panel.add(quentity);
		
		quentityText = new JTextField();
		quentityText.setBounds(230,310,200,50);
		panel.add(quentityText);
		
		route=new JLabel("ROUTE :");
		route.setBounds(100,370,100,50);
		panel.add(route);
		
		routeText=new JTextField();
		routeText.setBounds(230,370,200,50);
		panel.add(routeText);
		
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
		
		refresh = new JButton("Refresh");
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
		if(ac.equals(refresh.getText()))
		{
			busidText.setEnabled(true);
			busidText.setText("");
		    modelText.setText("");
			priceText.setText("");
			quentityText.setText("");
			routeText.setText("");
		}
         else if(ac.equals(back.getText()))
		{
			DriverHome ed=new DriverHome(UserId);
			ed.setVisible(true);
			this.setVisible(false);
		}
		else if(ac.equals(load.getText()))
		 {
			 busSearch();
		 }
		 else if(ac.equals(borrow.getText()))
		 {
			 borrowBus();
		 }
	 }
 public void busSearch()
	 {
		 String search = busidText.getText();
		 String query = "SELECT `Model`,`Price`,`Quantity`,`Route` FROM `bus` WHERE `BusId`='"+search+"';";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			
			boolean flag=false;
			while(rs.next())
			{
				modelText.setText(rs.getString("Model"));
				priceText.setText(rs.getString("Price"));
				quentityText.setText(rs.getString("Quantity"));
				routeText.setText(rs.getString("Route"));
				flag=true;
			}
			if(Integer.parseInt(quentityText.getText())>0)
			{
				borrow.setEnabled(true);
			}
			if(!flag)
			{
				modelText.setText("");
				priceText.setText("");
				routeText.setText("");
				quentityText.setText("");
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
	 public void borrowBus()
	 {
		 String newId= busidText.getText();
		 int newquentity=Integer.parseInt(quentityText.getText());
		 System.out.println(newquentity);
		 newquentity=newquentity-1;
		 String query = "UPDATE bus SET Quantity = '"+newquentity+"'WHERE BusId='"+newId+"'";
		 System.out.println(newquentity);
		 try
		{
			Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			 Statement st=con.createStatement();
			 st.executeUpdate(query);
			 st.close();
			 con.close();
			 JOptionPane.showMessageDialog(this, "Borrow Done");
			 borrow.setEnabled(false);
			 quentityText.setText(""+newquentity);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Update Fail");
		}
	 }
}
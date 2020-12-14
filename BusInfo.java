import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class BusInfo extends JFrame implements ActionListener
{
	 JLabel busid,model,price,quentity,route,borrowdate,returndate;
	 JTextField busidText,modelText,priceText,routeText,quentityText,returndateText,borrowdateText;
	 JButton logout,back,update,delete,refresh,search,borrowed,returned;
	 JPanel panel;
	 String UserId;
	
	 public  BusInfo(String UserId)
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
		
		borrowed = new JButton("Borrowed");
		borrowed.setBounds(500,75,100,50);
		borrowed.setBackground(Color.green);
		borrowed.addActionListener(this);
		borrowed.setEnabled(false);
		panel.add(borrowed);
		
		borrowdateText = new JTextField();
		borrowdateText.setBounds(600,95,130,30);
		borrowdateText.setEnabled(false);
		panel.add(borrowdateText);
		
		borrowdate=new JLabel("Enter Borrow Date");
		borrowdate.setBounds(605,70,130,30);
		panel.add(borrowdate);
		
		returned = new JButton("Returned");
		returned.setBounds(500,200,100,50);
		returned.setBackground(Color.red);
		returned.addActionListener(this);
		returned.setEnabled(false);
		panel.add(returned);
		
		returndateText = new JTextField();
		returndateText.setBounds(600,220,130,30);
		returndateText.setEnabled(false);
		panel.add(returndateText);
		
		returndate=new JLabel("Enter Return Date");
		returndate.setBounds(605,195,130,30);
		panel.add(returndate);
		
		search = new JButton("Search");
		search.setBounds(450,140,100,50);
		search.setBackground(Color.yellow);
		search.addActionListener(this);
		panel.add(search);
		
		delete=new JButton("DELETE");
		delete.setBounds(500,340,250,50);
		delete.setBackground(Color.red);
		delete.addActionListener(this);
		panel.add(delete);
		
		update=new JButton("update");
		update.setBounds(500,270,250,50);
		update.setBackground(Color.green);
		update.addActionListener(this);
		panel.add(update);
		
		back=new JButton("Back");
		back.setBounds(500,400,250,50);
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
         else if(ac.equals(back.getText()))
		{
			EmployeeHome em=new EmployeeHome(UserId);
			em.setVisible(true);
			this.setVisible(false);
		}
		 if(ac.equals(refresh.getText()))
		{
			update.setEnabled(false);
			delete.setEnabled(false);
			busidText.setEnabled(true);
			busidText.setText("");
		    modelText.setText("");
			priceText.setText("");
			quentityText.setText("");
			routeText.setText("");
		}
		 else if(ac.equals(update.getText()))
		 {
			 busUpdate();
		 }
		  else if(ac.equals(delete.getText()))
		 {
			 busDelete();
		 }
		  else if(ac.equals(search.getText()))
		 {
			 busSearch();
		 }
		 else if(ac.equals(borrowed.getText()))
		 {
			 borrowedBus();
		 }
		 else if(ac.equals(returned.getText()))
		 {
			 returnedBus();
		 }
	 }
	 
	 public void busSearch()
	 {
		 String search = busidText.getText();
		 String query = "SELECT `Model`, `Price`, `Quantity`, `Route` FROM `bus` WHERE `BusId`='"+search+"';";
		
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
				busidText.setEnabled(false);
				update.setEnabled(true);
				delete.setEnabled(true);
				returned.setEnabled(true);
				returndateText.setEnabled(true);
			}
			if(Integer.parseInt(quentityText.getText())>0)
			{
				borrowed.setEnabled(true);
				borrowdateText.setEnabled(true);
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
	  public void busUpdate()
	 {
		 priceText.setEnabled(true);
		 String newId = busidText.getText();
		 String newmodel= modelText.getText();
		 String newprice=priceText.getText();
		 String newquentity=quentityText.getText();
		 String newroute=routeText.getText();
		 
		 String query = "UPDATE bus SET Model='"+newmodel+"',Price = '"+newprice+"',Quantity = '"+newquentity+"',Route = '"+newroute+"' WHERE BusId='"+newId+"'";
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
			 busidText.setEnabled(true);
			 busidText.setText("");
			 modelText.setText("");
			 priceText.setText("");
			 quentityText.setText("");
			 routeText.setText("");
		 }
         catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Update Fail");
		}		 
	 }
	 public void busDelete()
	 {
		 String newId=busidText.getText();
		 String query = "DELETE from bus WHERE userId='"+newId+"';";
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
			busidText.setEnabled(true);
			busidText.setText("");
			modelText.setText("");
			priceText.setText("");
			quentityText.setText("");
			routeText.setText("");
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
	}
	public void borrowedBus()
	 {
		 String newId= busidText.getText();
		 int newquentity=Integer.parseInt(quentityText.getText());
		 System.out.println(newquentity);
		 newquentity=newquentity-1;
		 String newborrowdate=borrowdateText.getText();
		 String query = "UPDATE bus SET Quantity = '"+newquentity+"'WHERE BusId='"+newId+"'";
		 String query2 = "UPDATE borrowinfo SET BorrowDate = '"+newborrowdate+"'WHERE BusId='"+newId+"'";
		 System.out.println(newquentity);
		 try
		{
			Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			 Statement st=con.createStatement();
			 st.executeUpdate(query);
			 st.executeUpdate(query2);
			 st.close();
			 con.close();
			 JOptionPane.showMessageDialog(this, "Borrow Done");
			 quentityText.setText(""+newquentity);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Update Fail");
		}
	 }
	 public void returnedBus()
	 {
		 String newId= busidText.getText();
		 int newquentity=Integer.parseInt(quentityText.getText());
		 System.out.println(newquentity);
		 newquentity=newquentity+1;
		 String newreturndate=returndateText.getText();
		 String query = "UPDATE bus SET Quantity = '"+newquentity+"'WHERE BusId='"+newId+"'";
		 String query2 = "UPDATE borrowinfo SET ReturnDate = '"+newreturndate+"'WHERE BusId='"+newId+"'";
		 System.out.println(newquentity);
		 try
		{
			Class.forName("com.mysql.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			 Statement st=con.createStatement();
			 st.executeUpdate(query);
			 st.executeUpdate(query2);
			 st.close();
			 con.close();
			 JOptionPane.showMessageDialog(this, "Bus Returned");
			 quentityText.setText(""+newquentity);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this, "Update Fail");
		}
	 }
}
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class LogIn extends JFrame implements ActionListener
{
	 JLabel titleLabel, userLabel, passLabel , signinLabel, massLabel,imgLabel;
	 JTextField user;
	 JPasswordField pass;
	 JButton login,signup;
	 JPanel panel;
	 ImageIcon img;
	 public  LogIn()
	 {
		super("Log In ");
		this.setSize(400, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel =new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		Color myColor= new Color(54,105,143);
		Color myColor2= new Color(165,175,183);
		
		titleLabel = new JLabel("BUS.COM");
		titleLabel.setBounds(155, 170, 150, 50);
		panel.add(titleLabel);
		
		
		signinLabel = new JLabel("Sign in with your organizational User ID.");
		signinLabel.setBounds(60,240,360,15);
		panel.add(signinLabel);
		
		massLabel=new JLabel("-where You can find your bus.");
		massLabel.setBounds(100,220,200,10);
		massLabel.setForeground(myColor2);
		panel.add(massLabel);
		
		user=new JTextField();
		user.setBounds(20,280,340,40);
		user.addActionListener(this);
		panel.add(user);
		
		pass=new JPasswordField();
		pass.setBounds(20,340,340,40);
		pass.addActionListener(this);
	    panel.add(pass);
		
		login=new JButton("Sign in");
		login.setBounds(20,400,350,40);
		login.setBackground(myColor);
		login.setForeground(Color.white);
		login.addActionListener(this);
		panel.add(login);
		
		signup=new JButton("Sign up");
		signup.setBounds(20,20,350,40);
		signup.setBackground(Color.green);
		signup.addActionListener(this);
		panel.add(signup);
		
		img = new ImageIcon("tenor.png");
		imgLabel = new JLabel(img);
		imgLabel.setBounds(50,0,250,250);
		panel.add(imgLabel);
		
		
    	this.add(panel);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String ac=ae.getActionCommand();
	    if(ac.equals(login.getText()))
		{
			check();
		}
		else if(ac.equals(signup.getText()))
		{
			Signup sg=new Signup();
			sg.setVisible(true);
		    this.setVisible(false);
		}
	}
	public void check()
	{
		String query = "SELECT `UserId`,`Password`,`Status`FROM `login`;";
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			st = con.createStatement();
			rs = st.executeQuery(query);
			boolean flag=false;
			while(rs.next())
			{
				String UserId=rs.getString("UserId");
				String Password=rs.getString("Password");
				int Status=rs.getInt("Status");
				
				if(UserId.equals(user.getText()) && Password.equals(pass.getText()))
				{
					flag=true;
					if(Status==0)
					{
						EmployeeHome eh=new EmployeeHome(UserId);
						eh.setVisible(true);
						this.setVisible(false);
					}
					else if(Status==1)
					{
						ManagerHome mh=new ManagerHome(UserId);
						mh.setVisible(true);
						this.setVisible(false);
					}
					else if(Status==2)
					{
						DriverHome dh=new DriverHome(UserId);
						dh.setVisible(true);
						this.setVisible(false);
					}
				}
				
			}
			if(!flag)
			{
				JOptionPane.showMessageDialog(this,"Invalid ID or Password"); 
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
	}
}
package mysql;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JPasswordField;

public class Log {

	private JFrame frmLoginSystem;
	private JPasswordField pass;
    private WindowInfo window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log window = new Log();
					window.frmLoginSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Log() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginSystem = new JFrame();
		frmLoginSystem.setTitle("Login system");
		frmLoginSystem.getContentPane().setBackground(new Color(0, 191, 255));
		frmLoginSystem.setBounds(100, 100, 559, 660);
		frmLoginSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginSystem.getContentPane().setLayout(null);
		
		JTextPane user = new JTextPane();
		user.setForeground(new Color(0, 0, 255));
		user.setFont(new Font("Tahoma", Font.PLAIN, 18));
		user.setBounds(120, 266, 308, 54);
		frmLoginSystem.getContentPane().add(user);
		
		JLabel lblNewLabel = new JLabel("   Login Tab");
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 27));
		lblNewLabel.setBounds(188, 84, 182, 36);
		frmLoginSystem.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Stencil", Font.PLAIN, 33));
		lblNewLabel_1.setBounds(10, 217, 199, 43);
		frmLoginSystem.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Stencil", Font.PLAIN, 33));
		lblNewLabel_2.setBounds(10, 353, 193, 43);
		frmLoginSystem.getContentPane().add(lblNewLabel_2);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLUE);
	    btnLogin.setFont(new Font("Stencil", Font.PLAIN, 33));
		
	    btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			 
				try
				{
				   //Class.forName("com.mysql.jdbc.Driver");
				   Connection Mycon = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/buy_things","root","root");
					
				   String username = user.getText();
				   String password = pass.getText();
				   
				   Statement myStmt = Mycon.createStatement();
				   
				   String sql1="select ID from tabela_cu_utilizatori_si_parole where ID ='"+username+"' and pass ='"+password+"'";
				   
				   ResultSet rs = myStmt.executeQuery(sql1);
				   
				   if(rs.next())
				   {
					
					 
					 window = new WindowInfo();
					 
					 JOptionPane.showMessageDialog(frmLoginSystem,"Connecting successfully...");
					 
				
					 
					   
				   }else
				   {
					   JOptionPane.showMessageDialog(frmLoginSystem,"Username or password incorrect...");
						 
				   }
							
				}catch(Exception a)
				{
					a.printStackTrace();
				}
				
			}
	    	
	    	
	    	
	    	
	    });
	    
	  
	    btnLogin.setBounds(43, 514, 176, 54);
		frmLoginSystem.getContentPane().add(btnLogin);
		
		pass = new JPasswordField();
		pass.setBounds(120, 422, 308, 63);
		frmLoginSystem.getContentPane().add(pass);
	}
}

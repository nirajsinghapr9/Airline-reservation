package APP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Register extends JFrame  implements WindowListener {
	
	private JPanel contentPane;
	private JTextField Name;
	private JTextField Email;
	private JTextField Mobile;
	private JPasswordField password_field;
	private JPasswordField c_password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}

	
	  
	 
	public Register() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Airline Reservation System");
		addWindowListener(this); 
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 51, 51));
		contentPane.setBackground(new Color(0, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegisterationForm = new JLabel("Registration Form...");
		lblRegisterationForm.setBounds(10, 11, 220, 29);
		contentPane.add(lblRegisterationForm);
		lblRegisterationForm.setForeground(new Color(255, 255, 255));
		lblRegisterationForm.setFont(new Font("Sitka Display", Font.BOLD, 24));
		
		JLabel lblName = new JLabel("Name ");
		lblName.setBackground(new Color(255, 255, 255));
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setBounds(10, 65, 65, 14);
		contentPane.add(lblName);
		
		Name = new JTextField();
		Name.setBounds(166, 62, 162, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBackground(new Color(255, 255, 255));
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(10, 95, 103, 14);
		contentPane.add(lblEmail);
		
		Email = new JTextField();
		Email.setBounds(166, 92, 162, 20);
		contentPane.add(Email);
		Email.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setForeground(new Color(255, 255, 255));
		lblMobile.setBackground(new Color(255, 255, 255));
		lblMobile.setBounds(10, 125, 46, 14);
		contentPane.add(lblMobile);
		
		Mobile = new JTextField();
		Mobile.setBounds(166, 122, 162, 20);
		contentPane.add(Mobile);
		Mobile.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 155, 77, 14);
		contentPane.add(lblNewLabel);
		
		password_field = new JPasswordField();
		password_field.setBounds(166, 152, 162, 20);
		contentPane.add(password_field);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(new Color(255, 255, 255));
		lblConfirmPassword.setBounds(10, 185, 129, 20);
		contentPane.add(lblConfirmPassword);
		
		c_password = new JPasswordField();
		c_password.setBounds(166, 182, 162, 20);
		contentPane.add(c_password);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String password=new String(password_field.getText());
				String confirmpassword=new String(c_password.getText());
				
				if(Name.getText().isEmpty() && Email.getText().isEmpty() && 
						Mobile.getText().isEmpty() && password_field.getText().isEmpty()
						&& c_password.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Fill all required Fields");
				}
				try {
					Class.forName("org.h2.Driver");
					Connection connection=DriverManager.getConnection("jdbc:h2:~/test", "sa","");
					//JOptionPane.showMessageDialog(null,"successful");
					String query="insert into USER (UNAME,PASS, MOBILE, Email, C_PASS) Values(?, ?, ?, ?, ?)";
					PreparedStatement statement=connection.prepareStatement(query);
					statement.setString(1, Name.getText());
					statement.setString(4, Email.getText());
					statement.setString(3, Mobile.getText());
					statement.setString(2, password);
					statement.setString(5, confirmpassword);
					
					if(password.equals(confirmpassword))
					{
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null,"Account Created Succesfully, Use your Name As User name");
						
						  Home Ap=new Home(Name.getText()); Ap.setVisible(true);
						 dispose();
					
					
					
					}else {
						JOptionPane.showMessageDialog(null,"Password and Confirm Password must match");
					}
					connection.close();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null," not successful");
					e1.printStackTrace();
					
				}
				
			}
		});
		btnSubmit.setBounds(166, 220, 83, 23);
		contentPane.add(btnSubmit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Name.getText().isEmpty() && Email.getText().isEmpty() && 
						Mobile.getText().isEmpty() && password_field.getText().isEmpty()
						&& c_password.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Fill all required Fields");
				}
				Name.setText("");
				Email.setText("");
				Mobile.setText("");
				password_field.setText("");
				c_password.setText("");
			}
		});
		btnClear.setBounds(259, 220, 65, 23);
		contentPane.add(btnClear);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\13.jpg"));
		label.setBounds(0, 0, 444, 271);
		contentPane.add(label);
	}




	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}

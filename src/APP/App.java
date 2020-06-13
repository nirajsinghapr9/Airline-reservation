package APP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class App {

	private JFrame frmAirlineReservationSystem;
	public JTextField username_field;
	private JPasswordField password_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmAirlineReservationSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAirlineReservationSystem = new JFrame();
		frmAirlineReservationSystem.setResizable(false);
		frmAirlineReservationSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAirlineReservationSystem.setForeground(UIManager.getColor("Tree.selectionBorderColor"));
		frmAirlineReservationSystem.getContentPane().setForeground(UIManager.getColor("ToolBar.floatingBackground"));
		frmAirlineReservationSystem.setType(Type.UTILITY);
		frmAirlineReservationSystem.setTitle("Airline Reservation System");
		frmAirlineReservationSystem.getContentPane().setBackground(new Color(0, 51, 0));
		frmAirlineReservationSystem.setBounds(100, 100, 450, 300);
		frmAirlineReservationSystem.getContentPane().setLayout(null);
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(41, 69, 77, 14);
		lblUsername.setForeground(UIManager.getColor("ToolBar.background"));
		frmAirlineReservationSystem.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(UIManager.getColor("ToggleButton.highlight"));
		lblPassword.setBounds(41, 103, 77, 14);
		frmAirlineReservationSystem.getContentPane().add(lblPassword);
		
		username_field = new JTextField();
		username_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		username_field.setActionCommand("");
		username_field.setBounds(151, 66, 122, 20);
		frmAirlineReservationSystem.getContentPane().add(username_field);
		username_field.setColumns(10);
		
			
		
		password_field = new JPasswordField();
		password_field.setBounds(151, 100, 122, 20);
		frmAirlineReservationSystem.getContentPane().add(password_field);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(151, 143, 122, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			try {
				Class.forName("org.h2.Driver");
				Connection connection=DriverManager.getConnection("jdbc:h2:~/test", "sa","");
				//JOptionPane.showMessageDialog(null,"successful");
				String name=username_field.getText().toString();
				@SuppressWarnings("deprecation")
				String pass=password_field.getText().toString();
				
				
				String query="select * from USER where UNAME=? and PASS=?";
				PreparedStatement statement=connection.prepareStatement(query);
				statement.setString(1, name);
				statement.setString(2, pass);
				ResultSet set=statement.executeQuery();
				
				if(set.next())
				{
					//JOptionPane.showMessageDialog(null,"successful");
					
					Home home=new Home(username_field.getText().toString());
					home.setVisible(true);
					
					
					frmAirlineReservationSystem.dispose();

					  }else { JOptionPane.showMessageDialog(null,"login failed"); }
					 
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			}
		});
		frmAirlineReservationSystem.getContentPane().add(btnLogin);
		
		JButton btnNewButton = new JButton("New User");
		btnNewButton.setBounds(345, 19, 89, 23);
		frmAirlineReservationSystem.getContentPane().add(btnNewButton);
		
		
		JLabel lblNewLabel = new JLabel("Login Form...");
		lblNewLabel.setBounds(23, 11, 181, 29);
		frmAirlineReservationSystem.getContentPane().add(lblNewLabel);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 26));
		lblNewLabel.setForeground(UIManager.getColor("ToggleButton.highlight"));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\13.jpg"));
		label.setBounds(0, 0, 444, 271);
		frmAirlineReservationSystem.getContentPane().add(label);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register r=new Register();
				r.setVisible(true);
				frmAirlineReservationSystem.dispose();
				
			}
		});
		
	}
}

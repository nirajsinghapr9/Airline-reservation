package APP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cpassword extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cpassword frame = new Cpassword("User");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cpassword(String userName) {
		setTitle("Airline Reservation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Home h=new Home(userName);
				h.setVisible(true);	
				dispose();
						
			}
		});
		btnNewButton.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\Users-Exit-icon.png"));
		btnNewButton.setBounds(324, 11, 86, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(228, 61, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterOldPassword = new JLabel("Enter Old Password");
		lblEnterOldPassword.setForeground(new Color(255, 255, 255));
		lblEnterOldPassword.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblEnterOldPassword.setBounds(33, 104, 162, 14);
		contentPane.add(lblEnterOldPassword);		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		textField_1.setBounds(228, 100, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNewPassword.setForeground(new Color(255, 255, 255));
		lblNewPassword.setBounds(33, 145, 162, 14);
		contentPane.add(lblNewPassword);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(228, 141, 86, 20);
		contentPane.add(textField_2);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.h2.Driver");
					Connection connection=DriverManager.getConnection("jdbc:h2:~/test", "sa","");
					//JOptionPane.showMessageDialog(null,"successful");
					String name=textField.getText().toString();
					String pass=textField_1.getText().toString();			
					String query="select * from USER where UNAME=? and PASS=?";
					PreparedStatement statement=connection.prepareStatement(query);
					statement.setString(1, name);
					statement.setString(2, pass);
					ResultSet set=statement.executeQuery();
					
					if(set.next())
					{
						//JOptionPane.showMessageDialog(null,"successful");
						String query1="UPDATE USER SET (pass, c_pass)=(?, ?) where UNAME=?";
						PreparedStatement statement1=connection.prepareStatement(query1);
						statement1.setString(3, userName);						
						statement1.setString(1, textField_2.getText());
						statement1.setString(2, textField_2.getText());
						statement1.executeUpdate();
						JOptionPane.showMessageDialog(null,"Information updated");
						dispose();
						  }else { JOptionPane.showMessageDialog(null,"Invalid old password"); }
						 
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\Save.png"));
		btnSave.setBounds(228, 11, 86, 23);
		contentPane.add(btnSave);
		
		JLabel lblGetUserName = new JLabel("Click Here  To Get User Name");
		lblGetUserName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Class.forName("org.h2.Driver");
					Connection connection=DriverManager.getConnection("jdbc:h2:~/test", "sa","");
					//JOptionPane.showMessageDialog(null,"successful");
				String query="select uname from USER where uname=?";
				PreparedStatement statement=connection.prepareStatement(query);
				statement.setString(1, userName);
				ResultSet rs=statement.executeQuery();
				textField.setText(userName);
					
			}catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null," not successful");
				e1.printStackTrace();
				
			}
			}
		});
		lblGetUserName.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblGetUserName.setForeground(new Color(255, 255, 255));
		lblGetUserName.setBounds(33, 65, 176, 14);
		contentPane.add(lblGetUserName);
		
		JLabel label_1 = new JLabel("");
		label_1.setForeground(new Color(255, 255, 224));
		label_1.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\13.jpg"));
		label_1.setBounds(0, 0, 434, 261);
		contentPane.add(label_1);
	}
}

package APP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class Edit extends JFrame {

	private JPanel contentPane;
	private JTable table;
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
					Edit frame = new Edit("User");
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
	public Edit(String userName) {
		setResizable(false);
		setTitle("Airline Reservation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 70, 347, 79);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * int row=table.getSelectedRow();
				 * 
				 * @SuppressWarnings("unused") String
				 * table_click=(table.getModel().getValueAt(row, 0)).toString();
				 */
				
				try {
					Class.forName("org.h2.Driver");
					Connection connection=DriverManager.getConnection("jdbc:h2:~/test", "sa","");
					//JOptionPane.showMessageDialog(null,"successful");
					String query="select * from USER where uname=?";
					PreparedStatement statement=connection.prepareStatement(query);
					statement.setString(1, userName);
					ResultSet rs=statement.executeQuery();
					if(rs.next()) {
						String add1=rs.getString("Uname");
						textField.setText(add1);
						String add2=rs.getString("Email");
						textField_1.setText(add2);
						String add3=rs.getString("Mobile");
						textField_2.setText(add3);
					}
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User Name", "Email", "Mobile"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("org.h2.Driver");
					Connection connection=DriverManager.getConnection("jdbc:h2:~/test", "sa","");
					//JOptionPane.showMessageDialog(null,"successful");
				String query="select uname,Email,Mobile from USER where uname=?";
				PreparedStatement statement=connection.prepareStatement(query);
				statement.setString(1, userName);
				ResultSet rs=statement.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
					
			}catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(null," not successful");
				e1.printStackTrace();
				
			}
			}
		});
		btnNewButton.setBounds(47, 23, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("org.h2.Driver");
					Connection connection=DriverManager.getConnection("jdbc:h2:~/test", "sa","");
					
					String query="UPDATE USER SET (UNAME,Email, Mobile)=(?, ?, ?) WHERE Uname = ?";
					PreparedStatement statement=connection.prepareStatement(query);	
					statement.setString(4, userName);
					statement.setString(1, textField.getText());
					statement.setString(2, textField_1.getText());
					statement.setString(3, textField_2.getText());
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null,"Information updated");
					Home frame = new Home("User");
					
					frame.setVisible(true);
					
				}catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					}
			}
		});
		update.setBounds(152, 23, 89, 23);
		contentPane.add(update);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(47, 176, 77, 14);
		contentPane.add(lblUserName);
		
		textField = new JTextField();
		textField.setBounds(128, 173, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(47, 201, 46, 14);
		contentPane.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setBounds(128, 198, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setForeground(Color.WHITE);
		lblMobile.setBounds(47, 226, 46, 14);
		contentPane.add(lblMobile);
		
		textField_2 = new JTextField();
		textField_2.setBounds(128, 223, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Quit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home frame = new Home("User");				
				frame.setVisible(true);	
				dispose();
			}
		});
		btnNewButton_1.setBounds(259, 23, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\13.jpg"));
		lblNewLabel.setBounds(0, 0, 444, 271);
		contentPane.add(lblNewLabel);
	}
}

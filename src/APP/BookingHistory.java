package APP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookingHistory extends JFrame {

	private JPanel contentPane;
	private JTable B_History;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingHistory frame = new BookingHistory("User");
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
	public BookingHistory(String userName) {
		setTitle("Airline Reservation System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Home frame = new Home("User");				
				frame.setVisible(true);	
				dispose();
			}
		});
		btnQuit.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\Users-Exit-icon.png"));
		btnQuit.setBounds(439, 56, 89, 23);
		contentPane.add(btnQuit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 92, 504, 223);
		contentPane.add(scrollPane);
		
		B_History = new JTable();
		B_History.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"FLIGHT", "FROM", "TO", "TRAVEL DATE", "PASSENGER"
			}
		));
		B_History.getColumnModel().getColumn(3).setPreferredWidth(100);
		scrollPane.setViewportView(B_History);
		
		JLabel Uname = new JLabel("New label");
		Uname.setFont(new Font("Sitka Display", Font.BOLD | Font.ITALIC, 16));
		Uname.setForeground(Color.WHITE);
		Uname.setBounds(24, 22, 504, 59);
		contentPane.add(Uname);
		Uname.setText("<html>Hi"+ " " + userName +","+" " +"<ul><u>Below is your Booking History....</u></ul></html>");
		String query ="select FLIGHT, T_FROM,T_TO, T_DATE, NOOFPASS from I_BOOKED where uname=?";
		DisplayTable(query, userName);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\4.jpg"));
		label.setBounds(0, 0, 1401, 705);
		contentPane.add(label);
	}
	private void DisplayTable(String query, String userName) {
		try {
			Class.forName("org.h2.Driver");
			Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, userName);			
			ResultSet rs = pst.executeQuery();
			B_History.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}
	}
}

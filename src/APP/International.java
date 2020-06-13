package APP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ContainerAdapter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.FocusAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import net.proteanit.sql.DbUtils;
import javax.swing.ListSelectionModel;

import com.ibm.icu.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class International extends JFrame {

	

	private JPanel contentPane;
	private JTable t1;
	private static String startPoint;
	private static String endPoint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					International frame = new International("User");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param userName
	 * @wbp.parser.constructor
	 */

	public International(String userName) {
		setTitle("Airline Reservation System");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("India");
		comboBox.addItem("United States");
		comboBox.addItem("China");
		comboBox.addItem("United Arab Emirates");
		comboBox.addItem("United Kingdom");
		comboBox.addItem("France");
		comboBox.addItem("Singapore");
		comboBox.addItem("Thailand");
		comboBox.addItem("Malaysia");
		comboBox.addItem("Spain");
		comboBox.addItem("Canada");

		comboBox.addContainerListener(new ContainerAdapter() {
		});
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\Users-Exit-icon.png"));
		btnNewButton.setBounds(1144, 490, 103, 23);
		contentPane.add(btnNewButton);
		
		JLabel Uname = new JLabel("");
		Uname.setFont(new Font("Sitka Display", Font.BOLD, 13));
		Uname.setForeground(Color.WHITE);
		Uname.setBounds(1275, -2, 89, 20);
		contentPane.add(Uname);
		comboBox.setBounds(122, 59, 152, 20);
		contentPane.add(comboBox);
		Uname.setText("HI" +" " +userName);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("India");
		comboBox_1.addItem("United States");
		comboBox_1.addItem("China");
		comboBox_1.addItem("United Arab Emirates");
		comboBox_1.addItem("United Kingdom");
		comboBox_1.addItem("France");
		comboBox_1.addItem("Singapore");
		comboBox_1.addItem("Thailand");
		comboBox_1.addItem("Malaysia");
		comboBox_1.addItem("Spain");
		comboBox_1.addItem("Canada");

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBackground(Color.CYAN);
		dateChooser.getCalendarButton().setFont(new Font("Sitka Display", Font.PLAIN, 11));
		dateChooser.setSelectableDateRange(new Date(), null);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				International in = new International(userName, comboBox.getSelectedItem().toString(),
						comboBox_1.getSelectedItem().toString());
				in.setVisible(true);
				dispose();
			}
		});
		btnSearch.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\search.png"));
		btnSearch.setBounds(507, 58, 105, 23);
		contentPane.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(674, 23, 678, 254);
		contentPane.add(scrollPane);

		JComboBox<Integer> comboBox_2 = new JComboBox<Integer>();
		comboBox_2.setFont(new Font("Sitka Display", Font.PLAIN, 13));
		comboBox_2.addItem(1);
		comboBox_2.addItem(2);
		comboBox_2.addItem(3);
		comboBox_2.addItem(4);
		comboBox_2.addItem(5);
		comboBox_2.addItem(6);
		comboBox_2.addItem(7);
		comboBox_2.addItem(8);
		comboBox_2.addItem(9);
		comboBox_2.addItem(10);
		comboBox_2.setBounds(1144, 399, 50, 20);
		contentPane.add(comboBox_2);

		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Please Search & Select Flight");
			}
		});
		btnProceed.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\Save.png"));
		btnProceed.setBounds(1142, 452, 105, 23);
		contentPane.add(btnProceed);
		dateChooser.setBounds(1142, 346, 134, 20);
		contentPane.add(dateChooser);

		JLabel lblDepartureDate = new JLabel("Departure Date");
		lblDepartureDate.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblDepartureDate.setForeground(Color.WHITE);
		lblDepartureDate.setBounds(1034, 346, 98, 14);
		contentPane.add(lblDepartureDate);

		JLabel lblNoOgPassengers = new JLabel("No. of Passenger");
		lblNoOgPassengers.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNoOgPassengers.setForeground(Color.WHITE);
		lblNoOgPassengers.setBounds(1034, 402, 112, 14);
		contentPane.add(lblNoOgPassengers);

		t1 = new JTable();
		t1.setRowSelectionAllowed(false);
		t1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// t1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		t1.setFont(new Font("Sitka Display", Font.BOLD, 11));
		t1.addFocusListener(new FocusAdapter() {

		});
		t1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "FLIGHT", "ARRIVAL", "DEPARTURE", "PRICE", "TO", "FROM" }));
		// t1.setBackground(Color.WHITE);
		scrollPane.setViewportView(t1);
		comboBox_1.setBounds(333, 59, 152, 20);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel = new JLabel("To");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 16));
		lblNewLabel.setBounds(296, 61, 58, 14);
		contentPane.add(lblNewLabel);

		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Sitka Display", Font.BOLD, 16));
		lblFrom.setLabelFor(lblFrom);
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setBounds(66, 59, 58, 19);
		contentPane.add(lblFrom);

		JLabel lblAsas = new JLabel("");
		lblAsas.setFont(new Font("Sitka Display", Font.PLAIN, 11));
		lblAsas.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\13.jpg"));
		lblAsas.setBounds(0, 0, 1407, 727);
		contentPane.add(lblAsas);
		String query = "select F_name, S_point,E_point, price, D_time, A_time from I_flight";
		DisplayTable(query, false);
	}

	public International(String userName, String from, String destination) {
		startPoint = from;
		endPoint = destination;
		String query = "select F_name, S_point,E_point, price, D_time, A_time from I_flight where s_point=? and e_point=?";
		// DisplayTable(query,true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("India");
		comboBox_1.addItem("United States");
		comboBox_1.addItem("China");
		comboBox_1.addItem("United Arab Emirates");
		comboBox_1.addItem("United Kingdom");
		comboBox_1.addItem("France");
		comboBox_1.addItem("Singapore");
		comboBox_1.addItem("Thailand");
		comboBox_1.addItem("Malaysia");
		comboBox_1.addItem("Spain");
		comboBox_1.addItem("Canada");

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBackground(Color.CYAN);
		dateChooser.getCalendarButton().setFont(new Font("Sitka Display", Font.PLAIN, 11));
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setSelectableDateRange(new Date(), null);// hide past date
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnSearch.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\search.png"));
		btnSearch.setBounds(507, 58, 105, 23);
		contentPane.add(btnSearch);

		JComboBox<Integer> comboBox_2 = new JComboBox<Integer>();
		comboBox_2.addItem(1);
		comboBox_2.addItem(2);
		comboBox_2.addItem(3);
		comboBox_2.addItem(4);
		comboBox_2.addItem(5);
		comboBox_2.addItem(6);
		comboBox_2.addItem(7);
		comboBox_2.addItem(8);
		comboBox_2.addItem(9);
		comboBox_2.addItem(10);
		comboBox_2.setBounds(1144, 399, 50, 20);
		contentPane.add(comboBox_2);
		
		JLabel Uname = new JLabel("");
		Uname.setFont(new Font("Sitka Display", Font.BOLD, 13));
		Uname.setForeground(Color.WHITE);
		Uname.setBounds(1275, -2, 89, 20);
		contentPane.add(Uname);
		Uname.setText("HI" +" " +userName);

		JButton btnProceed = new JButton("Proceed");
		btnProceed.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\Save.png"));
		btnProceed.setBounds(1142, 452, 105, 23);
		contentPane.add(btnProceed);
		dateChooser.setBounds(1142, 346, 134, 20);
		contentPane.add(dateChooser);
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int numberOfPassengers = (int) comboBox_2.getSelectedItem();
				TableModel model = t1.getModel();
				int indexs[] = t1.getSelectedRows();
				Object row[] = new Object[7];				
				Booking boo = new Booking(userName);
				boo.setMaximumPassengers(numberOfPassengers);
				DefaultTableModel model2 = (DefaultTableModel) boo.table.getModel();
				SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
				String date = dFormat.format(dateChooser.getDate());
				for (int i = 0; i < indexs.length; i++) {
					System.out.println("row number = " + i);
					row[0] = model.getValueAt(indexs[i], 0);
					row[1] = model.getValueAt(indexs[i], 1);
					row[2] = model.getValueAt(indexs[i], 2);
					int price = Integer.parseInt((String) model.getValueAt(indexs[i], 3));
					row[3] = numberOfPassengers * price;
					row[4] = date;
					row[5] = numberOfPassengers;
					model2.addRow(row);
				}

				boo.setVisible(true);
				
				
			}
		});
		JLabel lblDepartureDate = new JLabel("Departure Date");
		lblDepartureDate.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblDepartureDate.setForeground(Color.WHITE);
		lblDepartureDate.setBounds(1034, 346, 98, 14);
		contentPane.add(lblDepartureDate);

		JLabel lblNoOgPassengers = new JLabel("No. of Passenger");
		lblNoOgPassengers.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblNoOgPassengers.setForeground(Color.WHITE);
		lblNoOgPassengers.setBounds(1034, 402, 112, 14);
		contentPane.add(lblNoOgPassengers);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(674, 23, 678, 254);
		contentPane.add(scrollPane);

		t1 = new JTable();
		t1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// t1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		t1.setFont(new Font("Sitka Display", Font.BOLD, 11));
		t1.addFocusListener(new FocusAdapter() {

		});
		t1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "FLIGHT", "ARRIVAL", "DEPARTURE", "PRICE", "TO", "FROM" }));
		// t1.setBackground(Color.WHITE);
		scrollPane.setViewportView(t1);
		comboBox_1.setBounds(333, 59, 152, 20);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel = new JLabel("To");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 16));
		lblNewLabel.setBounds(296, 61, 58, 14);
		contentPane.add(lblNewLabel);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("India");
		comboBox.addItem("United States");
		comboBox.addItem("China");
		comboBox.addItem("United Arab Emirates");
		comboBox.addItem("United Kingdom");
		comboBox.addItem("France");
		comboBox.addItem("Singapore");
		comboBox.addItem("Thailand");
		comboBox.addItem("Malaysia");
		comboBox.addItem("Spain");
		comboBox.addItem("Canada");

		comboBox.addContainerListener(new ContainerAdapter() {
		});
		/*
		 * JLabel Uname = new JLabel(""); Uname.setFont(new Font("Sitka Display",
		 * Font.BOLD, 13)); Uname.setForeground(Color.WHITE); Uname.setBounds(1275, -2,
		 * 89, 20); contentPane.add(Uname);
		 */
		comboBox.setBounds(122, 59, 152, 20);
		contentPane.add(comboBox);
		/* Uname.setText("HI" +" " +userName); */

		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Sitka Display", Font.BOLD, 16));
		lblFrom.setLabelFor(lblFrom);
		lblFrom.setForeground(Color.WHITE);
		lblFrom.setBounds(66, 59, 58, 19);
		contentPane.add(lblFrom);

		JLabel lblAsas = new JLabel("");
		lblAsas.setFont(new Font("Sitka Display", Font.PLAIN, 11));
		lblAsas.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\13.jpg"));
		lblAsas.setBounds(0, 0, 1407, 727);
		contentPane.add(lblAsas);
		// String query="select F_name, S_point,E_point, price, D_time, A_time from
		// I_flight";
		DisplayTable(query, true);

	}

	private void DisplayTable(String query, boolean flag) {
		try {
			Class.forName("org.h2.Driver");
			Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			PreparedStatement pst = connection.prepareStatement(query);
			if (flag) {
				pst.setString(1, startPoint);
				pst.setString(2, endPoint);
			}
			ResultSet rs = pst.executeQuery();
			t1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package APP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("serial")
public class Booking extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Integer maximumPassengers;
	private Integer numberOfPassengers = 0;

	public Integer getMaximumPassengers() {
		return maximumPassengers;
	}

	public void setMaximumPassengers(Integer maximumPassengers) {
		this.maximumPassengers = maximumPassengers;
	}

	public Integer getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(Integer numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking frame = new Booking("User");
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
	DefaultTableModel dm;

	public Booking(String userName) {
		setTitle("Airline Reservation System");

		setFont(new Font("Sitka Display", Font.BOLD, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRemove = new JButton("REMOVE");
		btnRemove.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\delete (1).png"));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				model.removeRow(table_1.getSelectedRow());
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});

		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\add-contact-icon.png"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (numberOfPassengers == maximumPassengers) {
					JOptionPane.showMessageDialog(null, "maximum passenger added");
					return;
				}
				if (textField.getText().isEmpty()) {
					textField.setText("Enter Name");
					return;

				} else if (textField_1.getText().isEmpty()) {
					textField_1.setText("Enter Age");
					return;

				} else if (textField_2.getText().isEmpty()) {
					textField_2.setText("Enter Valid ID");
					return;

				}
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				model.addRow(new Object[] { textField.getText(), textField_1.getText(), textField_2.getText() });
				JOptionPane.showMessageDialog(null, "Information added");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				numberOfPassengers++;
			}

		});

		JButton btnClear = new JButton("Update");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table_1.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				if (i >= 0) {
					model.setValueAt(textField.getText(), i, 0);
					model.setValueAt(textField_1.getText(), i, 1);
					model.setValueAt(textField_2.getText(), i, 2);
				} else {
					JOptionPane.showMessageDialog(null, "error");
				}
			}
		});

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Home hm = new Home("");
				hm.setVisible(true);
			}
		});
		
		JLabel Uname = new JLabel("");
		Uname.setFont(new Font("Sitka Display", Font.BOLD, 13));
		Uname.setForeground(Color.WHITE);
		Uname.setBounds(1234, 25, 99, 14);
		contentPane.add(Uname);
		Uname.setText("HI" + " " +userName);
		
		btnQuit.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\Users-Exit-icon.png"));
		btnQuit.setBounds(1234, 89, 89, 23);
		contentPane.add(btnQuit);
		

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblAge.setForeground(Color.WHITE);
		lblAge.setBounds(44, 378, 55, 21);
		contentPane.add(lblAge);

		JLabel lblPassengerName = new JLabel("Passenger Name");
		lblPassengerName.setForeground(Color.WHITE);
		lblPassengerName.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblPassengerName.setBounds(44, 341, 119, 26);
		contentPane.add(lblPassengerName);
		btnClear.setIcon(
				new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\Actions-document-edit-icon (1).png"));
		btnClear.setBounds(183, 456, 99, 23);
		contentPane.add(btnClear);

		JLabel lblIdNumber = new JLabel("ID Number");
		lblIdNumber.setForeground(Color.WHITE);
		lblIdNumber.setFont(new Font("Sitka Display", Font.BOLD, 13));
		lblIdNumber.setBounds(44, 410, 89, 23);
		contentPane.add(lblIdNumber);

		textField = new JTextField();
		textField.setBounds(163, 344, 119, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(163, 378, 119, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(163, 411, 119, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		btnAdd.setBounds(74, 456, 99, 23);
		contentPane.add(btnAdd);

		JLabel lblEnterPassengerDetails = new JLabel("--------Enter Passenger Details Below--------");
		lblEnterPassengerDetails.setFont(new Font("Sitka Display", Font.BOLD, 15));
		lblEnterPassengerDetails.setForeground(Color.WHITE);
		lblEnterPassengerDetails.setBounds(21, 316, 275, 17);
		contentPane.add(lblEnterPassengerDetails);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\images.jpg"));
		label_1.setBounds(21, 306, 275, 189);
		contentPane.add(label_1);
		btnRemove.setBounds(369, 273, 105, 23);
		contentPane.add(btnRemove);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 759, 65);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "FLIGHT", "FROM", "TO", "AMMOUNT", "DATE", "NO OF PASSENGER" }));
		table.getColumnModel().getColumn(5).setPreferredWidth(118);
		scrollPane.setViewportView(table);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 151, 759, 111);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * int SelectedRow = table_1.getSelectedRow(); DefaultTableModel model =
				 * (DefaultTableModel) table_1.getModel();
				 */
				textField.setText(table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
				textField_1.setText(table_1.getValueAt(table_1.getSelectedRow(), 1).toString());
				textField_2.setText(table_1.getValueAt(table_1.getSelectedRow(), 2).toString());
			}
		});
		table_1.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Passenger Name", "Age", "ID Number" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(105);
		scrollPane_1.setViewportView(table_1);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (numberOfPassengers != maximumPassengers) {
					JOptionPane.showMessageDialog(null, "Enter all pessanger detail");
					return;
				}
				try {
					int rows = table_1.getRowCount();
					Class.forName("org.h2.Driver");
//					SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//					String date = sdf.format(table.getSelectedRow());					
					Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
					conn.setAutoCommit(false);

					String queryBooking = "Insert into I_BOOKED(FLIGHT,T_FROM,T_TO,AMOUNT,T_DATE,NOOFPASS,Uname) values (?,?,?,?,?,?,?)";
					String queryPassengers = "Insert into PASSENGERDETAILS(NAME,AGE,IDPROOF,booking_id) values (?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(queryBooking, Statement.RETURN_GENERATED_KEYS);
					String FLIGHT = (String) table.getValueAt(0, 0);
					String T_FROM = (String) table.getValueAt(0, 1);
					String T_TO = (String) table.getValueAt(0, 2);
					String AMOUNT = table.getValueAt(0, 3).toString();
					String date = table.getValueAt(0, 4).toString();
					String NOOFPASS = table.getValueAt(0, 5).toString();		
					pst.setString(1, FLIGHT);
					pst.setString(2, T_FROM);
					pst.setString(3, T_TO);
					pst.setString(4, AMOUNT);
					pst.setString(5, date);
					pst.setString(6, NOOFPASS);
					pst.setString(7, userName);
					int result = pst.executeUpdate();
					if (result == 0) {
						throw new SQLException("Creating Booking Failed");
					}
					int bookingId=0;
					try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							bookingId = generatedKeys.getInt(1);
						} else {
							throw new SQLException("Creating Booking failed, no ID obtained.");
						}
					}
					System.out.println("Generated Id : " + bookingId);
					for (int row = 0; row < rows; row++) {
						PreparedStatement passengerPst = conn.prepareStatement(queryPassengers);
						String name = (String) table_1.getValueAt(row, 0);
						String age = (String) table_1.getValueAt(row, 1);
						String idproof = (String) table_1.getValueAt(row, 2);
						passengerPst.setString(1, name);
						passengerPst.setString(2, age);
						passengerPst.setString(3, idproof);
						passengerPst.setInt(4, bookingId);
						passengerPst.executeUpdate();
					}

					JOptionPane.showMessageDialog(null, "Booking confirmed");
					/*
					 * for(int row1 = 0; row1<rows; row1++) { String queryco2 =
					 * "Insert into I_BOOKED(NAME,AGE,IDPROOF) values (?,?,?)"; PreparedStatement
					 * pst2=conn.prepareStatement(queryco2); String name =
					 * (String)table_1.getValueAt(row1, 0); String age =
					 * (String)table_1.getValueAt(row1, 1); String idproof =
					 * (String)table_1.getValueAt(row1, 2); pst2.setString(1, name);
					 * pst2.setString(2, age); pst2.setString(3, idproof); pst2.addBatch();
					 * pst2.executeBatch(); Home h=new Home("user"); h.setVisible(true); dispose();
					 * }
					 */

					conn.commit();
					Home h = new Home("user");
					h.setVisible(true);
					dispose();

				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnSave.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\Save.png"));
		btnSave.setBounds(1234, 50, 89, 23);
		contentPane.add(btnSave);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\4.jpg"));
		label.setBounds(10, 21, 1362, 705);
		contentPane.add(label);
	}
}

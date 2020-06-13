package APP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class Home extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home("User");

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

	public Home(String userName) {
		getContentPane().setForeground(new Color(0, 0, 255));
		setTitle("Airline Reservation System");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
		getContentPane().setBackground(new Color(30, 144, 255));
		getContentPane().setLayout(null);
		
		JLabel lblYourJourney = new JLabel("Your Journey...");
		lblYourJourney.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 15));
		lblYourJourney.setForeground(Color.WHITE);
		lblYourJourney.setBounds(593, 36, 187, 27);
		getContentPane().add(lblYourJourney);

		JLabel offer_3 = new JLabel("");
		offer_3.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\download.png"));
		offer_3.setBounds(913, 297, 424, 119);
		getContentPane().add(offer_3);

		JLabel offer_2 = new JLabel("");
		offer_2.setIcon(new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\download (1).jpg"));
		offer_2.setBounds(495, 281, 382, 155);
		getContentPane().add(offer_2);

		JLabel offer_1 = new JLabel("");
		offer_1.setIcon(
				new ImageIcon("F:\\Learn java\\Airline Reservation System\\img\\58e2467868e812880862d37824fa96b1.jpg"));
		offer_1.setBounds(10, 268, 460, 192);
		getContentPane().add(offer_1);

		JLabel label_1 = new JLabel("<html><h3>Booking Flights with Airline Reservation System</h3><br>" +

				"Book your flights tickets with India's leading flight booking company since the year 2000."
				+ " While booking flights with ARS, you can expect the ultimate online booking experience. With premium customer service, 24/7 dedicated"
				+ " helpline for support, and over 5 million delighted customers, MakeMyTrip takes great pride in enabling customer satisfaction. With a"
				+ "cheapest flight guarantee, book your tickets at the lowest airfares. Avail great offers, exclusive deals for loyal customers and get"
				+ " instant updates for your flight status and fare drops.");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setFont(new Font("Sitka Display", Font.PLAIN, 14));
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(24, 477, 551, 196);
		getContentPane().add(label_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(593, 64, 759, 119);
		getContentPane().add(scrollPane_1);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Flight", "From", "To", "Date", "No of Passengers" }));
		table.getColumnModel().getColumn(4).setPreferredWidth(118);
		scrollPane_1.setViewportView(table);

		JLabel Uname = new JLabel("");
		Uname.setFont(new Font("Sitka Display", Font.BOLD, 13));
		Uname.setForeground(Color.WHITE);
		Uname.setBounds(1268, 11, 94, 14);
		getContentPane().add(Uname);
		Uname.setText("Hi" + " " + userName);
		String query ="select FLIGHT, T_FROM,T_TO, T_DATE, NOOFPASS from I_BOOKED where uname=?";
		DisplayTable(query, userName);
		 

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Button.light"));
		setJMenuBar(menuBar);

		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);

		JMenuItem mntmEdit = new JMenuItem("Edit");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Edit e = new Edit(userName);
				e.setVisible(true);

			}
		});
		mnUser.add(mntmEdit);
		JMenuItem mntmNewMenuItem = new JMenuItem("Change Password");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cpassword cp = new Cpassword(userName);
				cp.setVisible(true);

			}
		});
		mnUser.add(mntmNewMenuItem);

		JMenuItem mntmSignOut = new JMenuItem("Sign Out");
		mntmSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "successfully Logged out");
				dispose();
				
			}
		});
		mnUser.add(mntmSignOut);

		JMenu mnBooking = new JMenu("Booking");
		menuBar.add(mnBooking);

		JMenuItem mntmDomestic = new JMenuItem("Domestic");
		mntmDomestic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = Uname.getText();
				Domestic dm = new Domestic(userName);
				dm.setVisible(true);
				dm.mntmDomestic(user);
				dispose();
			}
		});
		mnBooking.add(mntmDomestic);

		JMenuItem mntmInternational = new JMenuItem("International");
		mntmInternational.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				International i = new International(userName);
				i.setVisible(true);
				
				dispose();
			}
		});
		mnBooking.add(mntmInternational);

		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);

		JMenuItem mntmBookingHistory = new JMenuItem("Booking History");
		mntmBookingHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BookingHistory bh= new BookingHistory(userName);
				bh.setVisible(true);
				dispose();
			}
		});
		mnReports.add(mntmBookingHistory);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnReports.add(mntmHelp);
	}
	private void DisplayTable(String query, String userName) {
		try {
			Class.forName("org.h2.Driver");
			Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, userName);			
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}
	}
}

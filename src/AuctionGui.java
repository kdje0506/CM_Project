import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class AuctionGui extends JFrame {

	JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuctionGui window = new AuctionGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AuctionGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 78, 562, 388);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		table.setForeground(new Color(0, 0, 0));
		table.setRowHeight(35);
		table.setToolTipText("");
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\uC218\uAC74", "20-10-15", "100", ""},
			},
			new String[] {
				"\uBB3C\uD488 \uBA85", "\uB9C8\uAC10\uB0A0\uC9DC", "\uCD5C\uACE0 \uC785\uCC30\uAC00", "\uC785\uCC30"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC544\uC6C3");
		btnNewButton.setBounds(12, 524, 110, 29);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AuctionClient.m_clientStub.logoutCM();
				frame.dispose();
			}
			
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uBB3C\uD488 \uB4F1\uB85D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnrollItem enrollitem = new EnrollItem();
				enrollitem.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(464, 524, 110, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Auction List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 50));
		lblNewLabel.setBounds(12, 10, 562, 70);
		frame.getContentPane().add(lblNewLabel);
	}
}

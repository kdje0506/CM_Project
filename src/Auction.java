import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Auction extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Auction frame = new Auction();
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
	public Auction() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Auction");
		lblNewLabel.setBounds(47, 93, 298, 15);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.setBounds(60, 203, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewButton_1.setBounds(248, 203, 97, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuctionClient.m_clientStub.syncLoginCM("submit", "submit");
				test test = new test();
				test.setVisible(true);			
			}
		});
		contentPane.add(btnNewButton_1);
	}
}

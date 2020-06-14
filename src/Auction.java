import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.ac.konkuk.ccslab.cm.event.CMDummyEvent;
import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Auction extends JFrame {

	private JPanel contentPane;
	private AuctionClient m_client;
	private CMClientStub m_clientStub;
	
	public Auction(CMClientStub clientStub, AuctionClient client)
	{
		m_client = client;
		m_clientStub = clientStub;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Auction");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		lblNewLabel.setBounds(60, 68, 298, 79);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.setBounds(72, 203, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_client.getLogin().setVisible(true);
				CMDummyEvent due = new CMDummyEvent(); 
				due.setHandlerSession(m_clientStub.getMyself().getCurrentSession()); 
				due.setHandlerGroup(m_clientStub.getMyself().getCurrentGroup()); 
				m_clientStub.send(due, "SERVER");
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewButton_1.setBounds(255, 203, 97, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp su = client.getSignUp();
				su.setVisible(true);			
			}
		});
		contentPane.add(btnNewButton_1);
		
		this.setVisible(true);
	}
}

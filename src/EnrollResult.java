import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnrollResult {
	private AuctionClient m_client;
	private CMClientStub m_clientStub;
	
	private JLabel EnrollSuccessLabel;
	JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EnrollResult window = new EnrollResult();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public EnrollResult(CMClientStub clientStub, AuctionClient client) {
		m_client = client;
		//m_outTextArea = textArea;
		m_clientStub = clientStub;
		initialize();
	}
	
	public void setMsg(String s) {
		EnrollSuccessLabel.setText(s);
	}
	
	public JFrame getJFrame() {
		return frame;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 314);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		EnrollSuccessLabel = new JLabel("\uD574\uB2F9 \uBB3C\uD488\uC5D0 \uB300\uD574 \uB4F1\uB85D\uC744 \uC644\uB8CC\uD558\uC600\uC2B5\uB2C8\uB2E4!");
		EnrollSuccessLabel.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		EnrollSuccessLabel.setBounds(95, 20, 354, 80);
		frame.getContentPane().add(EnrollSuccessLabel);
		
		JButton ConfirmButton = new JButton("Ȯ��");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		ConfirmButton.setBounds(221, 190, 97, 23);
		frame.getContentPane().add(ConfirmButton);
	}

}

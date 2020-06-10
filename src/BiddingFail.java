import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class BiddingFail {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiddingFail window = new BiddingFail();
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
	public BiddingFail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 636, 356);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel FailLabel = new JLabel("\uC785\uCC30 \uC2E4\uD328");
		FailLabel.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		FailLabel.setBounds(257, 23, 88, 34);
		frame.getContentPane().add(FailLabel);
		
		JLabel DescriptionLabel = new JLabel("<html>���� ������ ���� ���� �ݾ��� �Է��ϼ̰ų�<br>���� �̿��� �ٸ� ���ڸ� �Է��ϼ̽��ϴ�.</br><br>�ݾ��� �ٽ� �Է��ϼ���.</br></html>");
		DescriptionLabel.setFont(new Font("���ʷҵ���", Font.BOLD, 17));
		DescriptionLabel.setBounds(155, 108, 333, 109);
		frame.getContentPane().add(DescriptionLabel);
		
		JButton RebindButton = new JButton("���Է�");
		RebindButton.setBounds(383, 264, 97, 23);
		frame.getContentPane().add(RebindButton);
		
		JButton CancelButton = new JButton("���");
		CancelButton.setBounds(492, 264, 97, 23);
		frame.getContentPane().add(CancelButton);
	}

}

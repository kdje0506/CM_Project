import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnrollSuccess {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnrollSuccess window = new EnrollSuccess();
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
	public EnrollSuccess() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 314);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel EnrollSuccessLabel = new JLabel("\uD574\uB2F9 \uBB3C\uD488\uC5D0 \uB300\uD574 \uB4F1\uB85D\uC744 \uC644\uB8CC\uD558\uC600\uC2B5\uB2C8\uB2E4!");
		EnrollSuccessLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
		EnrollSuccessLabel.setBounds(95, 20, 354, 80);
		frame.getContentPane().add(EnrollSuccessLabel);
		
		JButton ConfirmButton = new JButton("확인");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		ConfirmButton.setBounds(221, 190, 97, 23);
		frame.getContentPane().add(ConfirmButton);
	}

}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BiddingSuccess {

	private JFrame frame;
	private JTextField ItemNameField;
	private JTextField PriceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiddingSuccess window = new BiddingSuccess();
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
	public BiddingSuccess() {
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
		
		JLabel BiddingSuccessLabel = new JLabel("�ش� ��ǰ�� ���� ������ �Ϸ��Ͽ����ϴ�!");
		BiddingSuccessLabel.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		BiddingSuccessLabel.setBounds(95, 20, 354, 80);
		frame.getContentPane().add(BiddingSuccessLabel);
		
		JLabel ItemNameLabel = new JLabel("��ǰ�� : ");
		ItemNameLabel.setBounds(27, 131, 57, 15);
		frame.getContentPane().add(ItemNameLabel);
		
		JLabel PriceLabel = new JLabel("������ : ");
		PriceLabel.setBounds(27, 195, 57, 15);
		frame.getContentPane().add(PriceLabel);
		
		ItemNameField = new JTextField();
		ItemNameField.setEnabled(false);
		ItemNameField.setColumns(10);
		ItemNameField.setBounds(89, 124, 169, 30);
		frame.getContentPane().add(ItemNameField);
		ItemNameField.setEnabled(false);
		
		PriceField = new JTextField();
		PriceField.setEnabled(false);
		PriceField.setColumns(10);
		PriceField.setBounds(89, 192, 169, 30);
		frame.getContentPane().add(PriceField);
		PriceField.setEnabled(false);
		
		JLabel NowWonLabel = new JLabel("��");
		NowWonLabel.setBounds(265, 199, 17, 15);
		frame.getContentPane().add(NowWonLabel);
		
		JButton ConfirmButton = new JButton("Ȯ��");
		ConfirmButton.setBounds(407, 242, 97, 23);
		frame.getContentPane().add(ConfirmButton);
	}

}

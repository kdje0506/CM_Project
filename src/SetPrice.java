import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetPrice {

	public JFrame frame;
	private JTextField NameField;
	private JTextField NowPriceField;
	private JTextField SetPriceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetPrice window = new SetPrice();
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
	public SetPrice() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 502, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel BiddingLabel = new JLabel("입찰");
		BiddingLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
		BiddingLabel.setBounds(220, 10, 48, 41);
		frame.getContentPane().add(BiddingLabel);
		
		JLabel ItemNameLabel = new JLabel("물품명 : ");
		ItemNameLabel.setBounds(37, 86, 57, 15);
		frame.getContentPane().add(ItemNameLabel);
		
		JLabel NowPriceLabel = new JLabel("현재가 : ");
		NowPriceLabel.setBounds(37, 135, 57, 15);
		frame.getContentPane().add(NowPriceLabel);
		
		JLabel SetPriceLabel = new JLabel("입찰가 설정 : ");
		SetPriceLabel.setBounds(37, 189, 76, 15);
		frame.getContentPane().add(SetPriceLabel);
		
		NameField = new JTextField();
		NameField.setColumns(10);
		NameField.setBounds(99, 79, 169, 30);
		frame.getContentPane().add(NameField);
		NameField.setEnabled(false);
		
		NowPriceField = new JTextField();
		NowPriceField.setColumns(10);
		NowPriceField.setBounds(99, 128, 169, 30);
		frame.getContentPane().add(NowPriceField);
		NowPriceField.setEnabled(false);
		
		SetPriceField = new JTextField();
		SetPriceField.setColumns(10);
		SetPriceField.setBounds(125, 182, 187, 30);
		frame.getContentPane().add(SetPriceField);
		
		JLabel NowWonLabel = new JLabel("원");
		NowWonLabel.setBounds(273, 135, 17, 15);
		frame.getContentPane().add(NowWonLabel);
		
		JLabel SetWonLabel = new JLabel("원");
		SetWonLabel.setBounds(316, 189, 17, 15);
		frame.getContentPane().add(SetWonLabel);
		
		JButton ConfirmButton = new JButton("확인");
		ConfirmButton.setBounds(261, 248, 97, 23);
		frame.getContentPane().add(ConfirmButton);
		
		JButton CancelButton = new JButton("취소");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CancelButton.setBounds(370, 248, 97, 23);
		frame.getContentPane().add(CancelButton);
	}

}

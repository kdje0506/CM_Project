import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ItemDescription {

	public JFrame frame;
	public JTextField NameField;
	public JTextField PriceField;
	public JTextField DateField;
	public JTextField DescriptionField;

	private AuctionClient m_client;
	private CMClientStub m_clientStub;

	/**
	 * Create the application.
	 */
	public ItemDescription(CMClientStub clientStub, AuctionClient client) {
		m_client = client;
		m_clientStub = clientStub;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 668, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel ItemDescriptionLabel = new JLabel("물품 설명서");
		ItemDescriptionLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
		ItemDescriptionLabel.setBounds(268, 24, 101, 27);
		frame.getContentPane().add(ItemDescriptionLabel);

		JLabel ItemNameLabel = new JLabel("이름 : ");
		ItemNameLabel.setBounds(63, 97, 55, 15);
		frame.getContentPane().add(ItemNameLabel);

		JLabel NowPriceLabel = new JLabel("현재가 : ");
		NowPriceLabel.setBounds(63, 151, 87, 15);
		frame.getContentPane().add(NowPriceLabel);

		JLabel DeadLineLabel = new JLabel("경매 종료 일시 : ");
		DeadLineLabel.setBounds(63, 204, 101, 15);
		frame.getContentPane().add(DeadLineLabel);

		JLabel DescriptionLabel = new JLabel("설명 : ");
		DescriptionLabel.setBounds(63, 260, 43, 15);
		frame.getContentPane().add(DescriptionLabel);

		NameField = new JTextField();
		NameField.setBounds(111, 90, 169, 30);
		frame.getContentPane().add(NameField);
		NameField.setColumns(10);
		NameField.setEnabled(false);

		PriceField = new JTextField();
		PriceField.setColumns(10);
		PriceField.setBounds(121, 144, 169, 30);
		frame.getContentPane().add(PriceField);
		PriceField.setEnabled(false);

		JLabel WonLabel = new JLabel("원");
		WonLabel.setBounds(295, 151, 19, 15);
		frame.getContentPane().add(WonLabel);

		DateField = new JTextField();
		DateField.setColumns(10);
		DateField.setBounds(163, 197, 200, 30);
		frame.getContentPane().add(DateField);
		DateField.setEnabled(false);

		DescriptionField = new JTextField();
		DescriptionField.setBounds(111, 257, 331, 80);
		frame.getContentPane().add(DescriptionField);
		DescriptionField.setColumns(10);
		DescriptionField.setEnabled(false);

		JButton ConfirmButton = new JButton("확인");
		ConfirmButton.setBounds(517, 349, 97, 23);
		frame.getContentPane().add(ConfirmButton);

		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

	}

}
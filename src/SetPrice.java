import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import kr.ac.konkuk.ccslab.cm.entity.CMUser;
import kr.ac.konkuk.ccslab.cm.event.CMDummyEvent;
import kr.ac.konkuk.ccslab.cm.info.CMInteractionInfo;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetPrice {

	public JFrame frame;
	public JTextField NameField;
	public JTextField NowPriceField;
	private JTextField SetPriceField;

	private AuctionClient m_client;
	private CMClientStub m_clientStub;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SetPrice(CMClientStub clientStub, AuctionClient client){
		m_client = client;
		m_clientStub = clientStub;
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
		ConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected_row = m_client.getAuctionGui().table.getSelectedRow();

				CMInteractionInfo interInfo = m_clientStub.getCMInfo().getInteractionInfo();
				CMUser myself = interInfo.getMyself();
				CMDummyEvent due3 = new CMDummyEvent();

				String tmp3 = "setPriceInfo#" + selected_row +"#"+Integer.parseInt(SetPriceField.getText());

				due3.setDummyInfo(tmp3);

				due3.setHandlerSession(myself.getCurrentSession());
				due3.setHandlerGroup(myself.getCurrentGroup());
				m_clientStub.send(due3, "SERVER");
				frame.dispose();

			}
		});
		frame.getContentPane().add(ConfirmButton);

		JButton CancelButton = new JButton("취소");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		CancelButton.setBounds(370, 248, 97, 23);
		frame.getContentPane().add(CancelButton);
	}

}

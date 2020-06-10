import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JButton;

public class ItemDescription {

	public JFrame frame;
	private JTextField NameField;
	private JTextField PriceField;
	private JTextField YearField;
	private JTextField MonthField;
	private JTextField DayField;
	private JTextField ClockField;
	private JTextField DescriptionField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemDescription window = new ItemDescription();
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
	public ItemDescription() {
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
		
		YearField = new JTextField();
		YearField.setColumns(10);
		YearField.setBounds(159, 197, 76, 30);
		frame.getContentPane().add(YearField);
		YearField.setEnabled(false);
		
		MonthField = new JTextField();
		MonthField.setColumns(10);
		MonthField.setBounds(258, 197, 43, 30);
		frame.getContentPane().add(MonthField);
		MonthField.setEnabled(false);
		
		DayField = new JTextField();
		DayField.setColumns(10);
		DayField.setBounds(323, 197, 43, 30);
		frame.getContentPane().add(DayField);
		DayField.setEnabled(false);
		
		ClockField = new JTextField();
		ClockField.setColumns(10);
		ClockField.setBounds(390, 197, 43, 30);
		frame.getContentPane().add(ClockField);
		ClockField.setEnabled(false);
		
		JLabel YearLabel = new JLabel("년");
		YearLabel.setBounds(242, 204, 19, 15);
		frame.getContentPane().add(YearLabel);
		
		JLabel MonthLabel = new JLabel("월");
		MonthLabel.setBounds(305, 204, 19, 15);
		frame.getContentPane().add(MonthLabel);
		
		JLabel DayLabel = new JLabel("일");
		DayLabel.setBounds(372, 204, 19, 15);
		frame.getContentPane().add(DayLabel);
		
		JLabel ClockLabel = new JLabel("시");
		ClockLabel.setBounds(438, 204, 19, 15);
		frame.getContentPane().add(ClockLabel);
		
		DescriptionField = new JTextField();
		DescriptionField.setBounds(111, 257, 331, 80);
		frame.getContentPane().add(DescriptionField);
		DescriptionField.setColumns(10);
		DescriptionField.setEnabled(false);
		
		JButton ConfirmButton = new JButton("확인");
		ConfirmButton.setBounds(517, 349, 97, 23);
		frame.getContentPane().add(ConfirmButton);
		
		
	}

}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EnrollItem {

	JFrame frame;
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
					EnrollItem window = new EnrollItem();
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
	public EnrollItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 617, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Headline = new JLabel("물품 등록");
		Headline.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
		Headline.setBounds(257, 10, 87, 36);
		frame.getContentPane().add(Headline);
		
		JLabel ItemName = new JLabel("이름 : ");
		ItemName.setBounds(63, 97, 55, 15);
		frame.getContentPane().add(ItemName);
		
		JLabel StartPrice = new JLabel("입찰 시작가 : ");
		StartPrice.setBounds(63, 151, 87, 15);
		frame.getContentPane().add(StartPrice);
		
		JLabel DeadLine = new JLabel("마감일 설정 : ");
		DeadLine.setBounds(63, 204, 76, 15);
		frame.getContentPane().add(DeadLine);
		
		JLabel Description = new JLabel("설명 : ");
		Description.setBounds(63, 260, 43, 15);
		frame.getContentPane().add(Description);
		
		NameField = new JTextField();
		NameField.setBounds(111, 90, 169, 30);
		frame.getContentPane().add(NameField);
		NameField.setColumns(10);
		
		PriceField = new JTextField();
		PriceField.setColumns(10);
		PriceField.setBounds(149, 144, 169, 30);
		frame.getContentPane().add(PriceField);
		
		JLabel Won = new JLabel("원");
		Won.setBounds(323, 151, 19, 15);
		frame.getContentPane().add(Won);
		
		YearField = new JTextField();
		YearField.setColumns(10);
		YearField.setBounds(151, 197, 76, 30);
		frame.getContentPane().add(YearField);
		
		MonthField = new JTextField();
		MonthField.setColumns(10);
		MonthField.setBounds(254, 197, 43, 30);
		frame.getContentPane().add(MonthField);
		
		DayField = new JTextField();
		DayField.setColumns(10);
		DayField.setBounds(323, 197, 43, 30);
		frame.getContentPane().add(DayField);
		
		ClockField = new JTextField();
		ClockField.setColumns(10);
		ClockField.setBounds(399, 197, 43, 30);
		frame.getContentPane().add(ClockField);
		
		JLabel Year = new JLabel("년");
		Year.setBounds(234, 204, 19, 15);
		frame.getContentPane().add(Year);
		
		JLabel Month = new JLabel("월");
		Month.setBounds(305, 204, 19, 15);
		frame.getContentPane().add(Month);
		
		JLabel Day = new JLabel("일");
		Day.setBounds(377, 204, 19, 15);
		frame.getContentPane().add(Day);
		
		JLabel Clock = new JLabel("시");
		Clock.setBounds(450, 204, 19, 15);
		frame.getContentPane().add(Clock);
		
		DescriptionField = new JTextField();
		DescriptionField.setBounds(111, 257, 331, 80);
		frame.getContentPane().add(DescriptionField);
		DescriptionField.setColumns(10);
		
		JButton Enroll = new JButton("등록");
		Enroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnrollSuccess enrollsuccess = new EnrollSuccess();
				enrollsuccess.frame.setVisible(true);
				frame.dispose();
			}
		});
		Enroll.setBounds(376, 371, 97, 23);
		frame.getContentPane().add(Enroll);
		
		JButton Cancel = new JButton("취소");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		Cancel.setBounds(492, 371, 97, 23);
		frame.getContentPane().add(Cancel);
	}
}

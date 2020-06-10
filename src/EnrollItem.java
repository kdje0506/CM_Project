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
		
		JLabel Headline = new JLabel("��ǰ ���");
		Headline.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		Headline.setBounds(257, 10, 87, 36);
		frame.getContentPane().add(Headline);
		
		JLabel ItemName = new JLabel("�̸� : ");
		ItemName.setBounds(63, 97, 55, 15);
		frame.getContentPane().add(ItemName);
		
		JLabel StartPrice = new JLabel("���� ���۰� : ");
		StartPrice.setBounds(63, 151, 87, 15);
		frame.getContentPane().add(StartPrice);
		
		JLabel DeadLine = new JLabel("������ ���� : ");
		DeadLine.setBounds(63, 204, 76, 15);
		frame.getContentPane().add(DeadLine);
		
		JLabel Description = new JLabel("���� : ");
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
		
		JLabel Won = new JLabel("��");
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
		
		JLabel Year = new JLabel("��");
		Year.setBounds(234, 204, 19, 15);
		frame.getContentPane().add(Year);
		
		JLabel Month = new JLabel("��");
		Month.setBounds(305, 204, 19, 15);
		frame.getContentPane().add(Month);
		
		JLabel Day = new JLabel("��");
		Day.setBounds(377, 204, 19, 15);
		frame.getContentPane().add(Day);
		
		JLabel Clock = new JLabel("��");
		Clock.setBounds(450, 204, 19, 15);
		frame.getContentPane().add(Clock);
		
		DescriptionField = new JTextField();
		DescriptionField.setBounds(111, 257, 331, 80);
		frame.getContentPane().add(DescriptionField);
		DescriptionField.setColumns(10);
		
		JButton Enroll = new JButton("���");
		Enroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnrollSuccess enrollsuccess = new EnrollSuccess();
				enrollsuccess.frame.setVisible(true);
				frame.dispose();
			}
		});
		Enroll.setBounds(376, 371, 97, 23);
		frame.getContentPane().add(Enroll);
		
		JButton Cancel = new JButton("���");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		Cancel.setBounds(492, 371, 97, 23);
		frame.getContentPane().add(Cancel);
	}
}

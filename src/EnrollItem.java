import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import kr.ac.konkuk.ccslab.cm.entity.CMUser;
import kr.ac.konkuk.ccslab.cm.event.CMDummyEvent;
import kr.ac.konkuk.ccslab.cm.info.CMInteractionInfo;
import kr.ac.konkuk.ccslab.cm.manager.CMDBManager;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
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
	
	private JPanel contentPane;
	private AuctionClient m_client;
	private CMClientStub m_clientStub;

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
	
	public EnrollItem(CMClientStub clientStub, AuctionClient client)
	{
		m_client = client;
		//m_outTextArea = textArea;
		m_clientStub = clientStub;
		
		initialize();
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
				HashMap<String, String> inputData = new HashMap<String, String>();
				inputData.put("name", NameField.getText());
				inputData.put("price", PriceField.getText());
				inputData.put("year", YearField.getText());
				inputData.put("month", MonthField.getText());
				inputData.put("day", DayField.getText());
				inputData.put("hour", ClockField.getText());
				inputData.put("description", DescriptionField.getText());
				
				
				
				if(isVaildForm(inputData)) {
					System.out.println("������ ����");
					
					CMInteractionInfo interInfo = m_clientStub.getCMInfo().getInteractionInfo(); 
					CMUser myself = interInfo.getMyself();
					
					CMDummyEvent due = new CMDummyEvent(); 
					due.setHandlerSession(myself.getCurrentSession()); 
					due.setHandlerGroup(myself.getCurrentGroup()); 
					due.setDummyInfo("123123123");
					m_clientStub.send(due, "SERVER");
					
					
//					String date = String.format("'%s-%s-%s %s:00:00'", 
//							inputData.get("year"),inputData.get("month"), inputData.get("day"),
//							inputData.get("hour"));
//					
//					String query = String.format("INSERT INTO item VALUES ('%s',%s,'%s','%s',%s,'f','%s')", 
//							inputData.get("name"),inputData.get("price"),date,
//							inputData.get("description"),"0",inputData.get("name"));
//					System.out.println(query);
					
					//CMDBManager.sendUpdateQuery(query,AuctionServer.m_serverStub.getCMInfo());
					EnrollSuccess enrollsuccess = new EnrollSuccess();
					enrollsuccess.frame.setVisible(true);
					frame.dispose();
				}
				
				
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
	
	private void updateList() {
		String query = String.format("INSERT INTO item VALUES (%s,%s,%s,%s,%s,%s,%s)", 
				"'tt1'","0","'2012-06-18 10:34:09'",
				"'de'","0","'f'","'tt'");
		//CMDBManager.sendUpdateQuery(query,m_serverStub.getCMInfo());
		//(name,start_price,due_date,description,now_price)
	}
	
	public boolean isVaildForm(HashMap<String, String> in) {
		
		System.out.println("����ó�� �޽��� �˾� �ʿ���");
		// ���� ����ó�� �ʿ�
		// �̸� ����ó��
		if(in.get("name").length() == 0) {
			return false;
		} else if(in.get("price").length() == 0){
			return false;
		} else if(in.get("year").length() == 0){
			return false;
		} else if(in.get("day").length() == 0){
			return false;
		} else if(in.get("hour").length() == 0){
			return false;
		} else if(in.get("description").length() == 0){
			return false;
		}
		return true;
		
	}
}

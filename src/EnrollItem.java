import javax.swing.JFrame;
import javax.swing.JTextField;
import kr.ac.konkuk.ccslab.cm.entity.CMUser;
import kr.ac.konkuk.ccslab.cm.event.CMDummyEvent;
import kr.ac.konkuk.ccslab.cm.info.CMInteractionInfo;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	
	public EnrollItem(CMClientStub clientStub, AuctionClient client)
	{
		m_client = client;
		//m_outTextArea = textArea;
		m_clientStub = clientStub;
		
		System.out.println(m_clientStub);
		
		initialize();
	}
	
	public JFrame getJFrame() {
		return frame;
	}
	
	/**
	 * Create the application.
	 */
//	public EnrollItem() {
//		initialize();
//	}

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
				HashMap<String, String> inputData = new HashMap<String, String>();
				inputData.put("name", NameField.getText());
				inputData.put("price", PriceField.getText());
				inputData.put("year", YearField.getText());
				inputData.put("month", MonthField.getText());
				inputData.put("day", DayField.getText());
				inputData.put("hour", ClockField.getText());
				inputData.put("description", DescriptionField.getText());
				
				
				
				if(isVaildForm(inputData)) {
					System.out.println("적법한 형식");
					CMInteractionInfo interInfo = m_clientStub.getCMInfo().getInteractionInfo(); 
					CMUser myself = interInfo.getMyself();
					
					String date = String.format("%s-%s-%s %s:00:00", 
							inputData.get("year"),inputData.get("month"), inputData.get("day"),
							inputData.get("hour"));
					
					CMDummyEvent due = new CMDummyEvent(); 
					due.setHandlerSession(m_clientStub.getMyself().getCurrentSession());
					due.setHandlerGroup(m_clientStub.getMyself().getCurrentGroup());
					due.setDummyInfo(String.format("EnrollItem#'%s'#%s#'%s'#'%s'#%s#'f'#'%s'", 
							inputData.get("name"),inputData.get("price"),date,
							inputData.get("description"),inputData.get("price"),inputData.get("name")));
					m_clientStub.send(due, "SERVER");}
				else {
					m_client.getEnrollResult().setMsg("적절하지 않은 형식입니다.");
					m_client.getEnrollResult().getJFrame().setVisible(true);
				}
				
			}
		});
		Enroll.setBounds(376, 371, 97, 23);
		frame.getContentPane().add(Enroll);
		
		JButton Cancel = new JButton("취소");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_client.getAuctionGui().initialize();
				m_client.getAuctionGui().frame.setVisible(true);
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
	
	private boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");  
	}
	
	public boolean isVaildForm(HashMap<String, String> in) {
		if(in.get("name").length() == 0 || in.get("price").length() == 0 ||
				in.get("year").length() == 0 || in.get("day").length() == 0 || in.get("month").length() == 0 ||
				in.get("hour").length() == 0 || in.get("description").length() == 0 ||
				isNumeric(in.get("name")) || !isNumeric(in.get("price")) ||
				!isNumeric(in.get("year")) || !isNumeric(in.get("day")) ||
				!isNumeric(in.get("hour"))) {
			return false;
		}
		int year = Integer.parseInt(in.get("year"));
		int month = Integer.parseInt(in.get("month"));
		int day = Integer.parseInt(in.get("day"));
		int hour = Integer.parseInt(in.get("hour"));
		
		if(year < 2000 || year > 2500 || month < 1 || month > 12 ||
				day < 1 || day > 31 || hour < 1 || hour > 24) {
			return false;
		}
		String date = String.format("%s-%02d-%02d %02d:00:00", 
				year, month,day,hour);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		if(dateTime.isBefore(LocalDateTime.now())) {
			return false;
		}
		
		return true;
		
	}
}

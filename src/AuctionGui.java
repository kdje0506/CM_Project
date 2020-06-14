import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import kr.ac.konkuk.ccslab.cm.entity.CMUser;
import kr.ac.konkuk.ccslab.cm.event.CMDummyEvent;
import kr.ac.konkuk.ccslab.cm.info.CMInteractionInfo;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public class AuctionGui extends JFrame {

	JFrame frame;
	public JTable table;
	private String[][] content;
	private AuctionClient m_client;
	private CMClientStub m_clientStub;

	public AuctionGui(CMClientStub clientStub, AuctionClient client) {
		m_client = client;
		m_clientStub = clientStub;
		initialize();
	}
	public void setContent(String[][] in, int r) {
		content = new String[r][4];
		for(int i=0; i<r;i++) {
			for(int j=0; j<4;j++) {
				content[i][j] = in[i][j];
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 78, 562, 388);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("±¼¸²", Font.PLAIN, 15));
		table.setForeground(new Color(0, 0, 0));
		table.setRowHeight(35);
		table.setToolTipText("");
		
		
		
		table.setModel(new DefaultTableModel(
			content,
			new String[] {
				"¹°Ç° ¸í", "¸¶°¨³¯Â¥", "ÃÖ°í ÀÔÂû°¡", "ÀÔÂû"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());;
		table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JTextField(),m_clientStub,m_client));
		table.getColumnModel().getColumn(0).setCellRenderer(new ButtonRenderer());;
		table.getColumnModel().getColumn(0).setCellEditor(new ButtonEditor(new JTextField(),m_clientStub,m_client));		
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("·Î±×¾Æ¿ô");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_client.m_clientStub.logoutCM();
				frame.dispose();
				Auction ac = m_client.getAuction();
				ac.setVisible(true);
			}
		});
		btnNewButton.setBounds(12, 524, 110, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("¹°Ç° µî·Ï");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnrollItem enrollitem = m_client.getEnrollItem();
				enrollitem.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(464, 524, 110, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Auction List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 50));
		lblNewLabel.setBounds(12, 10, 562, 70);
		frame.getContentPane().add(lblNewLabel);
	}
}

//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements  TableCellRenderer
{

	//CONSTRUCTOR
	public ButtonRenderer() {
		//SET BUTTON PROPERTIES
		setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
												   boolean selected, boolean focused, int row, int col) {

		//SET PASSED OBJECT AS BUTTON TEXT
		setText((obj==null) ? "":obj.toString());

		return this;
	}

}

//BUTTON EDITOR CLASS
class ButtonEditor extends DefaultCellEditor
{
	protected JButton btn;
	private String lbl;
	private Boolean clicked;
	private int fieldcol;
	private AuctionClient m_client;
	private CMClientStub m_clientStub;

	public ButtonEditor(JTextField txt,CMClientStub clientStub, AuctionClient client) {
		super(txt);
		m_client = client;
		m_clientStub = clientStub;

		btn=new JButton();
		btn.setOpaque(true);

		//WHEN BUTTON IS CLICKED
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	//OVERRIDE A COUPLE OF METHODS
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj,
												 boolean selected, int row, int col) {

		//SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
		lbl=(obj==null) ? "":obj.toString();
		btn.setText(lbl);
		fieldcol = col;
		clicked=true;
		return btn;
	}

	//IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
	@Override
	public Object getCellEditorValue() {

		if(clicked&&fieldcol==3)
		{
			m_client.getAuctionGui().frame.dispose();
			int selected_row = m_client.getAuctionGui().table.getSelectedRow();
			CMInteractionInfo interInfo = m_clientStub.getCMInfo().getInteractionInfo();
			CMUser myself = interInfo.getMyself();
			CMDummyEvent due2 = new CMDummyEvent();

			String tmp2 = "itemInfo#" + selected_row;

			due2.setDummyInfo(tmp2);

			due2.setHandlerSession(myself.getCurrentSession());
			due2.setHandlerGroup(myself.getCurrentGroup());
			m_clientStub.send(due2, "SERVER");

		}else if(clicked&&fieldcol==0){

			int selected_row = m_client.getAuctionGui().table.getSelectedRow();

			CMInteractionInfo interInfo = m_clientStub.getCMInfo().getInteractionInfo();
			CMUser myself = interInfo.getMyself();

			CMDummyEvent due = new CMDummyEvent();

			String tmp = "ItemDescription#" + selected_row;

			due.setDummyInfo(tmp);

			due.setHandlerSession(myself.getCurrentSession());
			due.setHandlerGroup(myself.getCurrentGroup());

			m_clientStub.send(due, "SERVER");

//         ItemDescription itemDes = m_client.getItemDescription();
//         itemDes.frame.setVisible(true);

		}
		//SET IT TO FALSE NOW THAT ITS CLICKED
		clicked=false;
		return new String(lbl);

	}

	@Override
	public boolean stopCellEditing() {
		//SET CLICKED TO FALSE FIRST
		clicked=false;
		return super.stopCellEditing();
	}

	@Override
	protected void fireEditingStopped() {
		// TODO Auto-generated method stub
		super.fireEditingStopped();
	}
}
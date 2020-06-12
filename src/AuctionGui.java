import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;

public class AuctionGui extends JFrame {

	JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuctionGui window = new AuctionGui();
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
	public AuctionGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
			new Object[][] {
				{"\uC218\uAC74", "20-10-15", "100", "\uC785\uCC30"},
			},
			new String[] {
				"\uBB3C\uD488 \uBA85", "\uB9C8\uAC10\uB0A0\uC9DC", "\uCD5C\uACE0 \uC785\uCC30\uAC00", "\uC785\uCC30"
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
		table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JTextField()));
		table.getColumnModel().getColumn(0).setCellRenderer(new ButtonRenderer());;
		table.getColumnModel().getColumn(0).setCellEditor(new ButtonEditor(new JTextField()));

		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC544\uC6C3");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(12, 524, 110, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uBB3C\uD488 \uB4F1\uB85D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnrollItem enrollitem = new EnrollItem();
				enrollitem.frame.setVisible(true);
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

 public ButtonEditor(JTextField txt) {
  super(txt);

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
	   SetPrice setprice = new SetPrice();
	   setprice.frame.setVisible(true);
    }else if(clicked&&fieldcol==0)
    {
    	ItemDescription itemDes = new ItemDescription();
		itemDes.frame.setVisible(true);
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
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JPasswordField pwd;
	
	public static boolean loginFlag = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(60, 88, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PW:");
		lblNewLabel_1.setBounds(60, 149, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		id = new JTextField();
		id.setBounds(109, 85, 240, 21);
		contentPane.add(id);
		id.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(109, 146, 240, 21);
		contentPane.add(pwd);
		pwd.setColumns(10);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.setBounds(60, 214, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginFlag = false;
				CMSessionEvent loginAckEvent = null;
				String userID = id.getText();
            	String userPassword = "";           	
            	char[] secret_pw = pwd.getPassword();
            	String errorMessage = "";
            	
            	for(char cha : secret_pw) {
            		Character.toString(cha);
            		userPassword += (userPassword.equals("")) ? cha + "" : "" + cha + "";
            	}
				
            	if(userID.length() == 0) {
            		errorMessage = "ID field cannot be blank!";
            		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            	}
            	
            	else if(userPassword.length() == 0) {
            		errorMessage = "Password field cannot be blank!";
            		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            	}
            	
            	else if(userID.length() != 0 && userPassword.length() != 0) {
            		loginAckEvent = AuctionClient.m_clientStub.syncLoginCM(userID, userPassword);
            		
            		if (loginAckEvent != null) {           			
                		if (loginAckEvent.isValidUser() == 0){
                			errorMessage = "Check your id or password!";
                			JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                		}
                		else if (loginAckEvent.isValidUser() == -1) {
                			errorMessage = "Already login that id!";
                			JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                		}
                		else {
                			AuctionGui auctionGui = new AuctionGui();
    						auctionGui.setVisible(true);           			
                			loginFlag = false;
                		}
            		}            		
            	}            	     
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uCDE8\uC18C");
		btnNewButton_1.setBounds(252, 214, 97, 23);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}			
		});
		contentPane.add(btnNewButton_1);				
	}
}

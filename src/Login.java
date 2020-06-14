import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

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

	private AuctionClient m_client;
	private CMClientStub m_clientStub;

	public static boolean loginFlag = false;

	/**
	 * Create the frame.
	 */
	public Login(CMClientStub clientStub, AuctionClient client) {
		m_client = client;
		//m_outTextArea = textArea;
		m_clientStub = clientStub;
		System.out.println("```````````````````````````````````````````");
		System.out.println(m_clientStub);

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
				//CMSessionEvent loginAckEvent = null;
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
					//System.out.println(m_clientStub);

					boolean bRequestResult = m_clientStub.loginCM(userID, userPassword);
					if(bRequestResult)
					{
						System.out.println("successfully sent the login request.\n");
						id.setText("");
						pwd.setText("");
						m_client.getLogin().dispose();
						m_client.getAuction().dispose();

					}
					else
					{
						System.out.println("failed the login request!\n");
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
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton_1);
	}
}

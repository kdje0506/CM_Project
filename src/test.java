import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.*;

import java.sql.*;

//import kr.ac.konkuk.ccslab.cm.info.CMInfo;
//import kr.ac.konkuk.ccslab.cm.manager.CMDBManager;



public class test extends JFrame
{
    JLabel lb1,lb2,la1,la2,la3;
    JTextField id;
    JPasswordField pwd;
    JTextField name;
    JPanel idPanel,pwdPanel,namePanel,loginPanel;
    JButton b1,b2;
    
    public static boolean registerFlag = false;	
    
    // MySQL driver
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // Database address
    private final String DB_URL = "jdbc:mysql://ahndb.caliu3u2esci.ap-northeast-2.rds.amazonaws.com:3306/cmdb?useSSL=false&autoReconnection=true";
    // User name & password (We use database in Amazon RDS, so we fixed user name and password)
    private final String USER_NAME = "ahn";
    private final String PASSWORD = "guqqnstks123";
    
    private Connection conn = null;
    private Statement state = null;

    public test()
    {
        super( "Sign Up" );
        setLayout( new GridBagLayout());
        setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        lb1 = new JLabel( "Join Us !!!");
        lb1.setFont(new Font("SanSerifBold", Font.BOLD,20));
        lb2 = new JLabel("Please Fill The Blank");
        lb2.setFont(new Font("SanSerifBold", Font.BOLD, 15));
        lb1.setHorizontalAlignment(JLabel.CENTER);
        lb2.setHorizontalAlignment(JLabel.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(lb1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(lb2, gbc);


        idPanel = new JPanel();
        pwdPanel = new JPanel();
        namePanel = new JPanel();
        
        la1 = new JLabel("ID");
        la1.setOpaque(true);
        la1.setForeground(Color.red);

        la2 = new JLabel("PW");
        la2.setOpaque(true);
        la2.setForeground(Color.red);

        id = new JTextField(15);
        pwd = new JPasswordField(15);
        name = new JTextField(15);

        la1.setBorder(new EmptyBorder(0,0,0,25));
        idPanel.add(la1);
        idPanel.add(id);

        la2.setBorder(new EmptyBorder(0,0,0,25));
        pwdPanel.add(la2);
        pwdPanel.add(pwd);

        loginPanel = new JPanel();
        b1 = new JButton("Submit");
        b1.setOpaque(true);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b2 = new JButton("Cancel");
        b2.setOpaque(true);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        loginPanel.add( b1 );
        loginPanel.add( b2 );
        
        // code for connecting to MySQL Server to use select query      
        try {
        	Class.forName(JDBC_DRIVER);
        	conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        	System.out.println(conn);
        	state = conn.createStatement();
        	
        } catch (Exception e) {
        	System.out.println(e);
        }

        // event handler for click »Æ¿Œ button
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
            	String newUserID = id.getText();	// id
            	String newUserPassword = "";		// password
            	
            	// code for reading input password
            	char[] secret_pw = pwd.getPassword();
            	String errorMessage = "";
            	
            	for(char cha : secret_pw) {
            		Character.toString(cha);
            		newUserPassword += (newUserPassword.equals("")) ? cha + "" : "" + cha + "";
            	}
            	
            	// case that id field is blank
            	if(newUserID.length() == 0) {
            		errorMessage = "ID field cannot be blank!";
            		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            	}
            	
            	// case that password field is blank
            	else if(newUserPassword.length() == 0) {
            		errorMessage = "Password field cannot be blank!";
            		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            	}
            	
            	// case that user fills both id and password field
            	else if(newUserID.length() != 0 && newUserPassword.length() != 0) {          		

            		ResultSet rs;
					try {
						// set MySQL Query
						PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user_table WHERE userName=?");
						pstmt.setString(1, newUserID);
						
						// execute 
						rs = pstmt.executeQuery();
						
						// get result
						rs.last();
						int rowCount = rs.getRow();
						
						// If newUserId value is already exists (in cmdb), it's impossible to submit
						if (rowCount != 0) {
	    					errorMessage = "ID Already exists! Try Again!!!";
	            			JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE); 
	    				}
	    				
						// If newUserId value doesn't exist, it's possible to submit new user information in cmdb
	    				else {
	    					// register user
	    					AuctionClient.m_clientStub.registerUser(newUserID, newUserPassword);
	    					try {
								Thread.sleep(3000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
	    					
	    					String successMessage = "Finish Signing up: " + newUserID;
	            			JOptionPane.showMessageDialog(null, successMessage, "Complete", JOptionPane.PLAIN_MESSAGE);	    					
	    				}


					} catch (SQLException e2) {
						e2.printStackTrace();
					}
            		  				    				
            	}
            		            	
            	else {
            		errorMessage = "Check your empty field!";
            		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            	}            	
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// close MySQL Connection resource
            	if (state != null) {
            		try {
    					state.close();
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}            	
            	}
            	
            	if (conn != null) {
            		try {
    					conn.close();
    				} catch (SQLException e1) {
    					e1.printStackTrace();
    				}
                	
            	}
            	
            	AuctionClient.m_clientStub.logoutCM();
                dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(idPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(pwdPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(namePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(loginPanel, gbc);

        setSize( 250, 350 );
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension frameSize = this.getSize(); 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 

        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); 
    }

    public static void main(String args[]){
        new test();
    }
}
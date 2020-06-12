import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import kr.ac.konkuk.ccslab.cm.event.CMDummyEvent;
import kr.ac.konkuk.ccslab.cm.event.CMEvent;
import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;
import kr.ac.konkuk.ccslab.cm.event.handler.CMAppEventHandler;
import kr.ac.konkuk.ccslab.cm.info.CMInfo;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

public class AuctionClientEventHandler implements CMAppEventHandler {
	//private JTextArea m_outTextArea;
		private AuctionClient m_client;
		private CMClientStub m_clientStub;
		private long m_lDelaySum;	// for forwarding simulation
		// for delay of SNS content downloading, distributed file processing, server response,
		// csc-ftp, c2c-ftp
		private long m_lStartTime;
		private int m_nEstDelaySum;	// for SNS downloading simulation
		private int m_nSimNum;		// for simulation of multiple sns content downloading
		private FileOutputStream m_fos;	// for storing downloading delay of multiple SNS content
		private PrintWriter m_pw;		//
		private int m_nCurrentServerNum;	// for distributed file processing
		private int m_nRecvPieceNum;		// for distributed file processing
		private boolean m_bDistFileProc;	// for distributed file processing
		private String m_strExt;			// for distributed file processing
		private String[] m_filePieces;		// for distributed file processing
		private boolean m_bReqAttachedFile;	// for storing the fact that the client requests an attachment
		private int m_nMinNumWaitedEvents;  // for checking the completion of asynchronous castrecv service
		private int m_nRecvReplyEvents;		// for checking the completion of asynchronous castrecv service
		
		// information for csc-ftp and c2c-ftp experiments
		private String m_strFileSender;
		private String m_strFileReceiver;
		private File[] m_arraySendFiles;
		private int m_nTotalNumFTPSessions;
		private int m_nCurNumFTPSessions;
		// information for c2c-ftp experiments
		private boolean m_bStartC2CFTPSession;
		private int m_nTotalNumFilesPerSession;
		private int m_nCurNumFilesPerSession;
		
		public AuctionClientEventHandler(CMClientStub clientStub, AuctionClient client)
		{
			m_client = client;
			//m_outTextArea = textArea;
			m_clientStub = clientStub;
			m_lDelaySum = 0;
			m_lStartTime = 0;
			m_nEstDelaySum = 0;
			m_nSimNum = 0;
			m_fos = null;
			m_pw = null;
			m_nCurrentServerNum = 0;
			m_nRecvPieceNum = 0;
			m_bDistFileProc = false;
			m_strExt = null;
			m_filePieces = null;
			m_bReqAttachedFile = false;
			m_nMinNumWaitedEvents = 0;
			m_nRecvReplyEvents = 0;
			
			m_strFileSender = null;
			m_strFileReceiver = null;
			m_arraySendFiles = null;
			m_nTotalNumFTPSessions = 0;
			m_nCurNumFTPSessions = 0;
			m_bStartC2CFTPSession = false;
			m_nTotalNumFilesPerSession = 0;
			m_nCurNumFilesPerSession = 0;
		}

//	@Override
//	public void processEvent(CMEvent event) {
//		if (event instanceof CMSessionEvent) {
//			CMSessionEvent sessionEvent = (CMSessionEvent)event;
//			processSessionEvent(sessionEvent);
//		}
//	}
	@Override
	public void processEvent(CMEvent cme) {
		// TODO Auto-generated method stub
		//System.out.println("Client app receives CM event!!");
		//System.out.println("Client app receives CM event!!");
		switch(cme.getType())
		{
		case CMInfo.CM_SESSION_EVENT:
			//System.out.println("CMInfo.CM_SESSION_EVENT)");
			processSessionEvent(cme);
			break;
//		case CMInfo.CM_INTEREST_EVENT:
//			processInterestEvent(cme);
//			break;
//		case CMInfo.CM_DATA_EVENT:
//			processDataEvent(cme);
//			break;
		case CMInfo.CM_DUMMY_EVENT:
			processDummyEvent(cme);
			break;
//		case CMInfo.CM_USER_EVENT:
//			processUserEvent(cme);
//			break;
//		case CMInfo.CM_FILE_EVENT:
//			processFileEvent(cme);
//			break;
//		case CMInfo.CM_SNS_EVENT:
//			processSNSEvent(cme);
//			break;
//		case CMInfo.CM_MULTI_SERVER_EVENT:
//			processMultiServerEvent(cme);
//			break;
//		case CMInfo.CM_MQTT_EVENT:
//			processMqttEvent(cme);
//			break;
		default:
			return;
		}
	}
	
	private void processSessionEvent(CMEvent cme)
	{
		CMSessionEvent se = (CMSessionEvent)cme;
		//System.out.println("processSessionEvent(CMEvent cme)");
		switch(se.getID())
		{
		case CMSessionEvent.LOGIN_ACK:
			if(se.isValidUser() == 0)
			{
				System.err.println("This client fails authentication by the default server!");
			}
			else if(se.isValidUser() == -1)
			{
				System.err.println("This client is already in the login-user list!");
			}
			else
			{
//				System.out.println("This client successfully logs in to the default server.");
				AuctionGui ag = m_client.getAuctionGui();
	            ag.frame.setVisible(true);
	            
	            //dispose();
			}
			break;
		case CMSessionEvent.RESPONSE_SESSION_INFO:
			//processRESPONSE_SESSION_INFO(se);
			break;
		case CMSessionEvent.SESSION_TALK:
			System.out.println("("+se.getHandlerSession()+")");
			System.out.println("<"+se.getUserName()+">: "+se.getTalk());
			break;
		case CMSessionEvent.ADD_NONBLOCK_SOCKET_CHANNEL_ACK:
			if(se.getReturnCode() == 0)
			{
				System.err.println("Adding a nonblocking SocketChannel("+se.getChannelName()+","+se.getChannelNum()
						+") failed at the server!");
			}
			else
			{
				System.out.println("Adding a nonblocking SocketChannel("+se.getChannelName()+","+se.getChannelNum()
						+") succeeded at the server!");
			}
			break;
		case CMSessionEvent.ADD_BLOCK_SOCKET_CHANNEL_ACK:
			if(se.getReturnCode() == 0)
			{
				System.err.println("Adding a blocking socket channel ("+se.getChannelName()+","+se.getChannelNum()
					+") failed at the server!");
			}
			else
			{
				System.out.println("Adding a blocking socket channel("+se.getChannelName()+","+se.getChannelNum()
					+") succeeded at the server!");
			}
			break;
		case CMSessionEvent.REMOVE_BLOCK_SOCKET_CHANNEL_ACK:
			if(se.getReturnCode() == 0)
			{
				System.err.println("Removing a blocking socket channel ("+se.getChannelName()+","+se.getChannelNum()
					+") failed at the server!");
			}
			else
			{
				System.out.println("Removing a blocking socket channel("+se.getChannelName()+","+se.getChannelNum()
					+") succeeded at the server!");
			}
			break;
		case CMSessionEvent.REGISTER_USER_ACK:
			if( se.getReturnCode() == 1 )
			{
				// user registration succeeded
				System.out.println("User["+se.getUserName()+"] successfully registered at time["
							+se.getCreationTime()+"].");
			}
			else
			{
				// user registration failed
				System.out.println("User["+se.getUserName()+"] failed to register!");
			}
			break;
		case CMSessionEvent.DEREGISTER_USER_ACK:
			if( se.getReturnCode() == 1 )
			{
				// user deregistration succeeded
				System.out.println("User["+se.getUserName()+"] successfully deregistered.");
			}
			else
			{
				// user registration failed
				System.out.println("User["+se.getUserName()+"] failed to deregister!");
			}
			break;
		case CMSessionEvent.FIND_REGISTERED_USER_ACK:
			if( se.getReturnCode() == 1 )
			{
				System.out.println("User profile search succeeded: user["+se.getUserName()
						+"], registration time["+se.getCreationTime()+"].");
			}
			else
			{
				System.out.println("User profile search failed: user["+se.getUserName()+"]!");
			}
			break;
		case CMSessionEvent.UNEXPECTED_SERVER_DISCONNECTION:
			System.err.println("Unexpected disconnection from ["+se.getChannelName()
					+"] with key["+se.getChannelNum()+"]!");
			break;
		case CMSessionEvent.INTENTIONALLY_DISCONNECT:
			System.err.println("Intentionally disconnected all channels from ["
					+se.getChannelName()+"]!");
			break;
		default:
			return;
		}
//		switch(se.getID())
//		{
//		case CMSessionEvent.LOGIN_ACK:
//			System.out.println("CMSessionEvent.LOGIN_ACK");
//			if(se.isValidUser() == 0)
//			{
//				System.err.println("This client fails authentication by the default server!");
//			}
//			else if(se.isValidUser() == -1)
//			{
//				System.err.println("This client is already in the login-user list!");
//			}
//			else
//			{
//				System.out.println("This client successfully logs in to the default server.");
//			}
//			break;
//		default:
//			return;
//		}
	}
	
	private void processDummyEvent(CMEvent cme)
	{
		CMDummyEvent due = (CMDummyEvent) cme;

		String due_tmp = due.getDummyInfo();

		String[] tmp = due_tmp.split("#");
		System.out.println(tmp.length);
		int rowLength = tmp.length / 4;
			
		String[][] input = new String[rowLength][4];
			
		for(int i=0;i<rowLength;i++) {
			for(int j=0;j<4;j++) {
				input[i][j]=tmp[4*i+j];
				}
			}
		
		m_client.getAuctionGui().setContent(input);
		
//	public void processSessionEvent(CMSessionEvent se) {
//		switch (se.getID()) {
//		case CMSessionEvent.REGISTER_USER_ACK:
//			if(se.getReturnCode() == 1) {
//				test.registerFlag = true;
//			}
//			else {
//				test.registerFlag = false;
//			}
//			break;
//		case CMSessionEvent.LOGIN_ACK:
//			System.out.println("---------------------------------------------------------------------------------");
//			if (se.isValidUser() == 0 || se.isValidUser() == -1) {
//				Login.loginFlag = false;
//			}
//			else if (se.isValidUser() == 1) {
//				Login.loginFlag = true;
//			}
//			break;
//		default:
//			return;
//		}
//	}
	}
}

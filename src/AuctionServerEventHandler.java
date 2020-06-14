import kr.ac.konkuk.ccslab.cm.entity.CMUser;
import kr.ac.konkuk.ccslab.cm.event.CMDummyEvent;
import kr.ac.konkuk.ccslab.cm.event.CMEvent;
import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;
import kr.ac.konkuk.ccslab.cm.event.handler.CMAppEventHandler;
import kr.ac.konkuk.ccslab.cm.info.CMConfigurationInfo;
import kr.ac.konkuk.ccslab.cm.info.CMInfo;
import kr.ac.konkuk.ccslab.cm.info.CMInteractionInfo;
import kr.ac.konkuk.ccslab.cm.manager.CMDBManager;
import kr.ac.konkuk.ccslab.cm.stub.CMServerStub;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuctionServerEventHandler implements CMAppEventHandler {
	private AuctionServer m_server;
	private CMServerStub m_serverStub;
	private int m_nCheckCount;	// for internal forwarding simulation
	private boolean m_bDistFileProc;	// for distributed file processing

	// information for csc_ftp
	private boolean m_bStartCSCFTPSession;
	private String m_strFileSender;
	private String m_strFileReceiver;
	private int m_nTotalNumFilesPerSession;
	private int m_nCurNumFilesPerSession;
	
	public AuctionServerEventHandler(CMServerStub serverStub, AuctionServer server)
	{
		m_server = server;
		m_serverStub = serverStub;
		m_nCheckCount = 0;
		m_bDistFileProc = false;
		
		m_bStartCSCFTPSession = false;
		m_strFileSender = null;
		m_strFileReceiver = null;
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
		System.out.println("cme.getType()");
		switch(cme.getType())
		{
		case CMInfo.CM_SESSION_EVENT:
			processSessionEvent(cme);
			break;
//		case CMInfo.CM_INTEREST_EVENT:
//			processInterestEvent(cme);
//			break;
		case CMInfo.CM_DUMMY_EVENT:
			System.out.println("CM_DUMMY_EVENT");
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
		CMConfigurationInfo confInfo = m_serverStub.getCMInfo().getConfigurationInfo();
		CMSessionEvent se = (CMSessionEvent) cme;
		System.out.println("processSessionEvent~~~~~~~~~");
		System.out.println(se.getID());
		switch(se.getID())
		{
		case CMSessionEvent.LOGIN:
			System.out.println("CMSessionEvent.LOGIN");
			System.out.println("["+se.getUserName()+"] requests login.");
			if(confInfo.isLoginScheme())
			{
				System.out.println("confInfo.isLoginScheme()");
				// user authentication...
				// CM DB must be used in the following authentication..
				boolean ret = CMDBManager.authenticateUser(se.getUserName(), se.getPassword(), 
						m_serverStub.getCMInfo());
				System.out.println("ret								" + ret);
				if(!ret)
				{
					System.out.println("["+se.getUserName()+"] authentication fails!");
					m_serverStub.replyEvent(se, 0);
				}
				else
				{
					CMInteractionInfo interInfo = m_serverStub.getCMInfo().getInteractionInfo();
					CMUser myself = interInfo.getMyself();
					System.out.println("["+se.getUserName()+"] authentication succeeded.");
					m_serverStub.replyEvent(se, 1);
					
					CMDummyEvent due = new CMDummyEvent();
					due.setHandlerSession(myself.getCurrentSession());
					due.setHandlerGroup(myself.getCurrentGroup()); 
					String tmp="";
				    try{
						ResultSet rs = CMDBManager.sendSelectQuery("SELECT name,start_price,due_date FROM item", m_serverStub.getCMInfo());
					    while(rs.next()) {
					    	tmp += rs.getString(1)+"#";
					    	tmp += rs.getString(2)+"#";
					    	tmp += rs.getString(3)+"#";					    	
					    	tmp += "ÀÔÂû"+"#";
					    }
				    }catch(SQLException e) {
				    }
				    tmp=tmp.substring(0, tmp.length()-1);
				    System.out.println(tmp);
			    	due.setDummyInfo(tmp);
					m_serverStub.send(due, se.getSender());
				}
			}
			break;
		case CMSessionEvent.LOGOUT:
			System.out.println("["+se.getUserName()+"] logs out.");
			break;
		case CMSessionEvent.REQUEST_SESSION_INFO:
			System.out.println("["+se.getUserName()+"] requests session information.");
			break;
		case CMSessionEvent.CHANGE_SESSION:
			System.out.println("["+se.getUserName()+"] changes to session("+se.getSessionName()+").");
			break;
		case CMSessionEvent.JOIN_SESSION:
			System.out.println("["+se.getUserName()+"] requests to join session("+se.getSessionName()+").");
			break;
		case CMSessionEvent.LEAVE_SESSION:
			System.out.println("["+se.getUserName()+"] leaves a session("+se.getSessionName()+").");
			break;
		case CMSessionEvent.ADD_NONBLOCK_SOCKET_CHANNEL:
			System.out.println("["+se.getChannelName()+"] request to add a nonblocking SocketChannel with key("
					+se.getChannelNum()+").");
			break;
		case CMSessionEvent.REGISTER_USER:
			System.out.println("User registration requested by user["+se.getUserName()+"].");
			break;
		case CMSessionEvent.DEREGISTER_USER:
			System.out.println("User deregistration requested by user["+se.getUserName()+"].");
			break;
		case CMSessionEvent.FIND_REGISTERED_USER:
			System.out.println("User profile requested for user["+se.getUserName()+"].");
			break;
		case CMSessionEvent.UNEXPECTED_SERVER_DISCONNECTION:
			System.err.println("Unexpected disconnection from ["
					+se.getChannelName()+"] with key["+se.getChannelNum()+"]!");
			break;
		case CMSessionEvent.INTENTIONALLY_DISCONNECT:
			System.err.println("Intentionally disconnected all channels from ["
					+se.getChannelName()+"]!");
			break;
		default:
			return;
		}
	}
	
	private void processDummyEvent(CMEvent cme)
	{
		CMDummyEvent due = (CMDummyEvent) cme;
		//System.out.println("session("+due.getHandlerSession()+"), group("+due.getHandlerGroup()+")");
		System.out.println("session("+due.getHandlerSession()+"), group("+due.getHandlerGroup()+")\n");
		//System.out.println("dummy msg: "+due.getDummyInfo());
		System.out.println("dummy msg: "+due.getDummyInfo()+"\n");
		
		String[] data = due.getDummyInfo().split("#");
		for(String s: data) {
			System.out.println(s);
		}
		System.out.println(data[0]);
		if(data.length == 0) {
			return;
		} else if(data[0].equals("EnrollItem")) {
			String query = String.format("INSERT "
					+ "INTO item(name,start_price,due_date,description,now_price,status,bid_winner) "
					+ "VALUES (%s,%s,%s,%s,%s,%s,%s)", 
					data[1],data[2],data[3],
					data[4],data[5],data[6],data[7]);
			System.out.println(query);
	
			int res = CMDBManager.sendUpdateQuery(query,m_serverStub.getCMInfo());
			System.out.println(res);
			
			CMDummyEvent ackMsg = new CMDummyEvent(); 
			ackMsg.setHandlerSession(due.getHandlerSession()); 
			ackMsg.setHandlerGroup(due.getHandlerGroup()); 
			System.out.println("11111111111111111111111111");
			if(res == 1) {
				System.out.println("2222222222222222222");
				ackMsg.setDummyInfo("EnrollItemAck#1");
			}
			else {
				ackMsg.setDummyInfo("EnrollItemAck#0");
			}
			m_serverStub.send(ackMsg, due.getSender());
		}
		
		return;
	}
	
//	public void processSessionEvent(CMSessionEvent se) {
//		CMConfigurationInfo confInfo = AuctionServer.m_serverStub.getCMInfo().getConfigurationInfo();
////		CMInfo cmInfo = AuctionServer.m_serverStub.getCMInfo();
//		
//		switch(se.getID()) {
//		case CMSessionEvent.REGISTER_USER:
//			CMSessionEvent resultEvent = new CMSessionEvent();
//			resultEvent.setID(CMSessionEvent.REGISTER_USER_ACK);
//			resultEvent.setReturnCode(1);
//			AuctionServer.m_serverStub.send(resultEvent, "submit");
//			
//		case CMSessionEvent.LOGIN:
//			if (confInfo.isLoginScheme()) {
//				boolean result = CMDBManager.authenticateUser(se.getUserName(), se.getPassword(), AuctionServer.m_serverStub.getCMInfo());
//				
//				if (result) {
//					AuctionServer.m_serverStub.replyEvent(se, 1);
//				}
//				else {
//					AuctionServer.m_serverStub.replyEvent(se, 0);
//				}
//			}
//			break;
//		default:
//			return;
//		}
//	}

}

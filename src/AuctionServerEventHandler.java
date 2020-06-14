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
	
	public AuctionServerEventHandler(CMServerStub serverStub, AuctionServer server)
	{
		m_server = server;
		m_serverStub = serverStub;
	}
	
	@Override
	public void processEvent(CMEvent cme) {
		// TODO Auto-generated method stub
		System.out.println("cme.getType()");
		switch(cme.getType())
		{
		case CMInfo.CM_SESSION_EVENT:
			processSessionEvent(cme);
			break;
		case CMInfo.CM_DUMMY_EVENT:
			System.out.println("CM_DUMMY_EVENT");
			processDummyEvent(cme);
			break;
		default:
			return;
		}
	}
	
	private void processSessionEvent(CMEvent cme)
	{
		CMConfigurationInfo confInfo = m_serverStub.getCMInfo().getConfigurationInfo();
		CMSessionEvent se = (CMSessionEvent) cme;
		switch(se.getID())
		{
		case CMSessionEvent.LOGIN:
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CMSessionEvent.LOGIN");
			System.out.println("["+se.getUserName()+"] requests login.");
			if(confInfo.isLoginScheme())
			{
				// user authentication...
				// CM DB must be used in the following authentication..
				boolean ret = CMDBManager.authenticateUser(se.getUserName(), se.getPassword(), 
						m_serverStub.getCMInfo());
				if(!ret)
				{
					m_serverStub.replyEvent(se, 0);
				}
				else
				{
					CMInteractionInfo interInfo = m_serverStub.getCMInfo().getInteractionInfo();
					CMUser myself = interInfo.getMyself();
					System.out.println("["+se.getUserName()+"] authentication succeeded.");
					m_serverStub.replyEvent(se, 1);

					sendItemList(se.getSender());
//					CMDummyEvent due = new CMDummyEvent();
//					due.setHandlerSession(myself.getCurrentSession());
//					due.setHandlerGroup(myself.getCurrentGroup());
//					String tmp="";
//				    try{
//						ResultSet rs = CMDBManager.sendSelectQuery("SELECT name,start_price,due_date FROM item", m_serverStub.getCMInfo());
//					    while(rs.next()) {
//					    	tmp += rs.getString(1)+"#";
//					    	tmp += rs.getString(2)+"#";
//					    	tmp += rs.getString(3)+"#";
//					    	tmp += "입찰"+"#";
//					    }
//				    }catch(SQLException e) {
//				    }
//				    tmp=tmp.substring(0, tmp.length()-1);
//				    System.out.println(tmp);
//			    	due.setDummyInfo(tmp);
//					m_serverStub.send(due, se.getSender());
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
	// 클라이언트에게 경매 목록 리스트 보내주기
	private void sendItemList(String clientName){
		CMDummyEvent due = new CMDummyEvent();
		due.setHandlerSession(m_serverStub.getMyself().getCurrentSession());
		due.setHandlerGroup(m_serverStub.getMyself().getCurrentGroup());
		String tmp="";
		try{
			ResultSet rs = CMDBManager.sendSelectQuery("SELECT name,now_price,due_date FROM item", m_serverStub.getCMInfo());
			while(rs.next()) {
				tmp += rs.getString(1)+"#";
				tmp += rs.getString(2)+"#";
				tmp += rs.getString(3)+"#";
				tmp += "입찰"+"#";
			}
		}catch(SQLException e) {
		}
		tmp=tmp.substring(0, tmp.length()-1);
		System.out.println(tmp);
		due.setDummyInfo(tmp);
		m_serverStub.send(due, clientName);

	}
	
	private void processDummyEvent(CMEvent cme)
	{
		CMInteractionInfo interInfo = m_serverStub.getCMInfo().getInteractionInfo();
		CMUser myself = interInfo.getMyself();

		CMDummyEvent due = (CMDummyEvent) cme;
		String[] data = due.getDummyInfo().split("#");

		if(data.length == 0) {
			return;
		}
		else if(data[0].equals("EnrollItem")) {
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
			if(res == 1) {
				this.sendItemList(due.getSender());
				ackMsg.setDummyInfo("EnrollItemAck#1");
			}
			else {
				ackMsg.setDummyInfo("EnrollItemAck#0");
			}
			m_serverStub.send(ackMsg, due.getSender());
		}
		else if(data[0].equals("setPriceInfo")){
		CMDummyEvent ID = new CMDummyEvent();
		ID.setHandlerSession(myself.getCurrentSession());
		ID.setHandlerGroup(myself.getCurrentGroup());
		//String tmp3="setPriceInfo#";
		int nowPrice = -1;

		String query = String.format("UPDATE item SET now_price = %d WHERE no= %d",Integer.parseInt(data[2]),(Integer.parseInt(data[1])+1));

		try{
			ResultSet rs = CMDBManager.sendSelectQuery("SELECT now_price FROM item WHERE no="+(Integer.parseInt(data[1])+1), m_serverStub.getCMInfo());
			while(rs.next()) {
				nowPrice = Integer.parseInt((rs.getString(1)));
			}
		}catch(SQLException e) {}

		if(nowPrice == -1){
			System.out.println("DB 접속 오류 예외처리");
		}
		else if(Integer.parseInt(data[2])>nowPrice){
			System.out.println("success!!!");
			CMDBManager.sendUpdateQuery(query, m_serverStub.getCMInfo());
			sendItemList(due.getSender());
		}
			else{
				System.out.println("현재 가격보다 작습니다.");
			}
		}
		else if(data[0].equals("itemInfo")){
			CMDummyEvent ID = new CMDummyEvent();
			ID.setHandlerSession(myself.getCurrentSession());
			ID.setHandlerGroup(myself.getCurrentGroup());
			String tmp2="itemInfo#";

			try{
				ResultSet rs = CMDBManager.sendSelectQuery("SELECT name, now_price FROM item WHERE no="+(Integer.parseInt(data[1])+1), m_serverStub.getCMInfo());
				while(rs.next()) {
					tmp2 += (rs.getString(1)+"#");
					tmp2 += (rs.getString(2));
				}
			}catch(SQLException e) {}
			ID.setDummyInfo(tmp2);
			m_serverStub.send(ID, due.getSender());
		}
		else if(data[0].equals("updateList")){
			sendItemList(due.getSender());
		}
		else if(data[0].equals("ItemDescription")) {
			CMDummyEvent ID = new CMDummyEvent();
			ID.setHandlerSession(myself.getCurrentSession());
			ID.setHandlerGroup(myself.getCurrentGroup());
			String tmp="ItemDescription#";
			try{
				ResultSet rs = CMDBManager.sendSelectQuery("SELECT name,start_price,due_date,description FROM item WHERE no="+(Integer.parseInt(data[1])+1), m_serverStub.getCMInfo());
				while(rs.next()) {
					tmp += (rs.getString(1)+"#");
					System.out.println(rs.getString(1));
					tmp += (rs.getString(2)+"#");
					tmp += (rs.getString(3)+"#");
					tmp += rs.getString(4);
				}
				System.out.println(tmp);
			}catch(SQLException e) {
			}
			ID.setDummyInfo(tmp);
			m_serverStub.send(ID, due.getSender());
		}

		return;
	}
}

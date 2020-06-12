
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.ac.konkuk.ccslab.cm.info.CMInfo;
import kr.ac.konkuk.ccslab.cm.manager.CMDBManager;
import kr.ac.konkuk.ccslab.cm.stub.CMServerStub;

public class AuctionServer {
	private CMServerStub m_serverStub;  
	private AuctionServerEventHandler m_eventHandler;    
	public AuctionServer()  {   
		m_serverStub = new CMServerStub();   
		m_eventHandler = new AuctionServerEventHandler(m_serverStub,this);  
	}    
	public CMServerStub getServerStub()  {   
			return m_serverStub;  
	}    
	public AuctionServerEventHandler getServerEventHandler()  {   
		return m_eventHandler;  
	} 
	 
	 public static void main(String[] args) {   
		 AuctionServer server = new AuctionServer();   
		 CMServerStub cmStub = server.getServerStub();   
		 cmStub.setAppEventHandler(server.getServerEventHandler());   
		 cmStub.startCM();  
		 } 
	 
}

//public class AuctionServer {
//	public static final CMServerStub m_serverStub = new CMServerStub(); 
//	private static final AuctionServerEventHandler m_eventHandler= new AuctionServerEventHandler();;
//	
//	public AuctionServer() {
//		//m_eventHandler = new AuctionServerEventHandler();
//	}
//		
//	public AuctionServerEventHandler getClientEventHandler() {
//		return m_eventHandler;
//	}
//	
//	public static void main(String[] args) throws SQLException {
//		AuctionServer server = new AuctionServer();
//		m_serverStub.setAppEventHandler(server.getClientEventHandler());
//		m_serverStub.startCM();
//		
////		ResultSet rs = CMDBManager.sendSelectQuery("SELECT * FROM item", m_serverStub.getCMInfo());
////		System.out.println(rs);
////		while(rs.next()) {
////			System.out.println(rs.getString(1));
////		}
//		
////		String query = String.format("INSERT INTO item VALUES (%s,%s,%s,%s,%s,%s,%s)", 
////				"'tt1'","0","'2012-06-18 10:34:09'",
////				"'de'","0","'f'","'tt'");
////		CMDBManager.sendUpdateQuery(query,AuctionServer.m_serverStub.getCMInfo());
//		//(name,start_price,due_date,description,now_price)
//		
//	}
//}

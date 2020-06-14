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

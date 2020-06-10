import kr.ac.konkuk.ccslab.cm.stub.CMServerStub;

public class AuctionServer {
	public static CMServerStub m_serverStub = new CMServerStub(); 
	private AuctionServerEventHandler m_eventHandler;
	
	public AuctionServer() {
		m_eventHandler = new AuctionServerEventHandler();
	}
		
	public AuctionServerEventHandler getClientEventHandler() {
		return m_eventHandler;
	}
	
	public static void main(String[] args) {
		AuctionServer server = new AuctionServer();
		m_serverStub.setAppEventHandler(server.getClientEventHandler());
		m_serverStub.startCM();
	}
}

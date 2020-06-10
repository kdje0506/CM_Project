import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

public class AuctionClient {
	public static CMClientStub m_clientStub = new CMClientStub(); 
	private AuctionClientEventHandler m_eventHandler;
	
	public AuctionClient() {
		m_eventHandler = new AuctionClientEventHandler();
	}
		
	public AuctionClientEventHandler getClientEventHandler() {
		return m_eventHandler;
	}
	
	public static void main(String[] args) {
		AuctionClient client = new AuctionClient();
		m_clientStub.setAppEventHandler(client.getClientEventHandler());
		m_clientStub.startCM();
	}
}

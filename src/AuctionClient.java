import java.sql.ResultSet;
import java.sql.SQLException;

import kr.ac.konkuk.ccslab.cm.manager.CMDBManager;
import kr.ac.konkuk.ccslab.cm.stub.CMClientStub;

//public class AuctionClient {
//	public static CMClientStub m_clientStub = new CMClientStub(); 
//	private AuctionClientEventHandler m_eventHandler;
//	
//	public AuctionClient() {
//		m_eventHandler = new AuctionClientEventHandler();
//	}
//		
//	public AuctionClientEventHandler getClientEventHandler() {
//		return m_eventHandler;
//	}
//	
//	public static void main(String[] args) {
//		AuctionClient client = new AuctionClient();
//		m_clientStub.setAppEventHandler(client.getClientEventHandler());
//		m_clientStub.startCM();
//	}
//}

public class AuctionClient {
	public CMClientStub m_clientStub; 
	public AuctionClientEventHandler m_eventHandler;
	
	private AuctionClient client;
	private CMClientStub cmStub;
	
	private Auction ac;
	private EnrollItem ei;
	private Login lg;
	private SignUp su;
	private AuctionGui ag;
	private SetPrice sp;
	private ItemDescription id;
	
	public AuctionClient() {
		//m_eventHandler = new AuctionClientEventHandler();
		m_clientStub = new CMClientStub();
		m_eventHandler = new AuctionClientEventHandler(m_clientStub, this);

	}
	
	public CMClientStub getClientStub()
	{
		return m_clientStub;
	}
	
	public AuctionClientEventHandler getClientEventHandler()
	{
		return m_eventHandler;
	}
	
	public void init() {
		client = this;
		cmStub = client.getClientStub();
		cmStub.setAppEventHandler(client.getClientEventHandler());
		cmStub.startCM();
		
		ac = new Auction(cmStub, this);
		lg = new Login(cmStub, this);
		ei = new EnrollItem(cmStub, this);
		su = new SignUp(cmStub, this);
		ag = new AuctionGui(cmStub, this);
		sp = new SetPrice(cmStub, this);
		id = new ItemDescription(cmStub, this);
	}
	
	public Login getLogin() {
		return lg;
	}
	
	public SignUp getSignUp() {
		return su;
	}
	
	public AuctionGui getAuctionGui() {
		return ag;
	}
	
	public SetPrice getSetPrice() {
		return sp;
	}
	public ItemDescription getItemDescription() {
		return id;
	}
	
	public static void main(String[] args){
		AuctionClient client = new AuctionClient();
		client.init();
		
	}
}

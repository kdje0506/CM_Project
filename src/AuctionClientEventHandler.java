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
		
		public AuctionClientEventHandler(CMClientStub clientStub, AuctionClient client)
		{
			m_client = client;
			m_clientStub = clientStub;
		}

	@Override
	public void processEvent(CMEvent cme) {
		// TODO Auto-generated method stub
		switch(cme.getType())
		{
		case CMInfo.CM_SESSION_EVENT:
			processSessionEvent(cme);
			break;

		case CMInfo.CM_DUMMY_EVENT:
			processDummyEvent(cme);
			break;
		default:
			return;
		}
	}
	
	private void processSessionEvent(CMEvent cme)
	{
		CMSessionEvent se = (CMSessionEvent)cme;
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
				System.out.println("This client successfully logs in to the default server.");
			}
			break;
		case CMSessionEvent.RESPONSE_SESSION_INFO:
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
	}
	
	private void processDummyEvent(CMEvent cme)
	{
		CMDummyEvent due = (CMDummyEvent) cme;

		String due_tmp = due.getDummyInfo();
		System.out.println("receive dummy msg: "+due.getDummyInfo()+"\n");
		String[] tmp = due_tmp.split("#");
		
		if(tmp.length == 0 ) {
			return;
		}else if(tmp[0].equals("EnrollItemAck")) {
			EnrollResult er = m_client.getEnrollResult();
			switch (tmp[1]) {
			case "0":
				er.setMsg("등록에 실패하였습니다.");
				break;
			case "1":
				er.setMsg("정상적으로 등록되었습니다.");
				break;
			default:
				er.setMsg("등록에 실패하였습니다.");
				break;
			}
			er.frame.setVisible(true);
			m_client.getEnrollItem().getJFrame().dispose();
		}
		else if(tmp[0].equals("ItemDescription")){
			m_client.getItemDescription().NameField.setText(tmp[1]);
			m_client.getItemDescription().PriceField.setText(tmp[2]);
			m_client.getItemDescription().DateField.setText(tmp[3]);
			m_client.getItemDescription().DescriptionField.setText(tmp[4]);
			m_client.getItemDescription().frame.setVisible(true);
		}
		else if(tmp[0].equals("itemInfo")){
			m_client.getSetPrice().NameField.setText(tmp[1]);
			m_client.getSetPrice().NowPriceField.setText(tmp[2]);
			m_client.getSetPrice().frame.setVisible(true);
		}
		else {

			int rowLength = tmp.length / 4;

			String[][] input = new String[rowLength][4];

			for (int i = 0; i < rowLength; i++) {
				for (int j = 0; j < 4; j++) {
					input[i][j] = tmp[4 * i + j];
				}
			}

			m_client.getAuctionGui().setContent(input, rowLength);
			m_client.getAuctionGui().initialize();
			m_client.getAuctionGui().frame.setVisible(true);
		}
	}
}

import kr.ac.konkuk.ccslab.cm.event.CMEvent;
import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;
import kr.ac.konkuk.ccslab.cm.event.handler.CMAppEventHandler;
import kr.ac.konkuk.ccslab.cm.info.CMInfo;

public class AuctionClientEventHandler implements CMAppEventHandler {

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
//		case CMInfo.CM_DUMMY_EVENT:
//			processDummyEvent(cme);
//			break;
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
				AuctionGui window = new AuctionGui();
	            window.frame.setVisible(true);
	            
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

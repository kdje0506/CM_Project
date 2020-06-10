import kr.ac.konkuk.ccslab.cm.event.CMEvent;
import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;
import kr.ac.konkuk.ccslab.cm.event.handler.CMAppEventHandler;
import kr.ac.konkuk.ccslab.cm.info.CMConfigurationInfo;
import kr.ac.konkuk.ccslab.cm.info.CMInfo;
import kr.ac.konkuk.ccslab.cm.manager.CMDBManager;
import java.sql.ResultSet;

public class AuctionServerEventHandler implements CMAppEventHandler {
	
	@Override
	public void processEvent(CMEvent event) {
		if (event instanceof CMSessionEvent) {
			CMSessionEvent sessionEvent = (CMSessionEvent)event;
			processSessionEvent(sessionEvent);
		}
	}
	
	public void processSessionEvent(CMSessionEvent se) {
		CMConfigurationInfo confInfo = AuctionServer.m_serverStub.getCMInfo().getConfigurationInfo();
//		CMInfo cmInfo = AuctionServer.m_serverStub.getCMInfo();
		
		switch(se.getID()) {
		case CMSessionEvent.REGISTER_USER:
			CMSessionEvent resultEvent = new CMSessionEvent();
			resultEvent.setID(CMSessionEvent.REGISTER_USER_ACK);
			resultEvent.setReturnCode(1);
			AuctionServer.m_serverStub.send(resultEvent, "submit");
			
		case CMSessionEvent.LOGIN:
			if (confInfo.isLoginScheme()) {
				boolean result = CMDBManager.authenticateUser(se.getUserName(), se.getPassword(), AuctionServer.m_serverStub.getCMInfo());
				
				if (result) {
					AuctionServer.m_serverStub.replyEvent(se, 1);
				}
				else {
					AuctionServer.m_serverStub.replyEvent(se, 0);
				}
			}
			break;
		default:
			return;
		}
	}

}

import kr.ac.konkuk.ccslab.cm.event.CMEvent;
import kr.ac.konkuk.ccslab.cm.event.CMSessionEvent;
import kr.ac.konkuk.ccslab.cm.event.handler.CMAppEventHandler;

public class AuctionClientEventHandler implements CMAppEventHandler {

	@Override
	public void processEvent(CMEvent event) {
		if (event instanceof CMSessionEvent) {
			CMSessionEvent sessionEvent = (CMSessionEvent)event;
			processSessionEvent(sessionEvent);
		}
	}
	
	public void processSessionEvent(CMSessionEvent se) {
		switch (se.getID()) {
		case CMSessionEvent.REGISTER_USER_ACK:
			if(se.getReturnCode() == 1) {
				test.registerFlag = true;
			}
			else {
				test.registerFlag = false;
			}
			break;
		case CMSessionEvent.LOGIN_ACK:
			if (se.isValidUser() == 0 || se.isValidUser() == -1) {
				Login.loginFlag = false;
			}
			else if (se.isValidUser() == 1) {
				Login.loginFlag = true;
			}
			break;
		default:
			return;
		}
	}

}

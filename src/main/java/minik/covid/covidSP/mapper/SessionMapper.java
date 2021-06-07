package minik.covid.covidSP.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import minik.covid.covidSP.entity.Session;
import minik.covid.covidSP.response.SessionResponse;

@Component
public class SessionMapper {

	@Autowired
	private UserMapper userMapper;

	public Session getSessionFromSessionResponse(SessionResponse sessionResponse) {

		Session session = new Session();

		session.setSessionId(sessionResponse.getSessionId());
		session.setIpAddress(sessionResponse.getIpAddress());
		session.setSessionEnd(sessionResponse.getSessionEnd());
		session.setSessionStart(sessionResponse.getSessionStart());
		session.setUser(this.userMapper.getUserFromUserResponse(sessionResponse.getUserResponse()));

		return session;

	}

	public SessionResponse getSessionResponse(Session session) {
		SessionResponse sessionResponse = new SessionResponse();

		sessionResponse.setSessionId(session.getSessionId());
		sessionResponse.setIpAddress(session.getIpAddress());
		sessionResponse.setSessionEnd(session.getSessionEnd());
		sessionResponse.setSessionStart(session.getSessionStart());
		
		return sessionResponse;
	}

	public SessionResponse getSessionResponseFromSession(Session session) {

		SessionResponse sessionResponse = new SessionResponse();

		sessionResponse.setSessionId(session.getSessionId());
		sessionResponse.setIpAddress(session.getIpAddress());
		sessionResponse.setSessionEnd(session.getSessionEnd());
		sessionResponse.setSessionStart(session.getSessionStart());
		sessionResponse.setUserResponse(this.userMapper.getUserResponseFromUser(session.getUser()));

		return sessionResponse;

	}

}

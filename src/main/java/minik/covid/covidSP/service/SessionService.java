package minik.covid.covidSP.service;

import java.util.List;

import minik.covid.covidSP.entity.Session;

public interface SessionService {

	List<Session> getAllSessions();
	
	Session getSessionById(Integer id);
	
	Session updateSession(Session session);
	
	void deleteSession(Integer id);
	
	Integer saveSession(Session session);
	
}

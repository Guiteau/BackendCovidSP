package minik.covid.covidSP.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minik.covid.covidSP.entity.Session;
import minik.covid.covidSP.repository.SessionRepository;

@Service
public class SessionService implements minik.covid.covidSP.service.SessionService {

	@Autowired
	private SessionRepository sessionRepository;
	
	@Override
	public List<Session> getAllSessions() {
		return sessionRepository.findAll();
	}

	@Override
	public Session getSessionById(Integer id) {
		return sessionRepository.getById(id);
	}

	@Override
	public Session updateSession(Session session) {
		return sessionRepository.save(session);
	}

	@Override
	public void deleteSession(Integer id) {
		sessionRepository.deleteById(id);
	}

	@Override
	public Integer saveSession(Session session) {
		Session sessionValue = new Session();
		sessionValue = sessionRepository.save(session);
		return sessionValue.getSessionId();
	}

}

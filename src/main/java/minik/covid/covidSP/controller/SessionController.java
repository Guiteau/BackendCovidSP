package minik.covid.covidSP.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minik.covid.covidSP.entity.Session;
import minik.covid.covidSP.mapper.SessionMapper;
import minik.covid.covidSP.response.SessionResponse;
import minik.covid.covidSP.service.impl.SessionService;

@RestController
@RequestMapping("session")
@CrossOrigin
public class SessionController {
	
	@Autowired
	private SessionService sessionService;
	@Autowired
	private SessionMapper sessionMapper;
	
	@GetMapping("getAllSessions")
	public ResponseEntity<List<SessionResponse>> getAllSessions(){
		
		List<SessionResponse> sessionResponseList = new ArrayList<>();	
		
		try {
			List<Session> sessionList = this.sessionService.getAllSessions();			
			for(Session sessionElement : sessionList) {
				sessionResponseList.add(this.sessionMapper.getSessionResponseFromSession(sessionElement));
			}			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(sessionResponseList, HttpStatus.OK);
		
	}
	
	@GetMapping("getSessionById")
	public ResponseEntity<SessionResponse> getSessionById(Integer id){
		
		SessionResponse sessionResponse = new SessionResponse();
		
		try {
			sessionResponse = this.sessionMapper.getSessionResponseFromSession(sessionService.getSessionById(id));
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(sessionResponse, HttpStatus.OK);
	}
	
	@PostMapping("saveSession")
	public ResponseEntity<Integer> saveSession(@RequestBody SessionResponse sessionResponse){
		
		Session session = new Session();
		
		System.out.println("entra en saveSession");
		
		try {
			if(sessionResponse.getSessionId() != null)
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);		
			session = this.sessionMapper.getSessionFromSessionResponse(sessionResponse);
			this.sessionService.saveSession(session);		
			if(session.getSessionId() == null)
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
	}
	
	@DeleteMapping("deleteSessionbyId/{id}")
	public ResponseEntity<Boolean> deleteSessionById(@Validated @PathVariable ("id") Integer id){
			
		try {
			this.sessionService.deleteSession(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
}

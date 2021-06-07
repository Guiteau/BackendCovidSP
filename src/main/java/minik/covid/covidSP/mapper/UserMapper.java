package minik.covid.covidSP.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import minik.covid.covidSP.entity.Session;
import minik.covid.covidSP.entity.User;
import minik.covid.covidSP.response.SessionResponse;
import minik.covid.covidSP.response.UserResponse;
import minik.covid.covidSP.service.impl.UserService;

@Component
public class UserMapper {

	@Autowired
	private CountryMapper countryMapper;
	@Autowired
	private SessionMapper sessionMapper;
	@Autowired
	private UserService userService;
	
	public User getUserFromUserResponse(UserResponse userResponse) {
		
		User user = new User();
		List<Session> sessionList = new ArrayList<>();
		List<SessionResponse> sessionResponseList = new ArrayList<>(); 
		
		user.setUserId(userResponse.getUserId());
		user.setUserName(userResponse.getUserName());
		user.setUserSurname(userResponse.getUserSurname());
		user.setBirthDate(userResponse.getBirthDate());
		user.setEmail(userResponse.getEmail());
		user.setPassword(userResponse.getPassword());
		user.setSex(userResponse.getSex());
		
		sessionResponseList = userResponse.getSessionResponseList();
		
		if(sessionResponseList != null) {
			for(SessionResponse sessionResponseElement : sessionResponseList) {		
				sessionList.add(this.sessionMapper.getSessionFromSessionResponse(sessionResponseElement));
			}
		}
				
		user.setCountry(this.countryMapper.getCountryFromCountryResponse(userResponse.getCountryResponse()));
		
		return user;
		
	}
	
	public UserResponse getUserResponseById(Integer id) {
		
		UserResponse userResponse = new UserResponse();
		
		User user = new User();
		
		user = this.userService.getUserById(id);
		userResponse = this.getUserResponseFromUser(user);
		
		return userResponse;
		
	}
	
	public UserResponse getUserResponseFromUser(User user) {
		
		UserResponse userResponse = new UserResponse();
		List<SessionResponse> sessionResponseList = new ArrayList<>();
		List<Session> sessionList = new ArrayList<>();
		
		userResponse.setBirthDate(user.getBirthDate());
		userResponse.setEmail(user.getEmail());
		userResponse.setPassword(user.getPassword());
		userResponse.setSex(user.getSex());
		userResponse.setUserId(user.getUserId());
		userResponse.setUserName(user.getUserName());
		userResponse.setUserSurname(user.getUserSurname());
		
		sessionList = user.getSessions();
		
		for(Session sessionElement : sessionList) {
			SessionResponse sessionResponse = new SessionResponse();
			sessionResponse = this.sessionMapper.getSessionResponse(sessionElement);
			sessionResponseList.add(sessionResponse);
		}
		
		userResponse.setSessionResponseList(sessionResponseList);
		
//		userResponse.setCountryResponse(this.countryMapper.getCountryResponseFromCountry(user.getCountry()));

		userResponse.setCountryResponse(this.countryMapper.getCountryResponse(user.getCountry()));
		
		return userResponse;
		
	}
	
}

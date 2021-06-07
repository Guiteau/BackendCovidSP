package minik.covid.covidSP.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import minik.covid.covidSP.entity.Country;
import minik.covid.covidSP.entity.User;
import minik.covid.covidSP.response.CountryResponse;
import minik.covid.covidSP.response.UserResponse;

@Component
public class CountryMapper {

	@Autowired
	private UserMapper userMapper;
	
	public Country getCountryFromCountryResponse(CountryResponse countryResponse) {
		
		List<UserResponse> userResponseList = new ArrayList<>();
		List<User> userList = new ArrayList<>();
				
		if(countryResponse != null) {
			Country country = new Country();
			country.setCountryCode(countryResponse.getCountryCode());
			country.setCountryName(countryResponse.getCountryName());
					
			userResponseList = countryResponse.getUsersResponseList();
			User user;
			
			if(userResponseList != null) {
				for(UserResponse userResponseElement : userResponseList) {
					user = this.userMapper.getUserFromUserResponse(userResponseElement);
					userList.add(user);
				}
			}
			
			country.setUsers(userList);			
			return country;
		}else {
			return null;
		}
		
	}
	
	public CountryResponse getCountryResponse(Country country) {
		
		CountryResponse countryResponse = new CountryResponse();
		
		countryResponse.setCountryCode(country.getCountryCode());
		countryResponse.setCountryName(country.getCountryName());
		
		return countryResponse;
	}
	
	public CountryResponse getCountryResponseFromCountry(Country country) {
		
		CountryResponse countryResponse = new CountryResponse();
		List<User> userList = new ArrayList<>();
		List<UserResponse> userResponseList = new ArrayList<>();
		
		countryResponse.setCountryCode(country.getCountryCode());
		countryResponse.setCountryName(country.getCountryName());
		
		userList = country.getUsers();
		UserResponse userResponse = new UserResponse();
			
		for(User userElement : userList) {
			userResponse = this.userMapper.getUserResponseFromUser(userElement);
			userResponseList.add(userResponse);
		}
		
		countryResponse.setUsersResponseList(userResponseList);
		
		return countryResponse;
	}
	
}

package minik.covid.covidSP.response;

import java.util.List;

public class CountryResponse {
	
	private String countryCode;
	private String countryName;
	private List<UserResponse> usersResponseList;
		
	public CountryResponse() {
		super();
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<UserResponse> getUsersResponseList() {
		return usersResponseList;
	}

	public void setUsersResponseList(List<UserResponse> usersResponseList) {
		this.usersResponseList = usersResponseList;
	}

}

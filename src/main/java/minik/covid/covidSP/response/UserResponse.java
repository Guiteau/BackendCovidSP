package minik.covid.covidSP.response;

import java.util.List;

public class UserResponse {
	
	private Integer userId;
	private Long birthDate;
	private String email;
	private String password;
	private String sex;
	private String userName;
	private String userSurname;
	private List<SessionResponse> sessionResponseList;
	private CountryResponse countryResponse;
	
	public UserResponse() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Long getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Long birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public List<SessionResponse> getSessionResponseList() {
		return sessionResponseList;
	}

	public void setSessionResponseList(List<SessionResponse> sessionResponseList) {
		this.sessionResponseList = sessionResponseList;
	}

	public CountryResponse getCountryResponse() {
		return countryResponse;
	}

	public void setCountryResponse(CountryResponse countryResponse) {
		this.countryResponse = countryResponse;
	}

}

package minik.covid.covidSP.response;

public class SessionResponse {
	
	private Integer sessionId;
	private String ipAddress;
	private Long sessionEnd;
	private Long sessionStart;
	private UserResponse userResponse;
	
	public SessionResponse() {
		super();
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Long getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(Long sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public Long getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(Long sessionStart) {
		this.sessionStart = sessionStart;
	}

	public UserResponse getUserResponse() {
		return userResponse;
	}

	public void setUserResponse(UserResponse userResponse) {
		this.userResponse = userResponse;
	}
	
}

package minik.covid.covidSP.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sessions database table.
 * 
 */
@Entity
@Table(name="sessions")
@NamedQuery(name="Session.findAll", query="SELECT s FROM Session s")
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="session_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sessionId;

	@Column(name="ip_address")
	private String ipAddress;

	@Column(name="session_end")
	private Long sessionEnd;

	@Column(name="session_start")
	private Long sessionStart;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Session() {
	}

	public Integer getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Long getSessionEnd() {
		return this.sessionEnd;
	}

	public void setSessionEnd(Long sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public Long getSessionStart() {
		return this.sessionStart;
	}

	public void setSessionStart(Long sessionStart) {
		this.sessionStart = sessionStart;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
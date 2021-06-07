package minik.covid.covidSP.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="country_code")
	private String countryCode;

	@Column(name="country_name")
	private String countryName;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="country")
	private List<User> users;

	public Country() {
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setCountry(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setCountry(null);

		return user;
	}

}
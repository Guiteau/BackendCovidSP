package minik.covid.covidSP.service;

import java.util.List;

import minik.covid.covidSP.entity.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	User getUserById(Integer id);
	
	void deleteUser(Integer id);
	
	Integer saveUser(User user);
	
	String getPasswordByUserEmail(String email);
	
	User getUserByEmail(String email);

}

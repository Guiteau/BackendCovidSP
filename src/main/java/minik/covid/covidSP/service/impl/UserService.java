package minik.covid.covidSP.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minik.covid.covidSP.entity.User;
import minik.covid.covidSP.repository.UserRepository;

@Service
public class UserService implements minik.covid.covidSP.service.UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		return userRepository.getById(id);
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public Integer saveUser(User user) {
		User userValue = new User();
		userValue = userRepository.save(user);
		return userValue.getUserId();
	}

	@Override
	public String getPasswordByUserEmail(String email) {
		User user = userRepository.getUserByEmail(email);
		return user.getPassword();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

}

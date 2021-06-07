package minik.covid.covidSP.service.impl;

import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BcryptService implements minik.covid.covidSP.service.BcryptService {

	public BcryptService() {}
	
	@Override
	public String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(10, new SecureRandom()));
	}
	
	@Override
	public Boolean decrypt(String password, String dbPassword) {
		return BCrypt.checkpw(password, dbPassword);
	}

}

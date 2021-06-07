package minik.covid.covidSP.service;

public interface BcryptService {

	String encrypt(String password);

	Boolean decrypt(String password, String dbPassword);
}

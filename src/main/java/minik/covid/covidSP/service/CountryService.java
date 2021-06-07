package minik.covid.covidSP.service;

import java.util.List;

import minik.covid.covidSP.entity.Country;

public interface CountryService {

	List<Country> getAllCountries();

	Country getCountry(String country);
	
	Country updateCountry(Country country);
	
	void deleteCountry(Country country);
	
	void deleteCountryById(String id);
	
	String saveCountry(Country country);
	
}

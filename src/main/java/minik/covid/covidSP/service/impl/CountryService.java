package minik.covid.covidSP.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import minik.covid.covidSP.entity.Country;
import minik.covid.covidSP.repository.CountryRepository;

@Service
public class CountryService implements minik.covid.covidSP.service.CountryService {
	
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

	@Override
	public Country getCountry(String country) {
		return countryRepository.getById(country);
	}

	@Override
	public Country updateCountry(Country country) {
		return countryRepository.save(country);
	}

	@Override
	public void deleteCountry(Country country) {
		countryRepository.delete(country);
	}

	@Override
	public String saveCountry(Country country) {
		Country countryValue = new Country();
		countryValue = countryRepository.save(country);
		return countryValue.getCountryCode();
	}

	@Override
	public void deleteCountryById(String id) {
		countryRepository.deleteById(id);
	}

}

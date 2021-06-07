package minik.covid.covidSP.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import minik.covid.covidSP.entity.Country;
import minik.covid.covidSP.mapper.CountryMapper;
import minik.covid.covidSP.response.CountryResponse;
import minik.covid.covidSP.service.impl.CountryService;

@RestController
@RequestMapping("country")
@CrossOrigin
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	@Autowired
	private CountryMapper countryMapper;
	
	@GetMapping("getAllCountries")
	public ResponseEntity<List<CountryResponse>> getAllCountries(){
		
		List<CountryResponse> countryResponseList = new ArrayList<>();
				
		try {
			List<Country> countryList = this.countryService.getAllCountries();	
			for(Country countryElement : countryList) {
				countryResponseList.add(this.countryMapper.getCountryResponseFromCountry(countryElement));
			}		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(countryResponseList, HttpStatus.OK);		
	}
	
	@GetMapping("getCountry/{country}")
	public ResponseEntity<CountryResponse> getCountry(@Validated @PathVariable ("country") String country){
		
		CountryResponse countryResponse = new CountryResponse();
		
		try {
			countryResponse = this.countryMapper.getCountryResponseFromCountry(this.countryService.getCountry(country));
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(countryResponse, HttpStatus.OK);		
	}
	
	@PostMapping("saveCountry")
	public ResponseEntity<String> saveCountry(@RequestBody CountryResponse countryResponse){
		
		Country country = new Country();
		
		try {			
			country = this.countryMapper.getCountryFromCountryResponse(countryResponse);
			this.countryService.saveCountry(country);		
			if(country.getCountryCode() == null) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}		
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(country.getCountryCode(), HttpStatus.OK);
	}

	@DeleteMapping("deleteCountry")
	public ResponseEntity<Boolean> deleteCountry(@RequestBody CountryResponse countryResponse){
		
		Country country = new Country();
		
		try {
			country = this.countryMapper.getCountryFromCountryResponse(countryResponse);
			this.countryService.deleteCountry(country);			
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@DeleteMapping("deleteCountryById/{id}")
	public ResponseEntity<Boolean> deleteCountryById(@Validated @PathVariable("id") String id){
		
		try {
			this.countryService.deleteCountryById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(true, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

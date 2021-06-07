package minik.covid.covidSP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import minik.covid.covidSP.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

}

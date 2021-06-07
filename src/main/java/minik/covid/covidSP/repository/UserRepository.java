package minik.covid.covidSP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import minik.covid.covidSP.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT us FROM User us where us.email = :email")
	User getUserByEmail(String email);
	
}

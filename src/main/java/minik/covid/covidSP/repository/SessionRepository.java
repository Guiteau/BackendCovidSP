package minik.covid.covidSP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import minik.covid.covidSP.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

}

package es.minddata.challenge.repository;

import es.minddata.challenge.entity.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {
}

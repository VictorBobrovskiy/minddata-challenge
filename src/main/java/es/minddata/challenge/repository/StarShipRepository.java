package es.minddata.challenge.repository;

import es.minddata.challenge.entity.StarShip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StarShipRepository extends JpaRepository<StarShip, Long> {
    Optional<StarShip> findByName(String name);

    Page<StarShip> findAllByNameContaining(String name, Pageable pageable);

}
package co.simplon.miniforum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.miniforum.models.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {

}

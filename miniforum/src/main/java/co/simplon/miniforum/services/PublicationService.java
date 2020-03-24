package co.simplon.miniforum.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.miniforum.models.Publication;
import co.simplon.miniforum.repositories.PublicationRepository;

@Service
public class PublicationService {
	
	 @Autowired
	 private PublicationRepository publicationRepository;
	 
	 public Collection<Publication> findAll() {
	        return publicationRepository.findAll();
	 }
	 
	 public Publication findById(int id) {
		 Optional<Publication> optPublication = publicationRepository.findById(id);
			if(optPublication.isPresent()) {
				return optPublication.get();
			} else {
				throw new IllegalArgumentException("id can't be found in database");
			}
	 }
	 
	 public Publication addPublication(Publication publi) {
		 return publicationRepository.saveAndFlush(publi);
	 }
	
	 public void removePublication(int id) {
		Optional<Publication> optPublication = publicationRepository.findById(id);
		if(optPublication.isPresent()) {
			publicationRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("id can't be found in database");
		}	
	 }
}

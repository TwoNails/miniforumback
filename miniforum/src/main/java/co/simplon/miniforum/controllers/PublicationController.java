package co.simplon.miniforum.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.miniforum.models.ForumUser;
import co.simplon.miniforum.models.Publication;
import co.simplon.miniforum.models.enums.Role;
import co.simplon.miniforum.services.ForumUserService;
import co.simplon.miniforum.services.PublicationService;


@RestController
@RequestMapping("/publication")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicationController {
	
	@Autowired 
	PublicationService publicationService;
	
	@Autowired 
	ForumUserService forumUserService;
	
	@GetMapping("/all")
	public ResponseEntity<Collection<Publication>> findAll(){
		return new ResponseEntity<Collection<Publication>>(publicationService.findAll(), HttpStatus.OK); // 200
	}
	
	@PostMapping("/save")
	public ResponseEntity<Publication> addPublication(@RequestBody Publication publi) {
		try {
				publicationService.addPublication(publi);						// should throw an error if the id of the author of the publi can't be found in base.
		} catch (Exception e) {														
			return new ResponseEntity<Publication>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Publication>(publicationService.addPublication(publi), HttpStatus.CREATED);	// 201
	}
	
	@PostMapping("/del/{id}")
	public ResponseEntity<Void> delPublication(@RequestParam int id, @RequestBody int userId/*, @RequestHeader("role") String role*/){ // est-ce que ca sera dans le jwt ou à part ?
		try {
			Publication publi = publicationService.findById(id);
			ForumUser currentUser = forumUserService.findById(userId);
			if (publi.getAuthor().getId() == userId || currentUser.getRole().equals(Role.ADMIN)) {
				publicationService.removePublication(id);
			} else {
				return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.GONE); // 410, 404 NOT_FOUND could be used aswell
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204
	}
	
}
 
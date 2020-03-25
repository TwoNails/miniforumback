package co.simplon.miniforum.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import co.simplon.miniforum.models.ForumUser;
import co.simplon.miniforum.models.Publication;
import co.simplon.miniforum.repositories.PublicationRepository;

@RunWith(SpringRunner.class)  /* is used to provide a bridge between Spring Boot test features and JUnit. 
								Whenever we are using any Spring Boot testing features in our JUnit tests, 
								this annotation will be required. */
@DataJpaTest /* provides some standard setup needed for testing the persistence layer:
					- configuring H2, an in-memory database					- performing an @EntityScan
					- setting Hibernate, Spring Data, and the DataSource	- turning on SQL logging */		
public class PublicationServiceTest {
	
	@Autowired
    private TestEntityManager entityManager; 	/* 	will allow us to setup some records in our database
													in the 'given' part of the test */
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Test
	public void whenFindAll_thenReturnCorrectList() {
	// given
	Publication firstPost = new Publication("Hello World", new ForumUser(), new Date());
	entityManager.persist(firstPost);
	Publication secondPost = new Publication("Bla Bla", new ForumUser(), new Date());
	entityManager.persist(secondPost);
	entityManager.flush();
	
	// when
	List<Publication> found = publicationRepository.findAll();
	
	// then
	assertThat(found.get(0).getTextContent())
		.isEqualTo(firstPost.getTextContent());
	
	/*
	    Publication firstPost = new Publication("Hello World", new ForumUser(), new Date());
	    entityManager.persist(firstPost);
		Publication secondPost = new Publication("Bla Bla", new ForumUser(), new Date());
	    entityManager.persist(secondPost);
	    entityManager.flush();
	 
	    // when
	    List<Publication> found = publicationRepository.findAll();
	 
	    // then
	    assertThat(found.get(0).getTextContent())
	      .isEqualTo(firstPost.getTextContent());
	*/
	}
	
}

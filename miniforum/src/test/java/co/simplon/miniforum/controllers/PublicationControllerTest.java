package co.simplon.miniforum.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import co.simplon.miniforum.models.ForumUser;
import co.simplon.miniforum.models.ForumUserDTO;
import co.simplon.miniforum.models.Publication;
import co.simplon.miniforum.services.PublicationService;


@ExtendWith(MockitoExtension.class)
class PublicationControllerTest extends PublicationController {

	@Autowired
	private MockMvc mvc;
	
	@InjectMocks
	PublicationController publicationController;
	
	@Mock
	PublicationService publicationService;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@Test
	public void stupidtest() {
		assertThat(2).isEqualTo(new Integer(2));
	}
	
	/*
	@Test
	public
	
	
	@Test
	public void testAddPublication() throws Exception {
		ForumUser author = new ForumUser(new ForumUserDTO(14, "Nils", "ADMIN", "J'aime les chocobons"));
		Publication publication = new Publication(3, "Contenu textuel de la publication bla bla bla...", new Date(), author);
		
		when(this.publicationService.addPublication(publication)).thenReturn(publication);
		
		this.mvc.perform(post("/publication/save", publication))
		.andExpect(status().isOk());
		// .andExpect(jsonPath)	
	}
	*/
	
	/*
	@Test
    public void testAddPublication() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Publication anyPub;
        when(publicationService.addPublication(anyPub = (Publication) any(Publication.class))).thenReturn(anyPub);
         
        Publication publi = new Publication("BLA BLA BLA", new ForumUser(), new Date());
        ResponseEntity<Publication> responseEntity = publicationController.addPublication(publi);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getId()).isEqualTo(1);
    }
	*/
	
	
	@Test
	public void getAllPublicationsEndpointTest() throws Exception {
		when(this.publicationService.findAll()).thenReturn(new ArrayList<Publication>());
		// EN QUOI CEST UNNECESSARY PUTAINB DE TROU DU CUL QUAND JE MLET PS CA JaI UN NULLPOINTER 
		// CEST L 3eme FOIS QUE JESSAI DE TE FAIRE MARCHER BORDEL ET AVZANT CA J'AI ESSAYE 4 AUTRE STESTS
		// JE FOUS RIEN JPP JEN AI MARRE 3 JOURS RIEN DE FAIT BOOOOORDELLLML!
		
		//IQUEZ VOUS
		this.mvc
		.perform(get("/publication/all"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	/**/

	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
	@AfterEach
	void tearDown() throws Exception {
	}

}

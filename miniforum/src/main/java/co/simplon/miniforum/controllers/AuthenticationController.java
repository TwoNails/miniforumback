package co.simplon.miniforum.controllers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.miniforum.JwtUtil;
import co.simplon.miniforum.models.AuthenticationRequest;
import co.simplon.miniforum.models.AuthenticationResponse;
import co.simplon.miniforum.models.ForumUser;
import co.simplon.miniforum.models.ForumUserDTO;
import co.simplon.miniforum.services.ForumUserService;

@RestController
public class AuthenticationController {
	
	@Autowired	
	private AuthenticationManager authenticationManager;
	
	@Autowired	
	private ForumUserService userDetailsService;
	
	@Autowired	
	private ForumUserService forumUserService;
	
	@Autowired	
	private JwtUtil jwtTokenUtil;
	
	@PostMapping("/signup")
	public ResponseEntity<ForumUserDTO> signup(@RequestBody ForumUser forumUser) {
		try {
			forumUserService.addUser(forumUser);
			return new ResponseEntity<ForumUserDTO>(new ForumUserDTO(forumUser), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<ForumUserDTO>(HttpStatus.BAD_REQUEST);	// 400
		}	
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}

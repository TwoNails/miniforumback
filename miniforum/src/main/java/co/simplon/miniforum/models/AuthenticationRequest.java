package co.simplon.miniforum.models;


// Cette classe structure le format d'objet JSON qu'on s'attends a recevoir de la part d'une requete d'authentification
public class AuthenticationRequest {
	
	private String username;
	private String password;
	
	
	// CONSTRUCTORS
	
	public AuthenticationRequest() {
	}
	
	public AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	// GETTERS / SETTERS

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

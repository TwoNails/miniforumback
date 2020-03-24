package co.simplon.miniforum.models;

public class ForumUserDTO {

	private int id;
	private String userName;
	private String role;
	private String signature;
	
	// CONSTRUCTORS 
	
	public ForumUserDTO(ForumUser forumUser) {
		this.id = forumUser.getId();
		this.userName = forumUser.getUserName();
		this.role = forumUser.getRole().name();
		this.signature = forumUser.getSignature();
	}
	
	public ForumUserDTO() {
	}
	
	public ForumUserDTO(int id, String userName, String role, String signature) {
		this.id = id;
		this.userName = userName;
		this.role = role;
		this.signature = signature;
	}
	
	// IMPORTANT OVERRIDES
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForumUserDTO other = (ForumUserDTO) obj;
		if (id != other.id)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ForumUserDTO [id=" + id + ", userName=" + userName + ", role=" + role + ", signature=" + signature
				+ "]";
	}
	
	// GETTERS / SETTERS
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	
	
	
}

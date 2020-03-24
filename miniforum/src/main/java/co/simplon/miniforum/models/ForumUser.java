package co.simplon.miniforum.models;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.simplon.miniforum.models.enums.Role;

@Entity
public class ForumUser implements UserDetails {

	private static final long serialVersionUID = 1L;	// faire une recherche plus tard, a priori nécessaire pour respecter l'interface serializable

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	@Column(name = "USER_NAME", nullable = false)
	private String userName;
	
	//@Column(name = "HASHED_PASSWORD", columnDefinition = "varchar(255)",nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE"/*length = 8, columnDefinition = "varchar(5)"*/, nullable = false)
	private Role role;

	private String signature;
	
	
	// CONSTRUCTORS
	
	public ForumUser() {
	}
	public ForumUser(ForumUserDTO dto) {
		this.id = dto.getId();
		this.userName = dto.getUserName();
		this.signature = dto.getSignature();
		
		if(dto.getRole().equals(Role.USER.name())) {
			this.role = Role.USER;
		} else if (dto.getRole().equals(Role.ADMIN.name())) {
			this.role = Role.ADMIN;
		}
	}
	public ForumUser(int id, String userName, String password, Role role, String signature) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.signature = signature;
	}
	
	// IMPORTANT OVERRIDES

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		ForumUser other = (ForumUser) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
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
		return "ForumUser [userName=" + userName + ", role=" + role + ", signature=" + signature + "]";
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
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

	// USERDETAILS OVERRIDES
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {				// A creuser, ajouter eventuellement une variable, boolean, pour permettre aux admins
														// de bloquer le compte
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}

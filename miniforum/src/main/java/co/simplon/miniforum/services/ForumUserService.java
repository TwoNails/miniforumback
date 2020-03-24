package co.simplon.miniforum.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.simplon.miniforum.models.ForumUser;
import co.simplon.miniforum.models.enums.Role;
import co.simplon.miniforum.repositories.ForumUserRepository;

@Service
public class ForumUserService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ForumUserRepository forumUserRepository;
	
	public ForumUser findById(int id) throws Exception {
		 Optional<ForumUser> optUser = forumUserRepository.findById(id);
			if(optUser.isPresent()) {
				return optUser.get();
			} else {
				throw new Exception("id can't be found in database");
			}
	 }
	
	public ForumUser addUser(ForumUser user) throws Exception {
		Optional<ForumUser> optUser = forumUserRepository.findByUserName(user.getUserName());
		if(optUser.isPresent()) {
			throw new Exception("username not available");
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println("methode addUser : mot de passe enregistré = " + user.getPassword());
			user.setRole(Role.USER);
			return forumUserRepository.saveAndFlush(user);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<ForumUser> optMember = forumUserRepository.findById(1);/* forumUserRepository.findByUserName(userName);*/
		if(optMember.isPresent()) {
			ForumUser memberInbase = optMember.get();
			return memberInbase;
		}
		
		System.out.println("optMember pas trouvé");
		return new User("foo", "foo", new ArrayList<>());	// eventually this will send back data taken from the DB based on the argument		
	}

}

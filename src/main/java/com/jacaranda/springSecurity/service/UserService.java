package com.jacaranda.springSecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jacaranda.springSecurity.exception.ExceptionCredentialNotValid;
import com.jacaranda.springSecurity.model.User;
import com.jacaranda.springSecurity.repository.UserRepository;



@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<User> result =  userRepository.findByUsername(username);
		// Como busco por username aunque la id se la primary key
		// comprueba que no hay dos usuario con el mismo usernam, si lo hay
		// devuelvo error.
		if (result != null && result.size()== 1)
			return result.get(0);
		else
			throw new ExceptionCredentialNotValid("Usuario no encontrado username: " + username);
		
	}
	
	

}

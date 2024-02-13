package com.jacaranda.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jacaranda.springSecurity.dto.user.LoginCredential;
import com.jacaranda.springSecurity.exception.ExceptionCredentialNotValid;
import com.jacaranda.springSecurity.model.User;
import com.jacaranda.springSecurity.utility.TokenUtils;



@Controller
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
     /**
      * singin se usa para loguearse. Se debe comprobar que el usuario y el password existen
      * y son correctas y devolver un token.
      * @param loginRequest: recibe un dto con el username y password que quier loguearse.
      * @return: debe devolver una respuesta con el token
      */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginCredential loginRequest) {

		Authentication authentication;
		//Si el usuario y el password que le paso son los adecuados me 
		// devuele un autentication. Si no lo encuentra, lanza una exception
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	
		} catch (Exception e) {
			throw new ExceptionCredentialNotValid(e.getMessage());
		}
		
		User user = (User)authentication.getPrincipal();
		String jwt = TokenUtils.generateToken(loginRequest.getUsername(), user.getEmail(), user.getRole());

		return ResponseEntity.ok(jwt);
	}

	

}

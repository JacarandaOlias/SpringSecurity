package com.jacaranda.springSecurity.utility;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	
		// Declaramos el token que usaremos para el cifrado y la decodificación.
		// Lo suyo es que este token sea generado de forma aleatoria al empezar la aplicación
		// Podemos poner el valor que queramos
	
		private final static String ACCESS_TOKEN_SECRET = "sksksskskskskiwoapqowlakskiwoaskskskskiwoapqowlapqowla";
		
		// Definimos el tiempo de validez del token en segundos. Mientras estemos en fase de desarrollo
		// podemos definir tiempos largos para que no nos den problema la caducidad del token.
		// Cuando nuestra aplicación esté en explotación deberemos ajustar este tiempo.
		// Estos dos parámetro también se pueden definir el el fichero aplicattion.properties
		
		private final static Long ACCESS_TOKEN_VALIDATY_SECONDS = (long)30*24*60*60; //30 días
	    

		/**
		 * Este método va a generar un token. En el token incluiremos el username, email y role. 
		 * Esto es un ejemplo, en el token podemos almacenar la información que necesite nuestra aplicación, por ejemplo, nombre y apellido 
		 * o lo que consideremos necesario.
		 * @param username: guardaremos el  username dentro del token
		 * @param email: guardaremos el email dentro del token
		 * @param role: guardaremos el role dentro del token. Si puediera tener varios roles, también se podría guardar.
		 * @return: el token que debe empezar por Bearer y un espacio.
		 */
	    public static String generateToken(String username, String email,String role)  {
	    	
	    	// Establecemos la fecha de expiración del token en milisegundos
	    	Date expirationDate = new Date(System.currentTimeMillis()+ ACCESS_TOKEN_VALIDATY_SECONDS*1000);
	    	
	    	// Creamos un mapa para guardar toda la información que queramos guardar en el token
	    	// El username no es necesario guardarlo porque ya va en el token, en el subject.
	    	Map<String,Object> extra = new HashMap<>();
	    	extra.put("email", email);
	    	extra.put("role", role);
	    	
	    	// Construimos el token con el nombre del usuario, la fecha de expiración, la información
	    	// que queremos guardar y el token que vamos a user para encriptarlo.
	        String token =  Jwts.builder()
	        		.subject(username)
	        		.issuedAt(expirationDate)
	        		.claims(extra)
	        		.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
	        		.compact();
	        
	        return "Bearer " + token;
	    }


}

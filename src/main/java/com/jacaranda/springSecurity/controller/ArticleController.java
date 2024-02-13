package com.jacaranda.springSecurity.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.springSecurity.dto.article.ArticleDto;
import com.jacaranda.springSecurity.exception.ExceptionPageNotFound;
import com.jacaranda.springSecurity.service.ArticleService;


@RestController
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
		
	/**
	 * Devuelve una página con el nombre y la descripción de los artículos existentes en esa página
	 * 
	 * @param pageNumber: número de la página. Si no recibe valor muestra la página 1
	 * @param order: campo de la clase Article por el que ordenar. Si no recibe ninguno ordena por codArt
	 * @param asc: Si recibe desc ordena descendentemente, si no ordena ascendente.
	 * @return
	 */
	@GetMapping("/article")
	public ResponseEntity<?> listArticles(@RequestParam("pageNumber") Optional<String> pageNumber, @RequestParam("order")Optional<String> order,
			@RequestParam("asc") Optional<Boolean> asc){
		
		
		Page<ArticleDto> pageArticle = articleService.getArticles(pageNumber.orElse("1"), 20, 
				order.orElse("codArt"), asc.orElse(false) ? "desc":"");
		
		if (pageArticle == null) {
			return ResponseEntity.notFound().build();
		}else if (pageArticle.getContent().size() == 0) {
			throw new ExceptionPageNotFound("Página no encontrada");
		}
		else {
			return ResponseEntity.ok(pageArticle);
		}
	}
	

		


}

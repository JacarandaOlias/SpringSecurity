package com.jacaranda.springSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jacaranda.springSecurity.dto.article.ArticleDto;
import com.jacaranda.springSecurity.exception.ExceptionValueNotRight;
import com.jacaranda.springSecurity.model.Article;
import com.jacaranda.springSecurity.repository.ArticleRepository;



@Service
public class ArticleService {

	@Autowired 
	private ArticleRepository articleRepository;
	
	/**
	 * Devuelvo un página de artículos dto donde sólo aparece el nombre y la descripción
	 * @param numPage: número de la página
	 * @param pageSize: tamaño de la página
	 * @param order: el campo por el que se debe ordenar
	 * @param ad: si el orden es asc o desc. Por defecto se ordena ascendente
	 * @return
	 */
	public Page<ArticleDto> getArticles(String numPage, int pageSize, String order, String ad){
		Pageable pageable = null;
		int numPageInt;
		try {
			numPageInt = Integer.parseInt(numPage);
		} catch (Exception e) {
			throw new ExceptionValueNotRight("El número de la página debe ser un valor entero");
		}
		if (numPageInt <=0) {
			throw new ExceptionValueNotRight("El número de la página debe ser un valor positivo");
		} 
		if (pageSize <=0) {
			throw new ExceptionValueNotRight("El tamaño de la página debe ser un valor positivo");
		}
		if(ad != null && ad.equals("desc")) {
			pageable = PageRequest.of(numPageInt, pageSize,Sort.by(order).descending());
		}else {
			pageable = PageRequest.of(numPageInt, pageSize,Sort.by(order).ascending());
		}
		try {
			Page<Article> pageOfARticle=  articleRepository.findByEliminate(0,pageable);
		} catch (Exception e) {
			throw new ExceptionValueNotRight("El campo por el que el ordenar no existe");
		}
		Page<Article> pageOfARticle=  articleRepository.findByEliminate(0,pageable);
		Page<ArticleDto> result = pageOfARticle.map(a -> new ArticleDto(a.getCodArt(),a.getDescrip()));
		return result;
	}
	
	
	
}

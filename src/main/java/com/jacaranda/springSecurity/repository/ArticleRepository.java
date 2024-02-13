package com.jacaranda.springSecurity.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.springSecurity.model.Article;


public interface ArticleRepository extends JpaRepository<Article, String> {

	public Page<Article> findByEliminate(Integer eliminate,Pageable pageable);
	
	public List<Article> findByCodArtOrDescrip(String codArt,String descrip);

}

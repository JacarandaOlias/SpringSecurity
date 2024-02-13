package com.jacaranda.springSecurity.dto.article;

public class ArticleDto {
	private String codArt;
	private String descrip;
	
	
	
	public ArticleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArticleDto(String codArt, String descrip) {
		super();
		this.codArt = codArt;
		this.descrip = descrip;
	}
	public String getCodArt() {
		return codArt;
	}
	public void setCodArt(String codArt) {
		this.codArt = codArt;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	
	


}

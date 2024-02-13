package com.jacaranda.springSecurity.model;

import java.math.BigDecimal;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "articulos")
public class Article {
	
	@Id
	@Column(name = "codart")
	@NotNull(message = "El codArt es obligatorio")
	@Length(min = 1, max = 8, message = "Error el tamaño el código de artículo debe ser igual o menor que 8")
	private String codArt;
	
	@NotNull(message = "La descripción no puede ser nula")
	@Length(min = 1, max = 40, message = "Error el tamaño de la descripción ser igual o menor que 40")
	private String descrip;
	
	@Digits(integer = 7, fraction = 2, message = "El precio debe tener una entero y como máximo dos decimales")
	@Min(value = 0,message = "El precio debe ser mayor que 0")
	@NotNull(message = "El precio no puede ser nulo")
	@Column(name = "precio")
	private BigDecimal price;

	@NotNull(message = "El stock no puede ser nulo")
	@Min(value = 0,message = "El stock debe ser igual o mayor que 0")
	@Max(value = 9999999 ,message = "El stock máximo es de 9999999")
	private Integer stock;
	
	@NotNull(message = "El stock min no puede ser nulo")
	@Min(value = 0,message = "El stock min debe ser mayor que 0")
	@Max(value = 9999999 ,message = "El stock mínimo máximo es de 9999999")
	@Column(name = "stock_min")
	private Integer stockMin;
	
	@Length(max = 500,message = "El tamaño de la imagen debe ser menor que 500")
	@Column(name = "imagen")
	private String img;

	@Column(name = "eliminado")
	private Integer eliminate;
	
	
	
		


	public String getCodArt() {
		return codArt;
	}

	public void setCodArt(String codArt){
		this.codArt = codArt;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descript){
		this.descrip = descript;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price){
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock){
		this.stock = stock;
	}

	public Integer getStockMin() {
		return stockMin;
	}

	public void setStockMin(Integer stockMin){
		this.stockMin = stockMin;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getEliminate() {
		return eliminate;
	}

	public void setEliminate(Integer eliminate){
		this.eliminate = eliminate;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(codArt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return Objects.equals(codArt, other.codArt);
	}

}

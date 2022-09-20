package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tb_categorias")
public class Categoria {

	@Id
	private int idcategoria	;
	private String descripcion;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

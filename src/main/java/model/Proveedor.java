package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name ="tb_proveedor")
public class Proveedor {

	@Id
	private int idproveedor;
	private String nombre_rs ;
	private String telefono ;
	private String email;
	
	
	
	
	
	
	
	
	
	
	
}

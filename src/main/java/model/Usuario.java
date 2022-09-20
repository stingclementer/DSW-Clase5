package model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

@Entity
@Data
//@AllArgsConstructor //CONSTRUCTOR DE LOMBOK CON TODOS LOS PARAMETROS
//@NoArgsConstructor  //CONSTRUCTOR VACIO SIN PARAMETROS
@Table(name = "tb_usuarios")
public class Usuario {
	
	@Id
	@Column(name = "cod_usua")
	private int codigo; //cod_usua
	
	@Column(name = "nom_usua")
	private String nombre; //nom_usua
	
	@Column(name = "ape_usua")
	private String apellido; //ape_usua
	
	@Column(name = "usr_usua")
	private String usuario; //usr_usua
	
	@Column(name = "cla_usua")
	private String clave; //cla_usua
	
	@Column(name = "fna_usua")
	private String fnhnac; //fna_usua
	
	@Column(name = "idtipo")
	private int tipo; //idtipo
	
	@Column(name = "est_usua")
	private int estado; //est_usua
	
	@ManyToOne
	@JoinColumn(name = "idtipo", insertable = false, updatable = false)
	private Tipo objTipo; //solo servira para obtener informacion de Tipo
	
}

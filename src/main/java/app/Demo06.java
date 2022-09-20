package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo06 {
	//listado de todos los usuarios
	public static void main(String[] args) {
	//obtener la conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//proceso en este caso listado que es una consulta - no lleva transaccion begin()
		//select*from tb_xxxx  pasar a una coleccion List o ArrayList
		//createNamedQuery = ejecuta una consulta asociada a un nombre
		//createNativeQuery - ejecuta sentencias sql --> No apropiado para atributos normalizados
		//createQuery - ejecuta sentencias sql + JPA(entidades) que es JPQL
		List<Usuario> lsUsuarios = em.createQuery("select u from Usuario u",Usuario.class).getResultList();
		
		//mostrar el resultado de la lista
		System.out.println("Listado");
		for(Usuario u : lsUsuarios) {
			System.out.println("Codigo:"+u.getCodigo());
			System.out.println("Nombre y apellido:"+u.getNombre()+" "+u.getApellido());
			System.out.println("Tipo:"+u.getTipo()+"-"+u.getObjTipo().getDescripcion());
			System.out.println("------------------------------------------");
		}
		//cerrar
		em.close();
	}
	
}

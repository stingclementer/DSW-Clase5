package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {

	public static void main(String[] args) {
		// Configurar
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//empieza los procesos (la transaccion)
		em.getTransaction().begin(); // --> registrar, actualizar, eliminar
		
		//proceso --> Actualiza los dato de un usuario
		//Usuario u = new Usuario(20,"Juan","Perez","jperez","12345","2000/05/15",1,1);
		//Usuario u = new Usuario(20,"Juan Carlos","Perez Lima","jperez","54321","2000/05/16",1,1);
		Usuario u = new Usuario();
		u.setCodigo(20);
		u.setNombre("Juan Carlos");
		u.setApellido("Perez Lima");
		u.setUsuario("jperez");
		u.setClave("54321");
		u.setFnhnac("2000/05/16");
		u.setTipo(1);
		u.setEstado(1);
		
		
		em.merge(u);
		//confirmar los procesos
		em.getTransaction().commit(); //confirma los procesos
		
		//cerrar
		em.close();

	}

}

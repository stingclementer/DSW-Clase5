package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(10, 64, 102, 14);
		contentPane.add(lblClave);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(122, 61, 161, 20);
		contentPane.add(txtClave);
		
	}

	
	private JTextField txtClave;
	
	
	void registrar() {
		//LEER LOS CAMPOS
		String usuario = leerUsuario();
		String clave = leerClave();
		//BUSCAR SI EXISTE UN USUARIO SEGUN LOS DATOS INGRESADOS
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//Usuario u = em.find(Usuario.class, ID); //SOLO BUSCA CUANDO SEGUN ID O PK
		try {
			Usuario u = em.createQuery("select u from Usuario u where u.usuario = :xusr and u.clave = :xcla"
					,Usuario.class).setParameter("xusr", usuario).setParameter("xcla", clave).getSingleResult();
			aviso("Bienvenido "+ u.getNombre());
			//MOSTRAR LOS MENSAJES DE EXITO
		} catch (Exception e) {
			// TODO Auto-generated catch block
			aviso("Usuario o Clave incorrecto\n"+e.getMessage());
		}
		
		em.close();
		
	}

	private void aviso(String s) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, s);
	}

	private String leerClave() {
		// TODO Auto-generated method stub
		return txtClave.getText();
	}

	private String leerUsuario() {
		// TODO Auto-generated method stub
		return txtUsuario.getText();
	}
	
	
	
	
	
	
	
	
	
	
}

package app;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import javax.swing.JTable;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable tblSalida;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 63, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 362, 414, 196);
		contentPane.add(scrollPane_1);
		
		tblSalida = new JTable();
		scrollPane_1.setViewportView(tblSalida);
		tblSalida.setModel(modelo);
		modelo.addColumn("Código");
		modelo.addColumn("Producto");
		modelo.addColumn("Stock");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Proveedor");
		
		llenaCombo();
	}

	void llenaCombo() {
		// COMBO 1 CATEGORIAS
		//obtener la conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		List<Categoria> lsCategorias = em.createQuery("select c from Categoria c",Categoria.class).getResultList();
		cboCategorias.addItem("Seleccione...");
		for(Categoria c: lsCategorias) {
			cboCategorias.addItem(c.getDescripcion());
		}
		
		//COMBO 2 PROVEEDOR
		List<Proveedor> lsProveedores = em.createQuery("select p from Proveedor p",Proveedor.class).getResultList();
		cboProveedores.addItem("Seleccione...");
		for(Proveedor p: lsProveedores) {
			cboProveedores.addItem(p.getIdproveedor()+"-"+p.getNombre_rs());
		}
		
		em.clear();
	}

	void registrar() {
		// LEER LOS CAMPOS EL FORMULARIO
		String codigo = leerCodigo();    //txtCodigo.getText();
		String nombre = txtDescripcion.getText();           //CREAR LOS METODOS CON SUS VALIDACIONES
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int categoria = cboCategorias.getSelectedIndex();
		int proveedor = cboProveedores.getSelectedIndex();
		
		//CREAR UN OBJ A GRABAR
		Producto p = new Producto();
		p.setId_prod(codigo);
		p.setDes_prod(nombre);
		p.setPre_prod(precio);
		p.setStk_prod(stock);
		p.setIdcategoria(categoria);
		p.setIdproveedor(proveedor);
		p.setEst_prod(1);                //VALOR FIJO X DEFAULT ---> 1 = TRUE / 0 = FALSE
		
		//GUARAR EL OBJ EN LA TABLA
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			//MOSTRAR LOS MENSAJES DE EXITO O ERROR
			aviso("Producto Registrado...�", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			aviso("Error al registrar Producto...�\n"+e.getMessage(), "Aviso del sistema", JOptionPane.ERROR_MESSAGE);		
		}		
		em.close();
		
	}

	private String leerCodigo() {
		//validaciones
		if(txtCodigo.getText().isEmpty()) {
			aviso("Debe ingresasr un c�digo...�", "Error en campo", JOptionPane.ERROR_MESSAGE);	
			return null;
		}
		return txtCodigo.getText();
	}
	
	private void aviso(String msg, String titulo, int icono) {
		JOptionPane.showMessageDialog(this, msg,titulo,icono);
		
	} 
	
	void listado() {
		// TODO Auto-generated method stub
		//obtener la conexion
				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
				EntityManager em = fabrica.createEntityManager();

				List<Producto> lsProducto = em.createQuery("select p from Producto p",Producto.class).getResultList();
				
				//mostrar el resultado de la lista
				System.out.println("------------------------------------------------------");
				for(Producto p : lsProducto) {
					imprimir("*****************************");
					imprimir("Id Producto : " + p.getId_prod());
					imprimir("Descripci�n : " + p.getDes_prod());
					imprimir("Stock : " + p.getStk_prod());
					imprimir("Precio : " + p.getPre_prod());
					imprimir("Categor�a : " + p.getIdcategoria() + 
							"-" + p.getObjCategoria().getDescripcion());
					imprimir("Estado : " + p.getEst_prod());
					imprimir("Id Proveedor : " + p.getIdproveedor() + 
							"-" + p.getObjProveedor().getNombre_rs());
					//PARA LA TABLA
					
					Object datos[] = {p.getId_prod(), p.getDes_prod(), p.getStk_prod(),
							p.getPre_prod(),
							p.getIdcategoria() + "-" + p.getObjCategoria().getDescripcion(),
							p.getIdproveedor() + "-" + p.getObjProveedor().getNombre_rs()
					};
					
					modelo.addRow(datos);
					
					
					
					
					
					
				}
				//cerrar
				em.close();
		
		
	}
	
	void imprimir (String s) {
		txtSalida.append(s + "\n");
	}
	
	void buscar() {
		// LEER EL CODIGO
		String codigo = leerCodigo();
		
		//OBTENER UN PRODUCTO SEGUN EL CODIGO INGRESADO --> ES UNA CONSULTA
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
		
		//SELECT * FROM tb_xxx where id = xxxxx
		Producto p = em.find(Producto.class, codigo);
		
		//SI EL CODIGO NO EXISTE, MUESTRA UN MENSAJE, SINO MUESTRA LOS DATOS DEL PRODUCTO
		if(p == null) {
			aviso("C�digo NO existe", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);	
		}else {
			txtDescripcion.setText(p.getDes_prod());
			txtStock.setText(p.getStk_prod()+"");
			txtPrecio.setText(p.getPre_prod()+"");
			cboCategorias.setSelectedItem(p.getObjCategoria().getDescripcion());
			cboProveedores.setSelectedItem(p.getIdproveedor()+"-"+p.getObjProveedor().getNombre_rs());
		}
	}
}

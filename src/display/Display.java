package display;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Display extends JFrame implements ActionListener {

	// Varibles
	private JLabel text;
	private JTextField box;
	private JButton boxanalyze;
	private JButton boxsearch;

	// Constructor
	public Display() {
		super();
		configurarVentana();
		inicializarComponentes();
	}

	// Configuracion de la ventana
	private void configurarVentana() {
		// Titulo de la ventana
		this.setTitle("Jar Analyzer");
		// Tamano de la ventana
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Icono de la ventana
		this.setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
	}

	// Inicia los componentes
	private void inicializarComponentes() {
		// Da valor a los componentes
		text = new JLabel();
		box = new JTextField();
		boxanalyze = new JButton();
		boxsearch = new JButton();
		// Configuracion del texto
		text.setText("Insert Jar");
		text.setBounds(122, 50, 55, 25);
		// Configuracion del campo de texto
		box.setBounds(50, 100, 200, 25);
		// Configuracion del boton para analizar
		boxanalyze.setText("Analyze");
		boxanalyze.setBounds(50, 200, 200, 25);
		boxanalyze.addActionListener(this);
		// Configuracion del boton para buscar
		boxsearch.setText("Search");
		boxsearch.setBounds(50, 150, 200, 25);
		boxsearch.addActionListener(this);
		// Anade lo componentes a la ventana
		this.add(text);
		this.add(box);
		this.add(boxanalyze);
		this.add(boxsearch);
	}

	// Acciones de los botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boxanalyze) {
			/*if (box.getText().length() != 0) {
				if (box.getText().substring(box.getText().length() - 3, box.getText().length()).equals("jar")) {
					File file = new File(box.getText());
					if (file.exists()) {*/
						DisplayAnalyzer analyzer = new DisplayAnalyzer(box.getText());
						analyzer.setVisible(true);
						dispose();
					/*} else {
						JOptionPane.showMessageDialog(this, "The entered route is not valid", "Error", 0);
					}
				} else {
					JOptionPane.showMessageDialog(this, "The file entered does not correspond to a JAR","Error", 0);
				}
			} else {
				JOptionPane.showMessageDialog(this, "No route has been entered", "Error", 0);
			}*/
		}
		if (e.getSource() == boxsearch) {
			FileDialog dialogoArchivo;
			dialogoArchivo = new FileDialog(this, "Open Jar", FileDialog.LOAD);
			dialogoArchivo.setVisible(true);
			if (dialogoArchivo.getFile() != null) { /* Validar que se haya Seleccionado un Archivo */
				String path = dialogoArchivo.getDirectory();
				String name = dialogoArchivo.getFile();
				String route = path + name;
				box.setText(route);
			} else
				System.out.println("No Selecciono Archivo");
		}
	}
}

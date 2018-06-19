package GUI;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultDesktopManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Analyzer.Analyzer;
import GraphStructure.Graph;

@SuppressWarnings("serial")
public class Display extends JFrame implements ActionListener {
	
	// Varibles
	private JLabel text;
	private JTextField box;
	private JButton boxanalyze;
	private JButton boxsearch;
	private JLabel iconLabel;
	private Graph graph;

	// Constructor
	public Display() {
		super();
		this.graph = new Graph();
		setUpWindow();
		initializeComponents();
	}

	// Configuracion de la ventana
	private void setUpWindow() {
		// Titulo de la ventana
		this.setTitle("Jar Analyzer");
		// Tamano de la ventana
		this.setSize(315, 335);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Icono de la ventana
		this.setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
	}


	// Inicia los componentes
	private void initializeComponents() {
		// Da valor a los componentes
		text = new JLabel();
		box = new JTextField();
		boxanalyze = new JButton();
		boxsearch = new JButton();
		iconLabel = new JLabel();
		// Configuracion del texto
		text.setText("Insert Jar");
		text.setBounds(122, 90, 55, 25);
		// Configuracion del campo de texto
		box.setBounds(50, 125, 200, 25);
		// Configuracion del boton para analizar
		boxanalyze.setText("Analyze");
		boxanalyze.setBounds(50, 225, 200, 25);
		boxanalyze.addActionListener(this);
		// Configuracion del boton para buscar
		boxsearch.setText("Search");
		boxsearch.setBounds(50, 175, 200, 25);
		boxsearch.addActionListener(this);
		// Configuracion del icono de inicio
		iconLabel.setIcon(new ImageIcon(this.getClass().getResource("/img/start.png")));
		iconLabel.setLocation(0, 0);
		iconLabel.setMaximumSize(new Dimension(50,50));
		iconLabel.setBounds(62, 5, 255, 75);
		// Anade lo componentes a la ventana\
		this.add(text);
		this.add(box);
		this.add(boxanalyze);
		this.add(boxsearch);
		this.add(iconLabel);
	}

	// Acciones de los botones
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boxanalyze) {
			String path = box.getText();
			if (path.length() != 0) {
				if (path.substring(path.length() - 3, path.length()).equals("jar")) {
					File file = new File(path);
					if (file.exists()) {
						try {
							Analyzer analyzer = new Analyzer(path, "JAR");						
							this.graph = analyzer.getGraph();
							
							DisplayAnalyzer analyzerDisp = new DisplayAnalyzer(this.graph);
							dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(this, "The entered route is not valid", "Error", 0);
					}
				} else {
					JOptionPane.showMessageDialog(this, "The file entered does not correspond to a JAR","Error", 0);
				}
			} else {
				JOptionPane.showMessageDialog(this, "No route has been entered", "Error", 0);
			}
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

@SuppressWarnings("serial")
class ImmovableDesktopManager extends DefaultDesktopManager {
    
	public void dragFrame(JComponent f, int newX, int newY)
    {
        if (!"fixed".equals(f.getClientProperty("dragMode")))
            super.dragFrame(f, newX, newY);
    }
}

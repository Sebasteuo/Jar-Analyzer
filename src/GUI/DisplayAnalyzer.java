package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DisplayAnalyzer extends JFrame implements ActionListener {
	private JButton back;
	private String route;

	// Constructor
	public DisplayAnalyzer(String route) {
		super();
		configurarVentana();
		inicializarComponentes();
		this.route = route;
		System.out.println(this.route);
	}

	// Configuracion de la ventana
	private void configurarVentana() {
		// Titulo de la ventana
		this.setTitle("Jar Analyzer");
		// Tamano de la ventana
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		// Elimina los bordes de la ventana
		// this.setUndecorated(true);
		//this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Icono de la ventana
		this.setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
	}

	// Inicia los componentes
	private void inicializarComponentes() {
		// Da valor a los componentes
		back = new JButton();
		// Configuracion del boton
		back.setText("Back");
		back.setBounds(875, 625, 100, 25);
		back.addActionListener(this);
		back.setFocusable(false);
		// Anade lo componentes a la ventana
		this.add(back);
	}

	// Acciones de los botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			/*Display display = new Display();
			display.setVisible(true);
			dispose();*/
		}
	}
}

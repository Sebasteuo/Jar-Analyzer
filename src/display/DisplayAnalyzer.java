package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DisplayAnalyzer extends JFrame implements ActionListener {
	private JButton back;
	private String route;
	private Canvas canvas;

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
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Icono de la ventana
		this.setIconImage(new ImageIcon(getClass().getResource("/img/icon.png")).getImage());
	}

	// Inicia los componentes
	private void inicializarComponentes() {
		// Da valor a los componentes
		back = new JButton();
		canvas = new Canvas();
		// Configuracion del boton
		back.setText("Back");
		back.setBounds(875, 625, 100, 25);
		back.addActionListener(this);
		back.setFocusable(false);
		canvas.setBackground(Color.RED);
		canvas.setBounds(100, 300, 300, 300);
		// Anade lo componentes a la ventana
		this.add(canvas);
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

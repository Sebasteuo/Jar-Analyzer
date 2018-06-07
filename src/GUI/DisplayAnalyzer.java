package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class DisplayAnalyzer extends JFrame implements ActionListener {
	private JButton back;
	private JLabel information;
	private JComboBox<String> menu;
	private String[] nodes;
	private JButton ranking;
	private JButton options;

	// Constructor
	public DisplayAnalyzer() {
		super();
		configurarVentana();
		inicializarComponentes();
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
				information = new JLabel();
				nodes = new String[] { "Option1", "Option2", "Option3", "Option4", "Option5" };
				menu = new JComboBox<String>(nodes);
				ranking = new JButton();
				options = new JButton();
				// Configuracion del boton
				information.setText("<html><body>Grado saliente: <br>" + nodes[0] + "<br>Grado entrante: <br>" + nodes[0]
						+ "<br>Grafo conexo: <br>" + nodes[0] + "</body></html>");
				information.setBounds(752, 50, 250, 210);
				information.setFont(new Font("Algerian", 1, 24));
				back.setText("Back");
				back.setBounds(875, 625, 100, 25);
				back.addActionListener(this);
				back.setFocusable(false);
				ranking.setText("Ranking");
				ranking.setBounds(770, 280, 200, 25);
				ranking.addActionListener(this);
				ranking.setFocusable(false);
				menu.setBounds(770, 20, 200, 25);
				menu.addActionListener(this);
				options.setText("Class Graph");
				options.setBounds(770, 325, 200, 25);
				options.addActionListener(this);
				options.setFocusable(false);
				// Añade lo componentes a la ventana
				this.add(back);
				this.add(information);
				this.add(menu);
				this.add(ranking);
				this.add(options);
	}

	// Acciones de los botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			//Display display = new Display();
			//display.setVisible(true);
			//dispose();
		}
		if (e.getSource() == menu) {
			information.setText("<html><body>Grado saliente: <br>" + menu.getSelectedItem() + "<br>Grado entrante: <br>"
					+ menu.getSelectedItem() + "<br>Grafo conexo: <br>" + menu.getSelectedItem() + "</body></html>");
		}
		if (e.getSource() == ranking) {
			Object[][] rows = { { "1", "", "" }, {"2", "", ""},{"3", "", ""} };
			Object[] cols = { "Posicion", "Cantidad", "Nombre"};
			JTable table = new JTable(rows, cols);
			JOptionPane.showMessageDialog(this, new JScrollPane(table), "Ranking", JOptionPane.PLAIN_MESSAGE);
		}
		if (e.getSource() == options) {
			if (options.getText() == "Class Graph") {
				options.setText("Jar Graph");
			} else {
				options.setText("Class Graph");
			}
		}
	}
}

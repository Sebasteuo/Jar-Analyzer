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

import GraphStructure.Vertex;
import LinkedListStructure.Node;
import LinkedListStructure.SimpleLinkedList;

@SuppressWarnings("serial")
public class DisplayAnalyzer extends JFrame implements ActionListener {
	private JButton back;
	private JLabel information;
	private JComboBox<String> menu;
	private String[] nodes;
	private JButton rankingD;
	private JButton rankingR;
	private JButton options;

	// Constructor
	public DisplayAnalyzer(SimpleLinkedList list) {
		super();
		configurarVentana();
		this.nodes = convertir(list);
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
		// this.setLayout(null);
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
		menu = new JComboBox<String>(nodes);
		rankingD = new JButton();
		rankingR = new JButton();
		options = new JButton();
		// Configuracion del boton
		information.setText("<html><body>Outgoing Grade: <br>" + nodes[0] + "<br>Incoming Grade: <br>" + nodes[0]
				+ "<br>Connected Graph: <br>" + nodes[0] + "</body></html>");
		information.setBounds(752, 50, 250, 210);
		information.setFont(new Font("Algerian", 1, 23));
		back.setText("Back");
		back.setBounds(875, 625, 100, 25);
		back.addActionListener(this);
		back.setFocusable(false);
		rankingD.setText("Dependences Ranking");
		rankingD.setBounds(770, 280, 200, 25);
		rankingD.addActionListener(this);
		rankingD.setFocusable(false);
		rankingR.setText("References Ranking");
		rankingR.setBounds(770, 325, 200, 25);
		rankingR.addActionListener(this);
		rankingR.setFocusable(false);
		menu.setBounds(770, 20, 200, 25);
		menu.addActionListener(this);
		options.setText("Class Graph");
		options.setBounds(770, 370, 200, 25);
		options.addActionListener(this);
		options.setFocusable(false);
		// Añade lo componentes a la ventana
		this.add(back);
		this.add(information);
		this.add(menu);
		this.add(rankingD);
		this.add(rankingR);
		this.add(options);
	}

	// Acciones de los botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			// Display display = new Display();
			// display.setVisible(true);
			// dispose();
		}
		if (e.getSource() == menu) {
			information.setText("<html><body>Outgoing Grade: <br>" + menu.getSelectedItem() + "<br>Incoming Grade: <br>"
					+ menu.getSelectedItem() + "<br>Connected Graph: <br>" + menu.getSelectedItem() + "</body></html>");
		}
		if (e.getSource() == rankingD) {
			Object[][] rows = { { "1", "", "" }, { "2", "", "" }, { "3", "", "" } };
			Object[] cols = { "Position", "Quantity", "Name" };
			JTable table = new JTable(rows, cols);
			JOptionPane.showMessageDialog(this, new JScrollPane(table), "Dependences Ranking",
					JOptionPane.PLAIN_MESSAGE);
		}
		if (e.getSource() == rankingR) {
			Object[][] rows = { { "1", "", "" }, { "2", "", "" }, { "3", "", "" } };
			Object[] cols = { "Position", "Quantity", "Name" };
			JTable table = new JTable(rows, cols);
			JOptionPane.showMessageDialog(this, new JScrollPane(table), "References Ranking",
					JOptionPane.PLAIN_MESSAGE);
		}
		if (e.getSource() == options) {
			if (options.getText() == "Class Graph") {
				options.setText("Jar Graph");
			} else {
				options.setText("Class Graph");
			}
		}
	}

	public String[] convertir(SimpleLinkedList list) {
		String[] array = new String[list.getSize()];
		Node current = list.getFlag();
		int contador = 0;
		while (current != null) {
			array[contador] = ((Vertex)current.getData()).getName();
			current = current.getNext();
			contador++;
		}
		return array;
	}

}

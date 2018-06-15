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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import GraphStructure.Graph;
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
	private Graph graph;

	// Constructor
	public DisplayAnalyzer(Graph graph) {
		super();
		configurarVentana();
		this.nodes = convertir(graph.getGraphVertexList());
		this.graph = graph;
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
		information
				.setText("<html><body>Outgoing Grade: <br>" + graph.getOutputDegree(menu.getSelectedItem().toString())
						+ "<br>Incoming Grade: <br>" + graph.getInputDegree(menu.getSelectedItem().toString())
						+ "<br>Connected Graph: <br>" + nodes[0] + "</body></html>");
		information.setBounds(752, 50, 250, 210);
		information.setFont(new Font("Algerian", 1, 23));
		back.setText("Back");
		back.setBounds(875, 625, 100, 25);
		back.addActionListener(this);
		back.setFocusable(false);
		rankingD.setText("Dependences Ranking");
		rankingD.setBounds(752, 280, 200, 25);
		rankingD.addActionListener(this);
		rankingD.setFocusable(false);
		rankingR.setText("References Ranking");
		rankingR.setBounds(752, 325, 200, 25);
		rankingR.addActionListener(this);
		rankingR.setFocusable(false);
		menu.setBounds(752, 20, 200, 25);
		menu.addActionListener(this);
		options.setText("Class Graph");
		options.setBounds(752, 370, 200, 25);
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
			information.setText(
					"<html><body>Outgoing Grade: <br>" + graph.getOutputDegree(menu.getSelectedItem().toString())
							+ "<br>Incoming Grade: <br>" + graph.getInputDegree(menu.getSelectedItem().toString())
							+ "<br>Connected Graph: <br>" + menu.getSelectedItem() + "</body></html>");
		}
		if (e.getSource() == rankingD) {
			Object[] cols = { "Position", "Name", "Quantity" };
			JTable table = new JTable(graph.getDependencesRanking(), cols);
			table.getTableHeader().setReorderingAllowed(false);
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(tcr);
			table.getColumnModel().getColumn(1).setCellRenderer(tcr);
			table.getColumnModel().getColumn(2).setCellRenderer(tcr);
			table.getColumnModel().getColumn(1).setPreferredWidth(300);
			JOptionPane.showMessageDialog(this, new JScrollPane(table), "Dependences Ranking",
					JOptionPane.PLAIN_MESSAGE);
		}
		if (e.getSource() == rankingR) {
			Object[] cols = { "Position", "Name", "Quantity" };
			JTable table = new JTable(graph.getReferencesRanking(), cols);
			table.getTableHeader().setReorderingAllowed(false);
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(tcr);
			table.getColumnModel().getColumn(1).setCellRenderer(tcr);
			table.getColumnModel().getColumn(2).setCellRenderer(tcr);
			table.getColumnModel().getColumn(1).setPreferredWidth(300);
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
			array[contador] = ((Vertex) current.getData()).getName();
			current = current.getNext();
			contador++;
		}
		return array;
	}

}

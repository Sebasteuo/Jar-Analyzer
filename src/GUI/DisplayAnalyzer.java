package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.bcel.classfile.ClassFormatException;
import org.json.JSONException;

import Analyzer.Analyzer;
import GraphStructure.Graph;
import GraphStructure.Vertex;
import LinkedListStructure.Node;
import LinkedListStructure.SimpleLinkedList;

/**
 * Pantalla donde se muestra el grafo
 * 
 * @author Sebastian Alba
 * @author Randal Mendez
 * @author David Pereira
 * @author Jose Cespedes
 */
@SuppressWarnings("serial")
public class DisplayAnalyzer extends JFrame implements ActionListener {
	private JButton back;
	private JLabel information;
	private JComboBox<String> menu;
	private String[] nodes;
	private JButton rankingD;
	private JButton rankingR;
	private JButton options;
	private JDesktopPane dp;
	private Graph graph;

	/**
	 * Constructor
	 * 
	 * @param graph
	 */
	public DisplayAnalyzer(Graph graph) {
		super();
		setUpWindow();
		this.graph = graph;
		this.nodes = convert(graph.getGraphVertexList());
		initializeComponents();
		this.dp = new JDesktopPane();
		generateGraph("JAR");
	}

	/**
	 * Configuracion de la ventana
	 */
	private void setUpWindow() {
		// Titulo de la ventana
		this.setTitle("Jar Analyzer");
		// Tamano de la ventana
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		// Elimina los bordes de la ventana
		// this.setUndecorated(true);
		// this.setLayout(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Icono de la ventana
		this.setIconImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage());
	}

	/**
	 * Inicia los componentes
	 */
	private void initializeComponents() {
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
						+ "<br>Connected Graph:<br> Weakly: <br>" + validacion(graph.esDebilmenteConexo())
						+ "<br>Strongly<br>" + validacion(graph.esFuertementeConexo()) + "</body></html>");
		information.setBounds(752, 50, 250, 310);
		information.setFont(new Font("Impact", Font.PLAIN, 27));
		back.setText("Back");
		back.setBounds(875, 625, 100, 25);
		back.addActionListener(this);
		back.setFocusable(false);
		rankingD.setText("Dependencies Ranking");
		rankingD.setBounds(752, 370, 200, 25);
		rankingD.addActionListener(this);
		rankingD.setFocusable(false);
		rankingR.setText("References Ranking");
		rankingR.setBounds(752, 415, 200, 25);
		rankingR.addActionListener(this);
		rankingR.setFocusable(false);
		menu.setBounds(752, 20, 200, 25);
		menu.addActionListener(this);
		options.setText("Class Graph");
		options.setBounds(752, 460, 200, 25);
		options.addActionListener(this);
		options.setFocusable(false);
		// A�ade lo componentes a la ventana
		this.add(back);
		this.add(information);
		this.add(menu);
		this.add(rankingD);
		this.add(rankingR);
		this.add(options);
	}

	/**
	 * Acciones de los botones
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			Display display = new Display();
			display.setVisible(true);
			dispose();
		}
		if (e.getSource() == menu) {
			information.setText(
					"<html><body>Outgoing Grade: <br>" + graph.getOutputDegree(menu.getSelectedItem().toString())
							+ "<br>Incoming Grade: <br>" + graph.getInputDegree(menu.getSelectedItem().toString())
							+ "<br>Connected Graph: <br> Weakly: <br>" + validacion(graph.esDebilmenteConexo())
							+ "<br>Strongly<br>" + validacion(graph.esFuertementeConexo()) + "</body></html>");
		}
		if (e.getSource() == rankingD) {
			Object[] cols = { "Position", "Name", "Quantity" };
			JTable table = new JTable(graph.getDependenciesRanking(), cols);
			table.getTableHeader().setReorderingAllowed(false);
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(tcr);
			table.getColumnModel().getColumn(1).setCellRenderer(tcr);
			table.getColumnModel().getColumn(2).setCellRenderer(tcr);
			table.getColumnModel().getColumn(1).setPreferredWidth(300);
			JOptionPane.showMessageDialog(this, new JScrollPane(table), "Dependencies Ranking",
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
				try {
					Analyzer analyzer = new Analyzer(
							graph.getVertex(menu.getSelectedItem().toString()).getData().toString(), "CLASS");
					this.graph = analyzer.getGraph();
					generateGraph("CLASS");
					options.setText("Jar Graph");
				} catch (ClassNotFoundException | JSONException e1) {
					e1.printStackTrace();
				} catch (ClassFormatException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				options.setText("Class Graph");
			}
		}
	}

	/**
	 * Genera el grafo del archivo ingresado
	 */
	public void generateGraph(String choice) {
		dp.setDesktopManager(new ImmovableDesktopManager());
		dp.setBackground(new Color(238, 238, 238));
		this.getContentPane().add(dp);

		JInternalFrame JIF = new JInternalFrame();
		JIF.setBorder(null);
		GraphDraw draw = new GraphDraw(this.graph);
		if (choice.equals("JAR"))
			draw.setPreferredSize(new Dimension(1500, 1000));
		else
			draw.setPreferredSize(new Dimension(2000, 1500));
		JIF.add(draw);
		JScrollPane JSP = new JScrollPane(draw, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JIF.getContentPane().add(JSP);
		JIF.pack();
		JIF.setSize(750, 661);
		JIF.putClientProperty("dragMode", "fixed");
		dp.add(JIF);

		this.setSize(1000, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JIF.setVisible(true);
	}

	/**
	 * Convierte lista enlazada a arreglo
	 * 
	 * @param list
	 * @return
	 */
	private String[] convert(SimpleLinkedList list) {
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

	/**
	 * Convierte un boolean en string
	 * 
	 * @param b
	 * @return
	 */
	private String validacion(boolean b) {
		if (b == true) {
			return "Yes";
		} else {
			return "No";
		}
	}

}

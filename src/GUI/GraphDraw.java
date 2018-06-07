package GUI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JInternalFrame;

import GraphStructure.Edge;
import GraphStructure.Graph;
import GraphStructure.Vertex;
import LinkedListStructure.Node;
import LinkedListStructure.SimpleLinkedList;

@SuppressWarnings("serial")
public class GraphDraw extends JInternalFrame {
    int width;
    int height;

    SimpleLinkedList vertices;
    SimpleLinkedList Edges;

    public GraphDraw(Graph graph) { //Constructor
		vertices = graph.getGraphVertexList();
		Edges = graph.getGraphEdgesList();
		width = 30;
		height = 30;
    }
    
    /*public void addVertex(String name, int coordX, int coordY) { 
		//Anade un nodo con las coordenadas (x,y) indicadas
		vertices.insertEnd(new Vertex(name,coordX,coordY));
		this.repaint();
    }
    
    public void addEdge(Vertex src, Vertex dest) {
		//Anade una arista entre el nodo inicial y el final
		Edges.insertEnd(new Edge(src,dest, 0));
		this.repaint();
    }*/
    
    public void paint(Graphics g) { // Metodo encargado de dibujar los nodos y las aristas
		FontMetrics font = g.getFontMetrics();
		int nodeHeight = 50;
		
		Node current = Edges.getFlag();
		g.setColor(Color.black);
		while(current != null) {
			Edge edge = (Edge) current.getData();
		    g.drawLine(((Vertex) vertices.getData(edge.getSource())).getCoordX(), ((Vertex) vertices.getData(edge.getSource())).getCoordY(),	// Dibuja las aristas desde la posicion inicial x, y indicada
			     ((Vertex) vertices.getData(edge.getDest())).getCoordX(), ((Vertex) vertices.getData(edge.getDest())).getCoordY());			// Hasta la posicicion  final x, y indicada
		    current = current.getNext();
		}
		
		Node current2 = vertices.getFlag();
		while(current2 != null) {
			Vertex vert = (Vertex) current2.getData();
		    int nodeWidth = 50; 
		    g.setColor(Color.lightGray);
		    g.fillOval(vert.getCoordX()-nodeWidth/2, vert.getCoordY()-nodeHeight/2,  // Dibuja el relleno del ovalo en las posiciones  x, y indicadas 
			       nodeWidth, nodeHeight);								   			// y con la altura y ancho indicados (x,y,widht,height)
		    g.setColor(Color.black);
		    g.drawOval(vert.getCoordX()-nodeWidth/2, vert.getCoordY()-nodeHeight/2,  // Dibuja el borde del ovalo en las posiciones  x, y indicadas 
			       nodeWidth, nodeHeight);								   			// y con la altura y ancho indicados (x,y,widht,height)
		    
		    g.drawString(vert.getName(), vert.getCoordX()-font.stringWidth(vert.getName())/2, // Dibuja el String dentro del ovalo
		    	vert.getCoordY()+font.getHeight()/2);
		    current2 = current2.getNext();
		}
    }
}

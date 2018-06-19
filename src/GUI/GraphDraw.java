package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import GraphStructure.Edge;
import GraphStructure.Graph;
import GraphStructure.Vertex;
import LinkedListStructure.Node;
import LinkedListStructure.SimpleLinkedList;
/**
 * Dibuja el grafo
 * @author Sebastian Alba
 * @author Randal Mendez
 * @author David Pereira
 * @author Jose Cespedes
 *
 */
@SuppressWarnings("serial")
public class GraphDraw extends JPanel {
    int width;
    int height;

    SimpleLinkedList vertices;
    SimpleLinkedList Edges;

    public GraphDraw(Graph graph) { //Constructor
		vertices = graph.getGraphVertexList();
		Edges = graph.getGraphEdgesList();
		width = 40;
		height = 40;
    }
    /**
     * Metodo encargado de dibujar los nodos y las aristas
     */
    @Override
    public void paintComponent(Graphics g) { // Metodo encargado de dibujar los nodos y las aristas
    	super.paintComponent(g);
    	
    	g.setFont(new Font("Impact", Font.PLAIN, 15));
		FontMetrics font = g.getFontMetrics();
		
		int nodeHeight = Math.max(height, font.getHeight());
		Node current = Edges.getFlag();
		g.setColor(Color.black);
		while(current != null) {
			Edge edge = (Edge) current.getData();
			Vertex vertSrc = (Vertex) vertices.getData(edge.getSource());
			Vertex vertDest = (Vertex) vertices.getData(edge.getDest());
			
			int VDWidth = Math.max(width, font.stringWidth(vertDest.getName())+width/2);
			
			int srcX = vertSrc.getCoordX(), srcY = vertSrc.getCoordY();
			int destX = vertDest.getCoordX(), destY = vertDest.getCoordY();
			
			if((destY-nodeHeight/2) > 300) destY -= nodeHeight/2;
			if((destY+nodeHeight/2) < 300) destY += nodeHeight/2;
			if((destX-VDWidth/2) > 375) destX -= VDWidth/2;
			if((destX+VDWidth/2) < 375) destX += VDWidth/2;
			
			drawArrowLine(g, srcX, srcY, destX, destY, 10, 10);
			current = current.getNext();
		}
		
		Node current2 = vertices.getFlag();
		while(current2 != null) {
			Vertex vert = (Vertex) current2.getData();
			int nodeWidth = Math.max(width, font.stringWidth(vert.getName())+width/2);  
		    g.setColor(Color.black);
		    g.fillOval(vert.getCoordX()-nodeWidth/2, vert.getCoordY()-nodeHeight/2,  // Dibuja el relleno del ovalo en las posiciones  x, y indicadas 
			       nodeWidth, nodeHeight);								   			// y con la altura y ancho indicados (x,y,widht,height)
		    //g.setColor(new Color(239, 127, 26));
		    //g.drawOval(vert.getCoordX()-nodeWidth/2, vert.getCoordY()-nodeHeight/2,  // Dibuja el borde del ovalo en las posiciones  x, y indicadas 
			       //nodeWidth, nodeHeight);								   			// y con la altura y ancho indicados (x,y,widht,height)
		    g.setColor(Color.white);
		    g.drawString(vert.getName(), vert.getCoordX()-font.stringWidth(vert.getName())/2, // Dibuja el String dentro del ovalo
		    	vert.getCoordY()+font.getHeight()/4);
		    current2 = current2.getNext();
		}
    }
    /**
     * Dibuja la flecha
     * @param g
     * @param srcX
     * @param srcY
     * @param destX
     * @param destY
     * @param width
     * @param height
     */
    private void drawArrowLine(Graphics g, int srcX, int srcY, int destX, int destY, int width, int height) {
        int distX = destX - srcX, distY = destY - srcY;
        double D = Math.sqrt(distX*distX + distY*distY);
        double xm = D - width, xn = xm, ym = height, yn = -height, x;
        double sin = distY / D, cos = distX / D;

        x = xm*cos - ym*sin + srcX;
        ym = xm*sin + ym*cos + srcY;
        xm = x;

        x = xn*cos - yn*sin + srcX;
        yn = xn*sin + yn*cos + srcY;
        xn = x;

        int[] xpoints = {destX, (int) xm, (int) xn};
        int[] ypoints = {destY, (int) ym, (int) yn};

        g.drawLine(srcX, srcY, destX, destY);
        g.fillPolygon(xpoints, ypoints, 3);
    }
}

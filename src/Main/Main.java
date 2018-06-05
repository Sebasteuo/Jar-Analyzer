package Main;

import GraphStructure.Graph;
import GraphStructure.Vertex;

public class Main {

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.setDirected(true);
		//Anade vertices
		Vertex vert1 = new Vertex("E");
		graph.addVertex(vert1);
		Vertex vert2 = new Vertex("A");
		graph.addVertex(vert2);
		Vertex vert3 = new Vertex("B");
		graph.addVertex(vert3);
		Vertex vert4 = new Vertex("C");
		graph.addVertex(vert4);
		Vertex vert5 = new Vertex("D");
		graph.addVertex(vert5);
		//Anade aristas
		graph.addEdges(vert1, vert2, 2, false);
		graph.addEdges(vert2, vert3, 1, false);
		graph.addEdges(vert3, vert4, 8, false);
		graph.addEdges(vert4, vert5, 7, false);
		graph.addEdges(vert5, vert1, 9, false);
		
		graph.breadthFirst(vert1);
		/*graph.printGraph();
		System.out.println("Vertex: " + graph.getNumVert());
		System.out.println("Edges: " + graph.getNumEdges());
		
		//graph.removeVertex(vert3);
		//graph.removeEdge(vert3, vert4);
		
		System.out.println("");
		graph.printGraph();
		System.out.println("Vertex: " + graph.getNumVert());
		System.out.println("Edges: " + graph.getNumEdges());*/
		/*Display display = new Display();
		display.setVisible(true);*/
	}

}

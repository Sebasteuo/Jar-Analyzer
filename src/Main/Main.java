package Main;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.DescendingVisitor;
import org.apache.bcel.classfile.JavaClass;

import Analyzer.DependencyEmitter;
import GUI.Display;
import GraphStructure.Graph;
import GraphStructure.Vertex;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		/*Graph graph = new Graph();
		graph.setDirected(true);
		//Anade vertices
		Vertex vert1 = new Vertex("E", "E", 375, 300);
		graph.addVertex(vert1);
		Vertex vert2 = new Vertex("A", "A", 250, 175);
		graph.addVertex(vert2);
		Vertex vert3 = new Vertex("B", "B", 500, 175);
		graph.addVertex(vert3);
		Vertex vert4 = new Vertex("C", "C", 500, 425);
		graph.addVertex(vert4);
		Vertex vert5 = new Vertex("D", "D", 250, 425);
		graph.addVertex(vert5);
		//Anade aristas
		graph.addEdges(vert1, vert3, 2, false);
		graph.addEdges(vert2, vert3, 1, false);
		graph.addEdges(vert2, vert1, 8, false);
		graph.addEdges(vert3, vert4, 7, false);
		graph.addEdges(vert5, vert2, 7, false);
		graph.addEdges(vert5, vert4, 5, false);
		graph.addEdges(vert1, vert5, 9, false);
		
		//graph.breadthFirst(vert1);
		/*graph.printGraph();
		System.out.println("Vertex: " + graph.getNumVert());
		System.out.println("Edges: " + graph.getNumEdges());		
		String[][] matrix = graph.getReferencesRanking();
		System.out.println("Ranking grado entrante:");
		System.out.println("Pos.   Name   Quant.");
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + matrix[i][j] + "     ");
			}
			System.out.println("");
		}
		
		String[][] matrix2 = graph.getDependencesRanking();
		System.out.println("Ranking grado saliente:");
		System.out.println("Pos.   Name   Quant.");
		for (int i = 0; i < matrix2.length; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + matrix2[i][j] + "     ");
			}
			System.out.println("");
		}
		//graph.removeVertex(vert3);
		//graph.removeEdge(vert3, vert4);
		
		/*System.out.println("");
		graph.printGraph();
		System.out.println("Vertex: " + graph.getNumVert());
		System.out.println("Edges: " + graph.getNumEdges());*/
		Display display = new Display();
		display.setVisible(true);
		
		/*JavaClass javaClass = Repository.lookupClass("GUI.Display");
	    DependencyEmitter visitor = new DependencyEmitter(javaClass);
	    DescendingVisitor classWalker = new DescendingVisitor(javaClass, visitor);
	    classWalker.visit();*/
	}

}
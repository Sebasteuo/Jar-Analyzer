package GraphStructure;

import LinkedListStructure.Node;
import LinkedListStructure.SimpleLinkedList;

public class Vertex {
	private Object data;
	private SimpleLinkedList edges;
	private boolean isVisited;
	
	private String name;
	private int coordX;
	private int coordY;
	
	public Vertex(Object data) {
		this.data = data;
		this.edges = new SimpleLinkedList();
		this.isVisited = false;
		this.setName("");
		this.setCoordX(0);
		this.setCoordY(0);
	}
	
	public Vertex(Object data, String name, int coordX, int coordY) {
		this.data = data;
		this.edges = new SimpleLinkedList();
		this.isVisited = false;
		this.setName(name);
		this.setCoordX(coordX);
		this.setCoordY(coordY);
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object value) {
		this.data = value;
	}
	
	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public void addEdge(Vertex dest, int weight) {
		Edge newEdge = new Edge(this, dest, weight);
		this.edges.insertEnd(newEdge);
	}
	
	public Edge getEdge(Vertex dest) {
		Node current = edges.getFlag();
		while(current != null) {
			Edge edge = (Edge) current.getData();
			if(edge.getDest().equals(dest)) {
				return edge;
			}
			current = current.getNext();
		}
		return null;
	}
	
	public void removeEdge(Edge edge) {
		Node current = edges.getFlag();
		while (current != null) {
			if(current.getData().equals(edge)) {
				edges.deleteNode(edge);
				break;
			}
			current = current.getNext();
		}
	}
}

package GraphStructure;

public class Edge {
	private Vertex src;
	private Vertex dest;
	private int weight;
	
	public Edge(Vertex src, Vertex dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.setWeight(weight);
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getSource() {
		return src;
	}
	
	public Vertex getDest() {
		return dest;
	}
	
}

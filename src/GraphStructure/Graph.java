package GraphStructure;

import LinkedListStructure.Node;
import LinkedListStructure.SimpleLinkedList;

public class Graph {

	private int numVert;
	private int numEdges;
	private boolean isDirected;
	
	private SimpleLinkedList adjList;
	
	public Graph() {
		this.numVert = 0;
		this.numEdges = 0;
		this.adjList = new SimpleLinkedList();
	}
	
	public int getNumVert() {
		return this.numVert;
	}
	
	public void setNumVert(int numVert) {
		this.numVert = numVert;
	}
	
	public int getNumEdges() {
		return this.numEdges;
	}
	
	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}
	
	public boolean isDirected() {
		return isDirected;
	}

	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public void addVertex(Vertex vertex) {
		SimpleLinkedList SLL = new SimpleLinkedList();
		SLL.insertEnd(vertex);
		adjList.insertEnd(SLL);	
		numVert++;
	}
	
	public void addEdges(Vertex src, Vertex dest, int weight, boolean bool) {
		Node current = adjList.getFlag();
		while(current != null) {
			SimpleLinkedList temp = (SimpleLinkedList) current.getData();
			if(temp.getFlag().getData().equals(src) && temp.getData(dest) == null) {
				src.addEdge(dest, weight);
				temp.insertEnd(dest);
				break;
			}
			current = current.getNext();
		}
		if(!isDirected() && !bool) {
			addEdges(dest, src, weight, true);
		}
		numEdges++;
	}
	
	public void removeVertex(Vertex vertex) {
		Node current = adjList.getFlag();
		while(current != null) {
			SimpleLinkedList vert = (SimpleLinkedList) current.getData();
			Vertex toRemove = (Vertex) vert.getData(vertex);
			Vertex head = (Vertex) vert.getFlag().getData();
			if(toRemove != null && toRemove != head) {
				vert.deleteNode(vertex);   				// Elimina la arista que conecta al vertice que se eliminara
				head.removeEdge(head.getEdge(vertex));  // Elimina la arista del registro del vertice que la contenia
				this.numEdges--;
			}
			if(head.equals(vertex)) {
				adjList.deleteNode(vert);
			}
			current = current.getNext();
		}
		this.numVert--;
	}
	
	public void removeEdge(Vertex src, Vertex dest) {
		Node current = adjList.getFlag();
		while(current != null) {
			SimpleLinkedList vertex = (SimpleLinkedList) current.getData();
			Vertex head = (Vertex) vertex.getFlag().getData();
			if(vertex.getData(dest) != null && vertex.getData(dest)!= head) {
				vertex.deleteNode(dest);
				head.removeEdge(head.getEdge(dest));
				this.numEdges--;
			}
			else {
				current = current.getNext();
			}
		}		
	}
	
	public void breadthFirst(Vertex src) {
		// Crea una lista con los nodos no visitados, en un principio ninguno ha sido visitado
		// Por lo tanto, la iguala a la lista de adyacencia.
        SimpleLinkedList visited = this.adjList;
        
        // Crea la cola para el recorrido breadth first
        SimpleLinkedList queue = new SimpleLinkedList();

        // Establece el nodo del principio como visitado y lo agrega a la cola
        ((Vertex) ((SimpleLinkedList) visited.getFlag().getData()).getData(src)).setVisited(true);
        queue.insertEnd(src);
 
        while (queue.getSize() != 0){
            // Saca el vertice de la cola y lo imprime
            src = (Vertex) queue.getFlag().getData();
            queue.deleteNode(src);
            System.out.print(src.getData() +" ");
 
            // Obtiene los vertices siguientes al src que obtuvimos
            // y si este no ha sido visitado lo establece como visitado
            // y lo agrega a la cola
            Node i = (Node) adjList.getFlag();
            while (i != null) {
                Vertex vert =  (Vertex) ((SimpleLinkedList) i.getData()).getFlag().getData();
                if (!vert.isVisited()) {
                    vert.setVisited(true);
                    queue.insertEnd(vert);
                }
                i = i.getNext();
            }
        }
	}
	
    public void printGraph(){
    	Node current = adjList.getFlag();
        while(current != null){
        	Node temp = ((SimpleLinkedList) current.getData()).getFlag();
        	Vertex head = (Vertex) temp.getData();
            System.out.println("Adjacency list of vertex "+ head.getData());
            System.out.print("Head ");
        	while(temp.getNext() != null) {
        		Vertex vert = (Vertex) temp.getNext().getData();
        		System.out.print("--> " + vert.getData());
        		System.out.print("(" + head.getEdge(vert).getWeight() + ")");
        		temp = temp.getNext();
        	}
            System.out.println("\n");
            current = current.getNext();
        }
    }
}
package LinkedListStructure;

public class Node {
    private Node next;
    private Object data;
    
    /**
     * Constructor Node
     * @param data : Dato que almacenara el nodo
     */
    public Node(Object data){
        this.data = data;
    }
    
    /**
     * Constructor Node
     * @param data : Dato que almacenara el nodo
     * @param next : Referencia al siguiente nodo
     */
    public Node(Object data, Node next){
        this.data = data;
        this.next = next;
    }
    
    /**
     * Setter, establece la referencia al nodo siguiente
     * @param next : Nodo siguiente
     */
    public void setNext(Node next){
        this.next = next;
    }
    
    /**
     * Getter, retorna el nodo siguiente
     * @return Node next : Nodo siguiente
     */
    public Node getNext(){
        return this.next;
    }
     /**
      * Setter, establece la nave que almacenara el nodo
      * @param data : Dato que almacena el nodo
      */
    public void setData(Object data){
        this.data = data;
    }
     /**
      * Getter, retorna la nave almacenada en el nodo
      * @return Dato que almacena el nodo
      */
    public Object getData(){
        return this.data;
    }
}


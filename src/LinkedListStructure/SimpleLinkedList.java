package LinkedListStructure;

public class SimpleLinkedList {
	private Node head;
	private int size;
	
    /**
     * Constructor
     * SimpleLinkedList
     */
    public SimpleLinkedList(){
        this.head = null;
        this.size = 0;
    }
   
    public Node getFlag(){
        return this.head;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public Object getData(Object obj){
        Node current = head;
        while(current != null){
            if(current.getData() == obj){
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
    /**
     * Inserta al final
     * @param obj
     */
    public void insertEnd(Object obj){
        Node newNode = new Node(obj, null);
        if(head == null){
            head = newNode;
        }
        else{
            Node current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }
    /**
     * Borra el nodo
     * @param obj
     */
    public void deleteNode(Object obj){
        try{
            Node current = head;
            if(current.getNext() == null && current.getData() == obj){
                head = null; 
                size--;
            }
            else if(current.getNext() != null && current.getData() == obj){
                head = current.getNext();
                current.setNext(null);
                size--;
            }
            else{
                while(current.getNext() != null){
                    if(current.getNext().getData() == obj){
                        current.setNext(current.getNext().getNext());
                        size--;
                    }
                    else{
                        current = current.getNext();
                    }
                }
            }
        }catch(NullPointerException ex){
        }
    }
}

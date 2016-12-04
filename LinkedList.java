public class LinkedList{
    private Node head;
    private Node lastNode;
    private Missile m;
    private int size;
    
    public LinkedList() {
        head = null;
        lastNode = null;
        size = 0;
    }
    
    private boolean isEmpty() {
        if (head == null && lastNode == null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        
        else {
            return size;
        }
    }
    
    public Node head() {
        return head;
    }
    
    public void insert(Missile m) {
        
        //error checking
        if (m == null) {
            return;
        }
        
        //special case for inserting a point for the first time
        else if (isEmpty()) {
            head = new Node(m);
            lastNode = new Node(m);
            size = 1;
        }
        
        
        else {
            Node insert = new Node(m);
            Node current = head;
            
            //go to the end of the linked list
            for (int i = 0; i < size - 1; i++) {
                //System.out.println("insertInOrder");
                current = current.next;
            }
            
            //insert the new point before lastNode
            insert.next = current.next;
            insert.previous = current;
            current.next = insert;
            
            //increment the size of the tour
            size++;
        }
    }
    
    public void moveMissiles() {
        
        
        // Should not draw if there are no missiles to draw
        if (isEmpty()) {
            return;
        }
        
        Node current = head;
        
        while (current != null){
            current.missile.move();
            current = current.next;
        }
    }
    
    // Draws all missiles
    public void draw() {
        
        
        // Should not draw if there are no missiles to draw
        if (isEmpty()) {
            return;
        }
        
        Node current = head;
        
        while (current != null){
            PennDraw.picture(current.missile.xCurrent, 
                             current.missile.yCurrent, "riku.png", 50, 50);
            current = current.next;
        }
    }
    
    
    // Goes throught the linked list and eliminates all missiles being
    // destroyed recursively.
    public void isDestroying(Node n) {
        if (isEmpty()) {
            return;
        }
        
        // Only user missiles can destroy other missles
        if (!n.missile.isEnemy) {
            
            // Only an exloding missile can destory other missiles
            if (n.missile.isExploding) {
                Node current = head;
                
                // Iterates through all nodes to see if n is destroying anything
                while (current != null) {
                    // A missile cannot destroy itself and a user missile 
                    // can not destory other user missiles.
                    if (current != n && current.missile.isEnemy) {
                        // If the enemy missile is within the blast, we delete
                        // it by eliminating it from the linked list
                        if (current.missile.distanceTo(n.missile) < 
                            current.missile.explosionRadius) {
                            current.previous.next = current.next;
                            current.next.previous = current.previous;
                        }
                    }
                    // Advances the iterations checking
                    current = current.next;
                }
            }
        }
        
        // Checks to make sure that the method is not called on a null node
        if (n.next != null) {
        isDestroying(n.next);
        }
    }
}
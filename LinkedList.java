public class LinkedList{
    private Node head;
    private Node lastNode;
    private Missile m;
    private int size;
    private int explosionDuration = 10;
    
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
            head.previous = null;
            head.next = lastNode;
            lastNode.previous = head;
            lastNode.next = null;
            size = 1;
        }
        
        
        else {
            Node insert = new Node(m);
          
            insert.next = lastNode.next;
            insert.previous = lastNode;
            lastNode.next = insert;
            lastNode = insert;
            //increment the size of the tour
            size++;
        }
    }
    
    public void delete (Node m) {

        if (m == null) {
            throw new RuntimeException("Error: invalid node to remove");
        }
        
        // We must check to see if m is the only node
        if (m == head && m == lastNode) {
            head = null;
            lastNode = null;
            // checks to see m it is the last node
        } else if (m.next == null) {
            lastNode = lastNode.previous;
            lastNode.next = null;
            
            // Checks to see if m is the head
        } else if (m.previous == null) {
            this.head = m.next;
            this.head.previous = null;
        } else {
            m.previous.next = m.next;
            m.next.previous = m.previous;
        }
        size--;
    }
    
    public void moveMissiles() {
        
        
        // Should not draw if there are no missiles to draw
        if (isEmpty()) {
            return;
        }
        
        Node current = head;
        
        while (current != null){
            current.missile.move();
            if(current.missile.iterationsExploded > explosionDuration) {
                delete(current);
            }
            
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
                            delete(current);
                            size--;
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
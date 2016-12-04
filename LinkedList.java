public class LinkedList{
    private Node head;
    private Node lastNode;
    private Missile m;
    private int size;
    private int explosionDuration = 100;
    
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
            Node current = head;
            
            //go to the end of the linked list
            while(current.next != null) {
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
        
        // Should not move if there are no missiles to move
        if (isEmpty()) {
            return;
        }
        
        Node current = head;
        
        while (current != null){
            current.missile.move();
            
            // After the missile has finished its explosion duration, it 
            // is removed.
            if (current.missile.iterationsExploded > explosionDuration) {
                /*
                 * Must check to see if the missile being 
                 * destroyed is a the very first or last node
                 */
                
                // Checks to see if current is the only node
                if (current.next == null && current == head) {
                    head = null;
                    lastNode = null;
                }
                // Checks to see if the current node is at the end
                else if (current.next == null) {
                    lastNode = lastNode.previous;
                    lastNode.next = null;
                    
                    // Checks to see if current is the head
                } else if (current.previous == null) {
                    head = current.next;
                    head.previous = null;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }   
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
                            /*
                             * Must check to see if the missile being 
                             * destroyed is a the very first or last node
                             */
                            
                            // Checks to see if current is the only node
                            if (current.next == null && current == head) {
                                head = null;
                                lastNode = null;
                            }
                            // Checks to see if the current node is at the end
                            else if (current.next == null) {
                                lastNode = lastNode.previous;
                                lastNode.next = null;
                                
                                // Checks to see if current is the head
                            } else if (current.previous == null) {
                                head = current.next;
                                head.previous = null;
                            } else {
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
}
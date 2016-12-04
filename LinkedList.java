public class LinkedList{
    private Node head;
    private Node lastNode;
    private Missile m;
    private int size;
    private int explosionDuration = 10;
    public int score = 0;
    
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
    
    public void insertP(Picture p) {
        
        //error checking
        if (p == null) {
            return;
        }
        
        //special case for inserting a point for the first time
        else if (isEmpty()) {
            Node insert = new Node (p);
            head = insert;
            lastNode = insert;
            size = 1;
        }
        
        
        else {
            Node insert = new Node(p);
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
    
    public void delete (Node m) {

        if (m == null) {
            throw new RuntimeException("Error: invalid node to remove");
        }
        
        if (!m.missile.isEnemy){
            double xCoord = m.missile.xEnd;
            double yCoord = m.missile.yEnd;
           explode(2, xCoord, yCoord, "explosion.png", 50, 50);
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
    
    public void moveMissiles(LinkedList background) {
        
        
        // Should not draw if there are no missiles to draw
        if (isEmpty()) {
            return;
        }
        
        Node current = head;
        
        while (current != null){
            current.missile.move();
            if(current.missile.iterationsExploded > explosionDuration) {
                if (current.missile.isEnemy){
                double xEnd = current.missile.xEnd;
                background.removePicture(xEnd);
                }
                //explode draw function
                delete(current);
            }
            
            current = current.next;
        }
    }
    
    public boolean defeat(){
        if ((head.picture.isDeleted == true) && (head.next.picture.isDeleted == true)
                && (head.next.next.picture.isDeleted == true)){
            //System.out.println("True");
          return true;  
        }
        
        //System.out.println("False");
        return false;
       
    }
    
    public void removePicture(double x){
            if ((1.0/5.0) <= x && x <= (2.0/5.0)){
                head.picture.isDeleted = true;
                //System.out.println("Huntsman");
            }
            else if ((2.0/5.0) < x && x <= (3.0/5.0)){
                head.next.picture.isDeleted = true;
                //System.out.println("PennMedicine");
            }
            else if ((3.0/5.0) < x && x <= (4.0/5.0)){
                head.next.next.picture.isDeleted = true;
                //System.out.println("Singh");
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
            if(current.missile.isEnemy){
            PennDraw.picture(current.missile.xCurrent, 
                             current.missile.yCurrent, "missiles.png", 100, 100);
            }
            if(!current.missile.isEnemy){
                PennDraw.picture(current.missile.xCurrent, 
                             current.missile.yCurrent, "defensivemissiles.png", 100, 100);
            }
            current = current.next;
        }
    }
    
     public void drawTo() {
        
        
        // Should not draw if there are no missiles to draw
        if (isEmpty()) {
            return;
        }
        
        Node current = head;
        
        while (current != null){
            if (current.picture.isDeleted == false){
            PennDraw.picture(current.picture.x, 
                             current.picture.y, current.picture.filename,
                             current.picture.width, current.picture.height);
            }
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
                            score+=100;
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
    
    private void explode (int numLevels, double xCenter, double yCenter, 
                          String filename, double width, double height){
        //base case
        if (numLevels <= 0) {
            return;
        }
        
        //call placePicture to place a randomly selected image
        if (numLevels <= 1){
        PennDraw.picture(xCenter, yCenter, filename, width, height);
        }
        
        //modify the x and y coordinates and decrease
        //width and height for each level
        double x1 = xCenter + .05;
        double x2 = xCenter - .05;
        double y1 = yCenter + .05;
        double y2 = yCenter - .05;
        
        //decrease the depth and size
        numLevels--;
        //size /= 2;
        
        //draw upper left picture
        explode(numLevels, x2, y1, filename, width, height);
        //draw upper right picture
        explode(numLevels, x1, y1, filename, width, height);
        //draw lower left
        explode(numLevels, x2, y2, filename, width, height);
        //draw lower right
        explode(numLevels, x1, y2, filename, width, height);
   
    }
   
}
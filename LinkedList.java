/*
 *Partner 1 Name: Evelyn Bailey
 *Partner 1 PennKey: ebail
 *Partner 1 Recitation #: 215
 * 
 *Partner 2 Name: Stephen Eyerly
 *Partner 2 PennKey: seyerly
 *Partner 2 Recitation #: 216
 * 
 * Description: The LinkedList class constructs an empty linked list and holds
 * several functions to insert nodes, remove nodes, and move objects within the
 * linked list.
 * 
 */

public class LinkedList{
    private Node head;
    private Node lastNode;
    private Missile m;
    private int size;
    private int explosionDuration = 10;
    public int score = 0;
    
    /*
     * Name: LinkedList
     * Description: creates an empty linked list
     * Inputs: -
     * Outputs: -
     */
    public LinkedList() {
        head = null;
        lastNode = null;
        size = 0;
    }
    
    /*
     * Name: isEmpty
     * Description: checks if the linked list is empty
     * Inputs: -
     * Outputs: True if empty, false otherwise
     */
    private boolean isEmpty() {
        if (head == null && lastNode == null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /*
     * Name: size
     * Description: keeps track of the size of the linked list for debugging
     * purposes
     * Inputs: -
     * Outputs: int size
     */
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        
        else {
            return size;
        }
    }
    
    /*
     * Name: head
     * Description: returns the head of the linked list for input into
     * isDestroying method
     * Inputs: -
     * Outputs: Node head
     */
    public Node head() {
        return head;
    }
    
    /*
     * Name: insert
     * Description: inserts a missile into the linked list
     * Inputs: Missile m
     * Outputs: -
     */
    public void insert(Missile m) {
        
        //if Missile is null, do nothing
        if (m == null) {
            return;
        }
        //special case for inserting a missile for the first time
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
            //create a new node containing the missile
            Node insert = new Node(m);
            
            //insert the node into the end of the list
            insert.next = lastNode.next;
            insert.previous = lastNode;
            lastNode.next = insert;
            lastNode = insert;
            
            //increment the size of the linked list
            size++;
        }
    }
    
    /*
     * Name: insert
     * Description: inserts a picture into the linked list
     * Inputs: Picture p
     * Outputs: -
     */
    public void insert(Picture p) {
        
        //error checking
        if (p == null) {
            return;
        }
        
        //special case for inserting a picture for the first time
        else if (isEmpty()) {
            Node insert = new Node (p);
            head = insert;
            lastNode = insert;
            size = 1;
        }
        
        
        else {
           //create a new node containing the picture
            Node insert = new Node(p);
            
            //insert the node into the end of the list
            insert.next = lastNode.next;
            insert.previous = lastNode;
            lastNode.next = insert;
            lastNode = insert;
            
            //increment the size of the linked list
            size++;
        }
    }
    
    /*
     * Name: delete
     * Description: removes a node from the linked list
     * Inputs: Node m
     * Outputs: -
     */
    public void delete (Node m) {
        //throw an error if the node is invalid
        if (m == null) {
            throw new RuntimeException("Error: invalid node to remove");
        }
        
        //if the missile to be deleted is a defensive missile, play
        //explosion animation
        
        
        // if m is the only node in the linked list, make the list empty
        if (m == head && m == lastNode) {
            head = null;
            lastNode = null;
        } 
        
        //if m is at the end of the linked list, set lastNode equal to the
        //previous node
        else if (m.next == null) {
            lastNode = lastNode.previous;
            lastNode.next = null;
        } 
        
        //if m is the head, set head to the next node
        else if (m.previous == null) {
            this.head = m.next;
            this.head.previous = null;
        } 
        
        //if m is in the middle of the list, remove the node as normal
        else {
            m.previous.next = m.next;
            m.next.previous = m.previous;
        }
        
        //decrement the size
        size--;
    }
    
     /*
     * Name: moveMissiles
     * Description: advances the animation of each missile within the linked list
     * Inputs: LinkedList background
     * Outputs: -
     */
    public void moveMissiles(LinkedList background) {
        
        
        // does nothing if there are no missiles in the list
        if (isEmpty()) {
            return;
        }
        
        Node current = head;
        
        
        while (current != null){
            //iterate through the linked list and move the missiles
            current.missile.move();
            //if the iterations after a missile has exploded exceeds the
            //explosion duration (defined above), remove node
            if(current.missile.iterationsExploded() > explosionDuration) {
                //if the missile to be deleted is an enemy missile,
                //check to see if it should destroy a city
                if (current.missile.isEnemy()){
                    double xEnd = current.missile.xEnd();
                    //removes a picture from the background linked list
                    background.removePicture(xEnd);
                }
                //remove the missile from the list
                delete(current);
            }
            
            current = current.next;
        }
    }
    
    /*
     * Name: defeat
     * Description: returns true if all the cities have been destroyed
     * Inputs: -
     * Outputs: True if all cities have been destroyed, and false otherwise
     */    
    public boolean defeat(){
        if ((head.picture.isDeleted() == true) && 
            (head.next.picture.isDeleted() == true)
                && (head.next.next.picture.isDeleted() == true)){
            //System.out.println("True");
            return true;  
        }
        
        //System.out.println("False");
        return false;
        
    }
    
    /*
     * Name: removePicture
     * Description: simulates the removal of an image by setting a city's
     * deleted attribute to true if a missile has landed on the city
     * Inputs: double x coordinate of missile
     * Outputs: -
     */ 
    public void removePicture(double x){
        //if a missile lands within the width of the image, the city
        //is marked destroyed
        
        if ((1.0/5.0) <= x && x <= (2.0/5.0)){
            head.picture.setDeletion(true);
            //System.out.println("Huntsman");
        }
        else if ((2.0/5.0) < x && x <= (3.0/5.0)){
            head.next.picture.setDeletion(true);
            //System.out.println("PennMedicine");
        }
        else if ((3.0/5.0) < x && x <= (4.0/5.0)){
            head.next.next.picture.setDeletion(true);
            //System.out.println("Singh");
        }
    }
    
    /*
     * Name: draw
     * Description: draws the missiles in a linked list
     * Inputs: -
     * Outputs: -
     */
    public void draw() {
        
        // does nothing if linked list is empty
        if (isEmpty()) {
            return;
        }
        
        Node current = head;
        
        while (current != null){
            //if the missile is an enemy, draw the enemy missile image
            if(current.missile.isEnemy()){
                PennDraw.picture(current.missile.xCurrent(), 
                                 current.missile.yCurrent(), 
                                 "missiles.png", 100, 100);
            }
            
            //if the missile is defensive, draw the user missile image
            if(!current.missile.isEnemy()){
                PennDraw.picture(current.missile.xCurrent(), 
                                 current.missile.yCurrent(), 
                                 "defensivemissiles.png", 100, 100);
            }
            current = current.next;
        }
    }
    
    /*
     * Name: drawTo
     * Description: draws the pictures in a linked list
     * Inputs: -
     * Outputs: -
     */
    public void drawTo() {
        
        //does nothing if the list is empty
        if (isEmpty()) {
            return;
        }
        
        Node current = head;
        
        while (current != null){
            //only draw the image if the city has not been destroyed
            if (current.picture.isDeleted() == false) {
                PennDraw.picture(current.picture.x(), 
                                 current.picture.y(), 
                                 current.picture.filename(),
                                 current.picture.width(), 
                                 current.picture.height());
            }
            current = current.next;
        }
    }
    
     /*
     * Name: isDestroying
     * Description: Goes through the linked list and eliminates all missiles being
     * destroyed recursively.
     * Inputs: Node n. Takes in head of linked list
     * Outputs: -
     */
  
    public void isDestroying(Node n) {
        
        //do nothing if the list is empty
        if (isEmpty()) {
            return;
        }
        
        // only check through the list if a defensive missile is exploding
        if (!n.missile.isEnemy()) {
            
            // check if the missile is exploding
            if (n.missile.isExploding()) {
                Node current = head;
                
                // if so, iterate through the list to see if the other missiles 
                //are within the explosion radius
                while (current != null) {
                    
                    // The user missile cannot destroy itself or other 
                    // user missiles.
                    if (current != n && current.missile.isEnemy()) {
                        // If the enemy missile is within the blast, delete
                        // it by eliminating it from the linked list
                        if (current.missile.distanceTo(n.missile) < 
                            current.missile.explosionRadius()) {
                            //delete the missile
                            delete(current);
                            size--;
                            
                            //increment the player's score for every missile
                            //destroyed
                            score+=100;
                        }
                    }
                    current = current.next;
                }
            }
        }
        
        // only continue checking through the list if current is not lastNode
        if (n.next != null) {
            isDestroying(n.next);
        }
    }
}
/*
 * Modification of Node class from HW 08
 * 
 * Partner 1 Name: Evelyn Bailey
 * Partner 1 Pennkey: ebail
 * Partner 1 Recitation #: 215
 * 
 * Partner 2 Name: Stephen Eyerly
 * Partner 2 Pennkey: seyerly
 * Partner 2 Recitation #: 216
 * 
 * This is the node class. This class can take either a missile object
 * constructor or a picture object constructor.
 */
public class Node {
    public Node next;
    public Node previous;
    public Missile missile;
    public Picture picture;
    
    // Node constructor which contains a missile
    public Node(Missile missile) {
        next = null;
        previous = null;
        this.missile = missile;
    }
    
    // Node constructor which contains a picture
    public Node(Picture picture) {
        this.next = next;
        this.previous = previous;
        this.picture = picture;
    }
    
    // For texting purposes only
    public static void main(String[]args){
       // Missile nuetron = new Missile (0.02);
       // Node m = new Node(nuetron);

        
    }
}

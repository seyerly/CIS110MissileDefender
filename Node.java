//from Node.java hw08 class
public class Node {
    public Node next;
    public Node previous;
    public Missile missile;
    public Picture picture;
    
    public Node(Missile missile) {
        next = null;
        previous = null;
        this.missile = missile;
    }
    
    public Node(Picture picture) {
        this.next = next;
        this.previous = previous;
        this.picture = picture;
        
    }
    public static void main(String[]args){
        Missile nuetron = new Missile (0.02);
        Node m = new Node(nuetron);

        
    }
}

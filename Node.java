//from Node.java hw08 class

public class Node {
    public Node next;
    public Node previous;
    public Missile missile;
    
    public Node(Missile missile) {
        next = null;
        previous = null;
        this.missile = missile;
    }
    
    public Node(Node next, Node previous, Missile missile) {
        this.next = next;
        this.previous = previous;
        this.missile = missile;
        
    }
    public static void main(String[]args){
        Missile nuetron = new Missile (0.02);
        Node m = new Node(nuetron);

        
    }
}
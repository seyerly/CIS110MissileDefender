/*
 * Partner 1 Name: Evelyn Bailey
 * Partner 1 Pennkey: ebail
 * Partner 1 Recitation #: 215
 * 
 * Partner 2 Name: Stephen Eyerly
 * Partner 2 Pennkey: seyerly
 * Partner 2 Recitation #: 216
 * 
 * This class holds the main method for the game of missile defender. It also
 * contains a method which sets up and stores the background of the game in 
 * a linked list.
 */

public class Picture {
    public double x;
    public double y;
    public String filename;
    public int width;
    public int height;
    public boolean isDeleted = false;
    
    /*
     * Name: Picture
     * Description: constructor for the Picture object to contain background images
     * Inputs: double xPosition, double yPosition, String imagename, int width, int
     * height
     * Outputs: -
     */
    public Picture (double x, double y, String filename, int width, int height) {
        this.x = x;
        this.y = y;
        this.filename = filename;
        this.width = width;
        this.height = height;
    }
}
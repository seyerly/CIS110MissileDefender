public class Picture {
    public double x;
    public double y;
    public String filename;
    public int width;
    public int height;
    public boolean isDeleted = false;
    
    public Picture (double x, double y, String filename, int width, int height) {
        this.x = x;
        this.y = y;
        this.filename = filename;
        this.width = width;
        this.height = height;
    }
}
/*
 *Partner 1 Name: Evelyn Bailey
 *Partner 1 PennKey: ebail
 *Partner 1 Recitation #: 215
 * 
 *Partner 2 Name: Stephen Eyerly
 *Partner 2 PennKey: seyerly
 *Partner 2 Recitation #: 216
 * 
 * Description: The missile class constructs a missile object and has functions
 * to move missiles and find the distance between missiles.
 * 
 */

public class Missile {
    
    // Variables to keep track of the missile's position
    private double xBegin;
    private double yBegin;
    private double xEnd;
    private double yEnd;
    private double xCurrent;
    private double yCurrent;
    private boolean isExploding = false;
    private int iterationsExploded = 0;
    
    // Variables that store the total change in x and y for the missile
    private double dX;
    private double dY;
    
    // This variable stores the move constant. It determines the speed at 
    // which missiles move.
    private double moveConstant;
   
    private boolean isEnemy;
    private double explosionRadius = 0.1;
    
    public double xBegin() {
        return xBegin;
    }
    
    public double yBegin() {
        return yBegin;
    }
    
    public double xEnd() {
        return xEnd;
    }
    
    public double yEnd() {
        return yEnd;
    }
    
    public double xCurrent() {
        return xCurrent;
    }
    
    public double yCurrent() {
        return yCurrent;
    }
    
    public boolean isExploding() {
        return isExploding;
    }
    
    public int iterationsExploded() {
        return iterationsExploded;
    }
    
    public boolean isEnemy() {
        return isEnemy;
    }
    
    public double explosionRadius() {
        return explosionRadius;
    }
    
    /*
     * Name: Missile
     * Description: constructor for defensive missiles
     * Inputs: -
     * Outputs: -
     */
    public Missile(double xBegin, double yBegin, double xEnd,
                   double yEnd, double moveConstant) {
        //the missile is not an enemy missile
        isEnemy = false;
        
        //the current x and y start at the x and y beginning coordinates
        this.xBegin = xBegin;
        xCurrent = xBegin;
        this.yBegin = yBegin;
        yCurrent = yBegin;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.moveConstant = moveConstant;

        this.moveConstant = moveConstant;
        
        double actualDX = xEnd - xBegin;
        
        double actualDY = yEnd - yBegin;
        
        //calculate actual distance to travel
        double distance = Math.sqrt(actualDX * actualDX + 
                                   actualDY * actualDY);
        dX = actualDX / distance;
        dY = actualDY / distance;
        
        
    }
    
    
     /*
     * Name: Missile
     * Description: constructor for enemy missiles
     * Inputs: double moveConstant
     * Outputs: -
     */
    
    public Missile(double moveConstant){
        isEnemy = true;
        
        // random lateral starting position
        xBegin = Math.random();
        
        xCurrent = xBegin;
        
        // Starts at the top
        yBegin = 1;
        
        yCurrent = yBegin;
        
        // random lateral ending position
        xEnd = Math.random();
        
        // ends at the bottom
        yEnd = 0;
        
        this.moveConstant = moveConstant;
        
        //calculations for total distance to travel
        double actualDX = xEnd - xBegin;
        
        double actualDY = yEnd - yBegin;
        
        double distance = Math.sqrt(actualDX * actualDX + 
                                   actualDY * actualDY);
        dX = actualDX / distance;
        dY = actualDY / distance;
        
    }
    
    /*
     * Name: move
     * Description: moves the missiles by updating the position of the
     * missile image
     * Inputs: -
     * Outputs: -
     */
    public void move() {
        //if the missile is not exploding, draw it
        if (!isExploding) {
            xCurrent += dX * moveConstant;
            yCurrent += dY * moveConstant;
        }
        //once the enemy missile is exploding, keep track of how long it has
        //been exploding by incrementing its iterationsExploded value
        if (isEnemy){
            if (yCurrent <= yEnd) {
                isExploding = true;
                iterationsExploded++;
                
            //retrieve x and y coordinates of the exploding missile
            double xCoord = xEnd;
            double yCoord = yEnd;
            
            //animate explosion
            explode(2, xCoord, yCoord, "explosion.png", 50, 50);
            }
        }
        
        //once the defensive missile is exploding, keep track of how long it has
        //been exploding by incrementing its iterationsExploded value
        if (!isEnemy){
            if (yCurrent >= yEnd) {
                isExploding = true;
                iterationsExploded++;
                //retrieve x and y coordinates of the exploding missile
            double xCoord = xEnd;
            double yCoord = yEnd;
            
            //animate explosion
            explode(2, xCoord, yCoord, "explosion.png", 50, 50);
            }
        }
    }

    /*
     * Name: distanceTo
     * Description: finds the distance between two missiles
     * Inputs: Missile j
     * Outputs: double distance
     */
    public double distanceTo(Missile j) {
        double deltaX = j.xCurrent - this.xCurrent;
        double deltaY = j.yCurrent - this.yCurrent;
        double d = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return d;
    }
    
    /*
     * Name: explode
     * Description: animations the explosion of a missile
     * Inputs: int numLevels, double xCenter, double yCenter, String filenae,
     * double width, double height
     * Outputs: -
     */
    private void explode (int numLevels, double xCenter, double yCenter, 
                          String filename, double width, double height){
        //base case
        if (numLevels <= 0) {
            return;
        }
        
        //draw the image only after the x and y coordinates have been shifted
        if (numLevels <= 1){
            PennDraw.picture(xCenter, yCenter, filename, width, height);
        }
        
        //modify the x and y coordinates for placement
        double x1 = xCenter + .05;
        double x2 = xCenter - .05;
        double y1 = yCenter + .05;
        double y2 = yCenter - .05;
        
        //decrease the depth
        numLevels--;
        
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
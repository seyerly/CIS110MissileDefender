public class Missile {
    
    // These are variables for the position of the missile
    public double xBegin;
    public double yBegin;
    public double xEnd;
    public double yEnd;
    public double xCurrent;
    public double yCurrent;
    public boolean isExploding = false; // missile starts out not exploded.
    public int iterationsExploded = 0;
    
    // These variable are the total change in x and y for the missile
    public double dX;
    public double dY;
    
    // This variable stores the move constant. It determines the speed at 
    // which missiles move.
    public double moveConstant;
    public boolean isEnemy;
    
    public double explosionRadius = 0.05;
    
    public Missile(double xBegin, double yBegin, double xEnd,
                   double yEnd, double moveConstant) {
        isEnemy = false;
        this.xBegin = xBegin;
        xCurrent = xBegin;
        this.yBegin = yBegin;
        yCurrent = yBegin;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.moveConstant = moveConstant;
        dX = xEnd - xBegin;
        dY = yEnd - yBegin;
        
        
    }
    
    /*
     * This is the constructor for an enemy missile. It is simply is constructed
     * with one argument, the moveConstat which is analogous to speed. 
     * Enemy missiles start at the top of the screen and move downward towards
     * the bottom. The lateral start and ending positions of the enemy missiles
     * however are random.
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
        
        dX = xEnd - xBegin;
        
        dY = yEnd - yBegin;
        
    }
    
    public void move() {
        if (!isExploding) {
            xCurrent += dX * moveConstant;
            yCurrent += dY * moveConstant;
        }
        if (isEnemy){
            if (yCurrent <= yEnd) {
                isExploding = true;
                iterationsExploded++;
            }
        }
        if (!isEnemy){
            if (yCurrent >= yEnd) {
                isExploding = true;
                iterationsExploded++;
            }
        }
    }

    public double distanceTo(Missile j) {
        double deltaX = j.xCurrent - this.xCurrent;
        double deltaY = j.yCurrent - this.yCurrent;
        double d = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        return d;
    }
    
    public static void main(String[] args) {
        PennDraw.enableAnimation(60);
        LinkedList missilesGalore = new LinkedList();
        
        
        while(true){
            PennDraw.clear();
            if (Math.random() < 0.05) {
                missilesGalore.insert(new Missile(0.02));
            }
            missilesGalore.draw();
            missilesGalore.moveMissiles();
            PennDraw.advance();
        }
    }
}
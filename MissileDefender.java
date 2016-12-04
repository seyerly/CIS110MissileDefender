public class MissileDefender {
    public static void main(String[] args) {
        final double leftSiloX = 0.1;
        final double rightSiloX = 0.9;
        final double speedConstant = 0.02;
        
        PennDraw.enableAnimation(23);
        LinkedList missilesGalore = new LinkedList();
        
        
        while(true){
            PennDraw.clear();
            if (Math.random() < 0.05) {
                missilesGalore.insert(new Missile(speedConstant));
            }
            
            if (PennDraw.mousePressed()) {
                double xCoordinate = PennDraw.mouseX();
                double yCoordinate = PennDraw.mouseY();
                if (xCoordinate < 0.5) {
                missilesGalore.insert(new Missile(leftSiloX, 0, xCoordinate,
                                                  yCoordinate, speedConstant));
            }
                else { 
                     missilesGalore.insert(new Missile(rightSiloX, 0,
                                                   xCoordinate, yCoordinate,
                                                        speedConstant));
                }
            }
            
            missilesGalore.moveMissiles();
            missilesGalore.isDestroying(missilesGalore.head());
            missilesGalore.draw();
            PennDraw.advance();
        }
    }
}
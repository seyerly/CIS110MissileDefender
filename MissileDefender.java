public class MissileDefender {
    public static void main(String[] args) {
        final double leftSiloX = 0.1;
        final double rightSiloX = 0.9;
        final double enemySpeedConstant = 0.01;
        final double userSpeedConstant = 0.05;
        
        PennDraw.enableAnimation(23);
        LinkedList missilesGalore = new LinkedList();
        
        
        while(true){
            PennDraw.clear();
            if (Math.random() < 0.03) {
                missilesGalore.insert(new Missile(enemySpeedConstant));
            }
            
            if (PennDraw.mousePressed()) {
                double xCoordinate = PennDraw.mouseX();
                double yCoordinate = PennDraw.mouseY();
                if (xCoordinate < 0.5) {
                missilesGalore.insert(new Missile(leftSiloX, 0, xCoordinate,
                                                  yCoordinate, 
                                                  userSpeedConstant));
            }
                else { 
                     missilesGalore.insert(new Missile(rightSiloX, 0,
                                                   xCoordinate, yCoordinate,
                                                        userSpeedConstant));
                }
            }
            
            missilesGalore.moveMissiles();
            missilesGalore.isDestroying(missilesGalore.head());
            missilesGalore.draw();
            PennDraw.advance();
            System.out.println(Math.random());
        }
    }
}
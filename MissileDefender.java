public class MissileDefender {
    
     public static void setUpCities(LinkedList background){
        double x = 0.29;
        double y = 0.06;
        Picture b = new Picture(x, y, "Huntsman-Hall.png", 100, 100);
        background.insertP(b);
        x+= .2;
        Picture c = new Picture(x, y, "PennMedicine.png", 100, 100);
        background.insertP(c);
        x+= .2;
        Picture d = new Picture(x, y - .01, "Center_for_Nanotechnology18.png", 100, 100);
        background.insertP(d);
        x+= .2;
    }
     
    public static void main(String[] args) {
        final double leftSiloX = 0.1;
        final double rightSiloX = 0.9;
        final double enemySpeedConstant = 0.01;
        final double userSpeedConstant = 0.05;
        
        
        
        PennDraw.enableAnimation(100);
        LinkedList missilesGalore = new LinkedList();
        LinkedList background = new LinkedList();
        setUpCities(background);
        
        while(true){
            PennDraw.clear();
            PennDraw.picture(0.5, 0.5, "images-1.jpeg", 512, 512);
            PennDraw.picture(0.09, 0.06, "canon1.png", 100, 100);
            PennDraw.picture(0.89, 0.06, "canon2.png", 100, 100);
            background.drawTo();
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
            
            missilesGalore.moveMissiles(background);
            missilesGalore.isDestroying(missilesGalore.head());
            missilesGalore.draw();
            PennDraw.advance();
        }
    }
}
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
public class MissileDefender {
    
     // This method takes a linked list and places the background images into
     // the linked list of pictures.
     public static void setUpCities(LinkedList background){
        double x = 0.29;
        double y = 0.06;
        Picture b = new Picture(x, y, "Huntsman-Hall.png", 100, 100);
        background.insert(b);
        x+= .2;
        Picture c = new Picture(x, y, "PennMedicine.png", 100, 100);
        background.insert(c);
        x+= .2;
        Picture d = new Picture(x, y - .01, 
                                "Center_for_Nanotechnology18.png", 100, 100);
        background.insert(d);
        x+= .2;
    }
     
    // This is the main method in which the game runs
    public static void main(String[] args) {
        
        // The location of the left missile silo
        final double leftSiloX = 0.1;
        
        // The location of the right missile silo
        final double rightSiloX = 0.9;
        
        // How fast the enemy missiles move
        final double enemySpeedConstant = 0.01;
        
        // How fast the user missiles move
        final double userSpeedConstant = 0.05;
        
        /*
         * The difficulty at any given moment. The larger this constant is,
         * the more likely at any given moment an enemy missile will appear.
         */
        double difficultyConstant = 0.02;
        
        // Set's the background for the intro to black
        PennDraw.clear(PennDraw.BLACK);
        
        // Set's the font to black
        PennDraw.setPenColor(PennDraw.GREEN);
        
        // Set's the font size
        PennDraw.setFontSize(20);
        
        // While loop for the beginning
        while(!PennDraw.hasNextKeyTyped()) {
        PennDraw.text(0.5, 0.9, "Fire your missiles by clicking. You must");
        PennDraw.text(0.5, 0.8, "destroy all enemy missiles before they" );
        PennDraw.text(0.5, 0.7, "destroy your buildings. Once your buildings");
        PennDraw.text(0.5, 0.6, "are gone, the game is over. You gain points");
        PennDraw.text(0.5, 0.5, "as time passes and lose points with every");
        PennDraw.text(0.5, 0.4, "missile you fire, so don't strafe like");
        PennDraw.text(0.5, 0.3, "a noob. Good Luck!");
        PennDraw.text(0.5, 0.1, "Press any key to continue");
        }
        
        PennDraw.enableAnimation(23);
        
        // The linked list object that holds the missiles
        LinkedList missilesGalore = new LinkedList();
        
        // The linked list which holds the buildings
        LinkedList background = new LinkedList();
        
        // Adds the buildings to the linked list the three buildings
        setUpCities(background);
        
        // For the score counter in the game
        PennDraw.setFontSize(20);
        
        // The font for the counter in the game
        PennDraw.setPenColor(PennDraw.BLACK);
        
        // The user keeps playing until all of the civilian buildings are 
        // gone. 
        while(!background.defeat()){
            
            // Clears the background before drawing anything new
            PennDraw.clear();
            
            // Draws the main backgroun (bucolic pasture)
            PennDraw.picture(0.5, 0.5, "images-1.jpeg", 512, 512);
            
            // Draws the left cannon
            PennDraw.picture(0.09, 0.06, "canon1.png", 100, 100);
            
            // Draws the right cannon
            PennDraw.picture(0.89, 0.06, "canon2.png", 100, 100);
            
            // Draws the buildings
            background.drawTo();
            
            /* If, by random chance, the randomly generated double is less`
             * than the difficulty constant, an enemy missile is generated.
             * The difficulty constant increases as time goes 
             */
            if (Math.random() < difficultyConstant) {
                missilesGalore.insert(new Missile(enemySpeedConstant));
            }
            
            // If the mouse was pressed, the user has fire a missile
            if (PennDraw.mousePressed()) {
                
                // Firing missiles costs you points
                missilesGalore.addToScore(-25);
                PennDraw.setPenColor(PennDraw.RED);
                PennDraw.text(0.15, 0.85, "-25");
                PennDraw.setPenColor(PennDraw.BLACK);
                
                double xCoordinate = PennDraw.mouseX();
                double yCoordinate = PennDraw.mouseY();
                
                // If the user clicked on the left side of the screen, the 
                // closest silo will fire the missile (the left silo). 
                // Otherwise, the right silo will fire the missile.
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
            
            // Updates the background and removes buildings if destoryed
            missilesGalore.moveMissiles(background);
            
            // Goes through and deletes enemy missiles which are being 
            // destoryed by a user missile
            missilesGalore.isDestroying(missilesGalore.head());
            
            // Draws the missiles
            missilesGalore.draw();
            
            // Displays the score
            PennDraw.text(0.1, 0.9, "Score: " + missilesGalore.score());
            
            // Advances the animation
            PennDraw.advance();
            
            // As time passes, you gain points
            missilesGalore.addToScore(1);;
            
            // The difficulty increases as time goes on 
            difficultyConstant += 0.00003;
        }
        // Must disable the animation before you can redraw
        PennDraw.disableAnimation();
        
        // Clears the background
        PennDraw.clear();
        
        // Sets the font to black
        PennDraw.setPenColor(PennDraw.BLACK);
        
        // Redraws the background
        PennDraw.picture(0.5, 0.5, "images-1.jpeg", 512, 512);
        PennDraw.picture(0.09, 0.06, "canon1.png", 100, 100);
        PennDraw.picture(0.89, 0.06, "canon2.png", 100, 100);
        
        // Set's the font size
        PennDraw.setFontSize(50);
        
        // Indicates that the game is over
        PennDraw.text(0.5, 0.8, "Game Over!");
        
        // Displays the score
        PennDraw.text(0.5, 0.7, "Score: " + missilesGalore.score());
    }
}
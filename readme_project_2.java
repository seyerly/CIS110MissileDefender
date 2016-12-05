    Instructions: Open all Java files, compile them, and run the file 
        MissileDefender.java. Follow the in game instructions, and have fun!
        
    Implementation:
        We created two types of objects, Missile and Picture. The Missile object
        stored the beginning, ending, and current x coordinates of the missile.
        There were two constructors for enemy missiles and user missiles.
        The Picture object stores the images of the three buildings that could
        be destroyed by the enemy missiles.
        
        We created two LinkedLists, one to store the missile objects and another
        to store the picture objects. Enemy missiles are created and inserted into
        the list at random every iteration of the animation loop. They are removed
        when they are destroyed by the user missiles or when they reach the bottom 
        of the screen. User missiles are inserted into the list when the user clicks
        on the screen to fire them. They are removed when the missile reaches where
        the user clicked. Once the user missile reaches its destination, enemy missiles
        are removed if they are too close to the explosion of the user missile. The
        picture linked list stores the city images and stops drawing them when an 
        enemy missile lands on a city.
        
        We used recursion to iterate through the linked list of missiles when checking
        to see if a user missile should destroy an enemy missile. For the explosion
        animation, we also used recursion to simulate the explosion of the missiles.
        
        
        

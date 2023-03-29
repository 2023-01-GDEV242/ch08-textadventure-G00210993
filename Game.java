 /**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room item;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        createItems();
    }

    /**
     * Create all the towns and link their neighboring towns together.
     */
    private void createRooms()
    { 
        Room palletTown, viridianCity, victoryRoad, indigoPlateau, viridianForest, pewterCity, mtMoon,
        ceruleanCity, billsHouse, rockTunnel, saffronCity, vermillionCity, lavenderTown, celadonCity, 
        bikeRoute, fuschiaCity, cinnabarIsland; 
      
        // create locations
        palletTown = new Room("in pallet town");
        viridianCity = new Room("in viridian city");
        victoryRoad = new Room("at victory road");
        indigoPlateau = new Room("at the indigo plateau");
        viridianForest = new Room("in viridian forest");
        pewterCity = new Room("in pewter city");
        mtMoon = new Room("in mt moon");
        ceruleanCity = new Room("in cerulean city");
        billsHouse = new Room("in bills house");
        rockTunnel = new Room("in rock tunnel");
        saffronCity = new Room("in saffron city");
        vermillionCity = new Room("in vermillion city");
        lavenderTown = new Room("in lavender town");
        celadonCity = new Room("in celadon city");
        bikeRoute = new Room("on bike route");
        fuschiaCity = new Room("in fuschia city");
        cinnabarIsland = new Room("on cinnabar island");
        
        // initialize neighboring towns
        palletTown.setExit("north", viridianCity);
        palletTown.setExit("south", cinnabarIsland);

        viridianCity.setExit("north", viridianForest);
        viridianCity.setExit("south", palletTown);
        viridianCity.setExit("west", victoryRoad);

        victoryRoad.setExit("north", indigoPlateau);
        victoryRoad.setExit("south", viridianCity);
        
        indigoPlateau.setExit("south", victoryRoad);

        viridianForest.setExit("north", pewterCity);
        viridianForest.setExit("south", viridianCity);

        pewterCity.setExit("east", mtMoon);
        pewterCity.setExit("south", viridianCity);
        
        mtMoon.setExit("east", ceruleanCity);
        mtMoon.setExit("west", pewterCity);
        
        ceruleanCity.setExit("north", billsHouse);
        ceruleanCity.setExit("east", rockTunnel);
        ceruleanCity.setExit("south", saffronCity);
        ceruleanCity.setExit("west", ceruleanCity);
        
        billsHouse.setExit("south", ceruleanCity);
        
        rockTunnel.setExit("west", ceruleanCity);
        rockTunnel.setExit("south", lavenderTown);
        
        saffronCity.setExit("north", ceruleanCity);
        saffronCity.setExit("east", lavenderTown);
        saffronCity.setExit("south", vermillionCity);
        saffronCity.setExit("west", celadonCity);
        
        vermillionCity.setExit("north", saffronCity);
        //vermillionCity.setExit("east", );
        
        lavenderTown.setExit("north", rockTunnel);
        //lavenderTown.setExit("south", );
        lavenderTown.setExit("west", saffronCity);
        
        celadonCity.setExit("east", saffronCity);
        celadonCity.setExit("west", bikeRoute);
        
        bikeRoute.setExit("east", celadonCity);
        bikeRoute.setExit("south", fuschiaCity);
        
        //fuschiaCity.setExit("east", );
        fuschiaCity.setExit("south", cinnabarIsland);
        fuschiaCity.setExit("west", bikeRoute);
        
        cinnabarIsland.setExit("north", palletTown);
        cinnabarIsland.setExit("east", fuschiaCity);
        
        currentRoom = palletTown;  // start game in pallet town
    }

    /**
     * Create all the items.
     */
    private void createItems()
    { 
        Item pokeBall, pokeFlute; 
      
        // create items
        pokeBall = new Item("Poke Ball", "A tool for catching wild Pokémon.", 10);
        pokeFlute = new Item("Poke Flute", "A sweet-sounding flute that awakens Pokémon.", 5);
        
        // initialize items
        
        
    }    

    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Pokemon!");
        System.out.println("You are now in the Kanto Region.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case BAG:
                printBag();
                break;
                
            case MAP:
                printMap();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current location.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Print out which items are in your bag and how much they weigh.
     */
    private void printBag() {
        System.out.println("Items:");
        parser.showCommands();
    }
    
    /**
     * Print out current town and neighboring towns 
     */
    private void printMap() {
        System.out.println("Current Location: " + currentRoom.getLongDescription());
        System.out.println("North: " + "East: " + "South: " + "West: ");
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

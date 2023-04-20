import java.util.HashMap;

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
    private Room prevRoom;
    private int item = 0;
    private int maxWeight = 6;
    private boolean finished = false;
    HashMap<String, String> bag = new HashMap<String, String>();
    
    private Room palletTown1;
    private Room viridianCity1;
    private Room victoryRoad1;
    private Room indigoPlateau1;
    private Room viridianForest1;
    private Room pewterCity1;
    private Room mtMoon1;
    private Room ceruleanCity1;
    private Room billsHouse1;
    private Room rockTunnel1;
    private Room saffronCity1;
    private Room vermillionCity1;
    private Room lavenderTown1;
    private Room celadonCity1;
    private Room bikeRoute1;
    private Room fuschiaCity1;
    private Room routeTwelve1;
    private Room cinnabarIsland1;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the towns and link their neighboring towns together.
     */
    private void createRooms()
    { 
        Room palletTown, viridianCity, victoryRoad, indigoPlateau, viridianForest, pewterCity, mtMoon,
        ceruleanCity, billsHouse, rockTunnel, saffronCity, vermillionCity, lavenderTown, celadonCity, 
        bikeRoute, fuschiaCity, routeTwelve, cinnabarIsland; 
      
        Item pokeBall, pokeFlute;

        // create locations
        palletTown = new Room("in pallet town" + "\nMom is waiting for you outside home.");
        viridianCity = new Room("in viridian city");
        victoryRoad = new Room("at victory road");
        indigoPlateau = new Room("at the indigo plateau" + "\nYou found Caio!");
        viridianForest = new Room("in viridian forest");
        pewterCity = new Room("in pewter city");
        mtMoon = new Room("in mt moon");
        ceruleanCity = new Room("in cerulean city");
        billsHouse = new Room("outside bills house" + "\nIt looks like Bill is home");
        rockTunnel = new Room("in rock tunnel");
        saffronCity = new Room("in saffron city");
        vermillionCity = new Room("in vermillion city");
        lavenderTown = new Room("in lavender town");
        celadonCity = new Room("in celadon city");
        bikeRoute = new Room("on bike route");
        fuschiaCity = new Room("in fuschia city");
        routeTwelve = new Room("on route 12");
        cinnabarIsland = new Room("on cinnabar island");
        
        // create items
        pokeBall = new Item("PokeBall", 1);
        pokeFlute = new Item("PokeFlute", 2);
        
        
        // initialize neighboring towns and items
        palletTown.setExit("north", viridianCity);
        palletTown.setExit("south", cinnabarIsland);
        palletTown.setItem(pokeBall);
        
        viridianCity.setExit("north", viridianForest);
        viridianCity.setExit("south", palletTown);
        viridianCity.setExit("west", victoryRoad);
        viridianCity.setItem(pokeBall);
        
        victoryRoad.setExit("north", indigoPlateau);
        victoryRoad.setExit("south", viridianCity);
        
        indigoPlateau.setExit("south", victoryRoad);

        viridianForest.setExit("north", pewterCity);
        viridianForest.setExit("south", viridianCity);
        viridianForest.setItem(pokeBall);
        
        pewterCity.setExit("east", mtMoon);
        pewterCity.setExit("south", viridianCity);
        pewterCity.setItem(pokeBall);
        
        mtMoon.setExit("east", ceruleanCity);
        mtMoon.setExit("west", pewterCity);
        mtMoon.setItem(pokeBall);
        
        ceruleanCity.setExit("north", billsHouse);
        ceruleanCity.setExit("east", rockTunnel);
        ceruleanCity.setExit("south", saffronCity);
        ceruleanCity.setExit("west", ceruleanCity);
        ceruleanCity.setItem(pokeBall);
        
        billsHouse.setExit("south", ceruleanCity);
        billsHouse.setItem(pokeBall);
        
        rockTunnel.setExit("west", ceruleanCity);
        rockTunnel.setExit("south", lavenderTown);
        rockTunnel.setItem(pokeBall);
        
        saffronCity.setExit("north", ceruleanCity);
        saffronCity.setExit("east", lavenderTown);
        saffronCity.setExit("south", vermillionCity);
        saffronCity.setExit("west", celadonCity);
        
        vermillionCity.setExit("north", saffronCity);
        vermillionCity.setExit("east", routeTwelve);
        
        lavenderTown.setExit("north", rockTunnel);
        lavenderTown.setExit("south", routeTwelve);
        lavenderTown.setExit("west", saffronCity);
        lavenderTown.setItem(pokeFlute);
        
        celadonCity.setExit("east", saffronCity);
        celadonCity.setExit("west", bikeRoute);
        
        bikeRoute.setExit("east", celadonCity);
        bikeRoute.setExit("south", fuschiaCity);
        
        fuschiaCity.setExit("north", routeTwelve);
        fuschiaCity.setExit("south", cinnabarIsland);
        fuschiaCity.setExit("west", bikeRoute);
        
        routeTwelve.setExit("north", lavenderTown);
        routeTwelve.setExit("south", fuschiaCity);
        routeTwelve.setExit("west", vermillionCity);
        
        cinnabarIsland.setExit("north", palletTown);
        cinnabarIsland.setExit("east", fuschiaCity);
        
        // declare towns
        palletTown1 = palletTown;
        
        currentRoom = palletTown;  // start game in pallet town
    }  

    
    /**
     * Main play routine. Loops until end of play.
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
        System.out.println("Thank you for playing, good bye!");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Pokemon!");
        System.out.println("You have recently become the Pokemon champion and may freely roam the Kanto region");
        System.out.println("However, there is still currently one more challenge waiting to be ");
        System.out.println("You have to find your rivel Caio and challenge him to a battle");
        System.out.println("Make sure to find him before it gets too dark outside though!");
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
                
            case TAKE:
                takeItem();
                break;
            
            case TALK:
                talkTo();
                break;
                
            //case DROP:
                //dropItem = drop(commmand);
                //break;
            
            case BACK:
                goBack();
                break;
                
            case QUIT:
                wantToQuit = quit(command);
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
        System.out.println("Find your rival Caio and challenge him to a battle.");
        System.out.println("Make sure to find him before it gets too dark outside though.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
        System.out.println("Your bag has a limit of 6 items.");
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

        if(nextRoom == null) {
            System.out.println("There is no where to go in this direction!");
        }
        else {
            prevRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        
        // Finish game 
        if(currentRoom == indigoPlateau1) {
            finished = true;
        }
    }
    
    
    /**
     * If the current location has an item, pick it up.
     * Put the item in your bag if theres space.
     */
    private void takeItem()
    {
        if(currentRoom.hasItem() == true && item > 6) {
            System.out.println("You can't pick this up, your bag is full");    
        }
        
        else if(currentRoom.hasItem() == true && item < 6) {
            Item itemToTake = currentRoom.getItem();
            System.out.println("You picked up the " + itemToTake.getItemDesc() + "!");
        }
        
        else {
            System.out.println("There are no items to pickup here.");    
        }
    }
    
    /**
     * Talk
     */
    private void talkTo() {
        if(currentRoom == palletTown1) {
            System.out.println("Mom: Welcome home!");
        }
        else if(currentRoom == billsHouse1) {
            System.out.println("Bill: Hey bud!");
            System.out.println("Bill: You should have been here a minute ago, Caio was just here.");
            System.out.println("Bill: He said something about heading south.");
        }
    }
        
    //private void dropItem(Command command) {
        //if(!command.hasSecondWord()) {
            //System.out.println("Drop what?");
            //return;
        //}
        
        //String itemToDrop = command.get
    //}
    
    /**
     * Try to go back to the previous location 1 time.
     */
    private void goBack() {
        if (prevRoom == null) {
            System.out.println("You have not been anywhere previously!");
        }

        else {
            currentRoom = prevRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        
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

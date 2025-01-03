import java.io.File;
import java.util.Scanner;

//Main starting point of the game, main class defines the menu of the game and allows user to input choice.
public class Main {
    public static void main(String[] args) {
        Scanner scanFile = new Scanner(System.in);
        while (true) {
            System.out.println("Dungeon Of Doom");
            System.out.println("...................");
            System.out.println("Please select the dungeon level");
            // Select the maps or level folder, path should be the working directory, (use "pwd" to get the path)
            File mapsFolder = new File("/workspaces/DungeonOfDoom_terminal/src/maps");
            File[] mapsList = mapsFolder.listFiles();

            //checking if there are no maps
            if (mapsList == null || mapsList.length == 0) {
                System.out.println("No maps found");
                return;
            }

            // Looping thrhough the maps to get the file name of the maps
            for (int i = 0; i < mapsList.length; ++i) {
                File file = mapsList[i];
                if (file.isFile()) {
                    String fileName = getSplitName(file);
                    System.out.println((i) + ". " + fileName);
                }
            }
            //Logic for choosing the map along with choosing the help menu options to view player commands
            int choice = -1;
            int fullListLength = mapsList.length;

            while (choice < 0 || choice > mapsList.length) {
                int listLength = fullListLength - 1;
                System.out.print("Select a map by entering the number (0-" + listLength + "), or enter "
                        + fullListLength + " to get help: ");
                if (scanFile.hasNextInt()) {
                    choice = scanFile.nextInt();
                } else {
                    System.out.println("Please enter a valid number");
                    scanFile.nextLine();
                }
            }
            if (choice == fullListLength) {
                helpGuide(scanFile);
            } else if (choice < fullListLength) {
                // selection of map based on the choice the player does
                File selectedMap = mapsList[choice];
                System.out.println("Selected Map: " + getSplitName(selectedMap));
                try {
                    MapReader map = new MapReader(selectedMap.getPath());
                    Game game = new Game(map);
                    game.start(scanFile);
                } catch (Exception e) {
                    System.out.println("Error in loading the game: " + e.getMessage());
                    break;
                }
                System.out.println("\nGame over. Returning to main menu...\n");
                System.out.println("Press enter to continue...");
                scanFile.nextLine();
            }
        }
        scanFile.close();
    }

    // A utility funtion created to help easily split filename to be shown or used in the main function
    public static String getSplitName(File file) {
        String fullName = file.getName();
        String[] splitFileName = fullName.split("\\.");
        String fileName = "";
        if (splitFileName.length > 0) {
            fileName = splitFileName[0];
        }
        return fileName;
    }

    //Function to show the help menu
    public static void helpGuide(Scanner scanFile) {
        System.out.println("Player controls and guide");
        System.out.println("You should enter the commands in the terminal, the commands are:\n");
        System.out.println("1. HELLO: Shows the gold required to win the level\n");
        System.out.println("2. GOLD: Shows the amount of gold you own\n");
        System.out.println("3. PICKUP: Helps you collect the gold when you are on a 'G' Tile\n");
        System.out.println("4. MOVE <N/W/S/E>: Lets the player character move in the specific direction \n");
        System.out.println("5. LOOK: Displays a grid of tiles near you on the map \n");
        System.out.println("7. QUIT: Allows you to exit the game\n");
        System.out.println("Press Enter to continue....\n");
        scanFile.nextLine();
    }
}
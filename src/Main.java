import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Dungeon Of Doom");
            System.out.println("...................");
            System.out.println("Please select the dungeon level");
            File mapsFolder = new File("D:/Akshay's Workspace/CW2 Principles of Programming/dungeonofdoom/src/maps");
            File[] mapsList = mapsFolder.listFiles();

            if (mapsList == null || mapsList.length == 0) {
                System.out.println("No maps found");
                return;
            }

            for (int i = 0; i < mapsList.length; ++i) {
                File file = mapsList[i];
                if (file.isFile()) {
                    String fileName = getSplitName(file);
                    System.out.println("FileName: " + fileName);
                    System.out.println((i) + ". " + fileName);
                }
            }
            Scanner scanFile = new Scanner(System.in);
            int choice = -1;
            int fullListLength = mapsList.length;

            while (choice < 0 || choice > mapsList.length) {
                int listLength = fullListLength - 1;

                System.out.print("Select a map by entering the number (0-" + listLength + "), or enter " + fullListLength + " to get help: ");
                choice = scanFile.nextInt();
            }
            if (choice == fullListLength) {
                helpGuide();

            } else {
                File selectedMap = mapsList[choice];
                System.out.println("Selected Map: " + getSplitName(selectedMap));
                try {
                    MapReader map = new MapReader(selectedMap.getPath());
                    Game game = new Game(map);
                    game.start();
                } catch (Exception e) {
                    System.out.println("Error in loading the game: " + e.getMessage());
                }
            }
        }


    }

    public static String getSplitName(File file) {
        String fullName = file.getName();
        String[] splitFileName = fullName.split("\\.");
        String fileName = "";
        if (splitFileName.length > 0) {
            fileName = splitFileName[0];
        }
        return fileName;
    }

    public static void helpGuide() {
        System.out.println("Player controls and guide");
        System.out.println("You should enter the commands in the terminal, the commands are:\n");
        System.out.println("1. HELLO: Shows the gold required to win the level\n");
        System.out.println("2. GOLD: Shows the amount of gold you own\n");
        System.out.println("3. PICKUP: Helps you collect the gold when you are on a 'G' Tile\n");
        System.out.println("4. MOVE <N/W/S/E>: Lets the player character move in the specific direction \n");
        System.out.println("5. LOOK: Displays a grid of tiles near you on the map \n");
        System.out.println("7. QUIT: Allows you to exit the game\n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
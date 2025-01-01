import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
        while (choice < 0 || choice > mapsList.length) {
            int listLength = mapsList.length - 1;
            System.out.print("Select a map by entering the number (0-" + listLength + "): ");
            choice = scanFile.nextInt();
        }
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

    public static String getSplitName(File file) {
        String fullName = file.getName();
        String[] splitFileName = fullName.split("\\.");
        String fileName = "";
        if (splitFileName.length > 0) {
            fileName = splitFileName[0];
        }
        return fileName;
    }
}
Initially extract the files from the zip into the required folder, modify the file/folder path in Main.java(see comments in file) to point to the maps folder location in your directory.
Once path is modified, run "javac *.java" to compile the files in that folder and run java Main.java to start the game.
The java classess defined and used for the game, uses core concepts of OOP, such as Encapsulation, Inheritance, Polymorphism, Abstraction. Encapsulation for example when MapReader class encapsulates the map related data and logic such as row, col, dispMap. Inheritance when the Player and Bot classes extend the CharacterController class. Polymorphism when the getCurrSymbol method in CharacterController is utilized differently by Bot and Player classes. Abstraction is also utilized where the CharacterController class abstracts away the complexity of Player and Bots to their respective classes. The bot actions are randomized so that it can either choose to attack the player, or go for the gold. A guide of the commands are available as an option in the game menu.
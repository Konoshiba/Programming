package Program;

import Commands.*;
import Managers.*;
import SourseFiles.Ticket;

import java.util.Scanner;

public class Main {
    public static final String INPUT_COMMAND = "/";
    public static final String INPUT_INFO = "> ";
    public static void main(String args[]){
        try{
            Scanner userScanner = new Scanner(System.in);
            Chronicler chronicler = new Chronicler();
      String fileName = System.getenv("MYPROJECTFILE");
            //String fileName = "C:\\Users\\Vkono\\IdeaProjects\\LB5\\src\\file.xml";
        ScannerManager scannerManager = new ScannerManager(userScanner);
        FileManager fileManager = new FileManager(fileName);
        CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                    new Add(collectionManager, scannerManager),
                    new Clear(collectionManager),new ExecuteScript(), new Exit(), new FilterPrice(collectionManager, scannerManager), new Help(),
                    new Info(collectionManager), new RemoveById(collectionManager), new RemoveByRefundable(collectionManager, scannerManager), new RemoveFirst(collectionManager), new RemoveLast(collectionManager),
                    new RemoveLower(collectionManager, scannerManager), new Save(collectionManager), new ShowByLessType(collectionManager, scannerManager), new Show(collectionManager), new UpdateId(collectionManager, scannerManager), new History(chronicler));
            ConsoleManager cons = new ConsoleManager(commandManager, userScanner, scannerManager, chronicler);
            cons.interactiveMode();
        } catch(ArrayIndexOutOfBoundsException e){
            ConsoleManager.printErr("Incorrect PATH!");
            System.exit(0);
        }

    }

}
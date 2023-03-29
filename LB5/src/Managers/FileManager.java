package Managers;

import Exeptions.DirectoryNotFoundException;
import SourseFiles.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }
    public void writeCollection(ArrayList<Ticket> ticketCollection) {
        try {
            if (fileName != null) {
                File file = new File(fileName);
                if (!file.exists())
                    throw new DirectoryNotFoundException();

                if (!file.canRead() || !file.canWrite())
                    throw new IOException();
                WriteXmlDomParser.write(ticketCollection, fileName);
            }
        } catch (DirectoryNotFoundException e) {
            ConsoleManager.printErr("No such file");
        }
        catch (IOException e){
            ConsoleManager.printErr("No permissions to write the file");
        }
    }

    public ArrayList<Ticket> readCollection() {
        try {
            if (fileName != null) {
                File file = new File(fileName);
                if (!file.exists())
                    throw new DirectoryNotFoundException();

                if (!file.canRead() || !file.canWrite())
                    throw new IOException();
                return ParseXML.readFile(fileName);
            }
        } catch (DirectoryNotFoundException e) {
            ConsoleManager.printErr("No such file");
        }
        catch (IOException e){
            ConsoleManager.printErr("No permissions to read the file");
        }
        return new ArrayList<>();
    }
}

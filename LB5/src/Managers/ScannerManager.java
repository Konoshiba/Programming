package Managers;


import Exeptions.*;
import Program.Main;
import SourseFiles.Coordinates;
import SourseFiles.TicketType;
import SourseFiles.VenueType;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerManager {
    private final double MIN_X = -390;
    private Pattern patternNumber = Pattern.compile("-?\\d+(\\.\\d+)?");
    private Pattern patternSymbols = Pattern.compile("^[A-Z][a-z]*(\\\\s(([a-z]{1,3})|(([a-z]+\\\\')?[A-Z][a-z]*)))*$");
    private Scanner userScanner;
    private boolean filemode;
    public ScannerManager(Scanner scanner){
        this.userScanner = scanner;
    }

    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }
    public void setFileMode() {
        filemode = true;
    }

    public void setUserMode() {
        filemode = false;
    }
    public Scanner getUserScanner() {
        return userScanner;
    }
    public String askName(String inputTitle, int minLength, int maxLength, String typeOfName) throws BadScriptException {
        String name;
        while (true) {
            try {
                System.out.println(inputTitle);
                System.out.print(Main.INPUT_INFO);
                name = userScanner.nextLine().trim();
                if (filemode) System.out.println(name);
                if (name.equals("")) throw new NotNullException();
                if (name.length() <= minLength) throw new IncorrectLengthException();
                if (name.length() >= maxLength) throw new IncorrectLengthException();
                if (!patternSymbols.matcher(name).matches()) throw new WrongNameException();
                break;
            } catch (WrongNameException exception) {
                ConsoleManager.printErr(String.format("%s must be char symbol!", typeOfName));
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr(String.format("%s didnt find!", typeOfName));
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (NotNullException exception) {
                ConsoleManager.printErr(String.format("%s cant be empty!", typeOfName));
                if (filemode) throw new BadScriptException();
            } catch (IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            } catch (IncorrectLengthException e) {
                ConsoleManager.printErr(String.format("Length must lay in (%d; %d)", minLength, maxLength));
            }
        }
        return name;
    }


    public String askPersonName(String collectionNameEnter, String collectionName) throws BadScriptException {
        return askName(collectionNameEnter, 0, Integer.MAX_VALUE, collectionName);
    }
    public float askXOfCoordinates() throws BadScriptException {
        String strX = "";
        float x;
        while (true) {
            try {
                System.out.println("Enter X coord:");
                System.out.print(Main.INPUT_INFO);
                strX = userScanner.nextLine().trim();
                if (filemode) System.out.println(strX);
                x = Float.parseFloat(strX);
                if (x < MIN_X) throw new IncorrectLengthException();
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Incorrect X coord");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (IncorrectLengthException exception) {
                ConsoleManager.printErr("Coord X must lay on (" + MIN_X
                        + ";" + (Float.MAX_VALUE) + ")!");
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException exception) {
                if (patternNumber.matcher(strX).matches())
                    ConsoleManager.printErr("Coord X must lay on (" + Float.MIN_VALUE
                            + ";" + (Float.MAX_VALUE) + ")!");
                else
                    ConsoleManager.printErr("Coord X must be an float");
                if (filemode) throw new BadScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            }
        }
        return x;
    }
    public int askYOfCoordinates() throws BadScriptException {
        String strY = "";
        int y;
        while (true) {
            try {
                System.out.println("Enter Y coord:");
                System.out.print(Main.INPUT_INFO);
                strY = userScanner.nextLine().trim();
                if (filemode) System.out.println(strY);
                y = Integer.parseInt(strY);
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Incorrect Y coord");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException exception) {
                if (patternNumber.matcher(strY).matches())
                    ConsoleManager.printErr("Coord Y must lay on (" + Integer.MIN_VALUE
                            + ";" + Integer.MAX_VALUE + ")!");
                else
                    ConsoleManager.printErr("Coord Y must be an int");
                if (filemode) throw new BadScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            }
        }
        return y;
    }
    public Coordinates askCoordinates() throws BadScriptException {
        Coordinates coords = new Coordinates();
        coords.setX(askXOfCoordinates());
        coords.setY(askYOfCoordinates());
        return coords;
    }


    public TicketType askTicketType() throws BadScriptException {
        String strTicktype;
        TicketType type;
        while (true) {
            try {
                System.out.println("Types list - " + TicketType.typeList());
                System.out.println("Enter your type:");
                System.out.print(Main.INPUT_INFO);
                strTicktype = userScanner.nextLine().trim();
                if (filemode) System.out.println(strTicktype);
                type = TicketType.valueOf(strTicktype.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Unknown type");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException exception) {
                ConsoleManager.printErr("No such type in list");
                if (filemode) throw new BadScriptException();
            } catch (IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error");
                System.exit(0);
            }
        }
        return type;
    }
    public VenueType askVenueType() throws BadScriptException {
        String strVenuetype;
        VenueType venueType;
        while (true) {
            try {
                System.out.println("List of venue types - " + VenueType.venuelList());
                System.out.println("Enter your venue type");
                System.out.print(Main.INPUT_INFO);
                strVenuetype = userScanner.nextLine().trim();
                if (filemode) System.out.println(strVenuetype);
                venueType = VenueType.valueOf(strVenuetype.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Unknown fuel type");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (IllegalArgumentException exception) {
                ConsoleManager.printErr("No such fuel type in list");
                if (filemode) throw new BadScriptException();
            } catch (IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error");
                System.exit(0);
            }
        }
        return venueType;
    }
    public boolean askRefundable() throws BadScriptException {
        String strRefundable = "";
        boolean refundable;
        while (true) {
            try {
                System.out.println("Enter refundable");
                System.out.print(Main.INPUT_INFO);
                strRefundable = userScanner.nextLine().trim();
                if(!strRefundable.equals("1") && !strRefundable.equals("0")) throw new NumberFormatException();
                else {
                    refundable = Integer.parseInt(strRefundable) == 1;
                    break;
                }
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Incorrect refundable!");
                if (filemode) throw new BadScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            }
            catch (NumberFormatException e){
                ConsoleManager.printErr("Incorrect input format!");
            }
        }
        return refundable;
    }
    public float askPrice() throws BadScriptException {
        String strPrice = "";
        float price;
        while (true) {
            try {
                System.out.println("Enter price");
                System.out.print(Main.INPUT_INFO);
                strPrice = userScanner.nextLine().trim();
                if (filemode) System.out.println(strPrice);
                price = Float.parseFloat(strPrice);
                if (price <= 0) throw new IncorrectLengthException();
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Incorrect price!");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException exception) {
                ConsoleManager.printErr("Price must be an float!");
                if (filemode) throw new BadScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            } catch (IncorrectLengthException e) {
                ConsoleManager.printErr("Price must lay on (" + 1 + ";" + Float.MAX_VALUE + ")!");
            }
        }
        return price;
    }

    public Integer askCapacity() throws BadScriptException {
        String strCapacity = "";
        int capacity;
        while (true) {
            try {
                System.out.println("Enter capacity");
                System.out.print(Main.INPUT_INFO);
                strCapacity = userScanner.nextLine().trim();
                if (filemode) System.out.println(strCapacity);
                capacity = Integer.parseInt(strCapacity);
                if(capacity <= 0) throw new IncorrectLengthException();
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Incorrect Capacity");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (NumberFormatException exception) {
                if (patternNumber.matcher(strCapacity).matches())
                ConsoleManager.printErr("Capacity must lay on ("
                + ";" + Integer.MAX_VALUE + ")!");
                else
                ConsoleManager.printErr("Capacity must be an int");
                if (filemode) throw new BadScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error!");
                System.exit(0);
            }
            catch (IncorrectLengthException e) {
                ConsoleManager.printErr("Capacity must lay on (" + 1 + ";" + Integer.MAX_VALUE + ")!");
            }
        }
        return capacity;
}
    public String askComment() throws BadScriptException {
        String answer = "";
        String comment = "";
        while (true) {
            try {
                System.out.println("Do you have any comments? Send Yes/No");
                System.out.print(Main.INPUT_INFO);
                answer = userScanner.nextLine().trim();
                if (answer.equals("Yes")) {
                    System.out.println("Send your comment");
                    System.out.print(Main.INPUT_INFO);
                    comment = userScanner.nextLine().trim();
                    if (filemode) System.out.println(comment);
                }
                if (!answer.equals("Yes") && !answer.equals("No")) throw new IncorrectLengthException();
                if (comment.length() > 404) throw new IncorrectHigthtExeption();
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Unknown answer!");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (IncorrectLengthException exception) {
                ConsoleManager.printErr("Answer must be Yes or No!");
                if (filemode) throw new BadScriptException();
            } catch (IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error");
                System.exit(0);
            } catch (IncorrectHigthtExeption exception) {
                ConsoleManager.printErr("Length must be less than 404 characters");
            }
        }
        return comment;
    }
    public boolean askQuestion(String question) throws BadScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                System.out.println(finalQuestion);
                System.out.print(Main.INPUT_INFO);
                answer = userScanner.nextLine().trim();
                if (filemode) System.out.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new IncorrectLengthException();
                break;
            } catch (NoSuchElementException exception) {
                ConsoleManager.printErr("Unknown answer!");
                System.exit(0);
                if (filemode) throw new BadScriptException();
            } catch (IncorrectLengthException exception) {
                ConsoleManager.printErr("Answer must be + or -!");
                if (filemode) throw new BadScriptException();
            } catch (IllegalStateException exception) {
                ConsoleManager.printErr("Unexpected error");
                System.exit(0);
            }
        }
        return answer.equals("+");
    }


}



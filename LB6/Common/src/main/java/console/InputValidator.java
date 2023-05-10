package console;

import data.TicketType;
import data.VenueType;
import exceptions.*;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class InputValidator {
    private final Class<?> cl;
    private final Boolean canBeNull;
    private final double minVal;
    private final double maxVal;

    private boolean updateMode;
    private Object prevValue;

    public InputValidator(Class<?> cl, Boolean canBeNull, double minVal, double maxVal) {
        this.cl = cl;
        this.canBeNull = canBeNull;
        this.minVal = minVal;
        this.maxVal = maxVal;
    }

    public InputValidator(Class<?> cl, Boolean canBeNull) {
        this(cl, canBeNull, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public InputValidator loadPreviousValue(boolean updateMode, @Nullable Object prevValue) {
        if (updateMode) {
            this.updateMode = true;
            this.prevValue = prevValue;
        }
        return this;
    }

    public Object interactiveInput(String text, boolean printMode, Supplier<String> valueGetter) throws ExecuteScriptFailedException {
        if (printMode) {
            String strInsert = "";
            if (updateMode) {
                strInsert = " >> " + prevValue;
            }
            while (true) {
                Console.print("Type " + text + strInsert + " >>> ");
                String input = "";
                try {
                    input = valueGetter.get();
                } catch (NoSuchElementException e) {
                    System.exit(0);
                }
                if (prevValue != null && input.equals("<")) return prevValue;

                try {
                    return validate(input, null, false);
                } catch (ValidateException e) {
                    Console.println(e.getMessage());
                }
            }
        } else {
            String input = valueGetter.get();
            try {
                if (prevValue != null && input.equals("<")) return prevValue;
                else if (input.equals("#validate_initial")) {
                    return validate((prevValue != null) ? prevValue.toString() : null,
                            text.substring(0, 1).toUpperCase() + text.substring(1) + ": ",
                            false);
                } else {
                    return validate(input, null, false);
                }
            } catch (ValidateException e) {
                throw new ExecuteScriptFailedException(e.getMessage());
            }
        }
    }

    public <T extends Enum<T>> Object interactiveInput(String text, T[] enumValues, boolean printMode, Supplier<String> valueGetter) throws ExecuteScriptFailedException {
        String textNew = text + " " + Arrays.asList(enumValues);
        return interactiveInput(textNew, printMode, valueGetter);
    }

    public Object validate(String input, String fieldName, Boolean arg) throws ValidateException {
        if (fieldName == null) fieldName = "";
        try {
            if (!canBeNull && (input==null || input.trim().isEmpty())) {
                throw new EmptyEntryException();
            }

            input = input.trim();

            if (cl == String.class) {
                if (input.trim().isEmpty()) return null;
                return input;

            } else if (cl == TicketType.class) {
                TicketType mg = TicketType.checkElement(input);
                if (mg != null) return mg;
                throw new InvalidEnumEntryException();
            } else if (cl == VenueType.class) {
                if (input.isEmpty()) return null;
                VenueType mr = VenueType.checkElement(input);
                if (mr != null) return mr;
                throw new InvalidEnumEntryException();
            } else {
                if (cl == int.class) {
                    int num = Integer.parseInt(input);
                    if (num <= minVal || num >= maxVal) throw new InvalidRangeException();
                    return num;
                } else if (cl == long.class) {
                    long num = Long.parseLong(input);
                    if (num <= minVal || num >= maxVal) throw new InvalidRangeException();
                    return num;
                } else if (cl == double.class || cl == Double.class) {
                    double num = Double.parseDouble(input);
                    if (num <= minVal || num >= maxVal) throw new InvalidRangeException();
                    return num;
                } else if (cl == float.class || cl == Float.class) {
                    float num = Float.parseFloat(input);
                    if (num <= minVal || num >= maxVal) throw new InvalidRangeException();
                    return num;
                } else if (cl == boolean.class || cl == Boolean.class) {
                    if (!input.equals("true") && !input.equals("false")) throw new InvalidRangeException();
                    boolean num = Boolean.parseBoolean(input);
                    return num;
                }

            }

        } catch (EmptyEntryException e) {
            throw new ValidateException(arg ? "Error: No argument find." : fieldName + "Field can't be empty.");
        } catch (InvalidEnumEntryException e) {
            throw new ValidateException(arg ? "Argument not one of shown constants." : fieldName + "Input value not one of shown constants.");
        }
        catch (NumberFormatException e) {
            throw new ValidateException(arg ? "Argument has wrong number format!" : fieldName + "Invalid number format.");
        } catch (InvalidRangeException e) {
            throw new ValidateException(arg ? "Argument has wrong number range!" : fieldName + "Invalid number range.");
        }
        throw new ValidateException("Validation error.");
    }
}

package commands;

import exceptions.ExecuteScriptFailedException;
import exceptions.InvalidArgumentException;

import java.io.IOException;


@FunctionalInterface
public interface Command {
    String run(String name, String arg) throws InvalidArgumentException, ExecuteScriptFailedException, IOException;
}

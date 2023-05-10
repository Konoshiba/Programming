package commands;

import exceptions.ExecuteScriptFailedException;
import exceptions.InvalidArgumentException;

import java.io.IOException;


@FunctionalInterface
public interface Command {
    Object run(Object arg) throws InvalidArgumentException, ExecuteScriptFailedException, IOException;
}

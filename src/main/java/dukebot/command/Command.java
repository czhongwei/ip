package dukebot.command;

import dukebot.DukeException;

/**
 * Abstract class that encapsulates a command.
 */
public abstract class Command {

    /**
     * Checks if command is an exit command.
     *
     * @return true if exit command, else false.
     */
    public boolean isExit() {
        return false;
    };

    /**
     * Executes the command.
     */
    public abstract String execute() throws DukeException;
}

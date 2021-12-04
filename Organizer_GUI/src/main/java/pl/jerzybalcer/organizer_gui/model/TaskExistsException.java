package pl.jerzybalcer.organizer_gui.model;

/**
 *Exception that's thrown when user tries to add a task that already exists
 * @author Jerzy Balcer
 */
public class TaskExistsException extends Exception {

    /**
     * Non-parameter constructor
     */
    public TaskExistsException() {
    }

    /**
     * Exception class constructor
     *
     * @param message display message
     */
    public TaskExistsException(String message) {
        super(message);
    }
}

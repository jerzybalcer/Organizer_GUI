package pl.jerzybalcer.organizer_gui.model;

/**
 *Represents a Task added to to-do list
 * @author Jerzy Balcer
 */
public class Task {
    /** Id of task in to-do list */
    private int id;
    /** Task's description (what to do) */
    private String description;
    /** Indicates the completion of the task */
    private boolean isDone;
    
    /** Parametrized constructor
     * @param id id of new task
     * @param description description of new task
     */
    public Task(int id, String description){
        this.id = id;
        this.description = description;
        this.isDone = false;
    }
    
    /** Mark task as completed*/
    public void makeDone(){
        this.isDone = true;
    }
    
    /** Get task's id
     * @return this task's id
     */
    public int getId(){
        return id;
    }
    
    /** Get task's description
     * @return this task's description
     */
    public String getDesc(){
        return description;
    }
    
    /** Check if task is done
     * @return bool value of this task being done or not
     */
    public boolean isDone(){
        return isDone;
    }
}

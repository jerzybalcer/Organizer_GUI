package pl.jerzybalcer.organizer_gui.model;

import java.util.ArrayList;

/**
 * Represents list of all  to-do tasks
 * @author Jerzy Balcer
 */
public class TaskList {
        /**
     * List of tasks itself
 */
    public ArrayList<Task> list = new ArrayList<Task>();
    
        /**
     * Adds a new task to the list
     * @param description description of a new task
     * @throws pl.jerzybalcer.organizer.model.TaskExistsException exception is thrown when a task already exists
 */
    public void add(String description) throws TaskExistsException{
        for(Task task : list){
            if(task.getDesc().equals(description)){
                throw new TaskExistsException("Task already exists!");
            }
        }

        list.add(new Task(list.size(), description));
    }
        /**
     * Removes a task from the list
     * @param id id of task that's being removed
     * @return was the operation successful, was there a task with input id
 */
    public boolean remove(int id){
        for(int i =0; i<list.size(); i++){
            if(list.get(i).getId() == id){
                list.remove(list.get(i));
                return true;
            }
        }
        return false;
    }
            /**
     * Marks a task as done
     * @param id id of task that's being marked
     * @return was the operation successful, was there a task with input id
 */
    public boolean makeDone(int id){
        for(int i =0; i<list.size(); i++){
            if(list.get(i).getId() == id){
                list.get(i).makeDone();
                return true;
            }
        }
        return false;
    }
}

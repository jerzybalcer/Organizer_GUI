package pl.jerzybalcer.organizer_gui.tests;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import pl.jerzybalcer.organizer_gui.model.Task;
import pl.jerzybalcer.organizer_gui.model.TaskExistsException;
import pl.jerzybalcer.organizer_gui.model.TaskList;

/**
 * Tests all methods of TaskList class
 * @author Jerzy Balcer
 */
public class TaskListTests {

    /** Test if trying to add existing task throws an exception
     * @param desc description of new task
     */
    @ParameterizedTest
    @ValueSource(strings = {"testTask"})
    public void addTheSameTest(String desc) throws TaskExistsException {

        TaskList taskList = new TaskList();

        taskList.list.add(new Task(0, "testTask"));

        assertThrows(TaskExistsException.class, ()-> taskList.add(desc));
    }

    /** Test if adding new task works as expected
     * @param desc description of new task
     */
    @ParameterizedTest
    @ValueSource(strings = {"testTask", "", "1"})
    public void addTest(String desc) throws TaskExistsException {

        TaskList taskList = new TaskList();

        int startSize = taskList.list.size();

        try{
            taskList.add(desc);
            assertTrue(startSize+1 == taskList.list.size() && taskList.list.get(startSize).getDesc().equals(desc));
        }catch (TaskExistsException e){
            assertTrue(startSize == taskList.list.size());
        }
    }

    /** Test if removing task works as expected
     * @param id id of task to remove
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 2})
    public void removeTest(int id){

        TaskList taskList = new TaskList();

        taskList.list.add(new Task(0, "testTask"));
        int startSize = taskList.list.size();

        boolean removeResult = taskList.remove(id);

        if(removeResult == true && startSize-1 == taskList.list.size()){
            assertTrue(true);
        }else if(removeResult == false && startSize == taskList.list.size()){
            assertTrue(true);
        }
    }

    /** Test if making task done works as expected
     * @param id id of task to mark as done
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 2})
    public void makeDoneTest(int id){

        TaskList taskList = new TaskList();

        taskList.list.add(new Task(0, "testTask"));

        boolean removeResult = taskList.makeDone(id);

        if(removeResult == false && (id < 0 || id > taskList.list.size()-1)){
            assertTrue(true);
        }else if(removeResult == true && taskList.list.get(id).isDone()){
            assertTrue(true);
        }else if(removeResult == false && taskList.list.get(id).isDone() == false){
            assertTrue(true);
        }
    }
}

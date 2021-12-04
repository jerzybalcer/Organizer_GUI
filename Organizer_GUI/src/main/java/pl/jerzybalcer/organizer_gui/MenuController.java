package pl.jerzybalcer.organizer_gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pl.jerzybalcer.organizer_gui.model.Task;
import pl.jerzybalcer.organizer_gui.model.TaskExistsException;
import pl.jerzybalcer.organizer_gui.model.TaskList;

/**
 * Controlls displaying elements on view "menu"
 * @author Jerzy Balcer
 */
public class MenuController {

    /** Reference to task list manager */
    private TaskList taskList;

    /** Input field for added task description */
    @FXML
    private TextField description;

    /** Input field for removed task id */
    @FXML
    private TextField idRemove;

    /** Input field for marked task id */
    @FXML
    private TextField idMark;

    /** Element that displays current list of tasks */
    @FXML
    private ScrollPane taskScrollPane;

    /** Constructs menu controller class and creates task list manager */
    public MenuController(){
        taskList = new TaskList();
    }

    /** Handles clicking add button event
     * @throws IOException thrown when there's problem with input/output
     * @throws TaskExistsException thrown when users tries to add a task that already exists
     * */
    @FXML
    private void addButtonClick() throws IOException, TaskExistsException {
        String desc = description.getText();

        if(desc.length() < 1){
            showAlert("Enter new task description!");
        }else{
            try{
                taskList.add(desc);
            }catch(TaskExistsException e){
                showAlert(e.getMessage());
            }
            updateScrollPane();
        }

        description.setText("");
    }

    /** Handles clicking remove button event
     * @throws IOException thrown when there's problem with input/output
     * */
    @FXML
    private void removeButtonClick() throws IOException {
        String id = idRemove.getText();
        int parsedId = -1;

        if(id.length() < 1){
            showAlert("Enter id of task to remove!");
        }else{
            try{
                parsedId = Integer.parseInt(id);

                if(!taskList.remove(parsedId)){
                    showAlert("There's no task with such id!");
                }else{
                    updateScrollPane();
                }

            }catch(NumberFormatException e){
                showAlert("Please enter only integer!");
            }
        }

        idRemove.setText("");
    }

    /** Handles clicking mark as done button event
     * @throws IOException thrown when there's problem with input/output
     * */
    @FXML
    private void markButtonClick() throws IOException {
        String id = idMark.getText();
        int parsedId = -1;

        if(id.length() < 1){
            showAlert("Enter id of task to mark!");
        }else{
            try{
                parsedId = Integer.parseInt(id);

                if(!taskList.makeDone(parsedId)){
                    showAlert("There's no task with such id!");
                }else{
                    updateScrollPane();
                }

            }catch(NumberFormatException e){
                showAlert("Please enter only integer!");
            }
        }

        idMark.setText("");
    }

    /** Updates task list on the screen every time it is changed */
    private void updateScrollPane(){
        taskScrollPane.setContent(new Pane());

        GridPane grid = new GridPane();

        int i = 0;

        for(Task task : taskList.list){
            grid.addRow(i);
            grid.add(new Label("ID: " + task.getId() + " | TASK: " + task.getDesc() + " | DONE?: " + task.isDone()), 0, i);

            i++;
        }

        taskScrollPane.setContent(grid);
    }

    /** Creates and shows alert box with specified message
     * @param msg string to show as message
     * */
    private void showAlert(String msg){
        (new Alert(Alert.AlertType.ERROR, msg)).show();
    }
}

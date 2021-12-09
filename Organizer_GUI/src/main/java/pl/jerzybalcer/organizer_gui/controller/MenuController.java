package pl.jerzybalcer.organizer_gui.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    /** Table where all to do tasks are being displayed */
    @FXML
    private TableView tasksTable;

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
            updateDisplayedTasks();
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
                    updateDisplayedTasks();
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
                    updateDisplayedTasks();
                }

            }catch(NumberFormatException e){
                showAlert("Please enter only integer!");
            }
        }

        idMark.setText("");
    }

    /** Updates task list on the screen every time it is changed */
    private void updateDisplayedTasks(){

        tasksTable.getItems().clear();

        TableColumn c1 = (TableColumn) tasksTable.getColumns().get(0);
        TableColumn c2 = (TableColumn) tasksTable.getColumns().get(1);
        TableColumn c3 = (TableColumn) tasksTable.getColumns().get(2);

        c1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        c2.setCellValueFactory(
                new PropertyValueFactory<>("description")
        );
        c3.setCellValueFactory(
                new PropertyValueFactory<>("done")
        );

        for(Task task : taskList.list){
            tasksTable.getItems().add(task);
        }
    }

    /** Creates and shows alert box with specified message
     * @param msg string to show as message
     * */
    private void showAlert(String msg){
        (new Alert(Alert.AlertType.ERROR, msg)).show();
    }
}

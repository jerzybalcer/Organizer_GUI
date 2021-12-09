module pl.jerzybalcer.organizer_gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.jerzybalcer.organizer_gui.controller to javafx.fxml;
    exports pl.jerzybalcer.organizer_gui;
    exports pl.jerzybalcer.organizer_gui.controller;
    exports pl.jerzybalcer.organizer_gui.model;
}

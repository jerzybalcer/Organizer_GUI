module pl.jerzybalcer.organizer_gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.jerzybalcer.organizer_gui to javafx.fxml;
    exports pl.jerzybalcer.organizer_gui;
}

module dad.calculadoracompleja {
    requires javafx.controls;
    requires javafx.fxml;


    opens dad.calculadoracompleja to javafx.fxml;
    exports dad.calculadoracompleja;
}
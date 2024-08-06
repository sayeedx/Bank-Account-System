module coe528.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens coe528.project to javafx.fxml;
    exports coe528.project;
}

module com.miniproject.gestionpatients {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires java.sql;
    opens com.miniproject.gestionpatients.data to javafx.base;

    opens com.miniproject.gestionpatients to javafx.fxml;
    exports com.miniproject.gestionpatients;
}
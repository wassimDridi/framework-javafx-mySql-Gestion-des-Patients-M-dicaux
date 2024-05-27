package com.miniproject.gestionpatients;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.miniproject.gestionpatients.data.Personnel;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

import java.io.IOException;

public class GestionApplication extends Application {

    private Stage primaryStage;
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        AuthentificationController controller_login = new AuthentificationController();
        controller_login.setMainApp(this);
        fxmlLoader.setController(controller_login);
        fxmlLoader.setLocation(GestionApplication.class.getResource("authentification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Interface d'Authentification ");
        primaryStage.setScene(scene);
        primaryStage.show();
        //fx:controller="com.miniproject.gestionpatients.AuthentificationController"
    }

    public boolean moveConroller(Personnel p){
        if(p.getFonction().equals("admin")){
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                AdminController controller_person = new AdminController();
                controller_person.setMainApp(this);
                loader.setController(controller_person);
                loader.setLocation(GestionApplication.class.getResource("admin-view.fxml"));
                //AnchorPane page = (AnchorPane) loader.load();
                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle(" Gestion du Personnel ");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                //Scene scene = new Scene(page);
                Scene scene = new Scene(loader.load());
                dialogStage.setScene(scene);
                // Set the person into the controller.
                AdminController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setPerson(p);
                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }else{
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                GestionHospitalController controller_person = new GestionHospitalController();
                loader.setController(controller_person);
                loader.setLocation(GestionApplication.class.getResource("gestion-hospital-view.fxml"));
                //AnchorPane page = (AnchorPane) loader.load();
                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle(" Gestion du Hospital ");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                //Scene scene = new Scene(page);
                Scene scene = new Scene(loader.load());
                dialogStage.setScene(scene);
                // Set the person into the controller.
                GestionHospitalController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setPerson(p);
                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

    }


    public static void main(String[] args) {
        launch();
    }
}

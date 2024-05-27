package com.miniproject.gestionpatients;

import com.miniproject.gestionpatients.data.Personnel;
import com.miniproject.gestionpatients.data.PersonnelImplUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthentificationController implements Initializable {
    @FXML
    private TextField tfLogin, tfPassword;
    @FXML
    private Button btnLogin, btnCancel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ecouteurs();
    }
    private GestionApplication mainApp;
    public void setMainApp(GestionApplication mainApp) {
        this.mainApp = mainApp;
    }
    public void ecouteurs() {
        btnLogin.setOnAction(event -> {
            try {
                handleSubmit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnCancel.setOnAction(event -> handleCancel());
    }
    public void handleSubmit() throws IOException {
        if (tfLogin.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            Alert diag = new Alert(Alert.AlertType.ERROR);
            diag.setTitle("erreur");
            diag.setHeaderText(null);
            diag.setContentText("Champs login et password sont requises");
            diag.showAndWait();
        } else {
            PersonnelImplUtil personnelImplUtil = new PersonnelImplUtil();
            Personnel p = personnelImplUtil.getPersonnelByloginPassword(tfLogin.getText(), tfPassword.getText());
            if (p == null) {
                Alert diag = new Alert(Alert.AlertType.ERROR);
                diag.setTitle("erreur");
                diag.setHeaderText(null);
                diag.setContentText("Personnel introuvable");
                diag.showAndWait();
            } else {
                boolean okClicked = mainApp.moveConroller(p);
                // Redirect to home
                //gestionApplication.changeScene("personnel-view.fxml");
            }
        }
    }
    public void handleCancel() {
        tfLogin.setText("");
        tfPassword.setText("");
    }
}

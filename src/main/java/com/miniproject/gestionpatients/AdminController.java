package com.miniproject.gestionpatients;



import com.miniproject.gestionpatients.data.Patient;
import com.miniproject.gestionpatients.data.Personnel;
import com.miniproject.gestionpatients.data.PersonnelImplUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable  {
    @FXML
    private Label labelAdmin;
    @FXML
    private TableView<Personnel> personnelTable;
    @FXML
    private TableColumn<Personnel, String> cinColumn;
    @FXML
    private TableColumn<Personnel, String> nomColumn;


    @FXML
    private TextField cinField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField fonctionField , rechercheField;
    private String action ;


    @FXML
    private Button btnEdit , btnDeleteP , btnOk , btnajouter , btnCancel , btnRechercher  , btnmove;

    private PersonnelImplUtil personnelImplUtil ;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    private Personnel person ;
    public void setPerson(Personnel person){
        this.person = person ;
        if(this.person != null){
            labelAdmin.setText(this.person.getNom()+" "+this.person.getPrenom());
        }
    }
    private GestionApplication mainApp;
    public void setMainApp(GestionApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.personnelImplUtil = new PersonnelImplUtil();

        initGraphique();
        ecouteurs();
    }
    private void ecouteurs(){
        personnelTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        showData(newValue));
        btnEdit.setOnAction(event -> handleActionEditPersonnel());
        btnajouter.setOnAction(event -> handleActionAddPersonnel());

        btnOk.setOnAction(event -> handleActionOkPersonnel());
        btnDeleteP.setOnAction(event -> handleDelete());
        btnCancel.setOnAction(event -> vide());
        btnRechercher.setOnAction(event -> recherche());
        btnmove.setOnAction(event -> moving());


    }

    private void initGraphique() {
        try {
            if (personnelImplUtil != null && personnelTable != null && cinColumn != null && nomColumn != null) {
                ObservableList<Personnel> liste = personnelImplUtil.getPersonnel();
                cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
                nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                if (liste != null) {


                    personnelTable.setItems(liste);

                } else {
                    System.out.println("List is null");
                }
            } else {
                System.out.println("One or more objects are not initialized");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred while initializing graphique: " + e.getMessage());
        }
    }
    private void recherche() {


        try {
            if (personnelImplUtil != null && personnelTable != null && cinColumn != null && nomColumn != null) {
                ObservableList<Personnel> liste = personnelImplUtil.rechercherPersonnel(rechercheField.getText());
                cinColumn.setCellValueFactory(new PropertyValueFactory<>("cin"));
                nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

                if (liste != null) {
                    personnelTable.setItems(liste);
                    vide();

                    System.out.println("recherche 22 : " + liste);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Invalid action");
                    alert.setHeaderText("Please correct invalid action");
                    alert.setContentText("T ok!!");
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid action");
                alert.setHeaderText("Please correct invalid action");
                alert.setContentText("");
                alert.showAndWait();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred while initializing graphique: " + e.getMessage());
        }
    }
    private void handleActionAddPersonnel(){
        this.action = "addPersonnel";
        vide();
        cinField.setEditable(true);
        nomField.setEditable(true);
        prenomField.setEditable(true);
        loginField.setEditable(true);
        fonctionField.setEditable(true);
        passwordField.setEditable(true);
    }
    private void handleActionEditPersonnel(){
        this.action = "editPersonnel";
        cinField.setEditable(false);
        nomField.setEditable(true);
        prenomField.setEditable(true);
        fonctionField.setEditable(true);
        loginField.setEditable(true);
        passwordField.setEditable(true);
    }
    private void handleActionOkPersonnel() {
        if (this.action.equals("addPersonnel")) {
            handleAdd();
            this.action = "" ;
        } else if (this.action.equals("editPersonnel")) {
            handleEdit();
            this.action = "" ;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("Try add or edit action then click ok!!");
            alert.showAndWait();
        }
    }

    private void moving(){
        this.person.setFonction("abc");
        boolean okClicked = mainApp.moveConroller(this.person);
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (cinField.getText() == null || cinField.getText().length()== 0) {
            errorMessage += "No valid  cim !\n";
        }
        if (nomField.getText() == null || nomField.getText().length()== 0) {
            errorMessage += "No valid nom !\n";
        }
        if (prenomField.getText() == null || prenomField.getText().length() == 0)
        {
            errorMessage += "No valid prenom !\n";
        }
        if (loginField.getText() == null || loginField.getText().length() == 0) {
            errorMessage += "No valid login !\n";
        }
        if (fonctionField.getText() == null || fonctionField.getText().length() == 0) {
            errorMessage += "No valid fonction  !\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password  !\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    private void showData(Personnel p){
        cinField.setText(p.getCin());
        nomField.setText(p.getNom());
        prenomField.setText(p.getPrenom());
        loginField.setText(p.getLogin());
        fonctionField.setText(p.getFonction());
        passwordField.setText(p.getPassword());

        cinField.setEditable(false);
        nomField.setEditable(false);
        prenomField.setEditable(false);
        loginField.setEditable(false);
        fonctionField.setEditable(false);
        passwordField.setEditable(false);

    }
    private void handleAdd() {
        if (isInputValid()) {
            Personnel p = new Personnel();
            p.setCin(cinField.getText());
            p.setNom(nomField.getText());
            p.setPrenom(prenomField.getText());
            p.setLogin(loginField.getText());
            p.setFonction(fonctionField.getText());
            p.setPassword(passwordField.getText());
            this.personnelImplUtil.ajouterPersonnel(p);
            vide();
            initGraphique();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("Try add and correct  action then click ok!!");
            alert.showAndWait();
            return;
        }
    }

    private void handleEdit() {
        if (isInputValid()) {
            Personnel oldP = personnelTable.getSelectionModel().getSelectedItem();
            Personnel p = new Personnel();
            p.setCin(cinField.getText());
            p.setNom(nomField.getText());
            p.setPrenom(prenomField.getText());
            p.setLogin(loginField.getText());
            p.setFonction(fonctionField.getText());
            p.setPassword(passwordField.getText());
            this.personnelImplUtil.modifierPersonnel(p);
            vide();
            initGraphique();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("error in edit check element ok!!");
            alert.showAndWait();
            return;
        }
    }

    private void vide(){
        cinField.setText("");
        nomField.setText("");
        prenomField.setText("");
        loginField.setText("");
        fonctionField.setText("");
        passwordField.setText("");

    }
    private void handleDelete() {

        Personnel p = personnelTable.getSelectionModel().getSelectedItem();

        boolean deleted = personnelImplUtil.supprimerPersonnel(p.getCin());
        System.out.println("Personnel deleted ?"+ deleted);
        if (deleted) {
            // Remove the item from the TableView
            personnelTable.getItems().remove(p);
            System.out.println("Personnel deleted successfully.");
            vide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("delete invalid try element to deleted ");
            alert.showAndWait();
            return;
        }

    }

    private void handleCancel() {
        dialogStage.close();
    }

}
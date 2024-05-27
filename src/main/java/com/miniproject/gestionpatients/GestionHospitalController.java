package com.miniproject.gestionpatients;

import com.miniproject.gestionpatients.data.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GestionHospitalController implements Initializable {
    @FXML
    private Label labelAdmin;
    //private Stage dialogStage;
    // bloc gestion patient--------------------------
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> prenomColumn;
    @FXML
    private TableColumn<Patient, String> nomColumn;
    private PatientImplUtil patientImplUtil ;

    @FXML
    private TextField cinField , recherchePatientField ;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField sexeField;
    @FXML
    private TextField telField;
    @FXML
    private Button btnEdit , btnDelete , btnOk , btnajouter , btnCancel , btnFilterPat;

    private String action ;
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
    // blog gestion medicament-----------------------------------
    @FXML
    private TableView<Medicament> medicamentTable;
    @FXML
    private TableColumn<Medicament, String> libelleColumn;
    @FXML
    private TableColumn<Medicament, Double> prixColumn;
    private MedicamentImplUtil medicamentImplUtil ;
    @FXML
    private TextField refField;
    @FXML
    private TextField libelleField;
    @FXML
    private TextField prixField , filtermedField;
    @FXML
    private Button btnEditM , btnDeleteM , btnOkM , btnajouterM , btnCancelM , btnfiltermed , btnpdfmed , btnpdfpat;

    private String actionMedicament ;
//-------------------------------------------------------------------------
    @FXML
    private TableView<PatientMed> affecterTable;
    @FXML
    private TableColumn<PatientMed, String> affPCinColumn;
    @FXML
    private TableColumn<PatientMed, Double> affMrefColumn;
    @FXML
    private TableColumn<PatientMed, String> affPrCinColumn;

    @FXML
    private TableView<Patient> patImedTable;
    @FXML
    private TableColumn<Patient, String> patnomColumn;
    @FXML
    private TableColumn<Patient, Double> patprenomColumn;
    @FXML
    private TableView<Medicament> medIpatTable;
    @FXML
    private TableColumn<Medicament, String> medlibColumn;
    @FXML
    private TableColumn<Medicament, Double> medpriColumn;
    @FXML
    private TextField cherPnomField , cherMlibField;
    @FXML
    private Button btnaffecter , btnFnom , btnFlibelle , btnSupprimer , btnlogoutp;
    private  PatientMedImplUtil patientMedImplUtil ;
    //---------------------------------------------------------
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.patientImplUtil = new PatientImplUtil();
        this.medicamentImplUtil = new MedicamentImplUtil();
        this.patientMedImplUtil = new PatientMedImplUtil();

        cinField.setEditable(false);
        this.action = "" ;
        initGraphique();
        ecouteurs();
    }
    private void initGraphique() {
        try {




            if (patientImplUtil != null && patientTable != null && prenomColumn != null && nomColumn != null) {
                ObservableList<Patient> liste = patientImplUtil.getPatient();
                prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                if (liste != null) {


                    patientTable.setItems(liste);

                } else {
                    System.out.println("List is null");
                }
            } else {
                System.out.println("One or more objects are not initialized");
            }
            //---------------------------------------------------------------
            if (patientImplUtil != null && patImedTable != null && patprenomColumn != null && patnomColumn != null) {
                ObservableList<Patient> liste = patientImplUtil.getPatient();
                patnomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                patprenomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                if (liste != null) {


                    patImedTable.setItems(liste);

                } else {
                    System.out.println("List is null");
                }
            } else {
                System.out.println("One or more objects are not initialized");
            }
            //----------------------------------------
            if (this.medicamentImplUtil != null && medicamentTable != null && prixColumn != null && libelleColumn != null) {
                ObservableList<Medicament> liste = medicamentImplUtil.getMedicament();
                libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
                if (liste != null) {

                    System.out.println("List is medcament");
                    medicamentTable.setItems(liste);

                } else {
                    System.out.println("List is null");
                }
            } else {
                System.out.println("One or more objects are not initialized medicament");
            }
            //--------------------------------------
            if (this.medicamentImplUtil != null && medIpatTable != null && medpriColumn != null && medlibColumn != null) {
                ObservableList<Medicament> liste = medicamentImplUtil.getMedicament();
                medlibColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                medpriColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
                if (liste != null) {
                    medIpatTable.setItems(liste);
                } else {
                    System.out.println("List is null");
                }
            } else {
                System.out.println("One or more objects are not initialized medicament");
            }
            //----------------------------------------------
            if (this.patientMedImplUtil != null && affecterTable != null && affPCinColumn != null  && affMrefColumn != null && affPrCinColumn != null) {
                ObservableList<PatientMed> liste = patientMedImplUtil.getPatientMed();
                affPCinColumn.setCellValueFactory(new PropertyValueFactory<>("cinPat"));
                affMrefColumn.setCellValueFactory(new PropertyValueFactory<>("refMed"));
                affPrCinColumn.setCellValueFactory(new PropertyValueFactory<>("cinPer"));
                if (liste != null) {
                    affecterTable.setItems(liste);
                } else {
                    System.out.println("List is null");
                }
            } else {
                System.out.println("One or more objects are not initialized medicament");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred while initializing graphique: " + e.getMessage());
        }
    }
    private void ecouteurs(){
        patientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        showData(newValue));
        btnEdit.setOnAction(event -> handleActionEditPatient());
        btnajouter.setOnAction(event -> handleActionAddPatient());
        btnOk.setOnAction(event -> handleActionOkPatient());
        btnDelete.setOnAction(event -> handleDelete());
        btnCancel.setOnAction(event -> vide());
        btnFilterPat.setOnAction(event -> filterPatient());
        btnlogoutp.setOnAction(event -> logOut());
        //---------------------
        medicamentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        showDataM(newValue));
        btnEditM.setOnAction(event -> handleActionEditMedicament());
        btnajouterM.setOnAction(event -> handleActionAddMedicament());
        btnOkM.setOnAction(event -> handleActionOkMedicament());
        btnDeleteM.setOnAction(event -> handleDeleteM());
        btnCancelM.setOnAction(event -> videM());
        btnfiltermed.setOnAction(event -> filterparlibelle());
        //---------------------
        btnFnom.setOnAction(event -> filterNomForMed());
        btnFlibelle.setOnAction(event -> filterLibelleForPat());
        btnaffecter.setOnAction(event -> affecterMedToPat());
        btnSupprimer.setOnAction(event -> supprimerAffecterMedToPat());
        btnpdfmed.setOnAction(event -> printMedicamentData());
        btnpdfpat.setOnAction(event -> printPatientData());


    }

    // crud patient -------------------------------------

    private void handleActionAddPatient(){
        this.action = "addPatient";
        vide();
        cinField.setEditable(true);
        nomField.setEditable(true);
        prenomField.setEditable(true);
        sexeField.setEditable(true);
        telField.setEditable(true);
    }
    private void handleActionEditPatient(){
        this.action = "editPatient";
        cinField.setEditable(false);
        nomField.setEditable(true);
        prenomField.setEditable(true);
        sexeField.setEditable(true);
        telField.setEditable(true);
    }
    private void handleActionOkPatient() {
        if (this.action.equals("addPatient")) {
            handleAdd();
            this.action = "" ;
        } else if (this.action.equals("editPatient")) {
            handleEdit();
            this.action = "" ;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("Try add or edit action then click ok!!");
            alert.showAndWait();
            return;
        }
    }

    private void filterPatient(){
        try {

            if (patientImplUtil != null && patientTable != null && prenomColumn != null && nomColumn != null) {
                ObservableList<Patient> liste = patientImplUtil.rechercherPatient(recherchePatientField.getText());
                prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                if (liste != null) {


                    patientTable.setItems(liste);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Invalid action");
                    alert.setHeaderText("Please correct invalid action");
                    alert.setContentText("list is null!");
                    alert.showAndWait();
                    return;

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid action");
                alert.setHeaderText("Please correct invalid action");
                alert.setContentText("One or more objects are not initialized!");
                alert.showAndWait();
                return;

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred while initializing graphique: " + e.getMessage());
        }
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
        if (sexeField.getText() == null || sexeField.getText().length() == 0) {
            errorMessage += "No valid sexe !\n";
        }
        if (telField.getText() == null || telField.getText().length() == 0) {
            errorMessage += "No valid telephone  !\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    private void showData(Patient p){
        cinField.setText(p.getCin());
        nomField.setText(p.getNom());
        prenomField.setText(p.getPrenom());
        sexeField.setText(p.getSexe());
        telField.setText(p.getTel());

        cinField.setEditable(false);
        nomField.setEditable(false);
        prenomField.setEditable(false);
        sexeField.setEditable(false);
        telField.setEditable(false);

    }
    private void handleAdd() {
        if (isInputValid()) {
            Patient p = new Patient();
            p.setCin(cinField.getText());
            p.setNom(nomField.getText());
            p.setPrenom(prenomField.getText());
            p.setSexe(sexeField.getText());
            p.setCinPersonnel(this.person.getCin());
            p.setTel(telField.getText());
            this.patientImplUtil.ajouterPatient(p);
            vide();
            initGraphique();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("Try add  action then click ok!!");
            alert.showAndWait();
            return;
        }
    }

    private void handleEdit() {
        if (isInputValid()) {
            Patient oldP = patientTable.getSelectionModel().getSelectedItem();
            Patient p = new Patient();
            p.setCin(cinField.getText());
            p.setNom(nomField.getText());
            p.setPrenom(prenomField.getText());
            p.setSexe(sexeField.getText());
            p.setCinPersonnel(oldP.getCinPersonnel());
            p.setTel(telField.getText());
            this.patientImplUtil.modifierPatient(p);
            vide();
            initGraphique();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("Try edit action then click ok!!");
            alert.showAndWait();
            return;
        }
    }

    private void vide(){
        cinField.setText("");
        nomField.setText("");
        prenomField.setText("");
        sexeField.setText("");
        telField.setText("");
    }
    private void handleDelete(){
        Patient p = patientTable.getSelectionModel().getSelectedItem();
        if (p != null) {
            boolean deleted = patientImplUtil.supprimerPatient(p.getCin());
            if (deleted) {
                patientTable.getItems().remove(p);
                vide();
                System.out.println("Personnel deleted successfully.");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid action");
                alert.setHeaderText("Please correct invalid action");
                alert.setContentText(" element not deleted !");
                alert.showAndWait();
                return;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("Try element to deleted then click ok!!");
            alert.showAndWait();
            return;

        }
    }

    // crud medicament -----------------------------------
    private void handleActionAddMedicament() {
        this.action = "addMedicament";
        videM();
        refField.setEditable(true);
        libelleField.setEditable(true);
        prixField.setEditable(true);
    }

    private void handleActionEditMedicament() {
        this.action = "editMedicament";
        refField.setEditable(false);
        libelleField.setEditable(true);
        prixField.setEditable(true);
    }

    private void handleActionOkMedicament() {
        System.out.println(this.action);
        if (this.action.equals("addMedicament")) {
            handleAddM();
            this.action = "";
        } else if (this.action.equals("editMedicament")) {
            handleEditM();
            this.action = "";
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("Try add or edit action then click ok!!");
            alert.showAndWait();
        }
    }

    private void filterparlibelle(){
        try {

            if (this.medicamentImplUtil != null && medicamentTable != null && prixColumn != null && libelleColumn != null) {
                ObservableList<Medicament> liste = medicamentImplUtil.rechercherMedicament(filtermedField.getText());
                libelleColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
                if (liste != null) {

                    System.out.println("List is medcament");
                    medicamentTable.setItems(liste);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Invalid action");
                    alert.setHeaderText("Please correct invalid action");
                    alert.setContentText("list is null ");
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid action");
                alert.setHeaderText("Please correct invalid action");
                alert.setContentText("create something then click ok!!");
                alert.showAndWait();
                return;

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred while initializing graphique: " + e.getMessage());
        }
    }

    private boolean isInputValidM() {
        String errorMessage = "";
        try {
            int reference = Integer.parseInt(refField.getText());
            if (reference <= 0) {
                errorMessage += "Reference must be a positive integer!\n";
            }
        } catch (NumberFormatException e) {
            errorMessage += "Invalid reference format! Please enter a valid integer.\n";
        }

        if (libelleField.getText() == null || libelleField.getText().isEmpty()) {
            errorMessage += "No valid libelle!\n";
        }

        try {
            double prix = Double.parseDouble(prixField.getText());
            if (prix <= 0) {
                errorMessage += "Price must be a positive number!\n";
            }
        } catch (NumberFormatException e) {
            errorMessage += "Invalid price format! Please enter a valid number.\n";
        }

        if (errorMessage.isEmpty()) {
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

    private void handleAddM() {
        if (isInputValidM()) {
            Medicament m = new Medicament();
            m.setRef(Integer.parseInt(refField.getText()));
            m.setLibelle(libelleField.getText());
            m.setPrix(Double.parseDouble(prixField.getText()));

            this.medicamentImplUtil.ajouterMedicament(m);
            videM();
            initGraphique();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("information not valide ok!!");
            alert.showAndWait();
            return;
        }
    }

    private void handleEditM() {
        if (isInputValidM()) {
            Medicament m = new Medicament();
            Medicament oldM = medicamentTable.getSelectionModel().getSelectedItem();

            m.setRef(oldM.getRef());
            m.setLibelle(libelleField.getText());
            m.setPrix(Double.parseDouble(prixField.getText()));

            this.medicamentImplUtil.modifierMedicament(m);
            videM();
            initGraphique();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("edit invalide !!");
            alert.showAndWait();
            return;
        }
    }
    private void showDataM(Medicament m){
        refField.setText(String.valueOf(m.getRef()));
        libelleField.setText(m.getLibelle());
        prixField.setText(String.valueOf(m.getPrix()));
        refField.setEditable(false);
        libelleField.setEditable(false);
        prixField.setEditable(false);
    }
    private void handleDeleteM(){
        Medicament oldM = medicamentTable.getSelectionModel().getSelectedItem();
        if (oldM != null) {
            boolean deleted = medicamentImplUtil.supprimerMedicament(oldM.getRef());
            if (deleted) {
                medicamentTable.getItems().remove(oldM);
                videM();
                System.out.println("Personnel deleted successfully.");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid action");
                alert.setHeaderText("Please correct invalid action");
                alert.setContentText("delete invalide !");
                alert.showAndWait();
                return;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("Try element then check delete button ok!!");
            alert.showAndWait();
            return;
        }
    }
    private void videM(){
        libelleField.setText("");
        refField.setText("");
        prixField.setText("");

    }
//-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    private void filterNomForMed(){
        try {

            //---------------------------------------------------------------
            if (patientImplUtil != null && patImedTable != null && patprenomColumn != null && patnomColumn != null) {
                ObservableList<Patient> liste = patientImplUtil.rechercherPatient(cherPnomField.getText());
                patnomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                patprenomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
                if (liste != null) {


                    patImedTable.setItems(liste);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Invalid action");
                    alert.setHeaderText("Please correct invalid action");
                    alert.setContentText("liste null !!");
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid action");
                alert.setHeaderText("Please correct invalid action");
                alert.setContentText("create text first then click ok!!");
                alert.showAndWait();
                return;
            }
            //----------------------------------------

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred while initializing graphique: " + e.getMessage());
        }
    }
    private void filterLibelleForPat(){
        try {

            //--------------------------------------
            if (this.medicamentImplUtil != null && medIpatTable != null && medpriColumn != null && medlibColumn != null) {
                ObservableList<Medicament> liste = medicamentImplUtil.rechercherMedicament(cherMlibField.getText());
                medlibColumn.setCellValueFactory(new PropertyValueFactory<>("libelle"));
                medpriColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
                if (liste != null) {
                    medIpatTable.setItems(liste);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Invalid action");
                    alert.setHeaderText("Please correct invalid action");
                    alert.setContentText("list is null !");
                    alert.showAndWait();
                    return;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid action");
                alert.setHeaderText("Please correct invalid action");
                alert.setContentText("create text first then click ok!!");
                alert.showAndWait();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred while initializing graphique: " + e.getMessage());
        }
    }
    private void affecterMedToPat(){
        // Check if both a medicament and a patient are selected
        if (medIpatTable.getSelectionModel().isEmpty() || patImedTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid selection");
            alert.setHeaderText("Please correct invalid selection");
            alert.setContentText("select a patient and a medicament pour termine affectation");
            alert.showAndWait();
            return;
        }

        Medicament m = medIpatTable.getSelectionModel().getSelectedItem();
        Patient p = patImedTable.getSelectionModel().getSelectedItem();
        PatientMed pm = new PatientMed();
        pm.setCinPat(p.getCin());
        pm.setRefMed(m.getRef());
        pm.setCinPer(this.person.getCin());

        // Add alert if operation is invalid
        boolean isValid = this.patientMedImplUtil.ajouterPatientMed(pm);
        if (!isValid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid ajouter");
            alert.setHeaderText("Please ensure ");
            alert.setContentText("this patient "+p.getNom()+" et medicament "+m.getLibelle()+"  deja existe dans la list de affectation");
            alert.showAndWait();
            return;
        }

        initGraphique();
    }

    private void supprimerAffecterMedToPat(){
        // Check if a patient medicament is selected
        if (affecterTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid selection");
            alert.setHeaderText("Please correct invalid selection");
            alert.setContentText("pour supprimer selectionner a affectaion ");
            alert.showAndWait();
            return;

        }

        PatientMed pm = affecterTable.getSelectionModel().getSelectedItem();

        // Add alert if operation is invalid
        boolean isValid = this.patientMedImplUtil.supprimerPatientMed(pm.getRefMed(),pm.getCinPat());
        if (!isValid) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid action");
            alert.setHeaderText("Please correct invalid action");
            alert.setContentText("supprimer not affecter ");
            alert.showAndWait();
            return;
        }

        initGraphique();
    }
    private void printMedicamentData() {
        Medicament m = medIpatTable.getSelectionModel().getSelectedItem();
        if (m == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Impression");
            alert.setHeaderText("Please choose");
            alert.setContentText("Impression impossible: please choose a medicament.");
            alert.showAndWait();
            return;
        } else {
            ObservableList<PatientMed> liste = this.patientMedImplUtil.rechercherPatientMedByRefMed(m.getRef());
            VBox printableContent = new VBox();
            printableContent.getChildren().addAll(
                    new Label("---------------------"),
                    new Label("Medicament Information:"),
                    new Label("Reference: " + m.getRef()),
                    new Label("Libelle: " + m.getLibelle()),
                    new Label("Prix: " + m.getPrix()),
                    new Label("---------------------------------------------"),
                    new Label("List of Patients consuming this Medicament:"),
                    new Label("---------------------------------------------")
            );

            for (PatientMed pm : liste) {
                Patient p = this.patientImplUtil.getPatientByCin(pm.getCinPat());
                printableContent.getChildren().addAll(
                        new Label("Patient Information:"),
                        new Label("Cin: " + p.getCin()),
                        new Label("Nom: " + p.getNom()),
                        new Label("Prenom: " + p.getPrenom()),
                        new Label("Sexe: " + p.getSexe()),
                        new Label("Telephone: " + p.getTel()),
                        new Label("Cin Personnel affecting patient: " + p.getCinPersonnel()),
                        new Label("----------")
                );
            }

            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null) {
                boolean success = printerJob.printPage(printableContent);
                if (success) {
                    printerJob.endJob();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Impression");
                alert.setHeaderText("Error during printing");
                alert.setContentText("Unable to create print job.");
                alert.showAndWait();
            }
        }
    }

    private void printPatientData() {
        Patient p = patImedTable.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Impression");
            alert.setHeaderText("Please choose");
            alert.setContentText("Impression impossible: please choose a Patient.");
            alert.showAndWait();
            return;
        } else {
            ObservableList<PatientMed> liste = this.patientMedImplUtil.rechercherPatientMedByPatient(p.getCin());
            VBox printableContent = new VBox();
            printableContent.getChildren().addAll(
                    new Label("---------------------"),
                    new Label("Patient Information:"),
                    new Label("Cin: " + p.getCin()),
                    new Label("Nom: " + p.getNom()),
                    new Label("Prenom: " + p.getPrenom()),
                    new Label("Sexe: " + p.getSexe()),
                    new Label("Telephone: " + p.getTel()),
                    new Label("Cin Personnel affecting patient: " + p.getCinPersonnel()),
                    new Label("---------------------------------------------"),
                    new Label("List of Medicaments consuming of this Patient:"),
                    new Label("---------------------------------------------")
            );

            for (PatientMed pm : liste) {
                Medicament m = this.medicamentImplUtil.getMedicamentByRef(pm.getRefMed());
                printableContent.getChildren().addAll(
                        new Label("Medicament Information:"),
                        new Label("Reference: " + m.getRef()),
                        new Label("Libelle: " + m.getLibelle()),
                        new Label("Prix: " + m.getPrix()),
                        new Label("----------")
                );
            }

            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null) {
                boolean success = printerJob.printPage(printableContent);
                if (success) {
                    printerJob.endJob();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Impression");
                alert.setHeaderText("Error during printing");
                alert.setContentText("Unable to create print job.");
                alert.showAndWait();
            }
        }
    }


    private void logOut(){
        Platform.exit();
    }
}
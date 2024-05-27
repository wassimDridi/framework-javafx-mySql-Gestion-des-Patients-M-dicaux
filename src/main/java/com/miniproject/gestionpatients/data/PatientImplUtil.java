package com.miniproject.gestionpatients.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class PatientImplUtil implements IPatientUtil {
    private static String dernierTitreErreur = "";
    private static String dernierMessageErreur = "";

    private static String JDBC_Driver = "com.mysql.cj.jdbc.Driver";

    private static String DB_Url = "jdbc:mysql://localhost:3306/gestion_patients?user=root&password=";

    public static String getDernierTitreErreur() {
        return dernierTitreErreur;
    }

    public static void setDernierTitreErreur(String dernierTitreErreur) {
        PatientImplUtil.dernierTitreErreur = dernierTitreErreur;
    }

    public static String getDernierMessageErreur() {
        return dernierMessageErreur;
    }

    public static void setDernierMessageErreur(String dernierMessageErreur) {
        PatientImplUtil.dernierMessageErreur = dernierMessageErreur;
    }

    @Override
    public ObservableList<Patient> getPatient() {
        ObservableList<Patient> liste = FXCollections.observableArrayList();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM patient";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String cin = rs.getString("cin");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String sexe = rs.getString("sexe");
                String tel = rs.getString("tel");
                String cinPersonnel = rs.getString("cinPersonnel");
                Patient patient = new Patient(cin, nom, prenom, sexe, tel, cinPersonnel);
                liste.add(patient);
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return liste;
    }

    @Override
    public Patient getPatientByCin(String cin) {
        Patient patient = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM patient WHERE cin=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String sexe = rs.getString("sexe");
                String tel = rs.getString("tel");
                String cinPersonnel = rs.getString("cinPersonnel");
                patient = new Patient(cin, nom, prenom, sexe, tel, cinPersonnel);
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return patient;
    }

    @Override
    public boolean ajouterPatient(Patient patient) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "INSERT INTO patient(cin, nom, prenom, sexe, tel, cinPersonnel) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getCin());
            stmt.setString(2, patient.getNom());
            stmt.setString(3, patient.getPrenom());
            stmt.setString(4, patient.getSexe());
            stmt.setString(5, patient.getTel());
            stmt.setString(6, patient.getCinPersonnel());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return success;
    }

    @Override
    public boolean modifierPatient(Patient patient) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "UPDATE patient SET nom=?, prenom=?, sexe=?, tel=?, cinPersonnel=? WHERE cin=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getSexe());
            stmt.setString(4, patient.getTel());
            stmt.setString(5, patient.getCinPersonnel());
            stmt.setString(6, patient.getCin());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return success;
    }

    @Override
    public ObservableList<Patient> rechercherPatient(String nom) {
        ObservableList<Patient> liste = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM patient WHERE nom LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nom + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String cin = rs.getString("cin");
                String patientNom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String sexe = rs.getString("sexe");
                String tel = rs.getString("tel");
                String cinPersonnel = rs.getString("cinPersonnel");
                Patient patient = new Patient(cin, patientNom, prenom, sexe, tel, cinPersonnel);
                liste.add(patient);
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return liste;
    }

    @Override
    public boolean supprimerPatient(String cin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "DELETE FROM patient WHERE cin=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cin);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return success;
    }
}

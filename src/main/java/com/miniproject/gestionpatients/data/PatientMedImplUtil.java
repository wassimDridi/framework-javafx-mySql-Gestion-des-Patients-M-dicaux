package com.miniproject.gestionpatients.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class PatientMedImplUtil implements IPatientMedUtil {
    private static String dernierTitreErreur = "";
    private static String dernierMessageErreur = "";

    private static String JDBC_Driver = "com.mysql.cj.jdbc.Driver";

    private static String DB_Url = "jdbc:mysql://localhost:3306/gestion_patients?user=root&password=";

    public static String getDernierTitreErreur() {
        return dernierTitreErreur;
    }

    public static void setDernierTitreErreur(String dernierTitreErreur) {
        PatientMedImplUtil.dernierTitreErreur = dernierTitreErreur;
    }

    public static String getDernierMessageErreur() {
        return dernierMessageErreur;
    }

    public static void setDernierMessageErreur(String dernierMessageErreur) {
        PatientMedImplUtil.dernierMessageErreur = dernierMessageErreur;
    }
    @Override
    public ObservableList<PatientMed> getPatientMed() {
        ObservableList<PatientMed> liste = FXCollections.observableArrayList();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM patientmed";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int refMed = rs.getInt("refMed");
                String cinPat = rs.getString("cinPat");
                String cinPer = rs.getString("cinPer");
                PatientMed patientMed = new PatientMed(refMed, cinPat, cinPer);
                liste.add(patientMed);
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
    public PatientMed getPatientMedByRefMed(int refMed) {
        PatientMed patientMed = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM patientmed WHERE refMed=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, refMed);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String cinPat = rs.getString("cinPat");
                String cinPer = rs.getString("cinPer");
                patientMed = new PatientMed(refMed, cinPat, cinPer);
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
        return patientMed;
    }

    @Override
    public boolean ajouterPatientMed(PatientMed pm) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "INSERT INTO patientmed(refMed, cinPat, cinPer) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pm.getRefMed());
            stmt.setString(2, pm.getCinPat());
            stmt.setString(3, pm.getCinPer());
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
    public boolean modifierPatientMed(PatientMed pm) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "UPDATE patientmed SET cinPat=?, cinPer=? WHERE refMed=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pm.getCinPat());
            stmt.setString(2, pm.getCinPer());
            stmt.setInt(3, pm.getRefMed());
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
    public ObservableList<PatientMed> rechercherPatientMedByRefMed(int refMed) {
        ObservableList<PatientMed> liste = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM patientmed WHERE refMed=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, refMed);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String cinPat = rs.getString("cinPat");
                String cinPer = rs.getString("cinPer");
                PatientMed patientMed = new PatientMed(refMed, cinPat, cinPer);
                liste.add(patientMed);
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
    public ObservableList<PatientMed> rechercherPatientMedByPatient(String cinPat) {
        ObservableList<PatientMed> liste = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM patientmed WHERE cinPat=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cinPat);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int refMed = rs.getInt("refMed");
                String cinPer = rs.getString("cinPer");
                PatientMed patientMed = new PatientMed(refMed, cinPat, cinPer);
                liste.add(patientMed);
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
    public ObservableList<PatientMed> rechercherPatientMedByPersonnel(String cinPer) {
        ObservableList<PatientMed> liste = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM patientmed WHERE cinPer=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cinPer);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int refMed = rs.getInt("refMed");
                String cinPat = rs.getString("cinPat");
                PatientMed patientMed = new PatientMed(refMed, cinPat, cinPer);
                liste.add(patientMed);
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
    public boolean supprimerPatientMed(int refMed, String cinPat) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "DELETE FROM patientmed WHERE refMed=? AND cinPat=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, refMed);
            stmt.setString(2, cinPat);
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

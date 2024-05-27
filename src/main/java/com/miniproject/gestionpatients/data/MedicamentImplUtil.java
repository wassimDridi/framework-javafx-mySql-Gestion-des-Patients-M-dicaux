package com.miniproject.gestionpatients.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class MedicamentImplUtil implements IMedicamentUtil {
    private static String dernierTitreErreur = "";
    private static String dernierMessageErreur = "";

    private static String JDBC_Driver = "com.mysql.cj.jdbc.Driver";

    private static String DB_Url = "jdbc:mysql://localhost:3306/gestion_patients?user=root&password=";
    public static String getDernierTitreErreur() {
        return dernierTitreErreur;
    }

    public static void setDernierTitreErreur(String dernierTitreErreur) {
        MedicamentImplUtil.dernierTitreErreur = dernierTitreErreur;
    }

    public static String getDernierMessageErreur() {
        return dernierMessageErreur;
    }

    public static void setDernierMessageErreur(String dernierMessageErreur) {
        MedicamentImplUtil.dernierMessageErreur = dernierMessageErreur;
    }
    @Override
    public ObservableList<Medicament> getMedicament() {
        ObservableList<Medicament> liste = FXCollections.observableArrayList();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM medicament";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int ref = rs.getInt("ref");
                String libelle = rs.getString("libelle");
                double prix = rs.getDouble("prix");
                Medicament medicament = new Medicament(ref, libelle, prix);
                liste.add(medicament);
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
    public Medicament getMedicamentByRef(int ref) {
        Medicament medicament = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM medicament WHERE ref=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ref);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String libelle = rs.getString("libelle");
                double prix = rs.getDouble("prix");
                medicament = new Medicament(ref, libelle, prix);
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
        return medicament;
    }

    @Override
    public boolean ajouterMedicament(Medicament medicament) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "INSERT INTO medicament(ref, libelle, prix) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, medicament.getRef());
            stmt.setString(2, medicament.getLibelle());
            stmt.setDouble(3, medicament.getPrix());
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
    public boolean modifierMedicament(Medicament medicament) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "UPDATE medicament SET libelle=?, prix=? WHERE ref=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, medicament.getLibelle());
            stmt.setDouble(2, medicament.getPrix());
            stmt.setInt(3, medicament.getRef());
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
    public ObservableList<Medicament> rechercherMedicament(String libelle) {
        ObservableList<Medicament> liste = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM medicament WHERE libelle LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + libelle + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int ref = rs.getInt("ref");
                String medicamentLibelle = rs.getString("libelle");
                double prix = rs.getDouble("prix");
                Medicament medicament = new Medicament(ref, medicamentLibelle, prix);
                liste.add(medicament);
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
    public boolean supprimerMedicament(int ref) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "DELETE FROM medicament WHERE ref=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ref);
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

package com.miniproject.gestionpatients.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class PersonnelImplUtil implements IPersonnelUtil {
    private static String dernierTitreErreur = "";
    private static String dernierMessageErreur = "";

    private static String JDBC_Driver = "com.mysql.cj.jdbc.Driver";

    private static String DB_Url = "jdbc:mysql://localhost:3306/gestion_patients?user=root&password=";

    public static String getDernierTitreErreur() {
        return dernierTitreErreur;
    }

    public static void setDernierTitreErreur(String dernierTitreErreur) {
        PersonnelImplUtil.dernierTitreErreur = dernierTitreErreur;
    }

    public static String getDernierMessageErreur() {
        return dernierMessageErreur;
    }

    public static void setDernierMessageErreur(String dernierMessageErreur) {
        PersonnelImplUtil.dernierMessageErreur = dernierMessageErreur;
    }
    @Override
    public ObservableList<Personnel> getPersonnel() {
        ObservableList<Personnel> liste = FXCollections.observableArrayList();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM personnel";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Personnel personnel = new Personnel(rs.getString("cin"), rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("password"), rs.getString("fonction"));
                liste.add(personnel);
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
    public Personnel getPersonnelByCin(String cin) {
        Personnel personnel = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM personnel WHERE cin=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                personnel = new Personnel(rs.getString("cin"), rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("password"), rs.getString("fonction"));
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return personnel;
    }

    @Override
    public boolean ajouterPersonnel(Personnel p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "INSERT INTO personnel(cin, nom, prenom, login, password, fonction) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getCin());
            stmt.setString(2, p.getNom());
            stmt.setString(3, p.getPrenom());
            stmt.setString(4, p.getLogin());
            stmt.setString(5, p.getPassword());
            stmt.setString(6, p.getFonction());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
    public boolean modifierPersonnel(Personnel p) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "UPDATE personnel SET nom=?, prenom=?, login=?, password=?, fonction=? WHERE cin=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNom());
            stmt.setString(2, p.getPrenom());
            stmt.setString(3, p.getLogin());
            stmt.setString(4, p.getPassword());
            stmt.setString(5, p.getFonction());
            stmt.setString(6, p.getCin());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
    public Personnel getPersonnelByloginPassword(String login, String password) {
        Personnel personnel = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "select * from personnel where login= ? and password= ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,login);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                personnel = new Personnel(rs.getString("cin"), rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("password"), rs.getString("fonction"));
            }

            rs.close();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return personnel;
    }


    @Override
    public ObservableList<Personnel> rechercherPersonnel(String nomR) {
        ObservableList<Personnel> liste = FXCollections.observableArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);
            String sql = "SELECT * FROM personnel WHERE nom LIKE ? ORDER BY cin";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomR + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Personnel personnel = new Personnel(rs.getString("cin"), rs.getString("nom"), rs.getString("prenom"), rs.getString("login"), rs.getString("password"), rs.getString("fonction"));
                liste.add(personnel);
            }
            rs.close();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
    public boolean supprimerPersonnel(String cin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;
        try {
            Class.forName(JDBC_Driver);
            conn = DriverManager.getConnection(DB_Url);


            String sql = "DELETE FROM personnel WHERE cin = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cin);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                success = true;
            }
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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

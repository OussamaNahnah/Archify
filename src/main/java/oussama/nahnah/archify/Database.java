//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package oussama.nahnah.archify;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Database {
    public Connection databaseLink;

    public Database() {
    }

    public Connection getDBConnection() {
        String url = "jdbc:sqlite:DataBase/db.db";

        try {
            this.databaseLink = DriverManager.getConnection(url);
            System.out.print("Connected");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return this.databaseLink;
    }

    int envCountSearch( boolean TYPE,String ref,String date,String recepient ,String object) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;
        String querry ="";

        try {
            if (TYPE){
                querry = "SELECT count(1) FROM sended WHERE reference LIKE  ? AND  date LIKE  ? AND  recipient LIKE  ? AND object LIKE  ?  ";

            }else{
                querry = "SELECT count(1) FROM sended WHERE reference LIKE  ? OR  date LIKE  ? OR  recipient LIKE  ? OR object LIKE  ?  ";


            }

            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + ref+ "%");
            ps.setString(2, "%" + date + "%");
            ps.setString(3, "%" + recepient + "%");
            ps.setString(4, "%" + object + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {

                System.out.println(" yes excited " + count);
            } else {
                System.out.print("Not excited");
            }
        } catch (Exception var23) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var23.getMessage());
            var23.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException var22) {
                    var22.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException var21) {
                    var21.printStackTrace();
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException var20) {
                    var20.printStackTrace();
                }
            }

        }

        return count;
    }
    ObservableList<Sended> envSeaAll(int from, int to,boolean TYPE, String ref,String date,String recepient ,String object ) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String querry ="";

        try {
            if (TYPE){
                  querry = "SELECT * FROM sended  WHERE  reference LIKE  ? AND  date LIKE  ? AND  recipient LIKE  ? AND object LIKE  ?   ORDER BY date DESC  LIMIT ? , ?  ";

            }else{
                  querry = "SELECT * FROM sended  WHERE  reference LIKE  ? OR  date LIKE  ? OR  recipient LIKE  ? OR object LIKE  ?   ORDER BY date DESC  LIMIT ? , ?  ";


            }
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + ref+ "%");
            ps.setString(2, "%" + date + "%");
            ps.setString(3, "%" + recepient + "%");
            ps.setString(4, "%" + object + "%");
            ps.setInt(5, from);
            ps.setInt(6, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id_ = rs.getInt("id");
                String reference_ = rs.getString("reference");
                String date_ = rs.getString("date");
                String recipient_ = rs.getString("recipient");
                String object_ = rs.getString("object");
                String path_ = rs.getString("path");
                sendedObservableList.add(new Sended(id_, reference_, date_, recipient_, object_, path_));
            }
        } catch (Exception var19) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var19.getMessage());
            var19.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

        }

        return sendedObservableList;
    }

    int recCountSearch( boolean TYPE,String ref,String oldref,String date,String sender ,String object) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;
        String querry ="";

        try {
            if (TYPE){
                querry = "SELECT count(1)  FROM received WHERE  reference LIKE  ?  AND old_reference LIKE  ?  AND  date LIKE  ? AND  sender LIKE  ? AND object LIKE  ?  ";

            }else{
                querry = "SELECT count(1)  FROM received WHERE  reference LIKE  ?  OR old_reference LIKE  ?  OR  date LIKE  ? OR  sender LIKE  ? OR object LIKE  ?  ";


            }

            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + ref+ "%");
            ps.setString(2, "%" + oldref + "%");
            ps.setString(3, "%" + date + "%");
            ps.setString(4, "%" + sender + "%");
            ps.setString(5, "%" + object + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println(" yes excited " + count);
            } else {
                System.out.print("Not excited");
            }
        } catch (Exception var23) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var23.getMessage());
            var23.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException var22) {
                    var22.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException var21) {
                    var21.printStackTrace();
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException var20) {
                    var20.printStackTrace();
                }
            }

        }

        return count;
    }
    ObservableList<Received> recSeaAll(int from, int to,boolean TYPE,String ref,String oldref,String date,String sender ,String object ) throws SQLException {
        ObservableList<Received> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String querry ="";
   try {
            if (TYPE){
                querry = "SELECT * FROM received  WHERE   reference LIKE  ?  AND old_reference LIKE  ?  AND  date LIKE  ? AND  sender LIKE  ? AND object LIKE  ?  ORDER BY date DESC  LIMIT ? , ?  ";

            }else{
                querry = "SELECT * FROM received  WHERE    reference LIKE  ?  OR old_reference LIKE  ?  OR  date LIKE  ? OR  sender LIKE  ? OR object LIKE  ? ORDER BY date DESC  LIMIT ? , ?  ";


            }
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + ref+ "%");
            ps.setString(2, "%" + oldref + "%");
            ps.setString(3, "%" + date + "%");
            ps.setString(4, "%" + sender + "%");
            ps.setString(5, "%" + object + "%");
            ps.setInt(6, from);
            ps.setInt(7, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id_ = rs.getInt("id");
                String reference_ = rs.getString("reference");
                String old_reference_ = rs.getString("old_reference");
                String date_ = rs.getString("date");
                String sender_ = rs.getString("sender");
                String object_ = rs.getString("object");
                String path_ = rs.getString("path");
                sendedObservableList.add(new Received(id_, reference_, old_reference_, date_, sender_, object_, path_));
            }
        } catch (Exception var19) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var19.getMessage());
            var19.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }

        }

        return sendedObservableList;
    }

    }

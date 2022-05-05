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
/*
    int Env_sendedCount() {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1) FROM sended ";
            ps = connection.prepareStatement(querry);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != -1) {
                System.out.println(" yes excited " + count);
            } else {
                System.out.print("Not excited");
            }
        } catch (Exception var22) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var22.getMessage());
            var22.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException var21) {
                    var21.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException var20) {
                    var20.printStackTrace();
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException var19) {
                    var19.printStackTrace();
                }
            }

        }

        return count;
    }
    ObservableList<Sended> Env_getSended(int from, int to) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Connection connection = this.getDBConnection();
        String querry = "SELECT * FROM sended  ORDER BY date DESC  LIMIT " + from + " , " + to;
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(querry);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var17) {
            var17.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (rs != null) {
                rs.close();
            }

        }

        return sendedObservableList;
    }


    public int Rec_receivedCount() {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1) FROM received ";
            ps = connection.prepareStatement(querry);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != -1) {
                System.out.println(" yes excited " + count);
            } else {
                System.out.print("Not excited");
            }
        } catch (Exception var22) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var22.getMessage());
            var22.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException var21) {
                    var21.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException var20) {
                    var20.printStackTrace();
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException var19) {
                    var19.printStackTrace();
                }
            }

        }

        return count;
    }
    public ObservableList Rec_getReceived(int from, int to) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Connection connection = this.getDBConnection();
        String querry = "SELECT * FROM received  ORDER BY date DESC  LIMIT " + from + " , " + to;
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(querry);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var18) {
            var18.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (rs != null) {
                rs.close();
            }

        }

        return ReceivedObservableList;
    }
*/
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
                System.out.println("DDDDd");
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
/*
        System.out.println("text:");
        System.out.println("envTempType:"+TYPE);
        System.out.println("envTempRef:"+ref);
        System.out.println("envTempDate:"+date );
        System.out.println("envTempRec:"+recepient);
        System.out.println("envTempObj:"+object);
*/
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

        System.out.println("text:");
        System.out.println("*****************************envTempType:"+TYPE);
        System.out.println("*****************************envTempRef:"+ref);
        System.out.println("*****************************oldref:"+oldref );
        System.out.println("*****************************date:"+date);
        System.out.println("*****************************sender:"+sender);
        System.out.println("*****************************object:"+object);

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
                System.out.println("DDDDd");
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

    /*
    public ObservableList Rec_getReceived() throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Connection connection = this.getDBConnection();
        String querry = "SELECT * FROM received  ORDER BY date DESC  ";
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(querry);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (rs != null) {
                rs.close();
            }

        }

        return ReceivedObservableList;
    }

    int Env_sendedCountByYear(String year) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1) FROM sended WHERE date LIKE ?";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Env_sendedCountByCategory(String recipient) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1) FROM sended WHERE recipient = ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, recipient);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Env_sendedCountByYearCategory(String year, String recipient) {
        System.out.println("333333333333333333333333333333333333333333333333;" + year + "/" + recipient);
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1) FROM sended WHERE date LIKE ? AND recipient = ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year + "%");
            ps.setString(2, recipient);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
                System.out.println(" yes excited " + count);
            } else {
                System.out.print("Not excited");
            }
        } catch (Exception var24) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var24.getMessage());
            var24.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException var23) {
                    var23.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException var22) {
                    var22.printStackTrace();
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException var21) {
                    var21.printStackTrace();
                }
            }

        }

        return count;
    }

    int Env_sendedCountSeaReference(String temp_reference) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM sended WHERE  reference LIKE  ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_reference + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Env_sendedCountSeaDate(String temp_date) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM sended WHERE  date LIKE  ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_date + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Env_sendedCountSeaRecipient(String temp_recipient) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM sended WHERE  recipient LIKE  ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_recipient + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Env_sendedCountSeaObject(String temp_object) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM sended WHERE  object LIKE  ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_object + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Env_sendedCountSeaAll(String temp) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM sended WHERE  reference LIKE  ? OR  date LIKE  ? OR  recipient LIKE  ? OR object LIKE  ?   ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp + "%");
            ps.setString(2, "%" + temp + "%");
            ps.setString(3, "%" + temp + "%");
            ps.setString(4, "%" + temp + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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


    ObservableList<Sended> Env_getSended() throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Connection connection = this.getDBConnection();
        String querry = "SELECT * FROM sended  ORDER BY date DESC   ";
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(querry);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (rs != null) {
                rs.close();
            }

        }

        return sendedObservableList;
    }

    ObservableList<Sended> Env_getSendedByYear(int from, int to, String year) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE date LIKE ? ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
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

    ObservableList<Sended> Env_getSendedByYear(String year) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE date LIKE ? ORDER BY date DESC ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var17) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var17.getMessage());
            var17.printStackTrace();
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

    ObservableList<Sended> Env_getSendedByCategory(String recipient_) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE recipient= ? ORDER BY date DESC ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, recipient_);
            rs = ps.executeQuery();
            System.out.println("ssssssssssssssssssssssssssssssssssss" + recipient_);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var17) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var17.getMessage());
            var17.printStackTrace();
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

    ObservableList<Sended> Env_getSendedByCategory(int from, int to, String recipient_) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE recipient= ? ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, recipient_);
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();
            System.out.println("ssssssssssssssssssssssssssssssssssss" + recipient_);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
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

    ObservableList<Sended> Env_getSendedByYearCategory(int from, int to, String year_, String recipient_) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE date LIKE ? AND recipient= ? ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year_ + "%");
            ps.setString(2, recipient_);
            ps.setInt(3, from);
            ps.setInt(4, to);
            rs = ps.executeQuery();
            System.out.println("ssssssssssssssssssssssssssssssssssss" + recipient_);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var20) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var20.getMessage());
            var20.printStackTrace();
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

    ObservableList<Sended> Env_getSendedByYearCategory(String year_, String recipient_) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE date LIKE ? AND recipient= ? ORDER BY date DESC   ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year_ + "%");
            ps.setString(2, recipient_);
            rs = ps.executeQuery();
            System.out.println("ssssssssssssssssssssssssssssssssssss" + recipient_);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var18) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var18.getMessage());
            var18.printStackTrace();
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

    ObservableList<Sended> Env_getSendedSeaReference(int from, int to, String temp_reference) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE reference LIKE ?  ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_reference + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
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

    ObservableList<Sended> Env_getSendedSeaReference(String temp_reference) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE reference LIKE ?  ORDER BY date DESC   ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_reference + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var17) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var17.getMessage());
            var17.printStackTrace();
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

    ObservableList<Sended> Env_getSendedSeaDate(int from, int to, String temp_date) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE date LIKE ?  ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_date + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
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

    ObservableList<Sended> Env_getSendedSeaDate(String temp_date) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE date LIKE ?  ORDER BY date DESC    ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_date + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var17) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var17.getMessage());
            var17.printStackTrace();
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

    ObservableList<Sended> Env_getSendedSeaRecipient(int from, int to, String temp_recipient) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE recipient LIKE ?  ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_recipient + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
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

    ObservableList<Sended> Env_getSendedSeaRecipient(String temp_recipient) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE recipient LIKE ?  ORDER BY date DESC  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_recipient + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var17) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var17.getMessage());
            var17.printStackTrace();
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

    ObservableList<Sended> Env_getSendedSeaObject(int from, int to, String temp_object) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE object LIKE ?  ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_object + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
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

    ObservableList<Sended> Env_getSendedSeaObject(String temp_object) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE object LIKE ?  ORDER BY date DESC   ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_object + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var17) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var17.getMessage());
            var17.printStackTrace();
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

    ObservableList<Sended> Env_getSendedSeaAll(int from, int to, String temp) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE  reference LIKE  ? OR  date LIKE  ? OR  recipient LIKE  ? OR object LIKE  ?   ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp + "%");
            ps.setString(2, "%" + temp + "%");
            ps.setString(3, "%" + temp + "%");
            ps.setString(4, "%" + temp + "%");
            ps.setInt(5, from);
            ps.setInt(6, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
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

    ObservableList<Sended> Env_getSendedSeaAll(String temp) throws SQLException {
        ObservableList<Sended> sendedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM sended  WHERE  reference LIKE  ? OR  date LIKE  ? OR  recipient LIKE  ? OR object LIKE  ?   ORDER BY date DESC  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp + "%");
            ps.setString(2, "%" + temp + "%");
            ps.setString(3, "%" + temp + "%");
            ps.setString(4, "%" + temp + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String date = rs.getString("date");
                String recipient = rs.getString("recipient");
                String object = rs.getString("object");
                String path = rs.getString("path");
                sendedObservableList.add(new Sended(id, reference, date, recipient, object, path));
            }
        } catch (Exception var17) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var17.getMessage());
            var17.printStackTrace();
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


    int Rec_receivedCountByYear(String year) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1) FROM received WHERE date LIKE ?";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Rec_receivedCountByCategory(String sender) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1) FROM received WHERE sender = ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, sender);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Rec_receivedCountByYearCategory(String year, String sender) {
        System.out.println("333333333333333333333333333333333333333333333333;" + year + "/" + sender);
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1) FROM received WHERE date LIKE ? AND sender = ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year + "%");
            ps.setString(2, sender);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
                System.out.println(" yes excited " + count);
            } else {
                System.out.print("Not excited");
            }
        } catch (Exception var24) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var24.getMessage());
            var24.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException var23) {
                    var23.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException var22) {
                    var22.printStackTrace();
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException var21) {
                    var21.printStackTrace();
                }
            }

        }

        return count;
    }

    int Rec_receivedCountSeaOldReference(String temp_reference) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM received WHERE  old_reference LIKE  ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_reference + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Rec_receivedCountSeaReference(String temp_reference) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM received WHERE  reference LIKE  ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_reference + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Rec_receivedCountSeaDate(String temp_date) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM received WHERE  date LIKE  ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_date + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Rec_receivedCountSeaSender(String temp_sender) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM received WHERE  sender LIKE  ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_sender + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Rec_receivedCountSeaObject(String temp_object) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM received WHERE  object LIKE  ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_object + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    int Rec_receivedCountSeaAll(String temp) {
        Connection connection = this.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = -1;

        try {
            String querry = "SELECT count(1)  FROM received WHERE  reference LIKE  ?  OR old_reference LIKE  ?  OR  date LIKE  ? OR  sender LIKE  ? OR object LIKE  ?   ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp + "%");
            ps.setString(2, "%" + temp + "%");
            ps.setString(3, "%" + temp + "%");
            ps.setString(4, "%" + temp + "%");
            ps.setString(5, "%" + temp + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

            if (count != 1) {
                System.out.println("DDDDd");
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

    public ObservableList Rec_getReceived(int from, int to) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Connection connection = this.getDBConnection();
        String querry = "SELECT * FROM received  ORDER BY date DESC  LIMIT " + from + " , " + to;
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(querry);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var18) {
            var18.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (rs != null) {
                rs.close();
            }

        }

        return ReceivedObservableList;
    }



    ObservableList<Received> Rec_getReceivedByYear(int from, int to, String year) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE date LIKE ? ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var20) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var20.getMessage());
            var20.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedByYear(String year) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE date LIKE ? ORDER BY date DESC    ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var18) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var18.getMessage());
            var18.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedByCategory(int from, int to, String sender) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE sender= ? ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, sender);
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();
            System.out.println("ssssssssssssssssssssssssssssssssssss" + sender);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender_ = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender_, object, path));
            }
        } catch (Exception var20) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var20.getMessage());
            var20.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedByCategory(String sender) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE sender= ? ORDER BY date DESC  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, sender);
            rs = ps.executeQuery();
            System.out.println("ssssssssssssssssssssssssssssssssssss" + sender);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender_ = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender_, object, path));
            }
        } catch (Exception var18) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var18.getMessage());
            var18.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedByYearCategory(int from, int to, String year_, String sender_) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE date LIKE ? AND sender= ? ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year_ + "%");
            ps.setString(2, sender_);
            ps.setInt(3, from);
            ps.setInt(4, to);
            rs = ps.executeQuery();
            System.out.println("ssssssssssssssssssssssssssssssssssss" + sender_);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var21) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var21.getMessage());
            var21.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedByYearCategory(String year_, String sender_) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE date LIKE ? AND sender= ? ORDER BY date DESC   ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, year_ + "%");
            ps.setString(2, sender_);
            rs = ps.executeQuery();
            System.out.println("ssssssssssssssssssssssssssssssssssss" + sender_);

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaReference(int from, int to, String temp_reference) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE reference LIKE ?  ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_reference + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var20) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var20.getMessage());
            var20.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaReference(String temp_reference) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE reference LIKE ?  ORDER BY date DESC    ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_reference + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var18) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var18.getMessage());
            var18.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaOldReference(int from, int to, String temp_reference) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE old_reference LIKE ?  ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_reference + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var20) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var20.getMessage());
            var20.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaOldReference(String temp_reference) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE old_reference LIKE ?  ORDER BY date DESC    ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_reference + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var18) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var18.getMessage());
            var18.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaDate(int from, int to, String temp_date) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE date LIKE ?  ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_date + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var20) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var20.getMessage());
            var20.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaDate(String temp_date) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE date LIKE ?  ORDER BY date DESC    ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_date + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var18) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var18.getMessage());
            var18.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaSender(int from, int to, String temp_sender) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE sender LIKE ?  ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_sender + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var20) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var20.getMessage());
            var20.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaSender(String temp_sender) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE sender LIKE ?  ORDER BY date DESC   ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_sender + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var18) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var18.getMessage());
            var18.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaObject(int from, int to, String temp_object) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE object LIKE ?  ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_object + "%");
            ps.setInt(2, from);
            ps.setInt(3, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var20) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var20.getMessage());
            var20.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaObject(String temp_object) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE object LIKE ?  ORDER BY date DESC   ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp_object + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var18) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var18.getMessage());
            var18.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaAll(int from, int to, String temp) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE  reference LIKE  ? OR old_reference LIKE  ?  OR  date LIKE  ? OR  sender LIKE  ? OR object LIKE  ?   ORDER BY date DESC  LIMIT ? , ?  ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp + "%");
            ps.setString(2, "%" + temp + "%");
            ps.setString(3, "%" + temp + "%");
            ps.setString(4, "%" + temp + "%");
            ps.setString(5, "%" + temp + "%");
            ps.setInt(6, from);
            ps.setInt(7, to);
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var20) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var20.getMessage());
            var20.printStackTrace();
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

        return ReceivedObservableList;
    }

    ObservableList<Received> Rec_getReceivedSeaAll(String temp) throws SQLException {
        ObservableList<Received> ReceivedObservableList = FXCollections.observableArrayList();
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String querry = "SELECT * FROM received  WHERE  reference LIKE  ? OR old_reference LIKE  ?  OR  date LIKE  ? OR  sender LIKE  ? OR object LIKE  ?   ORDER BY date DESC   ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, "%" + temp + "%");
            ps.setString(2, "%" + temp + "%");
            ps.setString(3, "%" + temp + "%");
            ps.setString(4, "%" + temp + "%");
            ps.setString(5, "%" + temp + "%");
            rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String reference = rs.getString("reference");
                String old_reference = rs.getString("old_reference");
                String date = rs.getString("date");
                String sender = rs.getString("sender");
                String object = rs.getString("object");
                String path = rs.getString("path");
                ReceivedObservableList.add(new Received(id, reference, old_reference, date, sender, object, path));
            }
        } catch (Exception var18) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var18.getMessage());
            var18.printStackTrace();
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

        return ReceivedObservableList;
    }
*/
}

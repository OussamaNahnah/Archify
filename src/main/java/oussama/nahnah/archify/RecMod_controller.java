//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package oussama.nahnah.archify;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class RecMod_controller implements Initializable {
    FileChooser fileChooser;
    String fileExtension;
    String fileName;
    ObservableList<Received> receivedObservableList;
    Received modreceived;
    @FXML
    private Button Rec_update;
    @FXML
    private Button Rec_filepicker;
    @FXML
    private TextArea Rec_mod_object;
    @FXML
    private DatePicker Rec_mod_date;
    @FXML
    private ChoiceBox<String> Rec_mod_sender;
    @FXML
    private ChoiceBox<String> Rec_mod_category;
    @FXML
    private TextField Rec_mod_reference;
    @FXML
    private TextField Rec_mod_oldreference;

    String category ="";
    String referance ="";
    public RecMod_controller() {
    }

    public void setReceivedObservableList(ObservableList<Received> receivedObservableList) {
        System.out.print("gg" + receivedObservableList.size());
        this.receivedObservableList = receivedObservableList;
    }

    public void setReceivedItem(Received modreceived) {
        this.modreceived = modreceived;

        String str=modreceived.getReference();
          category = str.substring(0, str.indexOf("/") );
          referance   =  str.substring( str.indexOf("/") + 1);
        System.out.println("cate:"+category);
        System.out.println("ref:"+referance);
        this.Rec_mod_reference.setText(referance);
        this.Rec_mod_oldreference.setText(modreceived.getOldreference());
        this.Rec_mod_date.setValue(LocalDate.parse(modreceived.getDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        this.Rec_mod_sender.setValue(modreceived.getSender());
        this.Rec_mod_object.setText(modreceived.getObject());
        this.Rec_mod_category.setValue(category);
       // this.Rec_mod_category.setValue("SET3");
    }

    public static void shutdown() {
        System.out.print("closed");
    }

    @FXML
    void Rec_func_update(ActionEvent event) throws SQLException {
        if (this.Rec_mod_reference.getText().equals(this.modreceived.getReference()) && this.Rec_mod_oldreference.getText().equals(this.modreceived.getOldreference()) && this.Rec_mod_date.getEditor().getText().equals(this.modreceived.getDate()) && ((String)this.Rec_mod_sender.getValue()).toString().toLowerCase().equals(this.modreceived.getSender().toLowerCase()) && this.Rec_mod_object.getText().equals(this.modreceived.getObject())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" tu n'as pas changé les informations");
            alert.showAndWait();
        } else {
            String cleanreference = this.cleanStringSromSpecialCar(Rec_mod_category.getValue()+"/"+this.Rec_mod_reference.getText());
            Alert alert;
            if (cleanreference != "") {
                if (!this.Rec_mod_oldreference.getText().isEmpty()) {
                    if (this.Rec_mod_date.getValue() != null) {
                        if (!((String)this.Rec_mod_sender.getValue()).toString().replace(" ", "").toLowerCase()
                                .equals("Choisi l'expéditeur".replace(" ", "").toLowerCase())) {
                            System.out.println("else");
                            if (!this.Rec_mod_object.getText().isEmpty()) {
                                String oldRef=category+referance;
                                String newRef= this.cleanStringSromSpecialCar(Rec_mod_category.getValue()+"/"+this.Rec_mod_reference.getText());

                                System.out.println();
                                System.out.println("oldRef"+oldRef);
                                System.out.println("newRef"+newRef);
                                if (oldRef.equals(newRef)) {
                                    this.update(event);
                                } else if (!this.Reference_Exiced(Rec_mod_category.getValue()+"/"+
                                        this.cleanStringSromSpecialCar(this.Rec_mod_reference.getText()))) {
                                    this.update(event);
                                } else {
                                    System.out.println("/////////////// ");
                                    alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Information Dialog");
                                    alert.setHeaderText((String)null);
                                    alert.setContentText(" S'il vous plait, entrez une autre reference");
                                    alert.showAndWait();
                                }
                            } else {
                                alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Information Dialog");
                                alert.setHeaderText((String)null);
                                alert.setContentText(" S'il vous plait, entrez l'objet");
                                alert.showAndWait();
                            }
                        } else {
                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText((String)null);
                            alert.setContentText(" S'il vous plait, entrez la distinataire");
                            alert.showAndWait();
                        }
                    } else {
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText((String)null);
                        alert.setContentText(" S'il vous plait, entrez la date");
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText((String)null);
                    alert.setContentText(" S'il vous plait, entrez l'ancien reference");
                    alert.showAndWait();
                }
            } else {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText((String)null);
                alert.setContentText(" S'il vous plait, entrez la reference");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void Rec_func_opendocumentlocation(ActionEvent event) {
        if (this.modreceived != null) {
            this.open_location(this.modreceived.getPath());
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" S'il vous plait, Selecioney un Envoi");
            alert.showAndWait();
        }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate minDate = LocalDate.of(2006, 1, 1);
        this.Rec_mod_date.setDayCellFactory((d) -> {
            return new DateCell() {
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setDisable(item.isBefore(minDate));
                }
            };
        });
        this.Rec_mod_date.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            public String toString(LocalDate localDate) {
                return localDate == null ? "" : this.dateTimeFormatter.format(localDate);
            }

            public LocalDate fromString(String dateString) {
                return dateString != null && !dateString.trim().isEmpty() ? LocalDate.parse(dateString, this.dateTimeFormatter) : null;
            }
        });
    }

    private boolean copyFile(File file, String newname) {
        System.out.println("ter");

        try {
            File dest = new File("Documents/" + newname);
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException var4) {
            System.out.println("message:" + var4.getMessage());
            return false;
        }
    }

    private boolean Reference_Exiced(String newReference) throws SQLException {
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean var8;
        try {
            String querry = "SELECT count(1) FROM received WHERE  reference= ?";
            ps = connection.prepareStatement(querry);
            ps.setString(1, newReference);
            rs = ps.executeQuery();
            int excited = 0;
            if (rs.next()) {
                excited = rs.getInt(1);
            }

            if (excited == 0) {
                System.out.print("Not excited");
                return false;
            }

            System.out.println(" yes excited ");
            var8 = true;
        } catch (Exception var12) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var12.getMessage());
            var12.printStackTrace();
            return false;
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

        return var8;
    }

    private void open_location(String path) {
        System.out.println(System.getProperty("os.name"));
        File filechkeck;
        if (System.getProperty("os.name").startsWith("Windows")) {
            filechkeck = new File(path);
            if (filechkeck.exists()) {
                try {
                    Runtime.getRuntime().exec("explorer.exe /select," + filechkeck.getAbsolutePath());
                } catch (IOException var6) {
                    var6.printStackTrace();
                }
            } else {
              Alert  alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText((String)null);
                alert.setContentText("File dose not exist");
                alert.showAndWait();

            }
        } else if (System.getProperty("os.name").startsWith("Linux")) {
            filechkeck = new File(path);
            if (filechkeck.exists()) {
                try {
                    Runtime.getRuntime().exec("xdg-open " + filechkeck.getAbsolutePath());
                } catch (IOException var5) {
                    var5.printStackTrace();
                }
            } else {
                System.out.println("File dose not exist");
            }
        } else if (System.getProperty("os.name").startsWith("Mac")) {
            filechkeck = new File(path);
            if (filechkeck.exists()) {
                try {
                    Runtime.getRuntime().exec("open -R " + filechkeck.getAbsolutePath());
                } catch (IOException var4) {
                    var4.printStackTrace();
                }
            } else {
                System.out.println("File dose not exist");
            }
        }

    }

    void update(ActionEvent event) throws SQLException {

        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        Object rs = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String querry = "UPDATE received SET reference= ? ,old_reference= ? , date= ? ,  sender= ? , object= ?  WHERE id= ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, Rec_mod_category.getValue()+"/"+this.cleanStringSromSpecialCar(this.Rec_mod_reference.getText()));
            ps.setString(2, this.Rec_mod_oldreference.getText().replace(" ", ""));
            ps.setString(3, formatter.format((TemporalAccessor)this.Rec_mod_date.getValue()));
            ps.setString(4, ((String)this.Rec_mod_sender.getValue()).toString());
            ps.setString(5, this.Rec_mod_object.getText());
            ps.setInt(6, this.modreceived.getId());
            int updated = ps.executeUpdate();
            if (updated == 1) {
                System.out.println("updateded ");
                int index = IntStream.range(0, this.receivedObservableList.size()).filter((i) -> {
                    return ((Received)this.receivedObservableList.get(i)).getId() == this.modreceived.getId();
                }).findFirst().orElse(-1);
                if (index != -1) {
                    this.receivedObservableList.remove(index);
                    Received received = new Received(
                            this.modreceived.getId(),
                            Rec_mod_category.getValue()+ "/"+this.cleanStringSromSpecialCar(this.Rec_mod_reference.getText()),
                            this.Rec_mod_oldreference.getText().replace(" ", ""),
                            formatter.format((TemporalAccessor)this.Rec_mod_date.getValue()),
                            ((String)this.Rec_mod_sender.getValue()).toString(),
                            this.Rec_mod_object.getText(),
                            this.modreceived.getPath());
                    received.setFrech(true);
                    this.receivedObservableList.add(index, received);
                }

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
            } else {
                System.out.print("Not updated");
            }
        } catch (Exception var14) {
            System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var14.getMessage());
            var14.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                ((ResultSet)rs).close();
            }

        }

    }

    String cleanStringSromSpecialCar(String str) {
        String resultStr = "";

        for(int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) > '@' && str.charAt(i) <= 'z' || str.charAt(i) > '/' && str.charAt(i) <= '9' || str.charAt(i) == '_') {
                resultStr = resultStr + str.charAt(i);
            }
        }

        System.out.println("String after removing special characters: " + resultStr);
        return resultStr;
    }
}

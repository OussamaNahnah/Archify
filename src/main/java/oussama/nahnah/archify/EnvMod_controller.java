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

public class EnvMod_controller implements Initializable {
    FileChooser fileChooser;
    String fileExtension;
    String fileName;
    ObservableList<Sended> sendedObservableList;
    Sended modSended;
    @FXML
    private Button Env_update;
    @FXML
    private Button Env_filepicker;
    @FXML
    private TextArea Env_mod_object;
    @FXML
    private DatePicker Env_mod_date;
    @FXML
    private ChoiceBox<String> Env_mod_recipient;
    @FXML
    private TextField Env_mod_reference;

    public EnvMod_controller() {
    }

    public void setSendedObservableList(ObservableList<Sended> sendedObservableList) {
        System.out.print("gg" + sendedObservableList.size());
        this.sendedObservableList = sendedObservableList;
    }

    public void setSendedItem(Sended modSended) {
        this.modSended = modSended;
        this.Env_mod_reference.setText(modSended.getReference());
        this.Env_mod_date.setValue(LocalDate.parse(modSended.getDate(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        this.Env_mod_recipient.setValue(modSended.getRecipient());
        this.Env_mod_object.setText(modSended.getObject());
    }

    public static void shutdown() {
        System.out.print("closed");
    }

    @FXML
    void Env_func_update(ActionEvent event) throws SQLException {
        if (this.Env_mod_reference.getText().equals(this.modSended.getReference()) && this.Env_mod_date.getEditor().getText().equals(this.modSended.date) && ((String)this.Env_mod_recipient.getValue()).toString().toLowerCase().equals(this.modSended.recipient.toLowerCase()) && this.Env_mod_object.getText().equals(this.modSended.getObject())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" tu n'as pas changé les informations");
            alert.showAndWait();
        } else {
            String cleanreference = this.cleanStringSromSpecialCar(this.Env_mod_reference.getText());
            Alert alert;
            if (cleanreference != "") {
                if (this.Env_mod_date.getValue() != null) {
                    if (!((String)this.Env_mod_recipient.getValue()).toString().replace(" ", "").toLowerCase().equals("Choisi Déstinatia re".replace(" ", "").toLowerCase())) {
                        System.out.println("else");
                        if (!this.Env_mod_object.getText().isEmpty()) {
                            if (this.Env_mod_reference.getText().equals(this.modSended.getReference())) {
                                this.update(event);
                            } else if (!this.Reference_Exiced(this.cleanStringSromSpecialCar(this.Env_mod_reference.getText()))) {
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
                alert.setContentText(" S'il vous plait, entrez la reference");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void Env_func_opendocumentlocation(ActionEvent event) {
        if (this.modSended != null) {
            this.open_location(this.modSended.getPath());
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
        this.Env_mod_date.setDayCellFactory((d) -> {
            return new DateCell() {
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setDisable(item.isBefore(minDate));
                }
            };
        });
        this.Env_mod_date.setConverter(new StringConverter<LocalDate>() {
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
            String querry = "SELECT count(1) FROM sended WHERE  reference= ?";
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
        System.out.println("**************");
        Database database = new Database();
        Connection connection = database.getDBConnection();
        PreparedStatement ps = null;
        Object rs = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String querry = "UPDATE sended SET reference= ? , date= ? ,  recipient= ? , object= ?  WHERE id= ? ";
            ps = connection.prepareStatement(querry);
            ps.setString(1, this.cleanStringSromSpecialCar(this.Env_mod_reference.getText()));
            ps.setString(2, formatter.format((TemporalAccessor)this.Env_mod_date.getValue()));
            ps.setString(3, ((String)this.Env_mod_recipient.getValue()).toString());
            ps.setString(4, this.Env_mod_object.getText());
            ps.setInt(5, this.modSended.getId());
            int updated = ps.executeUpdate();
            if (updated == 1) {
                System.out.println("updateded ");
                int index = IntStream.range(0, this.sendedObservableList.size()).filter((i) -> {
                    return ((Sended)this.sendedObservableList.get(i)).getId() == this.modSended.getId();
                }).findFirst().orElse(-1);
                if (index != -1) {
                    this.sendedObservableList.remove(index);
                    Sended sended = new Sended(this.modSended.getId(), this.cleanStringSromSpecialCar(this.Env_mod_reference.getText()), formatter.format((TemporalAccessor)this.Env_mod_date.getValue()), ((String)this.Env_mod_recipient.getValue()).toString(), this.Env_mod_object.getText(), this.modSended.getPath());
                    sended.setFrech(true);
                    this.sendedObservableList.add(index, sended);
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

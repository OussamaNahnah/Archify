//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package oussama.nahnah.archify ;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;

public class EnvAdd_controller implements Initializable {
    FileChooser fileChooser;
    File file;
    String fileExtension="";
    String fileName;
    ObservableList<Sended> sendedObservableList;
    @FXML
    private Button Env_add_save;
    @FXML
    private Button Env_filepicker;
    @FXML
    private TextArea Env_insert_object;
    @FXML
    private Label Env_insert_path;
    @FXML
    private DatePicker Env_insert_date;
    @FXML
    private ChoiceBox<String> Env_insert_recipient;
    @FXML
    private TextField Env_insert_reference;

    public EnvAdd_controller() {
    }

    public void setSendedObservableList(ObservableList<Sended> sendedObservableList) {
        this.sendedObservableList = sendedObservableList;
    }



    @FXML
    void Env_func_add_save(ActionEvent event) throws SQLException, InterruptedException {
        String cleanreference = this.cleanStringSromSpecialCar(this.Env_insert_reference.getText());
        Alert alert;
        if (cleanreference != "") {
            if (this.Env_insert_date.getValue() != null) {
                if (!((String)this.Env_insert_recipient.getValue()).toString().replace(" ", "").toLowerCase().equals("Choisi Déstinatia re".replace(" ", "").toLowerCase())) {

                    if (!this.Env_insert_object.getText().isEmpty()) {
                            if (!this.Reference_Exiced(cleanreference)) {
                                if (this.file != null) {
                                File idea = new File("Documents/ENV/" + cleanreference + "." + this.fileExtension);
                                if (!idea.exists()) {

                                        if (this.file.length() != 0L) {

                                            File var10001 = this.file;
                                            String var10002 = this.cleanStringSromSpecialCar(this.Env_insert_reference.getText());
                                            if (this.copyFile(var10001, var10002 + "." + this.fileExtension)) {
                                                add(event);
                                            }else {
                                                alert = new Alert(AlertType.INFORMATION);
                                                alert.setTitle("Information Dialog");
                                                alert.setHeaderText((String)null);
                                                alert.setContentText("le fichier n est pas copier");
                                                alert.showAndWait();
                                            }
                                        } else {
                                            alert = new Alert(AlertType.INFORMATION);
                                            alert.setTitle("Information Dialog");
                                            alert.setHeaderText((String)null);
                                            alert.setContentText("le fichier est Null");
                                            alert.showAndWait();
                                        }

                                } else {
                                    alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Information Dialog");
                                    alert.setHeaderText((String)null);
                                    alert.setContentText("le fichier didja exict");
                                    alert.showAndWait();
                                }
                            } else {
                                alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Information Dialog");
                                alert.setHeaderText((String)null);
                                alert.setContentText(" Vous souhaitez ajouter un document sans fichier ?" );
                                if (alert.showAndWait().get() == ButtonType.OK) {
                                    add(event);

                                }


                            }
                            } else {
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

    @FXML
    void Env_func_filepicker(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Open Resource File");
        this.fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("PDF files (*.pdf)", new String[]{"*.PDF", "*.pdf"})});
        this.file = this.fileChooser.showOpenDialog(stage);
        if (this.file != null) {
            this.fileName = this.file.getName();
            this.fileExtension = this.fileName.substring(this.fileName.lastIndexOf(".") + 1, this.file.getName().length());

            this.Env_insert_path.setText("Path:" + this.file.getPath());
        }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate minDate = LocalDate.of(2006, 1, 1);
        this.Env_insert_date.setDayCellFactory((d) -> {
            return new DateCell() {
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setDisable(item.isBefore(minDate));
                }
            };
        });
        this.Env_insert_date.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            public String toString(LocalDate localDate) {
                return localDate == null ? "" : this.dateTimeFormatter.format(localDate);
            }

            public LocalDate fromString(String dateString) {
                return dateString != null && !dateString.trim().isEmpty() ? LocalDate.parse(dateString, this.dateTimeFormatter) : null;
            }
        });
        this.Env_insert_reference.setText("PSG/DP/");
        Platform.runLater(() -> {
            int carretPosition = this.Env_insert_reference.getCaretPosition();
            if (this.Env_insert_reference.getAnchor() != carretPosition) {
                this.Env_insert_reference.selectRange(carretPosition, carretPosition);
                this.Env_insert_reference.positionCaret(8);
            }

        });
        this.Env_insert_date.setValue(LocalDate.now());
    }

    private boolean copyFile(File file, String newname) {


        try {
            File dest = new File("Documents/ENV/" + newname);
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            if (dest.exists()) {
                RandomAccessFile r = new RandomAccessFile(dest, "r");
                r.close();
                return true;
            }
        } catch (IOException var5) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(var5.getMessage());
            alert.showAndWait();

        }

        return false;
    }

    private boolean Reference_Exiced(String newReference) throws SQLException {
        newReference = this.cleanStringSromSpecialCar(newReference);
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

    String cleanStringSromSpecialCar(String str) {
        String resultStr = "";

        for(int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) > '@' && str.charAt(i) <= 'Z' || str.charAt(i) > '`' && str.charAt(i) <= 'z' || str.charAt(i) > '/' && str.charAt(i) <= '9' || str.charAt(i) == '_') {
                resultStr = resultStr + str.charAt(i);
            }
        }

        System.out.println("String after removing special characters: " + resultStr);
        return resultStr;
    }
    void add(Event e) throws SQLException {


            Database database = new Database();
            Connection connection = database.getDBConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String querry = "INSERT or IGNORE INTO sended  (reference ,date ,recipient,object,path)VALUES( ?,?,?,?,?)";
                ps = connection.prepareStatement(querry, 1);
                ps.setString(1, this.cleanStringSromSpecialCar(this.Env_insert_reference.getText()));
                ps.setString(2, formatter.format((TemporalAccessor)this.Env_insert_date.getValue()));
                ps.setString(3, ((String)this.Env_insert_recipient.getValue()).toString());
                ps.setString(4, this.Env_insert_object.getText());
                String var10002 = this.cleanStringSromSpecialCar(this.Env_insert_reference.getText());

                System.out.println();
                System.out.println("Uri"+"Documents/ENV/" + var10002 + "." + this.fileExtension);
                if (file!=null){
                    ps.setString(5, "Documents/ENV/" + var10002 + "." + this.fileExtension);
                }else {
                    ps.setString(5, "Il n'y a pas de document".toUpperCase());
                }

                ps.execute();
                rs = ps.getGeneratedKeys();
                int generatedKey = 0;
                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                }

                if (generatedKey != 0) {
                    String var10003 = this.cleanStringSromSpecialCar(this.Env_insert_reference.getText());
                    String var10004 = formatter.format((TemporalAccessor)this.Env_insert_date.getValue());
                    String var10005 = ((String)this.Env_insert_recipient.getValue()).toString();
                    String var10006 = this.Env_insert_object.getText();
                    Sended sended;
                    if (file!=null){
                        String var10007 = this.cleanStringSromSpecialCar(this.Env_insert_reference.getText());
                          sended = new Sended(generatedKey, var10003, var10004, var10005, var10006, "Documents/ENV/" + var10007 + "." + this.fileExtension);
                    }else {
                         sended = new Sended(generatedKey, var10003, var10004, var10005, var10006,  "Il n'y a pas de document".toUpperCase());

                    }

                    System.out.println("Inserted record's ID: " + generatedKey);
                    sended.setFrech(true);
                    this.sendedObservableList.add(0, sended);
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                    stage.close();
                } else {
                    System.out.print("Not Inserted");
                }
            } catch (Exception var16) {
                System.out.println("SQLITE_CONSTRAINT_UNIQUE" + var16.getMessage());
                var16.printStackTrace();
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


    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package oussama.nahnah.archify;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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

public class RecAdd_controller implements Initializable {
    FileChooser fileChooser;
    File file;
    String fileExtension;
    String fileName;
    ObservableList<Received> receivedObservableList;
    @FXML
    private TextArea Rec_insert_object;
    @FXML
    private Label Rec_insert_path;
    @FXML
    private DatePicker Rec_insert_date;
    @FXML
    private ChoiceBox<String> Rec_insert_sender;
    @FXML
    private ChoiceBox<String> Rec_insert_category;
    @FXML
    private TextField Rec_insert_reference;
    @FXML
    private TextField Rec_insert_oldreference;

    public RecAdd_controller() {
    }

    public void setreceivedObservableList(ObservableList<Received> receivedObservableList) {
        System.out.print("gg" + receivedObservableList.size());
        this.receivedObservableList = receivedObservableList;
    }

    @FXML
    void Rec_func_add_save(ActionEvent event) throws SQLException {
        String cleanreference = this.cleanStringSromSpecialCar(this.Rec_insert_reference.getText());
        Alert alert;
        if (cleanreference != "") {
            if (this.Rec_insert_oldreference.getText() != "") {
                if (this.Rec_insert_date.getValue() != null) {
                    if (!((String)this.Rec_insert_sender.getValue()).toString().replace(" ", "").toLowerCase().equals("Choisi l'expéditeur".replace(" ", "").toLowerCase())) {
                        System.out.println("else");
                        if (!this.Rec_insert_object.getText().isEmpty()) {
                                if (!this.Reference_Exiced(cleanreference)) {
                                    File idea = new File("Documents/REC/"+Rec_insert_category.getValue() + cleanreference + "." + this.fileExtension);
                                    if (this.file != null) {
                                        if (!idea.exists()) {

                                            if (this.file.length() != 0L) {
                                                System.out.println("----");
                                                File var10001 = this.file;
                                                String var10002 =Rec_insert_category.getValue() + this.cleanStringSromSpecialCar(this.Rec_insert_reference.getText());
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
                        alert.setContentText(" S'il vous plait, entrez l expediteur");
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
                alert.setContentText(" S'il vous plait, entrez l'auncien reference");
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
    void Rec_func_filepicker(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Open Resource File");
        this.fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("PDF files (*.pdf)", new String[]{"*.PDF", "*.pdf"})});
        this.file = this.fileChooser.showOpenDialog(stage);
        if (this.file != null) {
            this.fileName = this.file.getName();
            this.fileExtension = this.fileName.substring(this.fileName.lastIndexOf(".") + 1, this.file.getName().length());
            System.out.println(">> fileExtension" + this.fileExtension);
            this.Rec_insert_path.setText("Path:" + this.file.getPath());
        }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate minDate = LocalDate.of(2006, 1, 1);
        this.Rec_insert_date.setDayCellFactory((d) -> {
            return new DateCell() {
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setDisable(item.isBefore(minDate));
                }
            };
        });
        this.Rec_insert_date.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            public String toString(LocalDate localDate) {
                return localDate == null ? "" : this.dateTimeFormatter.format(localDate);
            }

            public LocalDate fromString(String dateString) {
                return dateString != null && !dateString.trim().isEmpty() ? LocalDate.parse(dateString, this.dateTimeFormatter) : null;
            }
        });
        this.Rec_insert_reference.setText("PSG/DP/");
        Platform.runLater(() -> {
            int carretPosition = this.Rec_insert_reference.getCaretPosition();
            if (this.Rec_insert_reference.getAnchor() != carretPosition) {
                this.Rec_insert_reference.selectRange(carretPosition, carretPosition);
                this.Rec_insert_reference.positionCaret(8);
            }

        });
        this.Rec_insert_date.setValue(LocalDate.now());
    }

    private boolean copyFile(File file, String newname) {
        System.out.println("ter");

        try {
            File dest = new File("Documents/REC/"  + newname);
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
            System.out.println("message:" + var5.getMessage());
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
        {
            Database database = new Database();
            Connection connection = database.getDBConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String querry = "INSERT or IGNORE INTO received  (reference,old_reference ,date ,sender,object,path)VALUES( ?,?,?,?,?,?)";
                ps = connection.prepareStatement(querry, 1);
                ps.setString(1, Rec_insert_category.getValue()+"/"+this.cleanStringSromSpecialCar(this.Rec_insert_reference.getText()));
                ps.setString(2, this.Rec_insert_oldreference.getText());
                ps.setString(3, formatter.format((TemporalAccessor)this.Rec_insert_date.getValue()));
                ps.setString(4, ((String)this.Rec_insert_sender.getValue()).toString());
                ps.setString(5, this.Rec_insert_object.getText());
               String var10002 = Rec_insert_category.getValue() +this.cleanStringSromSpecialCar(this.Rec_insert_reference.getText());
                if (file!=null){
                    ps.setString(6, "Documents/REC/" + var10002 + "." + this.fileExtension);
                }else {
                    ps.setString(6, "Il n'y a pas de document".toUpperCase());
                }

                ps.execute();
                rs = ps.getGeneratedKeys();
                int generatedKey = 0;
                if (rs.next()) {
                    generatedKey = rs.getInt(1);
                }

                if (generatedKey != 0) {
                    String var10003 =Rec_insert_category.getValue()+"/"+ this.cleanStringSromSpecialCar(this.Rec_insert_reference.getText());
                    String var10004 = this.Rec_insert_reference.getText();
                    String var10005 = formatter.format((TemporalAccessor)this.Rec_insert_date.getValue());
                    String var10006 = ((String)this.Rec_insert_sender.getValue()).toString();
                    String var10007 = this.Rec_insert_object.getText();
                    Received received;
                    if (file!=null){

                        String var10008 = this.cleanStringSromSpecialCar(this.Rec_insert_reference.getText());
                          received = new Received(generatedKey, var10003, var10004, var10005, var10006, var10007, "Documents/REC/" +Rec_insert_category.getValue() + var10008 + "." + this.fileExtension);
                    }else {
                          received = new Received(generatedKey, var10003, var10004, var10005, var10006, var10007, "Il n'y a pas de document".toUpperCase() );

                    }
                    System.out.println("Inserted record's ID: " + generatedKey);
                    received.setFrech(true);
                    this.receivedObservableList.add(0, received);
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
}

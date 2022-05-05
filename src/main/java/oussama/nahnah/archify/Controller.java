//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package oussama.nahnah.archify;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class Controller extends Application implements Initializable {
    Database database = null;
    int from = 0;
    int to = 0;
    int itemPerPage = 50;
    int count = 0;
    int pageCount = 0;
    FileChooser fileChooser=null;

   static String  envDate="",envRec="";
    static String envTempRef="", envTempDate="", envTempRec="",envTempObj="";
   boolean envTempType=true;

   static String  recRef="", recDate="",recSen="";
    static String recTempRef="", recTempOld="", recTempDate="", recTempSen="",recTempObj="";
   boolean recTempType=true;

    @FXML
    private RadioButton Env_chockbox_date;
    @FXML
    private RadioButton Env_chockbox_object;

    @FXML
    private RadioButton Env_chockbox_referance;
    @FXML
    private RadioButton Env_chockbox_all;
    @FXML
    private TableColumn<Sended, String> Env_clm_date;
    @FXML
    private TableColumn<Sended, String> Env_clm_ficher;
    @FXML
    private TableColumn<Sended, String> Env_clm_object;
    @FXML
    private TableColumn<Sended, String> Env_clm_recipient;
    @FXML
    private TableColumn<Sended, String> Env_clm_referance;
    @FXML
    private TableColumn<Sended, Integer> Env_clm_id;
    @FXML
    private ChoiceBox<String> Env_dropdown_category;
    @FXML
    private ChoiceBox<String> Env_dropdown_year;
    @FXML
    private TableView<Sended> Env_table;
    @FXML
    private Pagination Env_pagination;
    @FXML
    private TextField Env_search_TextField;
    @FXML
    private TabPane tabPane;
    @FXML
    private RadioButton Rec_chockbox_date;
    @FXML
    private RadioButton Rec_chockbox_object;

    @FXML
    private RadioButton Rec_chockbox_referance;
    @FXML
    private RadioButton Rec_chockbox_oldreferance;
    @FXML
    private RadioButton Rec_chockbox_all;
    @FXML
    private TableColumn<Received, String> Rec_clm_date;
    @FXML
    private TableColumn<Received, String> Rec_clm_ficher;
    @FXML
    private TableColumn<Received, String> Rec_clm_object;
    @FXML
    private TableColumn<Received, String> Rec_clm_old_referance;
    @FXML
    private TableColumn<Received, String> Rec_clm_sender;
    @FXML
    private TableColumn<Received, String> Rec_clm_referance;
    @FXML
    private TableColumn<Received, Integer> Rec_clm_id;
    @FXML
    private ChoiceBox<String> Rec_dropdown_category;
    @FXML
    private ChoiceBox<String> Rec_dropdown_type;
    @FXML
    private ChoiceBox<String> Rec_dropdown_year;
    @FXML
    private TableView<Received> Rec_table;
    @FXML
    private Pagination Rec_pagination;
    @FXML
    private TextField Rec_search_TextField;

    public Controller() {
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tabPane.tabMinWidthProperty().bind(this.tabPane.widthProperty().divide(this.tabPane.getTabs().size()).subtract(0));
        this.tabPane.setTabMinHeight(40.0D);
        this.Env_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.Env_clm_referance.setMaxWidth(2.147483648E10D);
        this.Env_clm_date.setMaxWidth(2.147483648E10D);
        this.Env_clm_recipient.setMaxWidth(2.147483648E10D);
        this.Env_clm_ficher.setMaxWidth(2.147483648E10D);
        this.Env_clm_object.setMaxWidth(1.2884901888E11D);
        this.Env_clm_referance.setMinWidth(90.0D);
        this.Env_clm_date.setMinWidth(90.0D);
        this.Env_clm_recipient.setMinWidth(90.0D);
        this.Env_clm_ficher.setMinWidth(90.0D);
        this.Env_clm_object.setMinWidth(90.0D);
        this.Env_clm_id.setCellValueFactory(new PropertyValueFactory("id"));
        this.Env_clm_referance.setCellValueFactory(new PropertyValueFactory("reference"));
        this.Env_clm_date.setCellValueFactory(new PropertyValueFactory("date"));
        this.Env_clm_recipient.setCellValueFactory(new PropertyValueFactory("recipient"));
        this.Env_clm_object.setCellValueFactory(new PropertyValueFactory("object"));
        this.Env_clm_ficher.setCellValueFactory(new PropertyValueFactory("path"));
        this.Rec_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.Rec_clm_referance.setMaxWidth(2.147483648E10D);
        this.Rec_clm_date.setMaxWidth(2.147483648E10D);
        this.Rec_clm_sender.setMaxWidth(2.147483648E10D);
        this.Rec_clm_old_referance.setMaxWidth(2.147483648E10D);
        this.Rec_clm_ficher.setMaxWidth(2.147483648E10D);
        this.Rec_clm_object.setMaxWidth(1.2884901888E11D);
        this.Rec_clm_referance.setMinWidth(90.0D);
        this.Rec_clm_date.setMinWidth(90.0D);
        this.Rec_clm_sender.setMinWidth(90.0D);
        this.Rec_clm_old_referance.setMinWidth(90.0D);
        this.Rec_clm_ficher.setMinWidth(90.0D);
        this.Rec_clm_object.setMinWidth(90.0D);
        this.Rec_clm_id.setCellValueFactory(new PropertyValueFactory("id"));
        this.Rec_clm_referance.setCellValueFactory(new PropertyValueFactory("reference"));
        this.Rec_clm_old_referance.setCellValueFactory(new PropertyValueFactory("oldreference"));
        this.Rec_clm_date.setCellValueFactory(new PropertyValueFactory("date"));
        this.Rec_clm_sender.setCellValueFactory(new PropertyValueFactory("sender"));
        this.Rec_clm_object.setCellValueFactory(new PropertyValueFactory("object"));
        this.Rec_clm_ficher.setCellValueFactory(new PropertyValueFactory("path"));

        this.Env_dropdown_category.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            getEnv();
        });
        this.Env_dropdown_year.setItems(this.getyears());
        this.Env_dropdown_year.getSelectionModel().selectFirst();
        this.Env_dropdown_category.getSelectionModel().selectFirst();
        this.Env_dropdown_year.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            getEnv();
        });
        this.Env_search_TextField.textProperty().addListener((observable, oldValue, newValue) -> {
          getEnv();
        });
        this.Env_table.setRowFactory((tv) -> {
            TableRow<Sended> row = new TableRow<Sended>() {
                protected void updateItem(Sended item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && item.isFrech()) {
                        if (item.isFrech()) {
                             this.setStyle("-fx-background-color: #DCE5F2;");
                           // setStyle("-fx-text-inner-color: red;");

                        }
                    } else {
                        this.setStyle("");
                    }

                }
            };
            row.setOnMouseClicked((event) -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Sended sended = (Sended)row.getItem();
                    this.openFILE(sended.getPath());
                    System.out.println("Double click on: " + sended.getReference());
                }

            });
            return row;
        });

        this.Rec_dropdown_year.setItems(this.getyears());
        this.Rec_dropdown_year.getSelectionModel().selectFirst();
        this.Rec_dropdown_category.getSelectionModel().selectFirst();
        this.Rec_dropdown_year.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            getRec();
        });
        this.Rec_dropdown_category.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            getRec();

        }); this.Rec_dropdown_type.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            getRec();

        });
        this.Rec_search_TextField.textProperty().addListener((observable, oldValue, newValue) -> {
            {
               getRec();
            }

        });
        /*this.Rec_search_TextField.setOnMouseClicked((e) -> {
            this.Rec_dropdown_year.getSelectionModel().selectFirst();
            this.Rec_dropdown_category.getSelectionModel().selectFirst();
            this.Rec_dropdown_type.getSelectionModel().selectFirst();
        });*/
        this.Rec_table.setRowFactory((tv) -> {
            TableRow<Received> row = new TableRow<Received>() {
                protected void updateItem(Received item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && item.isFrech()) {
                        if (item.isFrech()) {
                            this.setStyle("-fx-background-color: #DCE5F2;");
                        }
                    } else {
                        this.setStyle("");
                    }

                }
            };
            row.setOnMouseClicked((event) -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Received sended = (Received)row.getItem();
                    this.openFILE(sended.getPath());
                    System.out.println("Double click on: " + sended.getReference());
                }

            });
            return row;
        });
        this.Env_chockbox_all.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    Controller.this.Env_search_TextField.setText("");
                }

            }
        });
        this.Env_chockbox_referance.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    Controller.this.Env_search_TextField.setText("");
                }

            }
        });
        this.Env_chockbox_date.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    Controller.this.Env_search_TextField.setText("");
                }

            }
        });

        this.Env_chockbox_object.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    Controller.this.Env_search_TextField.setText("");
                }

            }
        });
        this.Rec_chockbox_all.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    Controller.this.Rec_search_TextField.setText("");
                }

            }
        });
        this.Rec_chockbox_referance.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    Controller.this.Rec_search_TextField.setText("");
                }

            }
        });
        this.Rec_chockbox_oldreferance.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    Controller.this.Rec_search_TextField.setText("");
                }

            }
        });
        this.Rec_chockbox_date.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    Controller.this.Rec_search_TextField.setText("");
                }

            }
        });

        this.Rec_chockbox_object.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    Controller.this.Rec_search_TextField.setText("");
                }

            }
        });


        //call get
        getEnv();
        getRec();
    }

    private void envSearche() {

        if (Env_dropdown_year.getValue().replace(" ", "").toLowerCase()
                .equals("Annees".replace(" ", "").toLowerCase())) {
            envDate=  "";
        }else {
            envDate=Env_dropdown_year.getValue();
        }

        if (Env_dropdown_category.getValue().replace(" ", "").toLowerCase()
                .equals("destinataire".replace(" ", "").toLowerCase())) {
            envRec="";
        }else {
            envRec=Env_dropdown_category.getValue();
        }
        envTempType=true;
        envTempRef="";
        envTempDate=envDate;
        envTempRec=envRec;
        envTempObj="";
        if (Env_search_TextField.getText().equals("")){
            System.out.println();
            System.out.println("*********************observable:"+Env_search_TextField.getText());

            System.out.println("*********************oldValue:"+Env_search_TextField.getText());
            System.out.println("*********************Env_search_TextField.getText():"+Env_search_TextField.getText());
            envTempType=true;
            envTempRef="";
            envTempDate=envDate;
            envTempRec=envRec;
            envTempObj="";
        }else {
            if (this.Env_chockbox_all.isSelected()) {
                envTempType=false;
                envTempRef=Env_search_TextField.getText();
                envTempDate=Env_search_TextField.getText();
                envTempRec=Env_search_TextField.getText();
                envTempObj=Env_search_TextField.getText();
            }else
            if (this.Env_chockbox_referance.isSelected()) {
                envTempType=true;
                envTempRef=Env_search_TextField.getText();
                envTempDate=envDate;
                envTempRec=envRec;
                envTempObj="";
            } else if (this.Env_chockbox_date.isSelected()) {
                envTempType=true;
                envTempRef="";
                envTempDate=envDate+Env_search_TextField.getText();
                envTempRec=envRec;
                envTempObj="";
            } else if (this.Env_chockbox_object.isSelected()) {
                envTempType=true;
                envTempRef="";
                envTempDate=envDate;
                envTempRec=envRec;
                envTempObj=Env_search_TextField.getText();
            }

        }
    }

    @FXML
    void Env_fun_add(ActionEvent event) {
        this.Env_search_TextField.setText("");
        this.Env_dropdown_category.getSelectionModel().selectFirst();
        this.Env_chockbox_all.setSelected(true);
        this.Env_dropdown_year.getSelectionModel().selectFirst();


        this.Rec_table.getItems().clear();
        this.database = new Database();
        this.count = this.database.recCountSearch(recTempType,recTempRef,recTempOld,recTempDate,recTempSen,recTempObj);
        this.pageCount = this.count / this.itemPerPage + 1;
        this.Rec_pagination.setPageCount(this.pageCount);
        this.Rec_pagination.setPageFactory(this::recCreatePageSearch);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Env_add.fxml"));

        try {
            Parent root1 = (Parent)fxmlLoader.load();
            EnvAdd_controller admissionController = (EnvAdd_controller)fxmlLoader.getController();
            admissionController.setSendedObservableList(this.Env_table.getItems());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Nouveau Envoi");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void Env_fun_edit(ActionEvent actionEvent) {
        Sended sended = (Sended)this.Env_table.getSelectionModel().getSelectedItem();
        if (sended != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Env_mod.fxml"));

            try {
                Parent root1 = (Parent)fxmlLoader.load();
                EnvMod_controller envMod_controller = (EnvMod_controller)fxmlLoader.getController();
                envMod_controller.setSendedObservableList(this.Env_table.getItems());
                envMod_controller.setSendedItem(sended);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("New Admission");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException var7) {
                var7.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" S'il vous plait, Selecioney un Envoi");
            alert.showAndWait();
        }

    }

    public void Env_fun_delete(ActionEvent actionEvent) throws SQLException {
        Sended sended = (Sended)this.Env_table.getSelectionModel().getSelectedItem();
        Alert alert;
        if (sended != null) {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" Vous etes Sur de supprimer Ref:" + sended.getReference());
            if (alert.showAndWait().get() == ButtonType.OK) {
                Database database = new Database();
                Connection connection = database.getDBConnection();
                PreparedStatement statement = null;

                try {
                    statement = connection.prepareStatement("DELETE FROM sended WHERE id = ?");
                    statement.setInt(1, sended.getId());
                    int deleted = statement.executeUpdate();
                    System.out.println("del:" + deleted);
                    if (deleted == 0) {
                        Alert alert0 = new Alert(AlertType.INFORMATION);
                        alert0.setTitle("Information Dialog");
                        alert0.setHeaderText((String)null);
                        alert0.setContentText(" Errour, la supprition n est pas effecter ");
                        alert0.showAndWait();
                    } else if (deleted == 1) {
                        int index = IntStream.range(0, this.Env_table.getItems().size()).filter((i) -> {
                            return ((Sended)this.Env_table.getItems().get(i)).getId() == sended.getId();
                        }).findFirst().orElse(-1);
                        if (index != -1) {
                            this.Env_table.getItems().remove(index);
                        }

                        RandomAccessFile r = new RandomAccessFile(sended.getPath(), "rws");
                        r.close();
                        Files.deleteIfExists(Path.of(sended.getPath()));
                    }
                } catch (Exception var13) {
                    var13.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.close();
                    }

                    if (statement != null) {
                        statement.close();
                    }

                }
            } else {
                System.out.println("annuler:");
            }
        } else {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" S'il vous plait, Selecioney un Envoi");
            alert.showAndWait();
        }

    }

    public void Env_fun_openfilelocation(ActionEvent actionEvent) {
        Sended sended = (Sended)this.Env_table.getSelectionModel().getSelectedItem();
        if (sended != null) {
            this.open_location(sended.path);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" S'il vous plait, sélectionner un Envoi");
            alert.showAndWait();
        }

    }

    private void open_location(String path) {
        System.out.println(System.getProperty("os.name"));
        File filechkeck;
        Alert alert;
        if (System.getProperty("os.name").startsWith("Windows")) {
            filechkeck = new File(path);
            if (filechkeck.exists()) {
                try {
                    Runtime.getRuntime().exec("explorer.exe /select," + filechkeck.getAbsolutePath());
                } catch (IOException var6) {
                    var6.printStackTrace();
                }
            } else {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText((String)null);
                alert.setContentText(" File dose not exist");
                alert.showAndWait();
                System.out.println("File dose not exist");
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
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText((String)null);
                alert.setContentText(" File dose not exist");
                alert.showAndWait();
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
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText((String)null);
                alert.setContentText(" File dose not exist");
                alert.showAndWait();
                System.out.println("File dose not exist");
            }
        }

    }

    private void openFILE(String path) {
        System.out.println(System.getProperty("os.name"));
        File filechkeck = new File(path);
        if (filechkeck.exists()) {
            try {
                this.getHostServices().showDocument(filechkeck.toURI().toURL().toExternalForm());
            } catch (MalformedURLException var4) {
                var4.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" File dose not exist");
            alert.showAndWait();
            System.out.println("File dose not exist");
        }

    }

    public void Env_continue_with_excel(ActionEvent actionEvent) throws IOException, SQLException, InterruptedException {
        ObservableList<Sended> sendedObservableValue = null;
        this.database = new Database();
        sendedObservableValue = database.envSeaAll(this.from, this.to,envTempType,envTempRef,envTempDate,envTempRec,envTempObj);
        if (sendedObservableValue.size() != 0) {
            Workbook workbook = new HSSFWorkbook();
            CreationHelper helper = workbook.getCreationHelper();
            Sheet spreadsheet = workbook.createSheet("sample");
            spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
            spreadsheet.setColumnWidth(0, 768);
            spreadsheet.setColumnWidth(1, 3584);
            spreadsheet.setColumnWidth(2, 2560);
            spreadsheet.setColumnWidth(3, 4096);
            spreadsheet.setColumnWidth(4, 10240);
            spreadsheet.setColumnWidth(5, 2560);
            Row row = spreadsheet.createRow(5);
            row.createCell(0).setCellValue("N=");
            row.createCell(1).setCellValue("Réferance");
            row.createCell(2).setCellValue("Date");
            row.createCell(3).setCellValue("Déstenataire");
            row.createCell(4).setCellValue("Objét");
            row.createCell(5).setCellValue("Document");

            for(int i = 0; i < sendedObservableValue.size(); ++i) {
                row = spreadsheet.createRow(i + 6);
                row.createCell(0).setCellValue((double)i);
                row.createCell(1).setCellValue(((Sended)sendedObservableValue.get(i)).getReference());
                row.createCell(2).setCellValue(((Sended)sendedObservableValue.get(i)).getDate());
                row.createCell(3).setCellValue(((Sended)sendedObservableValue.get(i)).getRecipient());
                row.createCell(4).setCellValue(((Sended)sendedObservableValue.get(i)).getObject());
                HyperlinkType var10001 = HyperlinkType.DOCUMENT;
                Hyperlink link = helper.createHyperlink(HyperlinkType.FILE);
                link.setAddress(((Sended)sendedObservableValue.get(i)).getPath());
                row.createCell(5).setHyperlink(link);
                row.createCell(5).setCellValue("link");
            }

            File file = new File("workbook.xls");
            if (file!=null) {
                boolean fileIsNotLocked = file.renameTo(file);
                if (fileIsNotLocked) {
                    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
                    workbook.write(fileOut);
                    fileOut.close();
                    this.openFILE("workbook.xls");
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText((String)null);
                    alert.setContentText("Le document déjà ouvrir");
                    alert.showAndWait();
                }
            } else {
                FileOutputStream fileOut = new FileOutputStream("workbook.xls");
                workbook.write(fileOut);
                fileOut.close();
                this.openFILE("workbook.xls");
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText("Il ya pas des document pour ajouter au Exel");
            alert.showAndWait();
        }

    }

    ObservableList<String> getyears() {
        ObservableList<String> years = FXCollections.observableArrayList();
        String date1 = "2006";
        int date2 = LocalDate.now().getYear() + 1;
        DateFormat formater = new SimpleDateFormat("yyyy");
        Calendar beginCalendar = Calendar.getInstance();
        Calendar finishCalendar = Calendar.getInstance();

        try {
            beginCalendar.setTime(formater.parse(date1));
            finishCalendar.setTime(formater.parse(""+date2));
        } catch (ParseException var8) {
            var8.printStackTrace();
        }

        while(beginCalendar.before(finishCalendar)) {
            String year = formater.format(beginCalendar.getTime()).toUpperCase();
            years.add(year);
            beginCalendar.add(1, 1);
        }

        years.add("Annees");
        FXCollections.reverse(years);
        return years;
    }

   /* private Node createPage_Sea_all(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = database.Env_getSendedSeaAll(this.from, this.to, this.Env_search_TextField.getText());
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }

    private Node createPage_Sea_object(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = database.Env_getSendedSeaObject(this.from, this.to, this.Env_search_TextField.getText());
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }

    private Node createPage_Sea_recipient(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = database.Env_getSendedSeaRecipient(this.from, this.to, this.Env_search_TextField.getText());
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }

    private Node createPage_Sea_date(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = database.Env_getSendedSeaDate(this.from, this.to, this.Env_search_TextField.getText());
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }

    private Node createPage_Sea_Reference(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = database.Env_getSendedSeaReference(this.from, this.to, this.Env_search_TextField.getText());
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }

    private Node createPage_year_category(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = database.Env_getSendedByYearCategory(this.from, this.to, (String)this.Env_dropdown_year.getValue(), (String)this.Env_dropdown_category.getValue());
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }

    private Node createPage_category(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = database.Env_getSendedByCategory(this.from, this.to, (String)this.Env_dropdown_category.getValue());
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }

    private Node createPage_year(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = database.Env_getSendedByYear(this.from, this.to, (String)this.Env_dropdown_year.getValue());
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }
*/
   /* private Node createPage(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = null;
            sendedObservableList = database.Env_getSended(this.from, this.to);
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }*/

    private Node envCreatePageSearch(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList sendedObservableList = null;
            sendedObservableList = database.envSeaAll(this.from, this.to,envTempType,envTempRef,envTempDate,envTempRec,envTempObj);
            this.Env_table.setItems(sendedObservableList);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Env_table;
    }   private Node recCreatePageSearch(int pageIndex) {
        try {
            this.from = pageIndex * this.itemPerPage;
            this.to = this.itemPerPage;
            Database database = new Database();
            ObservableList received = null;
            received = database.recSeaAll(from, to,recTempType,recTempRef,recTempOld,recTempDate,recTempSen,recTempObj);
            this.Rec_table.setItems(received);
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return this.Rec_table;
    }

    public void Rec_fun_add(ActionEvent actionEvent) {
        this.Rec_search_TextField.setText("");
        this.Rec_dropdown_category.getSelectionModel().selectFirst();
        this.Rec_chockbox_all.setSelected(true);
        this.Rec_dropdown_year.getSelectionModel().selectFirst();
        this.Rec_dropdown_type.getSelectionModel().selectFirst();

        this.Rec_table.getItems().clear();
        this.database = new Database();
        this.count = this.database.recCountSearch(recTempType,recTempRef,recTempOld,recTempDate,recTempSen,recTempObj);
        this.pageCount = this.count / this.itemPerPage + 1;
        this.Rec_pagination.setPageCount(this.pageCount);
        this.Rec_pagination.setPageFactory(this::recCreatePageSearch);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Rec_add.fxml"));

        try {
            Parent root1 = (Parent)fxmlLoader.load();
            RecAdd_controller Controller = (RecAdd_controller)fxmlLoader.getController();
            Controller.setreceivedObservableList(this.Rec_table.getItems());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Nouveau recevoire");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void Rec_continue_with_excel(ActionEvent actionEvent) throws IOException, SQLException, InterruptedException {
        ObservableList<Received> ReceivedObservableValue = null;
        this.database = new Database();
            ReceivedObservableValue = database.recSeaAll(from, to,recTempType,recTempRef,recTempOld,recTempDate,recTempSen,recTempObj);


        if (ReceivedObservableValue.size() != 0) {
            Workbook workbook = new HSSFWorkbook();
            CreationHelper helper = workbook.getCreationHelper();
            Sheet spreadsheet = workbook.createSheet("sample");
            spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
            spreadsheet.setColumnWidth(0, 768);
            spreadsheet.setColumnWidth(1, 3584);
            spreadsheet.setColumnWidth(2, 2048);
            spreadsheet.setColumnWidth(3, 2560);
            spreadsheet.setColumnWidth(4, 2304);
            spreadsheet.setColumnWidth(5, 10240);
            spreadsheet.setColumnWidth(6, 2560);
            Row row = spreadsheet.createRow(5);
            row.createCell(0).setCellValue("N=");
            row.createCell(1).setCellValue("Réferance");
            row.createCell(2).setCellValue("L auncien réferance");
            row.createCell(3).setCellValue("Date");
            row.createCell(4).setCellValue("Déstenataire");
            row.createCell(5).setCellValue("Objét");
            row.createCell(6).setCellValue("Document");

            for(int i = 0; i < ReceivedObservableValue.size(); ++i) {
                row = spreadsheet.createRow(i + 6);
                row.createCell(0).setCellValue((double)i);
                row.createCell(1).setCellValue(((Received)ReceivedObservableValue.get(i)).getReference());
                row.createCell(2).setCellValue(((Received)ReceivedObservableValue.get(i)).getOldreference());
                row.createCell(3).setCellValue(((Received)ReceivedObservableValue.get(i)).getDate());
                row.createCell(4).setCellValue(((Received)ReceivedObservableValue.get(i)).getSender());
                row.createCell(5).setCellValue(((Received)ReceivedObservableValue.get(i)).getObject());
                HyperlinkType var10001 = HyperlinkType.DOCUMENT;
                Hyperlink link = helper.createHyperlink(HyperlinkType.FILE);
                link.setAddress(((Received)ReceivedObservableValue.get(i)).getPath());
                row.createCell(6).setHyperlink(link);
                row.createCell(6).setCellValue("link");
            }

            File file = new File("workbook.xls");
            if (file!=null) {
                boolean fileIsNotLocked = file.renameTo(file);
                if (fileIsNotLocked) {
                    FileOutputStream fileOut = new FileOutputStream("workbook.xls");
                    workbook.write(fileOut);
                    fileOut.close();
                    this.openFILE("workbook.xls");
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText((String)null);
                    alert.setContentText("Le document déjà ouvrir");
                    alert.showAndWait();
                }
            } else {
                FileOutputStream fileOut = new FileOutputStream("workbook.xls");
                workbook.write(fileOut);
                fileOut.close();
                this.openFILE("workbook.xls");
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText("Il ya pas des document pour ajouter au Exel");
            alert.showAndWait();
        }

    }

    public void Rec_fun_openfilelocation(ActionEvent actionEvent) {
        Received received = (Received)this.Rec_table.getSelectionModel().getSelectedItem();
        if (received != null) {
            this.open_location(received.getPath());
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" S'il vous plait, sélectionner un Envoi");
            alert.showAndWait();
        }

    }

    public void Rec_fun_edit(ActionEvent actionEvent) {
        Received received = (Received)this.Rec_table.getSelectionModel().getSelectedItem();
        if (received != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Rec_mod.fxml"));

            try {
                Parent root1 = (Parent)fxmlLoader.load();
                RecMod_controller controller = (RecMod_controller)fxmlLoader.getController();
                controller.setReceivedObservableList(this.Rec_table.getItems());
                controller.setReceivedItem(received);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Modifier le recevoire");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException var7) {
                var7.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" S'il vous plait, Selecioney un Envoi");
            alert.showAndWait();
        }

    }

    public void Rec_fun_delete(ActionEvent actionEvent) throws SQLException {
        Received received = (Received)this.Rec_table.getSelectionModel().getSelectedItem();
        Alert alert;
        if (received != null) {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" Vous etes Sur de supprimer Ref:" + received.getReference());
            if (alert.showAndWait().get() == ButtonType.OK) {
                Database database = new Database();
                Connection connection = database.getDBConnection();
                PreparedStatement statement = null;

                try {
                    statement = connection.prepareStatement("DELETE FROM received WHERE id = ?");
                    statement.setInt(1, received.getId());
                    int deleted = statement.executeUpdate();
                    System.out.println("del:" + deleted);
                    if (deleted == 0) {
                        Alert alert0 = new Alert(AlertType.INFORMATION);
                        alert0.setTitle("Information Dialog");
                        alert0.setHeaderText((String)null);
                        alert0.setContentText(" Errour, la supprition n est pas effecter ");
                        alert0.showAndWait();
                    } else if (deleted == 1) {



                        int index = IntStream.range(0, this.Rec_table.getItems().size()).filter((i) -> {
                            return ((Received)this.Rec_table.getItems().get(i)).getId() == received.getId();
                        }).findFirst().orElse(-1);
                        if (index != -1) {
                            this.Rec_table.getItems().remove(index);
                        }

                        RandomAccessFile r = new RandomAccessFile(received.getPath(), "rws");
                        r.close();
                        Files.deleteIfExists(Path.of(received.getPath()));
                    }
                } catch (Exception var13) {
                    var13.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.close();
                    }

                    if (statement != null) {
                        statement.close();
                    }

                }
            } else {
                System.out.println("annuler:");
            }
        } else {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" S'il vous plait, Selecioney un Envoi");
            alert.showAndWait();
        }

    }

    public void Env_fun_refresh(ActionEvent actionEvent) {
        this.Env_table.getItems().clear();
        this.Env_dropdown_year.getSelectionModel().selectFirst();
        this.Env_dropdown_category.getSelectionModel().selectFirst();
        this.Env_search_TextField.setText("");
        this.Env_chockbox_all.setSelected(true);
        /*
        this.database = new Database();
        this.count = this.database.Env_sendedCount();
        this.pageCount = this.count / this.itemPerPage + 1;
        this.Env_pagination.setPageCount(this.pageCount);
        this.Env_pagination.setPageFactory(this::createPage);*/
       // getEnv();
    }

    public void Rec_fun_refresh(ActionEvent actionEvent) {
        this.Rec_dropdown_year.getSelectionModel().selectFirst();
        this.Rec_dropdown_category.getSelectionModel().selectFirst();
        this.Rec_dropdown_type.getSelectionModel().selectFirst();
        this.Rec_search_TextField.setText("");
        this.Rec_chockbox_all.setSelected(true);
   // getRec();
    }

    public void Rec_fun_setfile(ActionEvent actionEvent) throws SQLException {
        Received received = (Received) this.Rec_table.getSelectionModel().getSelectedItem();
        if (received != null) {

            File filechkeck = new File(received.getPath());
            if (filechkeck.exists()) {
                Alert   alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText((String)null);
                alert.setContentText(" Voulez-vous remplacer le document?" );
                if (alert.showAndWait().get() == ButtonType.OK) {

                    recSetFile(actionEvent,received);
                }
            } else {
                recSetFile(actionEvent,received);
            }

        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" S'il vous plait, Selecioney un Envoi");
            alert.showAndWait();
        }


    }



    public void Env_fun_setfile(ActionEvent actionEvent) throws SQLException {
        Sended sended = (Sended) this.Env_table.getSelectionModel().getSelectedItem();
        if (sended != null) {

            File filechkeck = new File(sended.getPath());
            if (filechkeck.exists()) {
             Alert   alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText((String)null);
                alert.setContentText(" Voulez-vous remplacer le document?" );
                if (alert.showAndWait().get() == ButtonType.OK) {

                    envSetFile(actionEvent,sended);
                }
            } else {
                envSetFile(actionEvent,sended);
            }

        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText((String)null);
            alert.setContentText(" S'il vous plait, Selecioney un Envoi");
            alert.showAndWait();
        }


    }
    private void envSetFile(ActionEvent actionEvent,Sended sended) throws SQLException {

    fileChooser = new FileChooser();
    MenuItem menuItem = (MenuItem)actionEvent.getTarget();
    Scene scene = menuItem.getParentPopup().getScene();
    Window window = scene.getWindow();
    this.fileChooser.setTitle("Open Resource File");
    this.fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("PDF files (*.pdf)", new String[]{"*.PDF", "*.pdf"})});


    File file =   fileChooser.showOpenDialog(((MenuItem)actionEvent.getTarget()).getParentPopup().getScene().getWindow());
    if ( file != null) {


        String fileName =  file.getName();
        String fileExtension =  fileName.substring( fileName.lastIndexOf(".") + 1,  file.getName().length());
        System.out.println("choosed:"+"Documents/ENV/" +cleanStringSromSpecialCar(sended.reference )+ "." +fileExtension);


        if ( copyFile(file,"Documents/ENV/" +cleanStringSromSpecialCar(sended.reference )+ "." +fileExtension)){







            System.out.println("**************");
            Database database = new Database();
            Connection connection = database.getDBConnection();
            PreparedStatement ps = null;

            try {
                String querry = "UPDATE sended SET path= ?    WHERE id= ? ";
                ps = connection.prepareStatement(querry);
                ps.setString(1,"Documents/ENV/" +cleanStringSromSpecialCar(sended.reference )+ "." +fileExtension);
                ps.setInt(2, sended.getId());
                int updated = ps.executeUpdate();
                if (updated == 1) {
                    System.out.println("updateded ");

                    int index = IntStream.range(0,Env_table.getItems().size()).filter((i) -> {
                        return ((Sended)Env_table.getItems().get(i)).getId() == sended.getId();
                    }).findFirst().orElse(-1);
                    if (index != -1) {
                        Env_table.getItems().remove(index);
                        Sended newsended =new Sended(sended.getId(), sended.getReference(),sended.getDate(),sended.getRecipient(),sended.getObject(),"Documents/ENV/" +cleanStringSromSpecialCar(sended.reference )+ "." +fileExtension);
                        newsended.setFrech(true);
                        System.out.println();
                        System.out.println("Le Path:"+newsended.getPath());
                        Env_table.getItems().add(index, newsended);
                    }





                } else {
                    System.out.print("Not updated");
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.close();
                }

                if (ps != null) {
                    ps.close();
                }


            }


        }


    }

}
    private void recSetFile(ActionEvent actionEvent,Received received) throws SQLException {

    fileChooser = new FileChooser();
    MenuItem menuItem = (MenuItem)actionEvent.getTarget();
    Scene scene = menuItem.getParentPopup().getScene();
    Window window = scene.getWindow();
    this.fileChooser.setTitle("Open Resource File");
    this.fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter[]{new FileChooser.ExtensionFilter("PDF files (*.pdf)", new String[]{"*.PDF", "*.pdf"})});


    File file =   fileChooser.showOpenDialog(((MenuItem)actionEvent.getTarget()).getParentPopup().getScene().getWindow());
    if ( file != null) {


        String fileName =  file.getName();
        String fileExtension =  fileName.substring( fileName.lastIndexOf(".") + 1,  file.getName().length());
        System.out.println("choosed:"+"Documents/REC/" +cleanStringSromSpecialCar(received.reference )+ "." +fileExtension);


        if ( copyFile(file,"Documents/REC/" +cleanStringSromSpecialCar(received.reference )+ "." +fileExtension)){







            System.out.println("**************");
            Database database = new Database();
            Connection connection = database.getDBConnection();
            PreparedStatement ps = null;

            try {
                String querry = "UPDATE received SET path= ?    WHERE id= ? ";
                ps = connection.prepareStatement(querry);
                ps.setString(1,"Documents/REC/" +cleanStringSromSpecialCar(received.reference )+ "." +fileExtension);
                ps.setInt(2, received.getId());
                int updated = ps.executeUpdate();
                if (updated == 1) {
                    System.out.println("updateded ");

                    int index = IntStream.range(0,Rec_table.getItems().size()).filter((i) -> {
                        return ((Received)Rec_table.getItems().get(i)).getId() == received.getId();
                    }).findFirst().orElse(-1);
                    if (index != -1) {
                        Rec_table.getItems().remove(index);
                        Received newreceived =new Received(
                                received.getId(),
                                received.getReference(),
                                received.getOldreference(),
                                received.getDate(),
                                received.getSender(),
                                received.getObject(),
                                "Documents/REC/" +cleanStringSromSpecialCar(received.reference )+ "." +fileExtension);
                        newreceived.setFrech(true);
                        System.out.println();
                        Rec_table.getItems().add(index, newreceived);
                    }





                } else {
                    System.out.print("Not updated");
                }
            } catch (Exception var14) {
                var14.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.close();
                }

                if (ps != null) {
                    ps.close();
                }


            }


        }


    }

}
    private boolean copyFile(File file, String newname) {
        System.out.println("ter");

        try {
            File dest = new File(  newname);
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
    void getEnv(){

        envSearche();
        this.Env_table.getItems().clear();
        this.database = new Database();
        this.count = this.database.envCountSearch(envTempType,envTempRef,envTempDate,envTempRec,envTempObj);
        this.pageCount = this.count / this.itemPerPage + 1;
        this.Env_pagination.setPageCount(this.pageCount);
        this.Env_pagination.setPageFactory(this::envCreatePageSearch);
    }
    void getRec(){

        recSearche();
        this.Rec_table.getItems().clear();
        this.database = new Database();
        this.count = this.database.recCountSearch(recTempType,recTempRef,recTempOld,recTempDate,recTempSen,recTempObj);
        this.pageCount = this.count / this.itemPerPage + 1;
        this.Rec_pagination.setPageCount(this.pageCount);
        this.Rec_pagination.setPageFactory(this::recCreatePageSearch);

    }
     private void recSearche() {


         if (Rec_dropdown_year.getValue().replace(" ", "").toLowerCase()
                 .equals("annees".replace(" ", "").toLowerCase())) {
             recDate=  "";
         }else {
             recDate=Rec_dropdown_year.getValue();
         }

         if (Rec_dropdown_type.getValue().replace(" ", "").toLowerCase()
                 .equals("categorie".replace(" ", "").toLowerCase())) {
             recRef=  "";
         }else {
             recRef=Rec_dropdown_type.getValue();
         }

         if (Rec_dropdown_category.getValue().replace(" ", "").toLowerCase()
                 .equals("Expediteur".replace(" ", "").toLowerCase())) {
             recSen="";
         }else {
             recSen=Rec_dropdown_category.getValue();
         }
         recTempType=true;
         recTempRef=recRef;
         recTempOld="";
         recTempDate=recDate;
         recTempSen=recSen;
         recTempObj="";
         if (Rec_search_TextField.getText().equals("")){
             recTempType=true;
             recTempRef=recRef;
             recTempOld="";
             recTempDate=recDate;
             recTempSen=recSen;
             recTempObj="";
         }else {
             if (this.Rec_chockbox_all.isSelected()) {
                 recTempType=false;
                 recTempRef=Rec_search_TextField.getText();
                 recTempOld=Rec_search_TextField.getText();
                 recTempDate=Rec_search_TextField.getText();
                 recTempSen=Rec_search_TextField.getText();
                 recTempObj=Rec_search_TextField.getText();
             }else
             if (this.Rec_chockbox_referance.isSelected()) {
                 recTempType=true;
                 recTempRef=recRef+Rec_search_TextField.getText();
                 recTempOld="";
                 recTempDate=recDate;
                 recTempSen=recSen;
                 recTempObj="";
             }else
             if (this.Rec_chockbox_oldreferance.isSelected()) {
                 recTempType=true;
                 recTempRef=recRef;
                 recTempOld=""+Rec_search_TextField.getText();
                 recTempDate=recDate;
                 recTempSen=recSen;
                 recTempObj="";
             } else if (this.Rec_chockbox_date.isSelected()) {
                 recTempType=true;
                 recTempRef=recRef;
                 recTempOld="";
                 recTempDate=recDate+Rec_search_TextField.getText();
                 recTempSen=recSen;
                 recTempObj="";
             } else if (this.Rec_chockbox_object.isSelected()) {
                 recTempType=true;
                 recTempRef=recRef;
                 recTempOld="";
                 recTempDate=recDate;
                 recTempSen=recSen;
                 recTempObj=""+Rec_search_TextField.getText();
             }

         }
    }

}

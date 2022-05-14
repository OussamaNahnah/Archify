module oussama.nahnah.archify {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.apache.poi.poi;

    requires java.sql;


    opens oussama.nahnah.archify to javafx.fxml;
    exports oussama.nahnah.archify;
}
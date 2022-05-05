//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package oussama.nahnah.archify;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Application extends javafx.application.Application {
    public Application() {
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load());
        stage.getIcons().add(new Image((InputStream)Objects.requireNonNull(Application.class.getResourceAsStream("icon.png"))));
        stage.setTitle("Archify");
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

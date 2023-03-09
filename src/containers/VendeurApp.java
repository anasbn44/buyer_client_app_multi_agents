package containers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VendeurApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(VendeurApp.class.getResource("../view/vendeur_gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MyChat");
        stage.setScene(scene);
        stage.show();
    }
}

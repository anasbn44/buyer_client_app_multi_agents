package containers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientAppTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(VendeurAppTest.class.getResource("../view/client_gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MyChat");
        stage.setScene(scene);
        stage.show();
    }
}

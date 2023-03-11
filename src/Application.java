import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("sell-buy");
        stage.setScene(scene);
        stage.show();
    }
}

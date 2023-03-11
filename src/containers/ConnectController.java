package containers;

import containers.ClientContainer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private Button connect;
    @FXML
    private ComboBox<String> comboBox;

    public void onConnect(String type) throws IOException {
        Stage next = (Stage) connect.getScene().getWindow();
        FXMLLoader fxmlLoader;
        String user = username.getText();
        if(type.equals("VENDEUR")){
            fxmlLoader = new FXMLLoader(getClass().getResource("../view/vendeur_gui.fxml"));
            VendeurContainer vendeurContainer = new VendeurContainer();
            vendeurContainer.setNickName(user);
            fxmlLoader.setController(vendeurContainer);
        }else {
            fxmlLoader = new FXMLLoader(getClass().getResource("../view/client_gui.fxml"));
            ClientContainer clientContainer = new ClientContainer();
            clientContainer.setNickName(user);
            fxmlLoader.setController(clientContainer);
        }
        System.out.println(fxmlLoader);
        next.setScene(new Scene(fxmlLoader.load()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.getItems().add("VENDEUR");
        comboBox.getItems().add("CLIENT");

        connect.setOnAction(keyEvent -> {
            try {
                onConnect(comboBox.getSelectionModel().getSelectedItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        username.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                try {
                    onConnect(comboBox.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

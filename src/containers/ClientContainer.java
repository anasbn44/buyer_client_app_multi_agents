package containers;

import model.ClientAgent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientContainer implements Initializable {
    @FXML
    private TextField text;
    @FXML
    private Button search;
    @FXML
    private ListView<String> listView;

    private String nickName;
    private ClientAgent clientAgent;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setClient(ClientAgent clientAgent) {
        this.clientAgent = clientAgent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            startContainer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        search.setOnAction(keyEvent -> askServices());
    }

    public void startContainer () throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profile);
        AgentController agent=container.createNewAgent( nickName,"model.ClientAgent",new Object[]{this});
        agent.start();
    }

    public void askServices() {
        listView.getItems().clear();
        GuiEvent guiEvent = new GuiEvent(this, 1);
        guiEvent.addParameter(text.getText());
        clientAgent.onGuiEvent(guiEvent);
    }

    public void showServices(String service){
        Platform.runLater(() -> listView.getItems().add(service));
    }
}

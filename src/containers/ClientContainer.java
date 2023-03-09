package containers;

import agents.Client;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
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

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            startContainer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startContainer () throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profile);
        AgentController agent=container.createNewAgent("client","agents.Client",new Object[]{this});
        agent.start();
    }

    public void getServices(){

    }
}

package containers;

import model.Produit;
import model.VendeurAgent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VendeurContainer implements Initializable {
    @FXML
    private TextField type;
    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private TextField prix;
    @FXML
    private Button add;
    @FXML
    private ListView<Produit> listView;

    private VendeurAgent vendeurAgent;

    private String nickName;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setVendeur(VendeurAgent vendeurAgent) {
        this.vendeurAgent = vendeurAgent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            startContainer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        add.setOnAction(keyEvent -> addService());
    }

    public void startContainer () throws Exception {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer container=runtime.createAgentContainer(profile);
        AgentController agent=container.createNewAgent( nickName,"model.VendeurAgent",new Object[]{this});
        agent.start();
    }

    public void addService() {
        GuiEvent guiEvent = new GuiEvent(this, 1);

        Produit produit = new Produit(nom.getText(), desc.getText(), Float.parseFloat(prix.getText()));
        guiEvent.addParameter(type.getText());
        guiEvent.addParameter(produit.toString());
        vendeurAgent.onGuiEvent(guiEvent);
        listView.getItems().add(produit);
    }
}

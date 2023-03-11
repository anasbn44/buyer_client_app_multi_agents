package containers;

import agents.Vendeur;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
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

    private Vendeur vendeur;

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
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
        AgentController agent=container.createNewAgent("vendeur","agents.Vendeur",new Object[]{this});
        agent.start();
    }

    public void addService() {
        GuiEvent guiEvent = new GuiEvent(this, 1);

        Produit produit = new Produit(nom.getText(), desc.getText(), Float.parseFloat(prix.getText()));
        guiEvent.addParameter(type.getText());
        guiEvent.addParameter(produit.toString());
        vendeur.onGuiEvent(guiEvent);
        listView.getItems().add(produit);
    }
}

package agents;

import containers.ClientContainer;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

public class Client extends GuiAgent {
    /*--module-path
"C:\Program Files\Java\javafx-sdk-19\lib"
--add-modules
javafx.controls,javafx.fxml*/
    private ClientContainer clientContainer;
    private AID[] vendeurAgents;
     @Override
    protected void setup() {
        clientContainer = (ClientContainer) getArguments()[0];
        clientContainer.setClient(this);
    }


    @Override
    public void onGuiEvent(GuiEvent guiEvent) {
        String search = guiEvent.getParameter(0).toString();
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        ServiceDescription service = new ServiceDescription();
        service.setType(search);
        dfAgentDescription.addServices(service);

        try {
            DFAgentDescription[] result = DFService.search(this, dfAgentDescription);
            vendeurAgents = new AID[result.length];

        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}

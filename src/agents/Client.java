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

import java.util.Iterator;

public class Client extends GuiAgent {
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
            System.out.println(result.length);
            for (DFAgentDescription a : result){
                Iterator<ServiceDescription> descriptionIterator = a.getAllServices();
                while (descriptionIterator.hasNext()){
                    ServiceDescription myService = descriptionIterator.next();
                    if(myService.getType().equals(search))
                        clientContainer.showServices(myService.getName() + ", from : " + a.getName());
                }
            }

        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}

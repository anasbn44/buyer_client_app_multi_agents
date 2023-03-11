package model;

import containers.VendeurContainer;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

public class VendeurAgent extends GuiAgent {
    private VendeurContainer vendeurContainer;
    private DFAgentDescription dfAgentDescription;
    @Override
    protected void setup() {
        vendeurContainer = (VendeurContainer) getArguments()[0];
        vendeurContainer.setVendeur(this);
        dfAgentDescription = new DFAgentDescription();
    }

    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuiEvent(GuiEvent guiEvent) {

        ServiceDescription service = new ServiceDescription();
        service.setType(guiEvent.getParameter(0).toString());
        service.setName(guiEvent.getParameter(1).toString());
        dfAgentDescription.addServices(service);
        try {
            DFService.register(this, dfAgentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}

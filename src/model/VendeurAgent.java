package model;

import containers.VendeurContainer;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.util.leap.Iterator;

import java.util.Base64;

public class VendeurAgent extends GuiAgent {
    private VendeurContainer vendeurContainer;
    private DFAgentDescription dfAgentDescription;
    @Override
    protected void setup() {
        vendeurContainer = (VendeurContainer) getArguments()[0];
        vendeurContainer.setVendeur(this);
        dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(getAID());
        try {
            DFService.register(this, dfAgentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

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
        Produit myProduit = (Produit) guiEvent.getParameter(1);
        myProduit.setAgent(this);
        String encodedProduit = Base64.getEncoder().encodeToString(myProduit.toString().getBytes());
        service.setName(encodedProduit);
        dfAgentDescription.addServices(service);
        Iterator allServices = dfAgentDescription.getAllServices();
        while (allServices.hasNext()){
            System.out.println(allServices.next().toString());
        }

        try {
            DFService.modify(this, dfAgentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}

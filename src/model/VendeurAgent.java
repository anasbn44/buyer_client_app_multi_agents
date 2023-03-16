package model;

import containers.VendeurContainer;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage response = receive();
                if(response != null){
                    if(response.getPerformative() == ACLMessage.CONFIRM){
                        ACLMessage aclMessage = new ACLMessage(ACLMessage.AGREE);
                        aclMessage.setContent("Congrats, you have bought " + response.getContent());
                        send(aclMessage);
                    } else {
                        ACLMessage aclMessage = new ACLMessage(ACLMessage.CANCEL);
                        send(aclMessage);
                    }
                } else {
                    block();
                }
            }
        });

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
        try {
            ServiceDescription service = new ServiceDescription();
            service.setType(guiEvent.getParameter(0).toString());

            service.setName(guiEvent.getParameter(1).toString());
            dfAgentDescription.addServices(service);
            Iterator allServices = dfAgentDescription.getAllServices();
            while (allServices.hasNext()){
                System.out.println(allServices.next().toString());
            }


            DFService.modify(this, dfAgentDescription);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}

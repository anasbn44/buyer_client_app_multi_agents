package model;

import containers.ClientContainer;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.Iterator;

public class ClientAgent extends GuiAgent {
    private ClientContainer clientContainer;
    private AID[] vendeurAgents;
     @Override
    protected void setup() {
        clientContainer = (ClientContainer) getArguments()[0];
        clientContainer.setClient(this);
    }


    @Override
    public void onGuiEvent(GuiEvent guiEvent) {
         if (guiEvent.getType() == 2) {
             Produit produit = (Produit) guiEvent.getParameter(0);
             ACLMessage aclMessage = new ACLMessage(ACLMessage.PROPOSE);
             aclMessage.addReceiver(produit.getAgent().getAID());
             send(aclMessage);
         } else {
             String search = guiEvent.getParameter(0).toString();
             DFAgentDescription dfAgentDescription = new DFAgentDescription();
             ServiceDescription service = new ServiceDescription();
             service.setType(search);
             dfAgentDescription.addServices(service);

             try {
                 DFAgentDescription[] result = DFService.search(this, dfAgentDescription);
                 System.out.println(result.length);
                 for (DFAgentDescription a : result) {
                     Iterator<ServiceDescription> descriptionIterator = a.getAllServices();
                     while (descriptionIterator.hasNext()) {
                         ServiceDescription myService = descriptionIterator.next();
                         if (myService.getType().equals(search)) {
                             byte[] bytes = Base64.getDecoder().decode(myService.getName());
                             ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                             ObjectInputStream ois = new ObjectInputStream(bais);
                             Produit produit = (Produit) ois.readObject();
                             clientContainer.showServices(produit);
                         }
                     }
                 }

             } catch (FIPAException | IOException | ClassNotFoundException e) {
                 e.printStackTrace();
             }
         }
    }
}

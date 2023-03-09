package agents;

import containers.ClientContainer;
import jade.core.Agent;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

public class Client extends GuiAgent {
    /*--module-path
"C:\Program Files\Java\javafx-sdk-19\lib"
--add-modules
javafx.controls,javafx.fxml*/
    private ClientContainer clientContainer;

    @Override
    protected void setup() {
        clientContainer = (ClientContainer) getArguments()[0];
        clientContainer.setClient(this);
    }


    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }
}

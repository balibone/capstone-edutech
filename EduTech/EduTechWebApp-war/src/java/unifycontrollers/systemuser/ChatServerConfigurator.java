package unifycontrollers.systemuser;

import javax.servlet.http.HttpSession;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class ChatServerConfigurator extends Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig serverEC, HandshakeRequest request, HandshakeResponse response) {
        serverEC.getUserProperties().put("username", (String)((HttpSession) request.getHttpSession()).getAttribute("username"));
    }
}

package unifycontrollers.systemuser;

import javax.servlet.http.HttpSession;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import commoninfraentities.UserEntity;

public class ChatServerConfigurator extends Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        UserEntity user = (UserEntity) httpSession.getAttribute("user");
        config.getUserProperties().put("user", user);
    }
}

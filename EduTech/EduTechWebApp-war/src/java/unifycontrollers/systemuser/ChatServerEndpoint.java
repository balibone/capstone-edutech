/******************************************************************************************************
*   Title:                  ChatServerEndpoint.java
*   Purpose:                CHAT ENDPOINT FOR OTHER USERS TO CONNECT TO
*   Author:                 TAN CHIN WEE WINSTON
*   Date:                   29 MARCH 2018
*   Code version:           1.1
*   Availability:           === FULL REPLICATE STRICTLY NOT ALLOWED. YOU HAVE BEEN WARNED. ===
*                           PLEASE CREDIT THE AUTHOR IF YOU WISH TO USE ANY PART OF THIS CODE.
*******************************************************************************************************/

package unifycontrollers.systemuser;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/WebSocketGateway/{sessionID}", configurator=ChatServerConfigurator.class)
public class ChatServerEndpoint {
    private static final Map<String, Set<Session>> SESSIONIDS = (Map<String, Set<Session>>)Collections.synchronizedMap(new HashMap<String, Set<Session>>());
    
    @OnOpen
    public void handleOpen(EndpointConfig config, Session userSession, @PathParam("sessionID") String sessionID) {
        userSession.getUserProperties().put("username", config.getUserProperties().get("username"));
        userSession.getUserProperties().put("sessionID", sessionID);
        Set<Session> sessionUsers = getChatSession(sessionID);
        sessionUsers.add(userSession);
    }
    
    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException {
        // String username = (String)userSession.getUserProperties().get("username");
        String sessionID = (String)userSession.getUserProperties().get("sessionID");
        Set<Session> sessionUsers = getChatSession(sessionID);
        sessionUsers.stream().forEach(x-> {
            try {
                // x.getBasicRemote().sendText(buildJSONData(username, message));
                x.getBasicRemote().sendText(message);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    
    @OnClose
    public void handleClose(Session userSession) {
        String sessionID = (String)userSession.getUserProperties().get("sessionID");
        Set<Session> sessionUsers = getChatSession(sessionID);
        sessionUsers.remove(userSession);
    }
    
    @OnError
    public void handleError(Throwable t) {}
    
    public Set<Session> getChatSession(String sessionName) {
        Set<Session> sessionID = SESSIONIDS.get(sessionName);
        if(sessionID == null) {
            sessionID = Collections.synchronizedSet(new HashSet<Session>());
            SESSIONIDS.put(sessionName, sessionID);
        }
        return sessionID;
    }
    
    /*
    private String buildJSONData(String username, String message) {
        JsonObject jsonObject = Json.createObjectBuilder().add("message", message).build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    } */
}
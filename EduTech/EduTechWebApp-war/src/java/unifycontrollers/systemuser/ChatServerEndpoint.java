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
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/WebSocketGateway", configurator=ChatServerConfigurator.class)
public class ChatServerEndpoint {
    private static final Set<Session> CHATUSERS = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void handleOpen(Session chatSession) {
        CHATUSERS.add(chatSession);
    }
    
    @OnMessage
    public void handleMessage(String message, Session chatSession) throws IOException {
        synchronized (CHATUSERS) {
            for (Session userChatSession : CHATUSERS) {
                if (!userChatSession.equals(chatSession)) {
                    userChatSession.getBasicRemote().sendText(message);
                }
            }
        }
    }
    
    @OnClose
    public void handleClose(Session chatSession) {
        CHATUSERS.remove(chatSession);
    }
}

package ch.performancebuildings.websocket.client;

import ch.performancebuildings.websocket.client.impl.AbstractWebSocketImpl;

import com.google.gwt.core.client.GWT;

public class WebSocket {
    
    private final AbstractWebSocketImpl impl = GWT.create(AbstractWebSocketImpl.class);
    
    public WebSocket(String url) {
	impl.init(url);
    }

   public void send(String message) {
       impl.send(message);
   }
    
    public void addHandler(WebSocketEventHandler handler) {
	impl.addHandler(handler);
    } 
}

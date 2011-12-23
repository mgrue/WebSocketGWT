package ch.performancebuildings.websocket.client;

public interface WebSocketEventHandler {

    public void onStateChange(WebSocketState newState);
    
    public void onMessage(String message);
    
    public void onFailure(Throwable caught);
}

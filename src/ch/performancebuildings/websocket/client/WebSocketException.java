package ch.performancebuildings.websocket.client;

public class WebSocketException extends Exception {

    private static final long serialVersionUID = -1L;
    
    public WebSocketException(String message) {
	super(message);
    }
}

package ch.performancebuildings.websocket.client.impl;


import com.google.gwt.core.client.JavaScriptObject;

public class WebSocketImplMoz extends AbstractWebSocketImpl {
    
    protected native JavaScriptObject createNativeInstance(String url) /*-{
	return new MozWebSocket(url);
}-*/;
}

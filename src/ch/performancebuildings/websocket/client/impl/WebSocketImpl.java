package ch.performancebuildings.websocket.client.impl;


import com.google.gwt.core.client.JavaScriptObject;

public class WebSocketImpl extends AbstractWebSocketImpl {

    @Override
    protected native JavaScriptObject createNativeInstance(String url) /*-{
	return new WebSocket(url);
    }-*/;

}

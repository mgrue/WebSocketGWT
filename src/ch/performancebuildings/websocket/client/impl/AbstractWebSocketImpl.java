package ch.performancebuildings.websocket.client.impl;

import java.util.ArrayList;
import java.util.List;

import ch.performancebuildings.websocket.client.WebSocketEventHandler;
import ch.performancebuildings.websocket.client.WebSocketException;
import ch.performancebuildings.websocket.client.WebSocketState;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class AbstractWebSocketImpl {

    protected JavaScriptObject instance;

    private final List<WebSocketEventHandler> handlerList = new ArrayList<WebSocketEventHandler>();

    public void init(String url) {
	instance = createNativeInstance(url);
	initJS(instance, this);
    }

    public JavaScriptObject getWebSocket() {
	return instance;
    }

    public native void send(String message) /*-{
		try {
			var ws = this.@ch.performancebuildings.websocket.client.impl.AbstractWebSocketImpl::instance
			ws.send(message);
		} catch (err) {
			this.@ch.performancebuildings.websocket.client.impl.AbstractWebSocketImpl::onFailure(Ljava/lang/String;)(err.type)
		}
    }-*/;

    public void addHandler(WebSocketEventHandler handler) {
	handlerList.add(handler);
    }

    protected abstract JavaScriptObject createNativeInstance(String url);

    protected void onOpen() {
	setStateForAll(WebSocketState.Open);
    }

    protected void onClose() {
	setStateForAll(WebSocketState.Closed);
    }
    
    protected void onError() {
	setStateForAll(WebSocketState.Error);
    }

    protected void onMessage(String message) {
	for (WebSocketEventHandler h : handlerList) {
	    h.onMessage(message);
	}
    }

    protected void onFailure(String message) {
	for (WebSocketEventHandler h : handlerList) {
	    h.onFailure(new WebSocketException(message));
	}
    }

    private native void initJS(JavaScriptObject ws, AbstractWebSocketImpl obj) /*-{
		try {
			ws.onopen = function(event) {
				obj.@ch.performancebuildings.websocket.client.impl.AbstractWebSocketImpl::onOpen()()
			}

			ws.onclose = function(event) {
				obj.@ch.performancebuildings.websocket.client.impl.AbstractWebSocketImpl::onClose()()
			}
			
			ws.onerror = function(event) {
				obj.@ch.performancebuildings.websocket.client.impl.AbstractWebSocketImpl::onError()()
			}

			ws.onmessage = function(event) {
				obj.@ch.performancebuildings.websocket.client.impl.AbstractWebSocketImpl::onMessage(Ljava/lang/String;)(event.data)
			}
		} catch (err) {
			this.@ch.performancebuildings.websocket.client.impl.AbstractWebSocketImpl::onFailure(Ljava/lang/String;)(err.type)
		}
    }-*/;
    
    private void setStateForAll(WebSocketState newState) {
	for (WebSocketEventHandler h : handlerList) {
	    h.onStateChange(newState);
	}
    }
}

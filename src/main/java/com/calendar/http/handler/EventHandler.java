package com.calendar.http.handler;

import com.calendar.http.util.JsonUtil;
import com.calendar.model.Event;
import com.calendar.service.EventService;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;
import java.util.Map;

public class EventHandler extends BaseHandler {
    private static final String BASE_PATH = "/api/event";

    private final EventService eventService;

    public EventHandler(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    protected void handleRequest(HttpExchange exchange) throws Exception {
        if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            List<Event> events = eventService.listAll();
            sendJson(exchange, 200, JsonUtil.events(events));
            return;
        }

        if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            Map<String, String> payload = JsonUtil.parseFlatObject(requireJsonBody(exchange));
            Event createdEvent = eventService.create(payload);
            sendJson(exchange, 201, JsonUtil.event(createdEvent));
            return;
        }

        sendMethodNotAllowed(exchange, "GET, POST, OPTIONS");
    }
}

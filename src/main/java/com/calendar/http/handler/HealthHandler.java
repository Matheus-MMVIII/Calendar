package com.calendar.http.handler;

import com.calendar.http.util.JsonUtil;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class HealthHandler extends BaseHandler {
    @Override
    protected void handleRequest(HttpExchange exchange) throws Exception {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendMethodNotAllowed(exchange, "GET, OPTIONS");
            return;
        }

        sendJson(exchange, 200, JsonUtil.object("status", "ok"));
    }
}

package com.calendar.http.handler;

import com.calendar.exception.ApiException;
import com.calendar.http.util.JsonUtil;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public abstract class BaseHandler implements HttpHandler {
    @Override
    public final void handle(HttpExchange exchange) throws IOException {
        applyDefaultHeaders(exchange);

        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendNoContent(exchange);
            return;
        }

        try {
            handleRequest(exchange);
        } catch (ApiException ex) {
            sendJson(exchange, ex.getStatusCode(), JsonUtil.error(ex.getMessage()));
        }/* catch (SQLException ex) {
            System.err.println(ex.getMessage());
            sendJson(exchange, 500, JsonUtil.error("Internal error accessing the database."));
        }*/ catch (IllegalArgumentException ex) {
            sendJson(exchange, 400, JsonUtil.error(ex.getMessage()));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            sendJson(exchange, 500, "Internal server error.");
        } finally {
            exchange.close();
        }
    }

    protected abstract void handleRequest(HttpExchange exchange) throws Exception;

    protected static void applyDefaultHeaders(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.set("Access-Control-Allow-Headers", "Content-Type");
    }

    protected static void sendNoContent(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(204, -1);
    }

    protected static void sendJson(HttpExchange exchange, int statusCode, String json) throws IOException {
        byte[] responseBytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        try (OutputStream responseBody = exchange.getResponseBody()) {
            responseBody.write(responseBytes);
        }
    }

    protected static void sendMethodNotAllowed(HttpExchange exchange, String allowedMethods) throws IOException {
        exchange.getResponseHeaders().set("Allow", allowedMethods);
        sendJson(exchange, 405, JsonUtil.error("This method is not permitted."));
    }
}

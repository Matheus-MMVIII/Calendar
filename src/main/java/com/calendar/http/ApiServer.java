package com.calendar.http;

import com.calendar.http.handler.EventHandler;
import com.calendar.http.handler.HealthHandler;
import com.calendar.repository.EventRepository;
import com.calendar.service.EventService;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {
    private final HttpServer server;

    public ApiServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8081), 50);
        registerContexts();
    }

    public void start() {
        server.start();
    }

    private void registerContexts() {
        EventService eventService = new EventService(new EventRepository());

        server.createContext("/health", new HealthHandler());
        server.createContext("/api/events", new EventHandler(eventService));
    }

}

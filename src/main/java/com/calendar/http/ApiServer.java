package com.calendar.http;

import com.calendar.http.handler.HealthHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {
    private final HttpServer server;

    public ApiServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8081), 50);
    }

    public void run() {
        server.createContext("/health", new HealthHandler());
        server.start();
    }
}

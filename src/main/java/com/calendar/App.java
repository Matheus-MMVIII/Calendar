package com.calendar;


import com.calendar.http.ApiServer;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        ApiServer server = new ApiServer();
        server.start();
    }
}
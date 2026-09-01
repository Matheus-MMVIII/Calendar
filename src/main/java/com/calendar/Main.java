package com.calendar;

import com.calendar.model.Date;

public class Main {
    public static void main(String[] args) {
        Date date = Date.getInstance();
        System.out.println("Data e Hora atual: "+date.getDate());
    }

}
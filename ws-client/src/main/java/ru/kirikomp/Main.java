package ru.kirikomp;

import ru.kirikomp.service.ToDoService;
import ru.kirikomp.service.ToDoServiceWs;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/jee-servlet/ToDoService/ToDoServiceImpl?wsdl");

        ToDoService toDoService = new ToDoService(url);
        ToDoServiceWs port = toDoService.getToDoServiceImplPort();
        port.findAll();
    }
}

package com.dgeorgiev.userservice.servlet;

import com.dgeorgiev.userservice.servlet.tmp.Hi;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Main
{

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();

        handler.addServletWithMapping(Hi.class, "/hi");
        handler.addServletWithMapping(GetAllUsers.class, "/user/getAll");


        server.setHandler(handler);

        server.start();
        server.join();
    }
}

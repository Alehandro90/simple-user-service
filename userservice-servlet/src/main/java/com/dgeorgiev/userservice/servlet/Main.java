package com.dgeorgiev.userservice.servlet;

import com.dgeorgiev.userservice.core.config.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Main
{

    public static void main(String[] args) throws Exception
    {

        Configuration.MIGRATOR.doIt();

        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();

        handler.addServletWithMapping(GetAllUsers.class, "/user/getAll");
        handler.addServletWithMapping(CreateNewUser.class, "/user/create");


        server.setHandler(handler);

        server.start();
        server.join();

    }
}

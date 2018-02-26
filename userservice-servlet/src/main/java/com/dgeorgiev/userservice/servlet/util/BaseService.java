package com.dgeorgiev.userservice.servlet.util;

import fj.data.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseService<A> extends HttpServlet
{


    public abstract Validation<String, A> validate(HttpServletRequest req);

    public abstract void service(A a, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    @Override protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Validation<String, A> v = validate(req);

        if (v.isFail())
        {
            String error = v.fail();
            resp.getWriter().println(Json.writeJson(error));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().flush();
            return;
        }

        A in = v.success();
        service(in, req, resp);

    }
}

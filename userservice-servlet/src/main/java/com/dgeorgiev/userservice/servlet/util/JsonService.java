package com.dgeorgiev.userservice.servlet.util;

import fj.P1;
import fj.data.Either;
import fj.data.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static fj.Function.identity;
import static fj.data.Validation.validation;

public abstract class JsonService<A, B> extends BaseService<A>
{

    @Override public void service(A a, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        P1<Validation<String, B>> v = service(a)
                .map(x -> validation(x.toEither().left().map(Throwables::getStackTraceAsString)));

        //run it
        Validation<String, B> result = v.f();

        Either<String, String> payload = result.map(Json::writeJson).toEither();
        int sc = payload.isLeft() ? HttpServletResponse.SC_INTERNAL_SERVER_ERROR : HttpServletResponse.SC_OK;
        String payloadString = payload.either(identity(), identity());

        resp.getWriter().println(payloadString);
        resp.setStatus(sc);
        resp.getWriter().flush();
        return;
    }

    public abstract P1<Validation<Exception, B>> service(A a);
}

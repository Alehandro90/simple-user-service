package com.dgeorgiev.userservice.servlet.util;

import fj.F;
import fj.P1;
import fj.Try;
import fj.Unit;
import fj.data.Validation;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static fj.data.Validation.fail;
import static fj.data.Validation.success;
import static fj.data.Validation.validation;

public class Validations
{

    public static Validation<String, Unit> noOp()
    {
        return success(Unit.unit());
    }

    public static Validation<String, String> exists(HttpServletRequest req, String paramName)
    {
        String val = req.getParameter(paramName);
        if (val == null || val.isEmpty())
        {
            return fail("PARAM_NOT_DEFINED");
        }

        return success(val);
    }

    public static <E1, E2, A> Validation<E2, A> mapLeft(Validation<E1, A> in, F<E1, E2> f)
    {
        return validation(in.toEither().left().map(f));
    }

    public static Validation<String, HttpServletRequest> methodIs(HttpServletRequest req, String method)
    {
        if (!req.getMethod().equals(method))
        {
            return fail("METHOD_NOT_SUPP");
        }
        else
        {
            return success(req);
        }
    }

    public static <A> Validation<String, A> bodyAsJson(HttpServletRequest req, Class<A> clazz)
    {
        P1<Validation<IOException, A>> v = Try.f(() -> Json.MAPPER.readValue(req.getInputStream(), clazz));
        return mapLeft(v.f(), Throwables::getStackTraceAsString);
    }

}

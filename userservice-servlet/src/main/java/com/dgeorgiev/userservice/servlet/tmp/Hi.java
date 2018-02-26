package com.dgeorgiev.userservice.servlet.tmp;

import com.dgeorgiev.userservice.servlet.util.JsonService;
import fj.P1;
import fj.data.List;
import fj.data.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dgeorgiev.userservice.servlet.util.Validations.exists;
import static fj.P.lazy;
import static fj.data.List.arrayList;
import static fj.data.Validation.success;

@SuppressWarnings("serial")
public class Hi extends JsonService<String, List<String>>
{

    @Override public Validation<String, String> validate(HttpServletRequest req)
    {

        return exists(req, "name");
    }

    @Override public P1<Validation<Exception, List<String>>> service(String name)
    {
        List<String> payload = arrayList("hi", name);
        return lazy(() -> success(payload));
    }


}

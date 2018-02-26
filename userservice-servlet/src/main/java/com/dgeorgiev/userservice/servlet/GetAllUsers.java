package com.dgeorgiev.userservice.servlet;

import com.dgeorgiev.userservice.servlet.util.JsonService;
import com.dgeorgiev.userservice.servlet.util.Validations;
import fj.P1;
import fj.Unit;
import fj.data.List;
import fj.data.Validation;

import javax.servlet.http.HttpServletRequest;

import static fj.P.lazy;
import static fj.data.List.arrayList;
import static fj.data.Validation.success;

public class GetAllUsers extends JsonService<Unit, List<Integer>>
{

    @Override public Validation<String, Unit> validate(HttpServletRequest req)
    {
        return Validations.noOp();
    }


    @Override public P1<Validation<Exception, List<Integer>>> service(Unit in)
    {
        return lazy(() -> success(arrayList(1, 2, 3)));
    }


}

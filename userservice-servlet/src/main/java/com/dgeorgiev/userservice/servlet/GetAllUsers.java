package com.dgeorgiev.userservice.servlet;

import com.dgeorgiev.userservice.core.UserDb;
import com.dgeorgiev.userservice.domain.User;
import com.dgeorgiev.userservice.servlet.util.JsonService;
import com.dgeorgiev.userservice.servlet.util.PagingParams;
import com.dgeorgiev.userservice.servlet.util.Throwables;
import fj.P1;
import fj.data.List;
import fj.data.Validation;

import javax.servlet.http.HttpServletRequest;

import static com.dgeorgiev.userservice.core.config.Configuration.DBI;
import static com.dgeorgiev.userservice.servlet.util.Validations.exists;
import static com.dgeorgiev.userservice.servlet.util.Validations.mapLeft;

@SuppressWarnings("serial")
public class GetAllUsers extends JsonService<PagingParams, List<User>>
{

    @Override public Validation<String, PagingParams> validate(HttpServletRequest req)
    {
        Validation<String, Integer> limit = exists(req, "limit")
                .bind(str -> mapLeft(Validation.parseInt(str), Throwables::getStackTraceAsString));

        Validation<String, Integer> offset = exists(req, "offset")
                .bind(str -> mapLeft(Validation.parseInt(str), Throwables::getStackTraceAsString));

        Validation<List<String>, PagingParams> res = offset.accumulate(limit, PagingParams::new);
        return mapLeft(res, xs -> xs.head());

    }


    @Override public P1<Validation<Exception, List<User>>> service(PagingParams in)
    {
        return DBI.submit(UserDb.selectAll(in.offset, in.limit));
    }


}

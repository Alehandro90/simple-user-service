package com.dgeorgiev.userservice.servlet;

import com.dgeorgiev.userservice.core.UserDb;
import com.dgeorgiev.userservice.servlet.util.JsonService;
import fj.P1;
import fj.data.Validation;

import javax.servlet.http.HttpServletRequest;

import static com.dgeorgiev.userservice.core.config.Configuration.DBI;
import static com.dgeorgiev.userservice.servlet.util.Validations.bodyAsJson;
import static com.dgeorgiev.userservice.servlet.util.Validations.methodIs;

@SuppressWarnings("serial")
public class CreateNewUser extends JsonService<CreateNewUser.NewUser, Integer>
{

    @Override public Validation<String, NewUser> validate(HttpServletRequest req)
    {
        return methodIs(req, "POST").bind(post -> bodyAsJson(post, NewUser.class));
    }

    @Override public P1<Validation<Exception, Integer>> service(NewUser newUser)
    {
        return DBI.submit(UserDb.insertUser(newUser.user, newUser.email, newUser.password));
    }



    public static class NewUser
    {
        public final String user;
        public final String email;
        public final String password;

        public NewUser(String user, String email, String password)
        {
            this.user = user;
            this.email = email;
            this.password = password;
        }
    }

}

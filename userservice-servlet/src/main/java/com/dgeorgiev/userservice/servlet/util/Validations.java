package com.dgeorgiev.userservice.servlet.util;

import fj.Unit;
import fj.data.Validation;

import javax.servlet.http.HttpServletRequest;

import static fj.data.Validation.success;

public class Validations
{

    public static Validation<String, Unit> noOp()
    {
        return success(Unit.unit());
    }

}

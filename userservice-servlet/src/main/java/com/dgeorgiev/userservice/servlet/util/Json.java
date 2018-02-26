package com.dgeorgiev.userservice.servlet.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novarto.jackson.fj.FjModule;

public class Json
{

    public static final ObjectMapper MAPPER = new ObjectMapper();

    static
    {
        MAPPER.registerModule(new FjModule());
    }

    public static String writeJson(Object o)
    {
        try
        {
            return MAPPER.writeValueAsString(o);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }
}

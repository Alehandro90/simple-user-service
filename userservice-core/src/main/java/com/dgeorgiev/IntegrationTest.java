package com.dgeorgiev;

import com.dgeorgiev.userservice.core.config.Configuration;

public class IntegrationTest
{

    static
    {
        Configuration.MIGRATOR.doIt();
    }
}

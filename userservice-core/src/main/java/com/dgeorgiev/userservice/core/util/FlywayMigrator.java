package com.dgeorgiev.userservice.core.util;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class FlywayMigrator
{

    private final String migrationPackage;
    private final DataSource datasource;

    public FlywayMigrator(String migrationPackage, DataSource ds)
    {
        this.migrationPackage = migrationPackage;
        this.datasource = ds;
    }

    void foo()
    {
        // set schema name
//        FLYWAY.setSchemas("FLYWAY");

        Flyway flyway = new Flyway();
        // set datasource
        flyway.setDataSource(datasource);


        // set location folders which contain migrations that needs to be applied by flyway
        final String packageFolder = migrationPackage.replace(".", "/");
//        final String migrationFolder = packageFolder + "/flyway";
        final List<String> migrationFolders = new ArrayList<>();
        migrationFolders.add(packageFolder);
        flyway.setLocations(migrationFolders.toArray(new String[migrationFolders.size()]));


        flyway.migrate();

    }
}

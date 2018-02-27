package migration;

import com.dgeorgiev.userservice.core.UserDb;
import com.dgeorgiev.userservice.core.config.Configuration;
import com.novarto.sanedbc.core.ops.EffectOp;
import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;

@SuppressWarnings("checkstyle:TypeName")
public class V1__0_Initial implements JdbcMigration
{
    @Override public void migrate(Connection c) throws Exception
    {
        new EffectOp(
                "CREATE TABLE USERS " +
                        "(ID INTEGER PRIMARY KEY IDENTITY," +
                        " NAME NVARCHAR(100) NOT NULL," +
                        " EMAIL NVARCHAR(100) NOT NULL UNIQUE," +
                        " PASSWORD NVARCHAR(300) NOT NULL)"
        ).run(c);

        UserDb.insertUser(Configuration.DEFAULT_ADMIN, Configuration.DEFAULT_ADMIN_EMAIL, Configuration.DEFAULT_ADMIN_PASSWORD)
        .run(c);
    }
}

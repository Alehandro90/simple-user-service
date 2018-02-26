package com.dgeorgiev.userservice.core;

import com.dgeorgiev.userservice.domain.User;
import com.novarto.sanedbc.core.ops.EffectOp;
import com.novarto.sanedbc.core.ops.InsertGenKeysOp;
import com.novarto.sanedbc.core.ops.SelectOp;
import com.novarto.sanedbc.core.ops.UpdateOp;
import fj.Unit;
import fj.control.db.DB;
import fj.data.List;
import fj.data.Option;

import static com.dgeorgiev.userservice.core.config.Configuration.PASSWORD_HASHER;
import static com.novarto.sanedbc.core.ops.DbOps.unique;

public class UserDb
{


    public static DB<Integer> insertUser(String name, String email, String password)
    {
        return new InsertGenKeysOp.Int(
                "INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES (?, ?, ?)",
                ps ->
                {
                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, PASSWORD_HASHER.toHash(password));
                }
        );
    }

    public static DB<Integer> updateUserName(String email, String newName)
    {
        return new UpdateOp(
                "UPDATE USERS SET NAME = ? WHERE EMAIL = ?",
                ps ->
                {
                    ps.setString(1, newName);
                    ps.setString(2, email);
                }
        );
    }

    public static DB<Option<User>> selectUserByID(int id)
    {
        SelectOp.FjList<User> userOption = new SelectOp.FjList<>(
                "SELECT NAME, EMAIL FROM USERS WHERE ID = ?",
                ps -> ps.setInt(1, id),
                rs -> new User(id, rs.getString(1), rs.getString(2))
        );

        return unique(userOption);
    }

    public static DB<Option<User>> selectUserByEmail(String email)
    {
        SelectOp.FjList<User> userOption = new SelectOp.FjList<>(
                "SELECT ID, NAME FROM USERS WHERE EMAIL = ?",
                ps -> ps.setString(1, email),
                rs -> new User(rs.getInt(1), rs.getString(2), email)
        );

        return unique(userOption);
    }

    public static DB<Boolean> authenticate(String email, String pass)
    {
        SelectOp.FjList<String> dbPass = new SelectOp.FjList<>(
                "SELECT PASSWORD FROM USERS WHERE EMAIL = ?",
                ps -> ps.setString(1, email),
                rs -> rs.getString(1)
        );

        return unique(dbPass).map(passOp -> passOp.isSome() && PASSWORD_HASHER.validatePassword(pass, passOp.some()));
    }

    public static DB<List<User>> selectAll(int offset, int limit)
    {
        return new SelectOp.FjList<>(
                "SELECT ID, NAME, EMAIL FROM USERS OFFSET ? LIMIT ?",
                ps -> {
                    ps.setInt(1, offset);
                    ps.setInt(2, limit);
                },
                rs -> new User(rs.getInt(1), rs.getString(2), rs.getString(3))
        );

    }
}

package com.example.cosport.data;

import android.content.Context;

import com.example.cosport.data.model.LoggedInUser;
import com.example.cosport.data.model.RegisteredUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    private RegisteredUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(RegisteredUser user) {
        this.user = user;
    }

    public Result<RegisteredUser> login(String username, String password, Context context) {
        Result<RegisteredUser> result = dataSource.login(username, password, context);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<RegisteredUser>) result).getData());
        }
        return result;
    }

    public Result<RegisteredUser> register(RegisteredUser curUser, Context context){

        Result<RegisteredUser> result = dataSource.register(curUser, context);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<RegisteredUser>) result).getData());

        }
        return result;

    }
}
 package com.example.cosport.data;

import android.content.Context;

import com.example.cosport.data.model.LoggedInUser;
import com.example.cosport.data.model.RegisteredUser;


import org.json.JSONObject;

import java.io.IOException;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cosport.ui.login.LoginFragment;
import com.example.cosport.ui.register.RegistrationFragment;


import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<RegisteredUser> login(String email, String password, Context context) {
        try {
            // TODO: handle loggedInUser authentication
            final RegisteredUser[] userData = new RegisteredUser[1];
            String url = "http://167.172.35.241/CSDB/Users/?Content-Type=application/json&Email="+email+"&Password="+password;
            JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                userData[0] = new RegisteredUser(response.getString("Nickname"),
                                        response.getString("Password"),
                                        response.getString("Name"),
                                        response.getString("Surname"),
                                        response.getString("Gender"),
                                        response.getString("Phone"),
                                        response.getString("Email"),
                                        response.getString("Occupation"),
                                        response.getString("Profsportman"),
                                        response.getString("City"),
                                        response.getString("Gender"));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            new AlertDialog.Builder(context)
                                    .setTitle("Error")
                                    .setMessage(error.getMessage())
                                    .show();
                        }
                    }
            ) ;

            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(postRequest);
            if(userData[0] == null) throw new NullPointerException();
            return new Result.Success<>(userData[0]);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public Result<RegisteredUser> register(RegisteredUser curUser, Context context) {

        try {
            // TODO: handle loggedInUser authentication

            try {

                JSONObject userPost = new JSONObject();
                userPost.put("Nickname", curUser.getUsername());
                userPost.put("Password", curUser.getPassword());
                userPost.put("Name", curUser.getFirstname());
                userPost.put("Surname", curUser.getSurname());
                userPost.put("Gender", curUser.getGender());
                userPost.put("Phone", curUser.getPhone());
                userPost.put("Email", curUser.getEmail());
                userPost.put("Tg_id", curUser.getSocialNetwork());
                userPost.put("Occupation", curUser.getOccupation());

                userPost.put("City", curUser.getCity());


                String url = "http://167.172.35.241/CSDB/Users/?Content-Type=application/json";
                JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, userPost,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //RegistrationFragment.registerSuccess();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                new AlertDialog.Builder(context)
                                        .setTitle("Error")
                                        .setMessage(error.getMessage())
                                        .show();
                            }
                        }
                ) ;

                // Add the request to the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(postRequest);
            } catch (JSONException e) {
                System.out.println(e.getMessage());
            }
            return new Result.Success<>(curUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error registration", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
package com.example.cosport.ui.register;

import android.content.Context;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.cosport.R;
import com.example.cosport.data.LoginRepository;
import com.example.cosport.data.Result;
import com.example.cosport.data.model.LoggedInUser;
import com.example.cosport.data.model.RegisteredUser;

import java.io.IOException;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    static NavController navController;

    RegisterViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    LiveData<RegisterResult> getRegisterResult() {
        return registerResult;
    }

    public Result<RegisteredUser> register(RegisteredUser curUser, Context context) {
        // can be launched in a separate asynchronous job

        Result<RegisteredUser> result = loginRepository.register(curUser, context);

        if (result instanceof Result.Success) {
            //navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation);
            RegisteredUser data = ((Result.Success<RegisteredUser>) result).getData();
            registerResult.setValue(new RegisterResult(new RegisteredUserView(data.getUsername())));

            return new Result.Success<>(curUser);
        } else {
            registerResult.setValue(new RegisterResult(R.string.registration_failed));
            return new Result.Error(new IOException("Error registration"));
        }
    }

    public void registerDataChanged(String username, String password, String passwordConfirm, String email, String city, String phone,String socialNetwork) {
        if (!isEmailValid(email)) {
            registerFormState.setValue(new RegisterFormState(null,null,null, R.string.invalid_email,null,null, null));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_password,null,null,null,null,null));
        } else if (!isUsernameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_username,null,null,null,null,null,null));
        } else if (!isCityValid(city)) {
            registerFormState.setValue(new RegisterFormState(null,null,null,null, R.string.invalid_city,null,null));
        } else if (!isPasswordConfirmValid(passwordConfirm,password)) {
            registerFormState.setValue(new RegisterFormState(null,null, R.string.invalid_password_confirm,null,null,null,null));
        } else if (!isPhoneValid(phone)) {
            registerFormState.setValue(new RegisterFormState(null,null,null,null,null, R.string.invalid_phone,null));
        }  if (!isSocialNetworkValid(socialNetwork)) {
            registerFormState.setValue(new RegisterFormState(null,null,null,null,null,null, R.string.invalid_social_network));
        } else{
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isEmailValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isUsernameValid(String username) {
        return username != null ;
    }
    private boolean isCityValid(String city) {
        return city != null ;
    }
    private boolean isSocialNetworkValid(String socialNetwork) {
        return socialNetwork != null ;
    }

    private boolean isPhoneValid(String phone) {
        return phone != null&&phone.matches("\\d{11}") ;
    }

    private boolean isPasswordConfirmValid(String password1, String password2) {
        return password1 != null && password1.equals(password2);
    }
}
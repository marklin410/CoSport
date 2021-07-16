package com.example.cosport.ui.register;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class RegisterFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    private Integer passwordConfError;
    private Integer emailError;
    private Integer cityError;
    private Integer phoneError;
    private Integer socialNetworkError;
    private boolean isDataValid;

    RegisterFormState(@Nullable Integer usernameError, @Nullable Integer passwordError,
                      @Nullable Integer passwordConfError, @Nullable Integer emailError,
                      @Nullable Integer cityError, @Nullable Integer phoneError,
                      @Nullable Integer socialNetworkError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.cityError = cityError;
        this.passwordConfError = passwordConfError;
        this.emailError = emailError;
        this.phoneError = phoneError;
        this.socialNetworkError = socialNetworkError;
        this.isDataValid = false;
    }

    RegisterFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.cityError = null;
        this.passwordConfError = null;
        this.emailError = null;
        this.phoneError = null;
        this.socialNetworkError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getCityError() {
        return cityError;
    }

    @Nullable
    Integer getPasswordConfError() {
        return passwordConfError;
    }
    @Nullable
    Integer getEmailError() {
        return emailError;
    }

    @Nullable
    Integer getPhoneError() {
        return phoneError;
    }

    @Nullable
    Integer getSocialNetworkError() {
        return socialNetworkError;
    }


    boolean isDataValid() {
        return isDataValid;
    }
}
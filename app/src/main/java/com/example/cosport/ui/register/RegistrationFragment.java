package com.example.cosport.ui.register;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.cosport.R;
import com.example.cosport.data.Result;
import com.example.cosport.data.model.RegisteredUser;
import com.example.cosport.databinding.FragmentRegisterBinding;
import com.example.cosport.preferences.SavedSharedPreference;
import com.google.android.material.navigation.NavigationView;

public class RegistrationFragment extends Fragment {

    private RegisterViewModel registerViewModel;
    private FragmentRegisterBinding binding;

    static NavController navController;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        registerViewModel = new ViewModelProvider(this, new RegisterViewModelFactory())
                .get(RegisterViewModel.class);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation);
        final EditText usernameEditText = binding.username;
        final EditText firstnameEditText = binding.firstname;
        final EditText surnameEditText = binding.surname;
        final EditText passwordEditText = binding.password;
        final EditText emailEditText = binding.email;
        final EditText passwordConfEditText = binding.passwordConf;
        final EditText cityEditText = binding.city;
        final EditText phoneEditText = binding.phone;
        final EditText socialNetworkEditText = binding.socialNetwork;
        final Spinner occupationSpinner = binding.occupation;
        final Button registerButton = binding.signup;
        final ProgressBar loadingProgressBar = binding.loading;
        final RadioGroup genderRadioGroup = binding.genderCheck;
        final CheckBox profSpostrsmanCheckBox = binding.profSportsmanCB;

        genderRadioGroup.clearCheck();
        final String[] gender = new String[1];
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        gender[0] ="";
                        break;
                    case R.id.radio_f:
                        gender[0] ="Ж";
                        break;
                    case R.id.radio_m:
                        gender[0] ="М";
                        break;
                    case R.id.radio_o:
                        gender[0] ="Другое";
                        break;
                }
            }
        });

        registerViewModel.getRegisterFormState().observe(getViewLifecycleOwner(), new Observer<RegisterFormState>() {
            @Override
            public void onChanged(@Nullable RegisterFormState registerFormState) {
                if (registerFormState == null) {
                    return;
                }
               /* registerButton.setEnabled(registerFormState.isDataValid());
                if (registerFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getUsernameError() != null) {
                    firstnameEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getUsernameError() != null) {
                    surnameEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(registerFormState.getPasswordError()));
                }
                if (registerFormState.getEmailError() != null) {
                    emailEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getCityError() != null) {
                    cityEditText.setError(getString(registerFormState.getPasswordError()));
                }
                if (registerFormState.getPhoneError() != null) {
                    phoneEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getPasswordConfError() != null) {
                    passwordConfEditText.setError(getString(registerFormState.getPasswordError()));
                }
                if (registerFormState.getSocialNetworkError() != null) {
                    socialNetworkEditText.setError(getString(registerFormState.getUsernameError()));
                }*/

            }
        });

        registerViewModel.getRegisterResult().observe(getViewLifecycleOwner(), new Observer<RegisterResult>() {
            @Override
            public void onChanged(@Nullable RegisterResult registerResult) {
                if (registerResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (registerResult.getError() != null) {
                    showLoginFailed(registerResult.getError());
                }
                if (registerResult.getSuccess() != null) {
                    updateUiWithUser(registerResult.getSuccess());
                }
                getActivity().setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                getActivity().finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                /*registerViewModel.registerDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        passwordConfEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        cityEditText.getText().toString(),
                        phoneEditText.getText().toString(),
                        socialNetworkEditText.getText().toString());*/

            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
       /* passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    registerViewModel.register(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(),
                            passwordConfEditText.getText().toString(),
                            emailEditText.getText().toString(),
                            cityEditText.getText().toString(),
                            phoneEditText.getText().toString(),
                            socialNetworkEditText.getText().toString(),
                            occupationSpinner.getSelectedItem().toString());
                }
                return false;
            }
        });*/

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                /*registerViewModel.registerDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        passwordConfEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        cityEditText.getText().toString(),
                        phoneEditText.getText().toString(),
                        socialNetworkEditText.getText().toString());*/
                String profS = "false";
                if(profSpostrsmanCheckBox.isChecked()) profS = "true";
                /*RegisteredUser curUser =
                        new RegisteredUser(
                                usernameEditText.getText().toString(),
                                firstnameEditText.getText().toString(),
                                surnameEditText.getText().toString(),
                                passwordEditText.getText().toString(),
                                emailEditText.getText().toString(),
                                cityEditText.getText().toString(),
                                phoneEditText.getText().toString(),
                                socialNetworkEditText.getText().toString(),
                                occupationSpinner.getSelectedItem().toString(),gender[0],profS);*/
                RegisteredUser curUser =
                        new RegisteredUser(
                                "user",
                                "12345",
                                "name",
                                "surname",
                                "male",
                                "89997776655",
                                "user@user.ru",
                                "student",
                                "true",
                                "moscow",
                                "user_tg");
                Result<RegisteredUser> result = registerViewModel.register(curUser, getActivity().getApplicationContext());
                if (result instanceof Result.Success) {
                    SavedSharedPreference.setLoggedIn(getActivity().getApplicationContext(), true, curUser.getEmail(), curUser.getPassword());
                    NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                    View nav_header = LayoutInflater.from(getActivity()).inflate(R.layout.nav_header_navigation, null);
                    ((TextView) nav_header.findViewById(R.id.usernameTV)).setText("UserName");
                    navigationView.addHeaderView(nav_header);
                    navController.navigate(R.id.nav_events);
                }

            }
        });
        return root;
    }

    private void updateUiWithUser(RegisteredUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(this.getContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(this.getContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
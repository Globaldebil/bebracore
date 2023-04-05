package com.example.lovelychecker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginFragment extends Fragment {

    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginButton;

    private ObjectMapper objectMapper = new ObjectMapper();

    public class Result {
        String accessToken;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginEmail = view.findViewById(R.id.login_email);
        loginPassword = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button);


        //КНОПКА LOGIN
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });


        //ПЕРЕХОД НА SIGNUP
        TextView signUpTextView = view.findViewById(R.id.signupTextView);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment signUpFragment = new SignupFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, signUpFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void loginUser() {
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        interfaceAPI apiService = RetrofitClientInstance.getInstance();
        LoginRequest loginRequest = new LoginRequest(email, password);
        Call<Result> call = apiService.loginUser(loginRequest);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "On response: Success " + response.code(), Toast.LENGTH_LONG).show();
                    String body = response.body().toString();
                    loginEmail.setText("");
                    loginPassword.setText("");

                    Fragment confirmFragment = new ConfirmFragment();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, confirmFragment)
                            .addToBackStack(null)
                            .commit();

                } else {
                    // Обработка ошибки сервера
                    try {

                        String body = response.errorBody().string();
                        System.out.println(body);
                        JsonNode node = objectMapper.readValue(body, JsonNode.class);
                        String emailError = node.get("body").get("fieldErrors").path("email").asText(null);
                        String passwordError = node.get("body").get("fieldErrors").path("password").asText(null);

                        if(emailError != null)  {
                            Toast.makeText(getActivity(), emailError, Toast.LENGTH_LONG).show();
                        }
                        else if (passwordError != null) {
                            Toast.makeText(getActivity(), passwordError, Toast.LENGTH_LONG).show();
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    //Toast.makeText(getActivity(), "On response: Failure " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // Обработка ошибки сети или других ошибок
                Toast.makeText(getActivity(), "On Failure: Faiure", Toast.LENGTH_LONG).show();
            }
        });
    }
}
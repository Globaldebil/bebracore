package com.example.lovelychecker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupFragment extends Fragment {

    private Button signupButton;
    private EditText signupUsername;
    private EditText signupEmail;
    private EditText signupPassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        signupButton = view.findViewById(R.id.signup_button);
        signupUsername = view.findViewById(R.id.signup_username);
        signupEmail = view.findViewById(R.id.signup_email);
        signupPassword = view.findViewById(R.id.signup_password);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });



        //ПЕРЕХОД НА LOGIN
        TextView signInTextView = view.findViewById(R.id.loginTextView);
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment loginFragment = new LoginFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, loginFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;

    }

    private void signUp() {
        String username = signupUsername.getText().toString();
        String email = signupEmail.getText().toString();
        String password = signupPassword.getText().toString();

        interfaceAPI apiService = RetrofitClientInstance.getInstance();
        Call<Post> call = apiService.signUp(username, email, password);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getActivity(), "On response: Success" + response.code(), Toast.LENGTH_LONG).show();
                    signupUsername.setText("");
                    signupEmail.setText("");
                    signupPassword.setText("");
                }
                Toast.makeText(getActivity(), "On response: Failure" + response.code(), Toast.LENGTH_LONG).show();

            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getActivity(), "On Failure: Failure", Toast.LENGTH_LONG).show();
                signupUsername.setText("");
                signupEmail.setText("");
                signupPassword.setText("");
            }
        });
    }
}
package com.azp.blog.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.azp.blog.MainActivity;
import com.azp.blog.R;
import com.azp.blog.api.ApiInterface;
import com.azp.blog.model.LoginData;
import com.azp.blog.utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EditText userName, password;
    Button buttonSubmit;
    TextView mResponse;
    private ApiInterface apiInterface;
    String token;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        userName = rootView.findViewById(R.id.et_username);
        password = rootView.findViewById(R.id.et_password);
        buttonSubmit = rootView.findViewById(R.id.btn_submit);
        mResponse = rootView.findViewById(R.id.tv_response);

        apiInterface = ApiUtils.getLoginAPI();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pass)) {
                    sendLogin(username, pass);
                }
            }
        });
        return rootView;
    }

    public void sendLogin(String name, String pass) {

        int client_id = 2;
        String client_secret = "vbh8FwES1Rbs5csk5MLL33Y1sy4B0NiJDrHRqaqE";
        String grant_type = "password";

        apiInterface.loginPosts(name, pass, client_id, client_secret, grant_type).enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                if (response.body() != null && response.isSuccessful()) {
                    showResponse(response.body().getAccessToken());
                    token = response.body().getAccessToken();

                } else {
                    showResponse("Null");
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                showResponse(t.toString());
                Log.e("FAIL", "Unable to submit post to API");
            }
        });
    }

    public void showResponse(String response) {
        if (mResponse.getVisibility() == View.GONE) {
            mResponse.setVisibility(View.VISIBLE);
        }

        mResponse.setText(response + "\n Successful Post");
    }

}

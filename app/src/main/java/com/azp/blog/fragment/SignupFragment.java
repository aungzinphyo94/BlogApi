package com.azp.blog.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.azp.blog.R;
import com.azp.blog.api.ApiInterface;
import com.azp.blog.model.RegisterData;
import com.azp.blog.utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    EditText etUerName, etPassword, etEmail, etPasswordConfirmation;
    Button buttonSubmit;
    TextView mResponse;
    private ApiInterface apiInterface;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);

        buttonSubmit = rootView.findViewById(R.id.btn_submit);
        mResponse = rootView.findViewById(R.id.tv_response);

        etUerName = rootView.findViewById(R.id.et_name);
        etEmail = rootView.findViewById(R.id.et_email);
        etPassword = rootView.findViewById(R.id.et_password);
        etPasswordConfirmation = rootView.findViewById(R.id.et_password_confirm);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        return rootView;
    }

    public void register() {

        String name = etUerName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etPasswordConfirmation.getText().toString().trim();

        apiInterface = ApiUtils.getAPI();

        Call<RegisterData> call = apiInterface.registerUser(name,email,password,confirmPassword);

        call.enqueue(new Callback<RegisterData>() {
            @Override
            public void onResponse(Call<RegisterData> call, Response<RegisterData> response) {
                if (response.isSuccessful()){
                    RegisterData registerData=response.body();
                    Toast.makeText(getContext(),registerData.getUser().getEmail(),Toast.LENGTH_SHORT).show();

                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new LoginFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onFailure(Call<RegisterData> call, Throwable t) {
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }

}

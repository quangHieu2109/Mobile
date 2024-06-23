package view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookshop.R;

import api.AApi;
import api.APIService;
import request.AccuracyOTP;
import request.ChangePasswordOTP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.activity.HomeActivity;
import view.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCreateNewPassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCreateNewPassword extends Fragment {
    Button btnContinute;
    EditText confirmPassword, password;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCreateNewPassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCreateNewPassword.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCreateNewPassword newInstance(String param1, String param2) {
        FragmentCreateNewPassword fragment = new FragmentCreateNewPassword();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_new_password, container, false);
        btnContinute = view.findViewById(R.id.btn_continue);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        password = view.findViewById(R.id.password);
        setBtnClickListeners();
        return view;
    }
    private void setBtnClickListeners() {
        btnContinute.setOnClickListener(v -> {
            String passwordInput = password.getText().toString();
            String confirmPasswordInput = confirmPassword.getText().toString();
            if(passwordInput.length() ==0){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogError);
                builder.setTitle("Error")
                        .setMessage("Vui lòng nhập mật khẩu mới!")
                        .show();
            }else{
                if(!passwordInput.equals(confirmPasswordInput)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogError);
                    builder.setTitle("Error")
                            .setMessage("Mật khẩu xác nhận không chính xác!")
                            .show();
                }else{
                    ChangePasswordOTP changePasswordOTP = new ChangePasswordOTP(AccuracyOTP.getEmail(), passwordInput);
                    APIService.apiService.changePasswordOTP(changePasswordOTP).enqueue(new Callback<AApi<Object>>() {
                        @Override
                        public void onResponse(Call<AApi<Object>> call, Response<AApi<Object>> response) {
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<AApi<Object>> call, Throwable t) {

                        }
                    });
                }
            }

        });
    }
}
package view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.bookshop.R;

import java.util.regex.Pattern;

import api.AApi;
import api.APIService;
import request.AccuracyOTP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentForgetPass#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentForgetPass extends Fragment {
    Button btnContinute;
    EditText email;
    ProgressBar progress_bar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentForgetPass() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentForgetPass.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentForgetPass newInstance(String param1, String param2) {
        FragmentForgetPass fragment = new FragmentForgetPass();
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
        View view = inflater.inflate(R.layout.fragment_forget_pass, container, false);
        // Inflate the layout for this fragment
        btnContinute = view.findViewById(R.id.btn_continue);
        email = view.findViewById(R.id.email);
        progress_bar = view.findViewById(R.id.progress_bar);
        setBtnClickListeners();
        return view;
    }
    private void setBtnClickListeners() {
        btnContinute.setOnClickListener(v -> {
            String emailInput = email.getText().toString();
            if(emailInput.length() ==0){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogError);
                builder.setTitle("Error")
                        .setMessage("Vui lòng nhập email!")
                        .show();
            }else{


                    progress_bar.setVisibility(View.VISIBLE);
                    APIService.apiService.sendOTP(emailInput).enqueue(new Callback<AApi<Object>>() {
                        @Override
                        public void onResponse(Call<AApi<Object>> call, Response<AApi<Object>> response) {

                                if (response.body().isStatus()) {
                                    AccuracyOTP.setEmail(emailInput);
                                    progress_bar.setVisibility(View.GONE);
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.container, new FramentConfirmMail())
                                            .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                            .addToBackStack("fragmentConfirmEmail")
                                            .commit();
                                } else {
                                    progress_bar.setVisibility(View.GONE);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogError);
                                    builder.setTitle("Error")
                                            .setMessage(response.body().getMessage())
                                            .show();
                                }
                            }


                        @Override
                        public void onFailure(Call<AApi<Object>> call, Throwable t) {

                        }
                    });
                }


        });
    }
}
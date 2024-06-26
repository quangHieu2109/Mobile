package view.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bookshop.R;
import com.noobcode.otpview.OTPView;

import api.AApi;
import api.APIService;
import request.AccuracyOTP;
import request.AccuracyRequest;
import request.ChangePasswordOTP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FramentConfirmMail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FramentConfirmMail extends Fragment {
    Button btnConfirmMail;
    OTPView otp;
    String otpInput;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FramentConfirmMail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FramentConfirmMail.
     */
    // TODO: Rename and change types and number of parameters
    public static FramentConfirmMail newInstance(String param1, String param2) {
        FramentConfirmMail fragment = new FramentConfirmMail();
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
        View view = inflater.inflate(R.layout.fragment_frament_confirm_mail, container, false);
        // Inflate the layout for this fragment
        btnConfirmMail = view.findViewById(R.id.btn_confirm);
        otp = view.findViewById(R.id.otp);

        setBtnClickListeners();
        return view;
    }
    private void setBtnClickListeners() {
        btnConfirmMail.setOnClickListener(v -> {
            otpInput = otp.getText().toString();
            if(otpInput.length() != 6 ){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogError);
                builder.setTitle("Error")
                        .setMessage("Vui lòng nhập đầy đủ OTP!")
                        .show();
            }else{
                AccuracyOTP.setOtp(otpInput);

                APIService.apiService.accuracyOTP(new AccuracyRequest(AccuracyOTP.getEmail(), otpInput)).enqueue(new Callback<AApi<Object>>() {
                    @Override
                    public void onResponse(Call<AApi<Object>> call, Response<AApi<Object>> response) {
                        if(response.body().isStatus()){

                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, new FragmentCreateNewPassword())
                                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .addToBackStack("fragmentCreateNewPassword")
                                    .commit();
                        }else{
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
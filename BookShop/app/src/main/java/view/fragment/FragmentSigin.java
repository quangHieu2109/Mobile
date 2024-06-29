package view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.Toast;

import com.example.bookshop.R;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import api.AApi;
import api.APIService;
import api.Login;
import request.LoginRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.activity.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSigin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSigin extends Fragment {
    EditText edtEmail, edtPassword;
    GoogleSignInOptions gso;
    ActivityResultLauncher<Intent> someActivityResultLauncher;
    GoogleSignInClient signInClient;

    Button btnForgetPass, btnSigin;
    ImageButton btnLoginByGoogle;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ActivityResultLauncher<IntentSenderRequest> intentSender;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSigin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSigin.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSigin newInstance(String param1, String param2) {
        FragmentSigin fragment = new FragmentSigin();
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
        View view = inflater.inflate(R.layout.fragment_sigin, container, false);
        edtEmail = view.findViewById(R.id.username);
        edtPassword = view.findViewById(R.id.password);
        btnForgetPass = view.findViewById(R.id.btn_forgot_password);
        btnSigin = view.findViewById(R.id.btn_sign_in);
        btnLoginByGoogle = view.findViewById(R.id.btn_login_gg);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                .requestIdToken("847546457050-0i16i95g131e3smcs5j3mofmon7n7b0o.apps.googleusercontent.com")
                .requestProfile()
                .build();
        signInClient = GoogleSignIn.getClient(getActivity(), gso);

        setBtnClickListeners();
         someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK) {
                            Log.d("TAG", "onActivityResult: ");
                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(o.getData());
                            handleSignInResult(task);
                        }
                    }
                });

        return view;

    }

    private void setBtnClickListeners() {

        btnForgetPass.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new FragmentForgetPass())
                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("fragmentForgetPass")
                    .commit();
        });
        btnSigin.setOnClickListener(v -> {
            APIService.apiService.login(new LoginRequest(edtEmail.getText().toString(), edtPassword.getText().toString())).enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if (response.isSuccessful()) {
                        Login.setToken(response.body().getData());
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Log.d("TAG", "onFailure: " + t.getMessage());
                    Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_SHORT).show();
                }
            });

        });
        btnLoginByGoogle.setOnClickListener(v -> {

            Intent signInIntent = signInClient.getSignInIntent();
//            startActivityForResult(signInIntent, 126);
            someActivityResultLauncher.launch(signInIntent);

        });

    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            APIService.apiService.loginGoogle(account.getIdToken()).enqueue(new Callback<AApi<String>>() {
                @Override
                public void onResponse(Call<AApi<String>> call, Response<AApi<String>> response) {

                    Login.setToken(response.body().getData());
                    Toast.makeText(getActivity(), "Welcome "+ account.getEmail(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), HomeActivity.class));
                }

                @Override
                public void onFailure(Call<AApi<String>> call, Throwable t) {

                }
            });
            

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());

        }
    }


}
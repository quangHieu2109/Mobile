package view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookshop.R;

import api.AApi;
import api.APIService;
import api.Login;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.activity.ActivityOrderManagement;
import view.activity.HomeActivity;
import view.activity.MainApp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAccount#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAccount extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView username, fullName, email, phoneNumber, gender;
    Button adminPage, logout;
    public FragmentAccount() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAccount newInstance(String param1, String param2) {
        FragmentAccount fragment = new FragmentAccount();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        username = view.findViewById(R.id.username);
        fullName = view.findViewById(R.id.fullName);
        email = view.findViewById(R.id.email);
        gender = view.findViewById(R.id.gender);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        logout = view.findViewById(R.id.logout);
        adminPage = view.findViewById(R.id.adminPage);

        APIService.apiService.getInfor("Bearer "+Login.getToken()).enqueue(new Callback<AApi<User>>() {
            @Override
            public void onResponse(Call<AApi<User>> call, Response<AApi<User>> response) {
                User user = response.body().getData();
                username.setText(((user.getUsername()!= null)?user.getUsername():""));
                fullName.setText((user.getFullName()!= null)?user.getFullName():"");
                email.setText((user.getEmail()!= null)?user.getEmail():"");
                phoneNumber.setText((user.getPhoneNumber()!= null)?user.getPhoneNumber():"");
                gender.setText(user.getGender() ==0?"Male":"Famale");
                if(!user.getRole().equalsIgnoreCase("CUSTOMER")){
                    adminPage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<AApi<User>> call, Throwable t) {

            }
        });
        setOnclickListener();
        return view;
    }
    private void setOnclickListener(){
        logout.setOnClickListener(v ->{
            Login.setToken("");
            startActivity(new Intent(getContext(), MainApp.class));
        });
        adminPage.setOnClickListener(v ->{
            startActivity(new Intent(getContext(), ActivityOrderManagement.class));
        });
    }
}
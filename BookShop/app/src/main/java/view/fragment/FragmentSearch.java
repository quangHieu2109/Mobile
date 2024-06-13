package view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;

import adapter.SearchBookAdapter;
import api.BookResponse;
import model.Book;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSearch extends Fragment {
AutoCompleteTextView autoCompleteTextView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Button btn_back;
    ISendData iSendData;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iSendData = (ISendData) getActivity();// lay isenddata tu activity
    }

    public FragmentSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSearch newInstance(String param1, String param2) {
        FragmentSearch fragment = new FragmentSearch();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        autoCompleteTextView = view.findViewById(R.id.search);
        SearchBookAdapter searchBookAdapter = new SearchBookAdapter(this.getContext(),R.layout.search_item_book,getData());
        autoCompleteTextView.setAdapter(searchBookAdapter);
        btn_back = view.findViewById(R.id.back);
        setBtnClickListeners();
        return view;
    }
    private void setBtnClickListeners() {
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                senDataBookToDetail((BookResponse) parent.getItemAtPosition(position));
            }
        });

        btn_back.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }
    private List<BookResponse> getData(){
        List<BookResponse> books = new ArrayList<>();
    return books;
    }
    public void senDataBookToDetail(BookResponse book){
        iSendData.sendData(book);

    }
    public interface ISendData{
        void sendData(BookResponse book);
    }

}
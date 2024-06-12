package view.fragment;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;

import adapter.BookAdapter;
import model.Book;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDiscover#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDiscover extends Fragment {
    BookAdapter bookAdapterSelling = new BookAdapter(getContext());
    BookAdapter bookAdapterCharts = new BookAdapter(getContext());
    BookAdapter bookAdapterRelease = new BookAdapter(getContext());
    private RecyclerView rcv_top_selling,rcv_top_charts,rcv_top_release;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentDiscover() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentDiscover.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDiscover newInstance(String param1, String param2) {
        FragmentDiscover fragment = new FragmentDiscover();
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
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        // Inflate the layout for this fragment
        rcv_top_selling = view.findViewById(R.id.rcv_top_selling);
        rcv_top_charts = view.findViewById(R.id.rcv_top_charts);
        rcv_top_release = view.findViewById(R.id.rcv_top_new_release);

         rcv_top_selling.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcv_top_selling.setAdapter(bookAdapterSelling);
        rcv_top_charts.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcv_top_charts.setAdapter(bookAdapterCharts);
        rcv_top_release.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rcv_top_release.setAdapter(bookAdapterRelease);

        new LoadData().execute();
        return view;
    }

    public class LoadData extends AsyncTask<Void,Void,List<Book>> {


        @Override
        protected List<Book> doInBackground(Void... voids) {
            List<Book> books = new ArrayList<>();
            books.add(new Book("The Great Gatsby", BitmapFactory.decodeResource(getResources(), R.drawable.book_harry), "F. Scott Fitzgerald", "The Great Gatsby is a novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan."));
            books.add(new Book("The Great Gatsby", BitmapFactory.decodeResource(getResources(), R.drawable.book_harry), "F. Scott Fitzgerald", "The Great Gatsby is a novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan."));
            books.add(new Book("The Great Gatsby", BitmapFactory.decodeResource(getResources(), R.drawable.book_harry), "F. Scott Fitzgerald", "The Great Gatsby is a novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan."));
            books.add(new Book("The Great Gatsby", BitmapFactory.decodeResource(getResources(), R.drawable.book_harry), "F. Scott Fitzgerald", "The Great Gatsby is a novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan."));

            return books;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            super.onPostExecute(books);
            bookAdapterSelling.setData(books);
            bookAdapterCharts.setData(books);
            bookAdapterRelease.setData(books);
        }
    }
}
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
import android.widget.ImageButton;

import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;

import adapter.BookAdapter;
import model.Book;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment {
    ImageButton btn_notification,btn_search;
    BookAdapter bookAdaperAllBook = new BookAdapter(getContext());
    BookAdapter bookAdapterRecommended = new BookAdapter(getContext());
    BookAdapter bookAdapterPurchased = new BookAdapter(getContext());
    BookAdapter bookAdapterWishlist = new BookAdapter(getContext());
    private RecyclerView rcv_all_book,rcv_recommended,rcv_purchased,rcv_wishlist;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        rcv_all_book = view.findViewById(R.id.rcv_all_book);
        rcv_recommended = view.findViewById(R.id.rcv_recommended);
        rcv_purchased = view.findViewById(R.id.rcv_purchased);
        rcv_wishlist = view.findViewById(R.id.rcv_wishlist);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rcv_all_book.setLayoutManager(linearLayoutManager);
        rcv_recommended.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv_purchased.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv_wishlist.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv_all_book.setAdapter(bookAdaperAllBook);
        rcv_recommended.setAdapter(bookAdapterRecommended);
        rcv_purchased.setAdapter(bookAdapterPurchased);
        rcv_wishlist.setAdapter(bookAdapterWishlist);
        new LoadData().execute();
        btn_notification = view.findViewById(R.id.btn_notification);
        btn_search = view.findViewById(R.id.btn_search);
        setBtnClickListeners();
        return view;
    }
    private void setBtnClickListeners() {
        btn_notification.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentNotification())
                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("fragmentNotification")
                    .commit();
        });
        btn_search.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentSearch())
                    .setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack("fragmentSearch")
                    .commit();
        });
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
            bookAdaperAllBook.setData(books);
            bookAdapterRecommended.setData(books);
            bookAdapterPurchased.setData(books);
            bookAdapterWishlist.setData(books);
        }
    }
}
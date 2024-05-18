package view.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;

import adapter.BookAdapter;
import model.Book;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBookDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBookDetail extends Fragment {
    RecyclerView rcv_similar;
    TextView counterReview, title_book, author, release_book, description, rating;
    Button btn_buy, btn_submit, btn_back;
    ProgressBar star_1, star_2, star_3, star_4, star_5;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentBookDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBookDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBookDetail newInstance(String param1, String param2) {
        FragmentBookDetail fragment = new FragmentBookDetail();
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
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        // Inflate the layout for this fragment
        star_1 = view.findViewById(R.id.star_1);
        star_2 = view.findViewById(R.id.star_2);
        star_3 = view.findViewById(R.id.star_3);
        star_4 = view.findViewById(R.id.star_4);
        star_5 = view.findViewById(R.id.star_5);
        counterReview = view.findViewById(R.id.counter_review);
        title_book = view.findViewById(R.id.title_book);
        author = view.findViewById(R.id.author);
        btn_back = view.findViewById(R.id.back);
        release_book = view.findViewById(R.id.release_book);
        description = view.findViewById(R.id.description);
        rating = view.findViewById(R.id.rating);
        btn_buy = view.findViewById(R.id.btn_buy);
        btn_submit = view.findViewById(R.id.btn_submit);
        rcv_similar = view.findViewById(R.id.rcv_similar);
        rcv_similar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        BookAdapter bookAdapterSimilar = new BookAdapter(getContext());
        rcv_similar.setAdapter(bookAdapterSimilar);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Book book = (Book) bundle.getSerializable("book");
            receiveData(book);

        }
        bookAdapterSimilar.setData(setDataBookSimilar());
        setBtnClickListeners();
        return view;
    }

    public void receiveData(Book book) {
        title_book.setText(book.getTitle());
        author.setText(book.getAuthor());
//        release_book.setText(book.getRelease());
        description.setText(book.getDescription());
//        rating.setText(book.getRating());
    }
    private void setBtnClickListeners() {
        btn_back.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    public List<Book> setDataBookSimilar() {

        List<Book> books = new ArrayList<>();
        books.add(new Book("The Great Gatsby", BitmapFactory.decodeResource(getResources(), R.drawable.book_harry), "F. Scott Fitzgerald", "The Great Gatsby is a novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan."));
        books.add(new Book("The Great Gatsby", BitmapFactory.decodeResource(getResources(), R.drawable.book_harry), "F. Scott Fitzgerald", "The Great Gatsby is a novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan."));
        books.add(new Book("The Great Gatsby", BitmapFactory.decodeResource(getResources(), R.drawable.book_harry), "F. Scott Fitzgerald", "The Great Gatsby is a novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan."));
        books.add(new Book("The Great Gatsby", BitmapFactory.decodeResource(getResources(), R.drawable.book_harry), "F. Scott Fitzgerald", "The Great Gatsby is a novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan."));
        return books;
    }
}
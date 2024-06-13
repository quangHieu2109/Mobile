package view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.R;
import com.squareup.picasso.Picasso;

import adapter.BookAdapter;
import api.AApi;
import api.APIService;
import api.BookResponse;
import api.Login;
import loader.BookLoader;
import loader.BookType;
import model.Book;
import model.Wishlist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBookDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBookDetail extends Fragment {
    BookResponse book;
    ImageView img_book;
    BookAdapter bookAdapterSimilar;
    RecyclerView rcv_similar;
    ImageButton btn_wishlist;
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
        img_book = view.findViewById(R.id.img_book);
        release_book = view.findViewById(R.id.release_book);
        description = view.findViewById(R.id.description);
        rating = view.findViewById(R.id.rating);
        btn_wishlist = view.findViewById(R.id.btn_wishlist);
        btn_buy = view.findViewById(R.id.btn_buy);
        btn_submit = view.findViewById(R.id.btn_submit);
        rcv_similar = view.findViewById(R.id.rcv_similar);
        rcv_similar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        bookAdapterSimilar = new BookAdapter(getContext());
        rcv_similar.setAdapter(bookAdapterSimilar);
        Bundle bundle = getArguments();
        if (bundle != null) {
            book = (BookResponse) bundle.getSerializable("book");
            receiveData(book);

        }

        setBtnClickListeners();
        return view;
    }

    public void receiveData(BookResponse book) {
        Book books = book.getProduct();
        title_book.setText(books.getName());
        author.setText(books.getAuthor());
        rating.setText(book.getRating()+"");
        Picasso.get().load(books.getImageName()).fit().into(img_book);

        btn_buy.setText("Buy " + (int) books.getPrice() + " VND");

//        release_book.setText(book.getRelease());
        description.setText(books.getDescription());
        BookLoader.loadBook(bookAdapterSimilar, BookType.SIMILAR, books);
    }

    private void setBtnClickListeners() {
        btn_back.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
        btn_wishlist.setOnClickListener(v -> {
            APIService.apiService.addWishList("Bearer "+Login.getToken(), (int) book.getProduct().getId()).enqueue(new Callback<AApi<Wishlist>>() {
                @Override
                public void onResponse(Call<AApi<Wishlist>> call, Response<AApi<Wishlist>> response) {
                    if (response.body()==null) {
                        Toast.makeText(getContext(), "Add to wishlist failed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (response.body().isStatus()) {
                        Toast.makeText(getContext(), "Add to wishlist successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AApi<Wishlist>> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                } ;
            });
        });


    }
}
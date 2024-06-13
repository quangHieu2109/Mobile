package view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bookshop.R;

import adapter.BookAdapter;
import adapter.BookWishlistAdapter;
import api.AApi;
import api.APIService;
import api.Login;
import loader.BookLoader;
import loader.BookType;
import model.Wishlist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentWishlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentWishlist extends Fragment {
    RecyclerView rcv_wishlist;
    int position;
    BookAdapter bookAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentWishlist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentWishlist.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentWishlist newInstance(String param1, String param2) {
        FragmentWishlist fragment = new FragmentWishlist();
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
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        rcv_wishlist = view.findViewById(R.id.rcv_wishlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcv_wishlist.setLayoutManager(linearLayoutManager);
        BookAdapter bookAdapterWishlist = new BookWishlistAdapter(getContext());
        rcv_wishlist.setAdapter(bookAdapterWishlist);
        BookLoader.loadBook(bookAdapterWishlist, BookType.WISHLIST, null);
        setSwipeDeleteBook();
        return view;
    }
    private void setSwipeDeleteBook() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                 position = viewHolder.getAdapterPosition();
                 bookAdapter = (BookAdapter) rcv_wishlist.getAdapter();
                int id =(int) bookAdapter.getData().get(position).getProduct().getId();
                APIService.apiService.deleteWishList("Bearer " + Login.getToken(), id).enqueue(new Callback<AApi<String>>() {
                    @Override
                    public void onResponse(Call<AApi<String>> call, Response<AApi<String>> response) {
                        if (response.body().isStatus()) {
                            bookAdapter.deleteBook(position);
                        }
                        else {
                            Toast.makeText(getContext(), "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AApi<String>> call, Throwable t) {
                        Log.d("FragmentWishlist", "onFailure: " + t.getMessage());
                    }
                });
            }
        });
        itemTouchHelper.attachToRecyclerView(rcv_wishlist);
    }
}
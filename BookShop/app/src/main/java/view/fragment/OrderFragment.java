package view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bookshop.R;

import java.util.List;

import adapter.BookBuyAdapter;
import api.vnpay.VNPaySDK;
import model.Book;
import view.activity.VNPayActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {
    List<Book> book;
    List<Integer> quantity;
    RecyclerView recyclerViewBook;
    TextView price_ship,date_ship,price_of_products,fee_ship,total_amount,total;
    Button btn_order;
    FrameLayout frameShippingMethod;
    RelativeLayout voucher_layout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        book = (List<Book>) getArguments().getSerializable("book");
        quantity = (List<Integer>) getArguments().getSerializable("quantity");
        recyclerViewBook = view.findViewById(R.id.order_books);
        price_ship = view.findViewById(R.id.price_ship);
        date_ship = view.findViewById(R.id.date_ship);
        price_of_products = view.findViewById(R.id.price_of_products);
        fee_ship = view.findViewById(R.id.fee_ship);
        total_amount = view.findViewById(R.id.total_amount);
        total = view.findViewById(R.id.total);
        btn_order = view.findViewById(R.id.btn_order);
        frameShippingMethod = view.findViewById(R.id.frameShippingMethod);
        voucher_layout = view.findViewById(R.id.voucher_layout);
        setData();
        BookBuyAdapter bookBuyAdapter = new BookBuyAdapter();
        recyclerViewBook.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewBook.setAdapter(bookBuyAdapter);
        bookBuyAdapter.setData(book,quantity);
        setClickListener();
        return view;
    }
    private void setClickListener() {
        btn_order.setOnClickListener(v -> {
            VNPaySDK.openSDK(getContext(),Double.valueOf(total.getText().toString()));

        });
        frameShippingMethod.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new ShippingFragment()).addToBackStack("shippingfragment").commit();
        });
        voucher_layout.setOnClickListener(v -> {

        });
    }
    private void setData() {
        double price_of_product = 0;
        for (int i = 0; i < book.size(); i++) {
            price_of_product += book.get(i).getPrice() * quantity.get(i);
        }
        price_of_products.setText((int)price_of_product + "");
        int totalP = (int)price_of_product + Integer.valueOf(fee_ship.getText().toString());
        total_amount.setText(totalP + "");
        total.setText(totalP + "");

    }
}
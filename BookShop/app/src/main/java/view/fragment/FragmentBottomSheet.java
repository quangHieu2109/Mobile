package view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshop.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import api.vnpay.VNPayCallBack;
import api.vnpay.VNPaySDK;
import model.Book;
import view.activity.OrderBookActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBottomSheet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBottomSheet extends BottomSheetDialogFragment {
    private TextView name_item, price_item, tv_quantity;
    private ImageView img_item;
    private ImageButton btn_minus, btn_plus, btn_buy, btn_add_to_cart;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Book book;


    public FragmentBottomSheet() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentBottomSheet.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBottomSheet newInstance(String param1, String param2) {
        FragmentBottomSheet fragment = new FragmentBottomSheet();
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
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        book = (Book) getArguments().getSerializable("book");
        name_item = view.findViewById(R.id.name_item);
        price_item = view.findViewById(R.id.price_item);
        img_item = view.findViewById(R.id.image_item);
        tv_quantity = view.findViewById(R.id.tv_quantity);
        btn_minus = view.findViewById(R.id.btn_minus);
        btn_plus = view.findViewById(R.id.btn_plus);
        btn_buy = view.findViewById(R.id.btn_buy);
//        btn_add_to_cart = view.findViewById(R.id.btn_add_to_cart);
        setBtnClickListener();
        setData(book);

        return view;
    }

    private void setBtnClickListener() {
        btn_minus.setOnClickListener(v -> {
            int quantity = Integer.parseInt(tv_quantity.getText().toString());
            if (quantity > 1) {
                quantity--;
                tv_quantity.setText(quantity + "");

            }
        });
        btn_plus.setOnClickListener(v -> {
            int quantity = Integer.parseInt(tv_quantity.getText().toString());
            quantity++;
            tv_quantity.setText(quantity + "");
        });
        btn_buy.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), OrderBookActivity.class);
            List<Book> books = new ArrayList<>();
            List<Integer> quantities = new ArrayList<>();
            quantities.add(Integer.parseInt(tv_quantity.getText().toString()));
            books.add(book);
            intent.putExtra("quantity", (Serializable) quantities);
            intent.putExtra("book", (Serializable) books);
            startActivity(intent);

        });
//        btn_add_to_cart.setOnClickListener(v -> {
//
//        });
    }

    private void setData(Book book) {
        name_item.setText(book.getName());
        price_item.setText((int) book.getPrice() + "VND");
        Picasso.get().load(book.getImageName()).into(img_item);
    }
}
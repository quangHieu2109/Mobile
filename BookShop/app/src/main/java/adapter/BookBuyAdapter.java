package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import api.BookResponse;
import model.Book;
import view.fragment.FragmentSearch;

public class BookBuyAdapter extends RecyclerView.Adapter<BookBuyAdapter.BookViewHolder>{
    private List<Book> data;
    private List<Integer> quantities;

    @NonNull
    @Override
    public BookBuyAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_buy, parent, false);
        return new BookBuyAdapter.BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookBuyAdapter.BookViewHolder holder, int position) {
        Book book = data.get(position);
        if (book == null)
            return;
        holder.titleBook.setText(book.getName());
        holder.price.setText(String.valueOf(((int)book.getPrice())+" VND"));
        Picasso.get().load(book.getImageName()).into(holder.imgBook);
        holder.quantity.setText(quantities.get(position)+"");
    }
    public void setData(List<Book> data,List<Integer> quantities){
        this.data = data;
        this.quantities = quantities;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (data != null||quantities!=null)
            return data.size();
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBook;
        private TextView price;
        private TextView titleBook;
        private TextView quantity;


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.book_price);
            imgBook = itemView.findViewById(R.id.book_image);
            titleBook = itemView.findViewById(R.id.book_name);
            quantity = itemView.findViewById(R.id.book_quantity);

        }
    }
}

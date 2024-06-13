package adapter;

import android.content.Context;
import android.util.Log;
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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>  {
    List<BookResponse> data;
    Context context;
    public BookAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookResponse bookResponse = data.get(position);
        Book book = data.get(position).getProduct();
        if (book == null)
            return;
        holder.titleBook.setText(book.getName());
        holder.price.setText(String.valueOf(((int)book.getPrice())+" VND"));
        Picasso.get().load(book.getImageName()).into(holder.imgBook);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentSearch.ISendData)v.getContext()).sendData(bookResponse);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }
    public List<BookResponse> getData(){
        return data;
    }
    public void setData(List<BookResponse> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void addData(BookResponse data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    public void addData(List<BookResponse> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void deleteBook(int position) {
        this.data.remove(position);
        notifyItemRemoved(position);
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBook;
        private TextView price;
        private TextView titleBook;
        private CardView cardView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            imgBook = itemView.findViewById(R.id.img_book);
            titleBook = itemView.findViewById(R.id.title_book);
            cardView = itemView.findViewById(R.id.layout_item_book);
        }
    }


}

package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.R;

import java.util.List;

import model.Book;
import view.fragment.FragmentSearch;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>  {
    List<Book> data;
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
        Book book = data.get(position);
        if (book == null)
            return;
        holder.titleBook.setText(book.getTitle());
        holder.imgBook.setImageResource(R.drawable.book_harry);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentSearch.ISendData)v.getContext()).sendData(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }
    public void setData(List<Book> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBook;
        private TextView titleBook;
        private CardView cardView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.img_book);
            titleBook = itemView.findViewById(R.id.title_book);
            cardView = itemView.findViewById(R.id.layout_item_book);
        }
    }


}

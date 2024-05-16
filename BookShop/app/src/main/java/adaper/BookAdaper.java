package adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.R;

import java.util.List;

import model.Book;

public class BookAdaper extends RecyclerView.Adapter<BookAdaper.BookViewHolder>  {
    List<Book> data;
    Context context;
//    public BookAdaper(Context context) {
//        this.context = context;
//    }
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
        holder.imgBook.setImageBitmap(book.getImage());
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

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.img_book);
            titleBook = itemView.findViewById(R.id.title_book);
        }
    }


}

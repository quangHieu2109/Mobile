package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookshop.R;

import java.util.ArrayList;
import java.util.List;

import model.Book;

public class SearchBookAdapter extends ArrayAdapter<Book> {
    List<Book> data;
    public SearchBookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        data = new ArrayList<>(objects);//clone data
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_book,parent,false);
        }
        TextView textView = convertView.findViewById(R.id.title_book);
        ImageView imageView = convertView.findViewById(R.id.img_book);
        Book book = getItem(position);

        imageView.setImageResource(R.drawable.book_harry);
        textView.setText(book.getTitle());
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Book> filterData = new ArrayList<>();
                if (constraint==null || constraint.length()==0){
                    filterData.addAll(data);
                }else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Book book : data){
                        if (book.getTitle().toLowerCase().contains(filterPattern)){
                            filterData.add(book);
                        }
                    }

                }
                FilterResults results = new FilterResults();
                results.values = filterData;
                results.count = filterData.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                addAll((List) results.values);
                notifyDataSetChanged();
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((Book) resultValue).getTitle();
            }
        };
    }
}

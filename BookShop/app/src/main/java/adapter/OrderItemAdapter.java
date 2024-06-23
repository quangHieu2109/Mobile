package adapter;

import android.content.Context;
import android.service.autofill.Validators;
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

import api.OrderItemResponse;
import api.OrderResponse;


public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {
    private Context context;
    private List<OrderItemResponse> data;

    public OrderItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_item, parent, false);

        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItemResponse orderItem = data.get(position);
        holder.book_name.setText(orderItem.getProduct().getName());
        holder.price.setText((int)orderItem.getProduct().getPrice()+"");
        holder.quantity.setText(orderItem.getQuantity()+"");
        holder.total_price.setText((int)orderItem.getPrice()+"");
        Picasso.get().load(orderItem.getProduct().getImageName()).into(holder.img_book);

    }
    public void setData(List<OrderItemResponse> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(OrderItemResponse orderItemResponse){
        this.data.add(orderItemResponse);
        notifyDataSetChanged();
    }
    public void addData(List<OrderItemResponse> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    public void removeData(int position){
        this.data.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder{
        ImageView img_book;
        TextView book_name, quantity, price, total_price;
        CardView layout;
        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img_book = itemView.findViewById(R.id.img_book);
            book_name = itemView.findViewById(R.id.book_name);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);
            total_price = itemView.findViewById(R.id.total_price);
            layout = itemView.findViewById(R.id.layout_item_order_item);

        }
    }
}

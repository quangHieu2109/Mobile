package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.R;

import java.util.List;

import api.AApi;
import api.APIService;
import api.Login;
import api.OrderResponse;
import model.District;
import model.OrderStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    Context context;
    List<OrderResponse> data;
    int newStatus, indexChange;

    public OrderAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        OrderViewHolder result = new OrderViewHolder(view);

        return result;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderResponse orderResponse = data.get(position);
        holder.orderId.setText( orderResponse.getId()+"");
        holder.orderAt.setText(orderResponse.getCreatedAt().toString());
        holder.customer.setText(orderResponse.getUser().getFullName());
        holder.totalPrice.setText((int)orderResponse.getDeliveryPrice()+"");
        List<OrderStatus> orderStatuses = OrderStatus.generatedOrderStatus(orderResponse.getStatus());
        ArrayAdapter<OrderStatus> adapterStatus = new ArrayAdapter<>(this.context, android.R.layout.simple_spinner_item, orderStatuses);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.status.setAdapter(adapterStatus);
        holder.status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               OrderStatus orderStatusSelected = orderStatuses.get(i);
               newStatus = orderStatusSelected.getStatus();
                View v = holder.status.getSelectedView();
                ((TextView)v).setTextColor(holder.status.getResources().getColor(R.color.white));
                if(i ==0){
                    holder.save.setEnabled(false);
                    holder.save.setBackgroundColor(ContextCompat.getColor(context, R.color.btnDisable));
                }else{
                    holder.save.setEnabled(true);
                    holder.save.setBackgroundColor(ContextCompat.getColor(context, R.color.btnEnable));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.save.setOnClickListener(v ->{
            long orderId = Long.parseLong(holder.orderId.getText().toString());
            APIService.apiService.updateOrderStatus("Bearer "+ Login.getToken(), orderId, newStatus)
                    .enqueue(new Callback<AApi<Object>>() {
                        @Override
                        public void onResponse(Call<AApi<Object>> call, Response<AApi<Object>> response) {
                            if(response.body().isStatus()){
                                deleteOrder(holder.getAdapterPosition());
                                notifyDataSetChanged();
                                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogSuccess);
                                builder.setTitle("Success")
                                        .setMessage(response.body().getMessage())
                                        .show();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogError);
                                builder.setTitle("Error")
                                        .setMessage(response.body().getMessage())
                                        .show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AApi<Object>> call, Throwable t) {

                        }
                    });
        });


    }

    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }

    public List<OrderResponse> getData() {
        return data;
    }

    public void setData(List<OrderResponse> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public void addData(OrderResponse orderResponse){
        this.data.add(orderResponse);
        notifyDataSetChanged();
    }
    public void addData(List<OrderResponse> orderResponses){
        this.data.addAll(orderResponses);
        notifyDataSetChanged();
    }
    public void deleteOrder(int position) {
        this.data.remove(position);
        notifyDataSetChanged();
    }
    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView orderId, customer, orderAt, totalPrice;


        CardView layout_item_order;
        Spinner status;
        Button detail, save;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            customer = itemView.findViewById(R.id.customer);
            orderAt = itemView.findViewById(R.id.orderAt);
            totalPrice = itemView.findViewById(R.id.totalPrice);
            layout_item_order = itemView.findViewById(R.id.layout_item_order);
            status = itemView.findViewById(R.id.status);
            detail = itemView.findViewById(R.id.detail);
            save = itemView.findViewById(R.id.save);

        }
    }
}

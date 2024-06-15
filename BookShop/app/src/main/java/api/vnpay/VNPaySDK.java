package api.vnpay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import view.activity.VNPayActivity;


public class VNPaySDK  {

    public static void openSDK(Context context,double mount){
        Intent intent = new Intent(context, VNPayActivity.class);

        intent.putExtra("url"," https://sandbox.vnpayment.vn/paymentv2/vpcpay.html");
        intent.putExtra("tmn_code", "EO1BO5ZM");
        intent.putExtra("scheme", "homeapp");
        intent.putExtra("vnp_Amount",mount*100 );
        intent.putExtra("is_sandbox", true);
        VNPayActivity.setSdkCompletedCallback(new VNPayCallBack() {

            @Override
            public void action(TypeAction action) {
                String message = getMessage(action);
                 Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        });
        context.startActivity(intent);

    }
    private static String getMessage(TypeAction action){
        switch (action){
            case SUCCESS:
                return "Giao dịch thành công";
            case UNFINISHED:
                return "Giao dịch chưa hoàn thành";
            case PENDING:
                return "Giao dịch đang chờ xử lý";
            case FAILED:
                return "Giao dịch thất bại";
            case CHEAT:
                return "Giao dịch bị nghi ngờ gian lận";
            case REFUND_REFUSED:
                return "Hoàn tiền bị từ chối";
            case UNKNOWN:
                return "Không xác định";
            default:
                return "Không xác định";
        }
    }
}

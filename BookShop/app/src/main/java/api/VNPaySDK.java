package api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.vnpay.authentication.VNP_AuthenticationActivity;
import com.vnpay.authentication.VNP_SdkCompletedCallback;

public class VNPaySDK  {
    public static void openSDK(Context context){
        Intent intent = new Intent(context, VNP_AuthenticationActivity.class);
        intent.putExtra("url"," https://sandbox.vnpayment.vn/paymentv2/vpcpay.html");
        intent.putExtra("tmn_code", "EO1BO5ZM");
        intent.putExtra("scheme", "homeapp");
        intent.putExtra("is_sandbox", true);
        VNP_AuthenticationActivity.setSdkCompletedCallback(new VNP_SdkCompletedCallback() {
            @Override
            public void sdkAction(String action) {
                Log.d("Splas",action);
            }
        });

    }
}

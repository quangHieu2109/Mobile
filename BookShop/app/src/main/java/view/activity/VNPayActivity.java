package view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookshop.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import api.vnpay.RedirectUrl;
import api.vnpay.TypeAction;
import api.vnpay.VNPayCallBack;

public class VNPayActivity extends AppCompatActivity {
    WebView webView;
    static VNPayCallBack vnPayCallBack;
    String url;
    String tmn_code;
    String scheme;
    String vnp_Amount;
    boolean is_sandbox;
    int timeExpireM = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vnpay);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        tmn_code = intent.getStringExtra("tmn_code");
        scheme = intent.getStringExtra("scheme");
        vnp_Amount = ((int)(intent.getDoubleExtra("vnp_Amount",0)*100))+"";
        is_sandbox = intent.getBooleanExtra("is_sandbox", true);
        webView = findViewById(R.id.webView);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.webView.setScrollBarStyle((int) 33554432);
        this.webView.setInitialScale(1);
        this.webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        this.webView.getSettings().setLoadWithOverviewMode(true);
        this.webView.getSettings().setUseWideViewPort(true);
        this.webView.getSettings().setSupportZoom(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(RedirectUrl.URL_REDIRECT)) {
                    int indexResponseCode = url.indexOf("vnp_ResponseCode=") + "vnp_ResponseCode=".length();
                    String response = url.substring(indexResponseCode,indexResponseCode+2);
                    switch (response){
                        case "00":
                            vnPayCallBack.action(TypeAction.SUCCESS);
                            break;
                        case "01":
                            vnPayCallBack.action(TypeAction.UNFINISHED);
                            break;
                        case "02":
                            vnPayCallBack.action(TypeAction.FAILED);
                        case "04":
                            vnPayCallBack.action(TypeAction.PENDING);
                            break;
                        case "07":
                            vnPayCallBack.action(TypeAction.CHEAT);
                            break;
                        case "09":
                            vnPayCallBack.action(TypeAction.REFUND_REFUSED);
                            break;
                        default:
                            vnPayCallBack.action(TypeAction.UNKNOWN);
                            break;
                    }
                    finish();
                    return true;
                }
                return false;
            }

        });
        webView.loadUrl(createURL());
    }

    public static void setSdkCompletedCallback(VNPayCallBack vnPayCallBacka) {
       vnPayCallBack = vnPayCallBacka;

    }

    private String createURL() {

        Map vnp_Params = new HashMap();


        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", tmn_code);
        vnp_Params.put("vnp_Amount", vnp_Amount);
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", getRandomNumber(8));
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang");
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", RedirectUrl.URL_REDIRECT);
        vnp_Params.put("vnp_IpAddr", getIPAddress(this));
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, timeExpireM);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        try {
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                    query.append(fieldName+"="+fieldValue);
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
        } catch (Exception e) {
            Log.d("e", e.getMessage());
        }

        String queryUrl = query.toString();
        String vnp_SecureHash =generateHmacSHA512( hashData.toString(),"LLYRBGUYQSWCKJGMWESGOIHBTWMPKMHW");
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        return url+"?"+ queryUrl;
    }

    private static String generateHmacSHA512(String data, String secret) {
        byte[] hmacBytes = new byte[0];
        try{
            Mac sha512Hmac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA512");
            sha512Hmac.init(secretKey);
            hmacBytes = sha512Hmac.doFinal(data.getBytes());
        }catch (Exception e){
            Log.d("e",e.getMessage());
        }

        return bytesToHex(hmacBytes); // Chuyển byte[] thành chuỗi hex
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();

    }

    private static String getIPAddress(Context context) {
        String ipAddress = "";

        try {
            // Lấy đối tượng WifiManager
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            // Lấy địa chỉ IP
            int ipInt = wifiManager.getConnectionInfo().getIpAddress();

            // Chuyển đổi địa chỉ IP từ dạng int sang dạng chuỗi
            ipAddress = String.format("%d.%d.%d.%d", (ipInt & 0xff), (ipInt >> 8 & 0xff), (ipInt >> 16 & 0xff), (ipInt >> 24 & 0xff));
        } catch (Exception e) {
            Log.e("IPAddressHelper", "Error getting IP address: " + e.getMessage());
        }

        return ipAddress;
    }
    private static String getRandomNumber(int length) {
        String characters = "0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }
}
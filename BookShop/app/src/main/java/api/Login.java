package api;


public class Login extends AApi<LoginObject>{

    private static String token="";
    private static String refreshToken="";


    public Login(boolean status, String message, LoginObject data) {
        super(status, message, data);
    }


    public static void setToken(String _token){
        token = _token;
    }
    public static String getToken(){
        return token;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    public static void setRefreshToken(String refreshToken) {
        Login.refreshToken = refreshToken;
    }
}

package api;

public class Login extends AApi<String>{
    private static String token="";

    public Login(boolean status, String message, String data) {
        super(status, message, data);
    }

    public static void setToken(String _token){
        token = _token;
    }
    public static String getToken(){
        return token;
    }
}

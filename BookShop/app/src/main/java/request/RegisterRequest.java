package request;

public class RegisterRequest {
    private  String username;
    private  String password;
    private  String email;
    private  String fullname;

    public RegisterRequest(String username, String password, String email, String fullname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
    }
}

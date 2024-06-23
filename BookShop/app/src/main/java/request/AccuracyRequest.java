package request;

public class AccuracyRequest {
    String email;
    String otp;

    public AccuracyRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "AccuracyRequest{" +
                "email='" + email + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}

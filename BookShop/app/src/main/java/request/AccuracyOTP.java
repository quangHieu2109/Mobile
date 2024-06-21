package request;

public class AccuracyOTP {
    private static String email;
    private static String otp;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        AccuracyOTP.email = email;
    }

    public static String getOtp() {
        return otp;
    }

    public static void setOtp(String otp) {
        AccuracyOTP.otp = otp;
    }
}

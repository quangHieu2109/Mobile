package api;

import java.util.List;

public class ViettheoResponse<T> {
    int status;
    boolean error;
    String message;
    List<T> data;

    public ViettheoResponse(int status, boolean error, String message, List<T> data) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


}

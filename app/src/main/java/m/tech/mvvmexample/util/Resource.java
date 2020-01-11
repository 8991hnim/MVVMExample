package m.tech.mvvmexample.util;

public class Resource<T> {

    public Status status;

    public T data;

    public String message;

    public Resource(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    public static <T> Resource<T> error(T data, String message) {
        return new Resource<>(Status.ERROR, data, message);
    }


    public enum Status {
        SUCCESS, LOADING, ERROR
    }
}

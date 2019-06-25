package sort;

import java.util.Arrays;

public interface IArraySort<T> {
    T sort(T data) throws Exception;

    default void print(T res) {
        Arrays.stream((int[])res).forEach((value -> {
            System.out.print(value+" ");
        }));
    }
}

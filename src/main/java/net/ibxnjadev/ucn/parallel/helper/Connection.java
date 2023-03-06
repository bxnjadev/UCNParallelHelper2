package net.ibxnjadev.ucn.parallel.helper;

public interface Connection<T> {

    void connect();

    void close();

    T get();

}

package org.lijun.okhttpjavademo.network.until;

public interface NetworkCallBack<T>{
    void successful(T t);
    void fail(Exception e);
}

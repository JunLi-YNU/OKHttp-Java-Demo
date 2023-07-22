package org.lijun.okhttpjavademo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.lijun.okhttpjavademo.network.java.JavaSynchronousGet;

public class SynchronousGetTest {
    JavaSynchronousGet synchronousGet = new JavaSynchronousGet();

    @Test
    public void synchronousGetMethodTest(){
        assertNotNull(synchronousGet.synchronousGet());
    }

}

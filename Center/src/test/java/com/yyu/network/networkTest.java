package com.yyu.network;

import com.yyu.POJO.IOBody;
import org.junit.Test;

public class networkTest {

    @Test
    public void clientTest() throws Exception{
        IOBody IOBody = new ServiceRequestClient().execute(8080, "127.0.0.1", "python test.py");
        System.out.println(IOBody.getResult().toString());
    }
}

import com.yyu.POJO.Response;
import com.yyu.Util.SerializationUtil;
import com.yyu.execute.Execute;
import org.junit.Test;

import java.util.HashMap;

public class SerializationUtilTest {

    @Test
    public void codeTest() throws Exception{
        Object result;
        Response response = new Response();
        HashMap decodeResult;


        result = new Execute().exeProcess("python D:/test.py decs");
        response.setResult(result);
        byte[] resultBytes = SerializationUtil.serialize(response);
        decodeResult = (HashMap<String, String>) SerializationUtil.deserialize(resultBytes, Response.class).getResult();
    }
}

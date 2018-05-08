import com.yyu.POJO.IOBody;
import com.yyu.Util.SerializationUtil;
import com.yyu.execute.Execute;
import org.junit.Test;

import java.util.HashMap;

public class SerializationUtilTest {

    @Test
    public void codeTest() throws Exception{
        Object result;
        IOBody IOBody = new IOBody();
        HashMap decodeResult;


        result = new Execute().exeProcess("python D:/test.py decs");
        IOBody.setResult(result);
        byte[] resultBytes = SerializationUtil.serialize(IOBody);
        decodeResult = (HashMap<String, String>) SerializationUtil.deserialize(resultBytes, IOBody.class).getResult();
    }
}

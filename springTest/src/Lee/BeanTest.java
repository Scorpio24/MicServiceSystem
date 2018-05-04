package Lee;

//import com.yy.Axe;
//import com.yy.impl.StoneAxe;
//import com.yy.impl.Chinese;
import com.yy.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BeanTest {
    public static void main(String[] args) throws Exception
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("beans.xml");

        Person p = ctx.getBean("chinese", Person.class);
        p.useAxe();
//        Axe axe = new StoneAxe();
//        Person chinese = new Chinese(axe);
//        chinese.useAxe();
    }
}

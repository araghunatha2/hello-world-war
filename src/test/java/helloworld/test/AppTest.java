package helloworld.test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;


public class AppTest {

    @Before
    public void prepare() {
        setBaseUrl("https://testhelloworld-javahelloworldweb.1d35.starter-us-east-1.openshiftapps.com");
    }

    @Test
    public void testLogin() {
        System.out.println("Test");
        
        beginAt("index.jsp");
        assertTitleEquals("Its Java Hello Demo World again!!!!!!!");        

    }

    
}


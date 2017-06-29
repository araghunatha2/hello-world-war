package helloworld.test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;


public class AppTest {

    @Before
    public void prepare() {
        //setBaseUrl("http://helloworld-dev.cloudapps.duw905.gcp.testdrive.openshift.com/");
    }

    @Test
    public void testLogin() {
        System.out.println("Test");
        
        beginAt("index.jsp");
        assertTitleEquals("Its Java Hello Demo World again!!!!!!!");        
    }
}


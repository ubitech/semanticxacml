package eu.paasword.semanticxacml;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import eu.paasword.semanticxacml.Application;
import eu.paasword.semanticxacml.AuthorizationService;
import eu.paasword.semanticxacml.facts.AuthorizationResponse;
import eu.paasword.semanticxacml.facts.Request;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class WebServiceTest {

    @Autowired
    private AuthorizationService authorizationService;

    @Test
    public void getAuthorizationRequest1() {
        Request request = new Request("Steve", 16);
        AuthorizationResponse authresponse = authorizationService.getAuthorizationResponse(request);        
        System.out.println("AuthorizationResponse: " + authresponse);        
        assertEquals(AuthorizationResponse.class, authresponse.getClass());
    }//
    
    @Test
    public void getAuthorizationRequest2() {
        Request request = new Request("Steve", 22);
        AuthorizationResponse authresponse = authorizationService.getAuthorizationResponse(request);        
        System.out.println("AuthorizationResponse: " + authresponse);        
        assertEquals(AuthorizationResponse.class, authresponse.getClass());
    }//

}
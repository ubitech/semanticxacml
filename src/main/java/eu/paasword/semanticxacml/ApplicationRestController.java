package eu.paasword.semanticxacml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.paasword.semanticxacml.facts.AuthorizationResponse;
import eu.paasword.semanticxacml.facts.Request;

@RestController
public class ApplicationRestController {

    private static Logger log = LoggerFactory.getLogger(ApplicationRestController.class);

    private final AuthorizationService authService;
    @Autowired
    public ApplicationRestController(AuthorizationService busPassService) {
        this.authService = busPassService;
    }

    @RequestMapping(value = "/authrequest", method = RequestMethod.GET, produces = "application/json")
    public AuthorizationResponse getQuestions(
            @RequestParam(required = true) String name,
            @RequestParam(required = true) int age,
            @RequestParam(required = true) String roomname            
    ) {

        Request person = new Request(name, age);
        log.debug("Bus pass request received for: " + person);      
        AuthorizationResponse busPass = authService.getAuthorizationResponse(person);
        return busPass;
    }

}

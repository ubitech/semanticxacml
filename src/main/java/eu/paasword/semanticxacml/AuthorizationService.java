package eu.paasword.semanticxacml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.paasword.semanticxacml.facts.AuthorizationResponse;
import eu.paasword.semanticxacml.facts.Request;
import eu.paasword.semanticxacml.facts.semantic.Building;
import eu.paasword.semanticxacml.facts.semantic.Department;
import eu.paasword.semanticxacml.facts.semantic.Room;

@Service
public class AuthorizationService {

    private static Logger log = LoggerFactory.getLogger(AuthorizationService.class);

    private final KieContainer kieContainer;

    @Autowired
    public AuthorizationService(KieContainer kieContainer) {
        log.info("Initialising a authroization session1.");
        this.kieContainer = kieContainer;
    }

    /**
     * Create a new session, insert a person's details and fire rules to
     * determine what kind of bus pass is to be issued.
     */
    public AuthorizationResponse getAuthorizationResponse(Request request) {
        KieSession kieSession = kieContainer.newKieSession("AuthorizationSession");
        kieSession.insert(request);
        
        //Knowledge from database
        Building b1 = new Building("b1");
        Building b2 = new Building("b2");
        Building b3 = new Building("b3");

        Department d11 = new Department("d11");
        Department d12 = new Department("d12");        
        Department d13 = new Department("d13");        
        b1.getDepartments().add(d11);
        b1.getDepartments().add(d12);
        b1.getDepartments().add(d13);
        
        Department d21 = new Department("d21");
        Department d22 = new Department("d22");        
        Department d23 = new Department("d23");        
        b2.getDepartments().add(d21);
        b2.getDepartments().add(d22);
        b2.getDepartments().add(d23);
        
        Department d31 = new Department("d31");
        Department d32 = new Department("d32");        
        Department d33 = new Department("d33");        
        b3.getDepartments().add(d31);        
        b3.getDepartments().add(d32);        
        b3.getDepartments().add(d33);        
        
        Room r111 = new Room("r111");
        Room r112 = new Room("r112");
        d11.getRooms().add(r111);
        d11.getRooms().add(r112);

        Room r121 = new Room("r121");
        Room r122 = new Room("r122");
        d12.getRooms().add(r121);
        d12.getRooms().add(r122);

        Room r131 = new Room("r131");
        Room r132 = new Room("r132");
        d13.getRooms().add(r131);
        d13.getRooms().add(r132);
        
        Room r211 = new Room("r211");
        Room r212 = new Room("r212");
        d21.getRooms().add(r211);        
        d21.getRooms().add(r212);        
        
        Room r311 = new Room("r311");
        Room r312 = new Room("r312");
        d31.getRooms().add(r311);         
        d31.getRooms().add(r312); 
              
        kieSession.insert(b1);
        kieSession.insert(b2);
        kieSession.insert(b3);
        kieSession.insert(d11);
        kieSession.insert(d12);
        kieSession.insert(d13);
        kieSession.insert(d21);
        kieSession.insert(d22);
        kieSession.insert(d23);
        kieSession.insert(d31);
        kieSession.insert(d32);
        kieSession.insert(d33);
        
        //facts to be expanded
        
        //Invoke 
        kieSession.fireAllRules();
        AuthorizationResponse busPass = findBusPass(kieSession);
        kieSession.dispose();
        return busPass;
    }
    
    /**
     * Search the {@link KieSession} for bus passes.
     */
    private AuthorizationResponse findBusPass(KieSession kieSession) {
        
        // Find all AuthorizationResponse facts and 1st generation child classes of AuthorizationResponse.
        ObjectFilter busPassFilter = new ObjectFilter() {
            @Override
            public boolean accept(Object object) {
                if (AuthorizationResponse.class.equals(object.getClass())) return true;
                if (AuthorizationResponse.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };

        // printFactsMessage(kieSession);
        
        List<AuthorizationResponse> facts = new ArrayList<AuthorizationResponse>();
        for (FactHandle handle : kieSession.getFactHandles(busPassFilter)) {
            facts.add((AuthorizationResponse) kieSession.getObject(handle));
        }
        if (facts.size() == 0) {
            return null;
        }
        // Assumes that the rules will always be generating a single bus pass. 
        return facts.get(0);
    }
    
    /**
     * Print out details of all facts in working memory.
     * Handy for debugging.
     */
    @SuppressWarnings("unused")
    private void printFactsMessage(KieSession kieSession) {
        Collection<FactHandle> allHandles = kieSession.getFactHandles();
        
        String msg = "\nAll facts:\n";
        for (FactHandle handle : allHandles) {
            msg += "    " + kieSession.getObject(handle) + "\n";
        }
        System.out.println(msg);
    }

}

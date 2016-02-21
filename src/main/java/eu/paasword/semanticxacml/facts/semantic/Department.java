/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.paasword.semanticxacml.facts.semantic;

import eu.paasword.semanticxacml.facts.SemanticConcept;
import java.util.ArrayList;

/**
 *
 * @author pgouvas
 */
public class Department  extends SemanticConcept {
    
    private String depname;
    private ArrayList<Room> rooms;    
    
    public Department(String depname) {
        this.depname = depname;
        rooms = new ArrayList();
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Department that = (Department) o;
        return this.depname == that.depname ;
    }

    @Override
    public int hashCode() {
        int result = depname.hashCode();
        return result;
    }    
        
}

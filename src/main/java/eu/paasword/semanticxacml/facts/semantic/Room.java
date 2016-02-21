/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.paasword.semanticxacml.facts.semantic;

import eu.paasword.semanticxacml.facts.SemanticConcept;

/**
 *
 * @author pgouvas
 */
public class Room  extends SemanticConcept {
    
    private String roomname;

    public Room(String roomname) {
        this.roomname = roomname;
        this.setId(roomname);        
    }
          
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Room that = (Room) o;
        return this.roomname == that.roomname ;
    }

    @Override
    public int hashCode() {
        int result = roomname.hashCode();
        return result;
    }    
            
}


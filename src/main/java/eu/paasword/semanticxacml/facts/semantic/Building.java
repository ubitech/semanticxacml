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
public class Building extends SemanticConcept {
 
    private String buildingname;
    private ArrayList<Department> departments;

    public Building(String buildingname) {
        this.buildingname = buildingname;
        departments = new ArrayList();
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Building that = (Building) o;
        return this.buildingname == that.buildingname ;
    }

    @Override
    public int hashCode() {
        int result = buildingname.hashCode();
        return result;
    }
    
}
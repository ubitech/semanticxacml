package eu.paasword.semanticxacml.facts;

import eu.paasword.semanticxacml.facts.semantic.Room;

public class Request<SC extends SemanticConcept> {

    private String name;
    private int age;
    private SC islocatedAt;
    
    
    public SC getIslocatedAt() {
        return islocatedAt;
    }

    public void setIslocatedAt(SC islocatedAt) {
        this.islocatedAt = islocatedAt;
    }
    
    public Request(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Request(String name, int age, String roomname) {
        this.name = name;
        this.age = age;
        Room rm = new Room(roomname);
        this.islocatedAt = (SC) rm;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Request: { name=\"" + name + "\"" + ", age=" + age + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Request that = (Request) o;
        return this.age == that.age && this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }
    
}
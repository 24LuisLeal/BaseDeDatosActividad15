package aplication;

/**
 * 
 * @author HungryStudentDev
 */

public class Autor {
    //Nombre del autor
    public String name;
    //Apellido paterno del autor
    public String lastName;

    public Autor() {
    
    }
    
    public Autor(String name,String lastName){
        this.name = name;
        this.lastName = lastName;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

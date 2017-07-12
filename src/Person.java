public class Person {
    private final int number;
    private static int previousAccountNumber = 0;
    String firstname;
    String lastname;
    
    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.number = previousAccountNumber;
        previousAccountNumber++;
    }
    public int getNumber() {
        return this.number;
    }
    
    public String getName() {
        return firstname + " " + lastname;
    }
    
    public String toString() {
        return this.getName();
    }
}
    
     
    
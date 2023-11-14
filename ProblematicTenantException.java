import java.util.List;

public class ProblematicTenantException extends Exception {
    public ProblematicTenantException() {
        super("Person is problematic.");
    }

    public ProblematicTenantException(Person person) {
        super("Person " + person.getName() + " had already renting rooms : " + person.getRentedRooms());
    }

    @Override
    public String getMessage() {
        return "Error: " + super.getMessage();
    }
}

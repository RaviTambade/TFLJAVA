package Membership;

public class AuthManager {
public static boolean validate(String email, String password) {
    boolean status = false;
    if (email.equals("ravi.tambade@transflower.in") && password.equals("seed")) {
        status = true;
    }
    return status;
}

public static boolean register(String firstName, String lastName, String email, String location, String contactNumber) {
    boolean status = false;
    try {
        User theUser = new User();
        theUser.setFirstName(firstName);
        theUser.setLastName(lastName);
        theUser.setEmail(email);
        theUser.setLocation(location);
        theUser.setContactNumber(contactNumber);
        // Store object into persistent medium (e.g., database, file)
        status = true;
    } catch (Exception e) {
        e.printStackTrace();  // Exception handling code
    }
    return status;
}
}
package Personnel;

/**
 * <b>Class Unknown</b>
 * <p>
 * This type of users can't have access to the application until an
 * administrator confirm their inscription.
 * </p>
 * 
 * @author Mathieu BOUTELIER
 * 
 */
public class Unknown extends Personnel {
    /**
     * user's type (can be Administrator, Student or Teacher)
     */
    private String type;
    
    public Unknown(String type, String firstName, String name, String email,
            String password, int identifiant) {
        super(firstName, name, email, password, identifiant);
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(super.getFirstName());
        str.append(" veut etre un ");
        str.append(type);
        str.append("\n");
        
        return str.toString();
    }
}

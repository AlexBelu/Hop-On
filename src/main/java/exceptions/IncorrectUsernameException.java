package exceptions;

public class IncorrectUsernameException extends Exception{
    private String username;
    public IncorrectUsernameException(String username)
    {
        super("Incorrect username entered");
        this.username=username;
    }

    public String getUsername() {
        return username;
    }
}

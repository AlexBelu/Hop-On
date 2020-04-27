package exceptions;

public class IncorrectPasswordException extends Exception{
            String password;
            public IncorrectPasswordException(String password)
            {
                super("You entered a wrong password");
                this.password=password;
            }

    public String getPassword() {
        return password;
    }
}

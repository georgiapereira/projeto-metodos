package src.model;

public class User {
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Getters
    public String getlogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


}

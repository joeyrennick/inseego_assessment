package inseego.assessment;

public class User {

    private String name;
    private String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
    }
}

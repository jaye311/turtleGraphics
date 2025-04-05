package MoveMe;

public abstract class AccountHolder {
    protected String name;
    protected String phoneNumber;
    protected int avgRating;
    private Account account;
    public AccountHolder(String n, String p){
        name = n;
        phoneNumber = p;
    }
}

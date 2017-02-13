package main;

/**
 * Created by kills on 02.02.2017.
 */
public class UserRight {
    private String right;
    private boolean access;
    public UserRight(String right,boolean access)
    {
        this.right = right;
        this.access = access;
}

    public String getRight() {
        return right;
    }

    public boolean isAccess() {
        return access;
    }
}

package Models;

public class UserData {
    private User user;
    private boolean registrationStatus;

    public UserData() {
        user = null;
        registrationStatus = false;
    }

    public UserData(User user, boolean status) {
        this.user = user;
        this.registrationStatus = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User _user) {
        this.user = _user;
    }

    public boolean isRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(boolean _registrationStatus) {
        this.registrationStatus = _registrationStatus;
    }

    @Override
    public String toString() {
        return "Usr{" + "User='" + user + '\'' +
                ",isReg='" + registrationStatus + '\'' +
                '}';
    }
}

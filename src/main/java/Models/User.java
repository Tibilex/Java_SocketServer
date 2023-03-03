package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;


@Entity
@Table(name = "Users")
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String login;
    protected String password;
    public User(){}
    public User(String log,String pas){
        this.id = 0;
        this.login = log;
        this.password = pas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getInfo(){
        return "\n\tId = "+id+"\n\tLogin = "+login+"\n\tPassword = "+password;
    }
    @Override
    public String toString(){
        return "User{" + "Id=" + id +
                ", Login='" + login + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}

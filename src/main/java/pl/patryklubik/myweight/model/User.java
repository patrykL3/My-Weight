package pl.patryklubik.myweight.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


/**
 * Create by Patryk Łubik on 03.09.2021.
 */

@Entity
@Table(name = "users", uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Username must not be blank")
    private String username;

    @NotBlank(message = "Password must not be blank")
    private String password;
    private boolean active;
    @OneToOne
    @JoinColumn(name = "role_id")
    private int roleId;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
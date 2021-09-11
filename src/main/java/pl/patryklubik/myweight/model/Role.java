package pl.patryklubik.myweight.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


/**
 * Create by Patryk Łubik on 05.09.2021.
 */

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @OneToMany
    private Set<Permission> permissions;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
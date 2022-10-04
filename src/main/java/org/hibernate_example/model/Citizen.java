package org.hibernate_example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name="Citizen")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Citizen {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @OneToOne(mappedBy = "citizen")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Passport passport;

    public Citizen(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
        passport.setCitizen(this);
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

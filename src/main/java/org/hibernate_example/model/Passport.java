package org.hibernate_example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Passport")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passport {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="passport_number")
    private int passportNumber;

    @OneToOne
    @JoinColumn(name="citizen_id", referencedColumnName = "id")
    private Citizen citizen;

    public Passport(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", citizen=" + citizen +
                ", passportNumber=" + passportNumber +
                '}';
    }
}

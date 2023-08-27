package Opdracht.Opdracht.entity;


import jakarta.persistence.*;
import lombok.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "voornaam")
    private String voornaam;
    @Column(name = "achternaam")
    private String achternaam;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "geboortedatum")
    private LocalDate geboortedatum;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservatie> reservaties = new ArrayList<>();

}
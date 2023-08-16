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
    @Column(name = "voorNaam")
    private String voorNaam;
    @Column(name = "achterNaam")
    private String achterNaam;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "geboorteDatum")
    private LocalDate geboorteDatum;

}
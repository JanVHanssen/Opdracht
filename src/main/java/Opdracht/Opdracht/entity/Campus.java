package Opdracht.Opdracht.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campus")
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "naam")
    private String naam;
    @Column(name = "adres")
    private String adres;
    @Column(name = "parkeerplaatsen")
    private Long parkeerplaatsen;
    @Column(name = "aantal_lokalen")
    private Long aantal_lokalen;
    @OneToMany(cascade = CascadeType.ALL)
    public Lokaal lokaal;
}

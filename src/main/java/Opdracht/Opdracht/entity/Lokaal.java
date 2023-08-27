package Opdracht.Opdracht.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lokaal")
public class Lokaal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "naam")
    private String naam;
    @Column(name = "type")
    private String type;
    @Column(name = "capaciteit")
    private Long capaciteit;
    @Column(name = "verdieping")
    private Long verdieping;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "campus_id")
    @JsonIgnore
    private Campus campus;

}

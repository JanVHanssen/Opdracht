package Opdracht.Opdracht.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservatie")
public class Reservatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start")
    private LocalDateTime start;
    @Column(name = "einde")
    private LocalDateTime einde;
    @Column(name = "comment")
    private String comment;
    @Column(name = "max_aantal")
    private Long max_aantal;
    @ManyToMany(cascade = CascadeType.ALL)
    public Lokaal lokaal;
    @ManyToOne(cascade = CascadeType.ALL)
    public User user;
}

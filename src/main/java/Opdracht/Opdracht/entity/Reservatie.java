package Opdracht.Opdracht.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    public User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lokaal_id")
    @JsonIgnore
    public Lokaal lokaal;
}

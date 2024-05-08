package cz.vsb.fei.java.mlc0044_java_psp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "osoby")
@Getter @Setter @NoArgsConstructor
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_osoba", nullable = false)
    private Integer idOsoba;

    @Column(name = "pred", nullable = true)
    private String pred;

    @Column(name = "jmeno", nullable = true)
    private String jmeno;

    @Column(name = "prijmeni", nullable = true)
    private String prijmeni;

    @Column(name = "za", nullable = true)
    private String za;

    @Temporal(TemporalType.DATE)
    @Column(name = "narozeni", nullable = true)
    private Date narozeni;

    @Column(name = "pohlavi", length = 1, nullable = true)
    private String pohlavi;

    @Temporal(TemporalType.DATE)
    @Column(name = "zmena", nullable = true)
    private Date zmena;

    @Temporal(TemporalType.DATE)
    @Column(name = "umrti", nullable = true)
    private Date umrti;
}

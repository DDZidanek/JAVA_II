package cz.vsb.fei.java.mlc0044_java_psp.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "poslanec")
@Getter @Setter @NoArgsConstructor
public class Poslanec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poslanec", nullable = false)
    private Integer idPoslanec;

    @ManyToOne
    @JoinColumn(name = "id_osoba", nullable = false)
    private Osoba idOsoba;

    @Column(name = "id_kraj", nullable = false)
    private Integer idKraj;

    @ManyToOne
    @JoinColumn(name = "id_kandidatka", nullable = true)
    private Organy idKandidatka;

    @ManyToOne
    @JoinColumn(name = "id_obdobi", nullable = true)
    private Organy idObdobi;

    @Column(name = "web", nullable = true)
    private String web;

    @Column(name = "ulice", nullable = true)
    private String ulice;

    @Column(name = "obec", nullable = true)
    private String obec;

    @Column(name = "psc", nullable = true)
    private String psc;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "telefon", nullable = true)
    private String telefon;

    @Column(name = "fax", nullable = true)
    private String fax;

    @Column(name = "psp_telefon", nullable = true)
    private String pspTelefon;

    @Column(name = "facebook", nullable = true)
    private String facebook;

    @Column(name = "foto", nullable = true)
    private Integer foto;
}


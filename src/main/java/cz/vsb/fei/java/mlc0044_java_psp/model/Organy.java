package cz.vsb.fei.java.mlc0044_java_psp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.util.Date;

@Entity
@Table(name = "organy")
@Getter @Setter @NoArgsConstructor
public class Organy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organ", nullable = false)
    private Integer idOrgan;

    @Column(name = "organ_id_organ", nullable = true)
    private Integer parentOrgan;

    @Column(name = "id_typ_organu", nullable = true)
    private String idTypOrganu;

    @Column(name = "zkratka", nullable = true)
    private String zkratka;

    @Column(name = "nazev_organu_cz", nullable = true)
    private String nazevOrganuCz;

    @Column(name = "nazev_organu_en", nullable = true)
    private String nazevOrganuEn;

    @Temporal(TemporalType.DATE)
    @Column(name = "od_organ", nullable = true)
    private Date odOrgan;

    @Temporal(TemporalType.DATE)
    @Column(name = "do_organ", nullable = true)
    private Date doOrgan;

    @Column(name = "priorita", nullable = true)
    private Integer priorita;

    @Column(name = "cl_organ_base", nullable = true)
    private Integer clOrganBase;
}


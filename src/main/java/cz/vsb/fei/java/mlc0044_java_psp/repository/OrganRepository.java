package cz.vsb.fei.java.mlc0044_java_psp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cz.vsb.fei.java.mlc0044_java_psp.model.Organy;

@Repository
public interface OrganRepository extends JpaRepository<Organy, Integer> {
}

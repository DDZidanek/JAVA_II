package cz.vsb.fei.java.mlc0044_java_psp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cz.vsb.fei.java.mlc0044_java_psp.model.*;
import cz.vsb.fei.java.mlc0044_java_psp.repository.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/organy")
public class OrganyController {

    @Autowired
    private OrganRepository organRepository;

    @GetMapping
    public List<Organy> getAllOrgany() {
        return organRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Organy> createOrgan(@RequestBody Organy organ) {
        Organy savedOrgan = organRepository.save(organ);
        return ResponseEntity.ok(savedOrgan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organy> getOrganById(@PathVariable Integer id) {
        Optional<Organy> organ = organRepository.findById(id);
        if (organ.isPresent()) {
            return ResponseEntity.ok(organ.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organy> updateOrgan(@PathVariable Integer id, @RequestBody Organy organDetails) {
        return organRepository.findById(id)
                .map(organ -> {
                    organ.setParentOrgan(organDetails.getParentOrgan());
                    organ.setIdTypOrganu(organDetails.getIdTypOrganu());
                    organ.setZkratka(organDetails.getZkratka());
                    organ.setNazevOrganuCz(organDetails.getNazevOrganuCz());
                    organ.setNazevOrganuEn(organDetails.getNazevOrganuEn());
                    organ.setOdOrgan(organDetails.getOdOrgan());
                    organ.setDoOrgan(organDetails.getDoOrgan());
                    organ.setPriorita(organDetails.getPriorita());
                    organ.setClOrganBase(organDetails.getClOrganBase());
                    Organy updatedOrgan = organRepository.save(organ);
                    return ResponseEntity.ok().body(updatedOrgan);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mazání orgánu
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrgan(@PathVariable Integer id) {
        return organRepository.findById(id)
                .map(organ -> {
                    organRepository.delete(organ);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}


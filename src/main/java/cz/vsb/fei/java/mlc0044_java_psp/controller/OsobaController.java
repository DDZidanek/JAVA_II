package cz.vsb.fei.java.mlc0044_java_psp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cz.vsb.fei.java.mlc0044_java_psp.model.*;
import cz.vsb.fei.java.mlc0044_java_psp.repository.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/osoby")
public class OsobaController {

    @Autowired
    private OsobaRepository osobaRepository;

    @GetMapping
    public List<Osoba> getAllOsoby() {
        return osobaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Osoba> createOsoba(@RequestBody Osoba osoba) {
        Osoba savedOsoba = osobaRepository.save(osoba);
        return ResponseEntity.ok(savedOsoba);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Osoba> getOsobaById(@PathVariable Integer id) {
        Optional<Osoba> osoba = osobaRepository.findById(id);
        if (osoba.isPresent()) {
            return ResponseEntity.ok(osoba.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Osoba> updateOsoba(@PathVariable Integer id, @RequestBody Osoba osobaDetails) {
        return osobaRepository.findById(id)
                .map(osoba -> {
                    osoba.setPred(osobaDetails.getPred());
                    osoba.setJmeno(osobaDetails.getJmeno());
                    osoba.setPrijmeni(osobaDetails.getPrijmeni());
                    osoba.setZa(osobaDetails.getZa());
                    osoba.setNarozeni(osobaDetails.getNarozeni());
                    osoba.setPohlavi(osobaDetails.getPohlavi());
                    osoba.setZmena(osobaDetails.getZmena());
                    osoba.setUmrti(osobaDetails.getUmrti());
                    Osoba updatedOsoba = osobaRepository.save(osoba);
                    return ResponseEntity.ok().body(updatedOsoba);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOsoba(@PathVariable Integer id) {
        return osobaRepository.findById(id)
                .map(osoba -> {
                    osobaRepository.delete(osoba);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}


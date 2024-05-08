package cz.vsb.fei.java.mlc0044_java_psp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cz.vsb.fei.java.mlc0044_java_psp.model.*;
import cz.vsb.fei.java.mlc0044_java_psp.repository.*;
import java.util.List;

@RestController
@RequestMapping("/poslanci")
public class PoslanecController {

    @Autowired
    private PoslanecRepository poslanecRepository;

    @GetMapping
    public List<Poslanec> getAllPoslanci() {
        return poslanecRepository.findAll();
    }

    @PostMapping
    public Poslanec createPoslanec(@RequestBody Poslanec poslanec) {
        return poslanecRepository.save(poslanec);
    }

    @GetMapping("/{id}")
    public Poslanec getPoslanecById(@PathVariable Integer id) {
        return poslanecRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Poslanec updatePoslanec(@PathVariable Integer id, @RequestBody Poslanec poslanecDetails) {
        Poslanec poslanec = poslanecRepository.findById(id).orElse(null);
        if (poslanec != null) {
            poslanec.setIdOsoba(poslanecDetails.getIdOsoba());
            poslanec.setIdKraj(poslanecDetails.getIdKraj());
            return poslanecRepository.save(poslanec);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePoslanec(@PathVariable Integer id) {
        poslanecRepository.deleteById(id);
    }
}


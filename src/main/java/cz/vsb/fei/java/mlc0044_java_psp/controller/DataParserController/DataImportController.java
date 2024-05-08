package cz.vsb.fei.java.mlc0044_java_psp.controller.DataParserController;
import cz.vsb.fei.java.mlc0044_java_psp.DataParser.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class DataImportController {

    @Autowired
    private DataParser dataParser;

    @GetMapping("/import/osoby")
    public String importOsoby() {
        Resource resource = new ClassPathResource("OpenData/osoby.unl");
        if (resource.exists() && resource.isReadable()) {
            log.info("Soubor existuje a je čitelný");
        } else {
            log.error("Soubor neexistuje nebo není čitelný");
        }
        try
        {
            String path = resource.getFile().getAbsolutePath();
            dataParser.parseOsoby(path);
            return "Import osob byl úspěšný.";
        }catch (IOException e)
        {
            e.printStackTrace();
            return "Nepodařilo se načísti soubor";
        }
    }
    @GetMapping("/import/poslanci")
    public String importPoslanci() {
        Resource resource = new ClassPathResource("OpenData/poslanec.unl");
        if (resource.exists() && resource.isReadable()) {
            log.info("Soubor existuje a je čitelný");
        } else {
            log.error("Soubor neexistuje nebo není čitelný");
        }
        try
        {
            String path = resource.getFile().getAbsolutePath();
            dataParser.parsePoslanci(path);
            return "Import poslanců byl úspěšný.";
        }catch (IOException e)
        {
            e.printStackTrace();
            return "Nepodařilo se načísti soubor";
        }
    }
    @GetMapping("/import/organy")
    public String importOrgany() {
        Resource resource = new ClassPathResource("OpenData/organy.unl");
        if (resource.exists() && resource.isReadable()) {
            log.info("Soubor existuje a je čitelný");
        } else {
            log.error("Soubor neexistuje nebo není čitelný");
        }
        try
        {
            String path = resource.getFile().getAbsolutePath();
            dataParser.parseOrgany(path);
            return "Import orgánů byl úspěšný.";
        }catch (IOException e)
        {
            e.printStackTrace();
            return "Nepodařilo se načísti soubor";
        }
    }
}


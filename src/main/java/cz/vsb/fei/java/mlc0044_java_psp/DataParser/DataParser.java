package cz.vsb.fei.java.mlc0044_java_psp.DataParser;

import cz.vsb.fei.java.mlc0044_java_psp.model.Organy;
import cz.vsb.fei.java.mlc0044_java_psp.model.Osoba;
import cz.vsb.fei.java.mlc0044_java_psp.model.Poslanec;
import cz.vsb.fei.java.mlc0044_java_psp.repository.OrganRepository;
import cz.vsb.fei.java.mlc0044_java_psp.repository.OsobaRepository;
import cz.vsb.fei.java.mlc0044_java_psp.repository.PoslanecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DataParser {

    @Autowired
    private OsobaRepository osobaRepository;
    @Autowired
    private PoslanecRepository poslanecRepository;
    @Autowired
    private OrganRepository organyRepository;

    public void parseOsoby(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Osoba> osoby = new ArrayList<>();
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 9) {
                    continue;
                }

                Osoba osoba = new Osoba();
                osoba.setIdOsoba(Integer.parseInt(parts[0].trim()));
                osoba.setPred(parts[1].trim());
                osoba.setPrijmeni(parts[2].trim());
                osoba.setJmeno(parts[3].trim());
                osoba.setZa(parts[4].trim());
                osoba.setNarozeni(convertStringToDate(parts[5].trim()));
                osoba.setPohlavi(parts[6].trim());
                osoba.setZmena(convertStringToDate(parts[7].trim()));
                osoba.setUmrti(convertStringToDate(parts[8].trim()));

                osoby.add(osoba);
            }

            if (osoby.isEmpty()) {
                System.out.println("Žádná data k načtení z UNL souboru.");
            } else {
                System.out.println("Načteno " + osoby.size() + " záznamů, ukládám do databáze.");
                osobaRepository.saveAll(osoby);
                System.out.println("Data byla uložena.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void parsePoslanci(String filePath)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Poslanec> poslanci = new ArrayList<>();
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 15) {
                    continue;
                }
                System.out.println(parts);
                Poslanec poslanec = new Poslanec();
                poslanec.setIdPoslanec(Integer.parseInt(parts[0].trim()));

                Optional<Osoba> optionalOsoba = osobaRepository.findById(Integer.parseInt(parts[1].trim()));

                if (optionalOsoba.isPresent()) {
                    Osoba osoba = optionalOsoba.get();
                    poslanec.setIdOsoba(osoba);
                }else {
                    continue;
                }
                poslanec.setIdKraj(Integer.parseInt(parts[2].trim()));

                Organy kandidatka = null;
                if (parts[3] != "" && parts[3] != " ")
                {
                    kandidatka = organyRepository.findById((Integer.parseInt(parts[3].trim()))).orElse(null);
                }else
                {
                    continue;
                }

                poslanec.setIdKandidatka(kandidatka);

                Organy obdobi = null;
                if (parts[4] != "" && parts[4] != "")
                {
                    obdobi = organyRepository.findById((Integer.parseInt(parts[4].trim()))).orElse(null);
                }else
                {
                    continue;
                }
                poslanec.setIdObdobi(obdobi);
                poslanec.setWeb(parts[5].trim());
                poslanec.setUlice(parts[6].trim());
                poslanec.setObec(parts[7].trim());
                poslanec.setPsc(parts[8].trim());
                poslanec.setEmail(parts[9].trim());
                poslanec.setTelefon(parts[10].trim());
                poslanec.setFax(parts[11].trim());
                poslanec.setPspTelefon(parts[12].trim());
                poslanec.setFacebook(parts[13].trim());
                poslanec.setFoto(Integer.parseInt(parts[14].trim()));

                poslanci.add(poslanec);
            }

            if (poslanci.isEmpty()) {
                System.out.println("Žádná data k načtení z UNL souboru.");
            } else {
                System.out.println("Načteno " + poslanci.size() + " záznamů, ukládám do databáze.");
                poslanecRepository.saveAll(poslanci);
                System.out.println("Data byla uložena.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void parseOrgany(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<Organy> organy = new ArrayList<>();
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 10) {
                    continue;
                }

                Organy organ = new Organy();
                if (!parts[0].trim().isEmpty()) {
                    organ.setIdOrgan(Integer.parseInt(parts[0].trim()));
                }
                if (!parts[1].trim().isEmpty()) {
                    organ.setParentOrgan(Integer.parseInt(parts[1].trim()));
                }
                if (!parts[2].trim().isEmpty()) {
                    organ.setIdTypOrganu(parts[2].trim());
                }
                if (!parts[3].trim().isEmpty()) {
                    organ.setZkratka(parts[3].trim());
                }
                if (!parts[4].trim().isEmpty()) {
                    organ.setNazevOrganuCz(parts[4].trim());
                }
                if (!parts[5].trim().isEmpty()) {
                    organ.setNazevOrganuEn(parts[5].trim());
                }
                if (!parts[6].trim().isEmpty()) {
                    organ.setOdOrgan(convertStringToDate(parts[6].trim()));
                }
                if (!parts[7].trim().isEmpty()) {
                    organ.setDoOrgan(convertStringToDate(parts[7].trim()));
                }
                if (!parts[8].trim().isEmpty()) {
                    organ.setPriorita(Integer.parseInt(parts[8].trim()));
                }
                if (!parts[9].trim().isEmpty()) {
                    organ.setClOrganBase(Integer.parseInt(parts[9].trim()));
                }
                organy.add(organ);
            }

            if (organy.isEmpty()) {
                System.out.println("Žádná data k načtení z UNL souboru.");
            } else {
                System.out.println("Načteno " + organy.size() + " záznamů, ukládám do databáze.");
                organyRepository.saveAll(organy);
                System.out.println("Data byla uložena.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Date convertStringToDate(String dateString) {
        return new Date();
    }
}

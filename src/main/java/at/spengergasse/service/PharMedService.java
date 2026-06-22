package at.spengergasse.service;

import at.spengergasse.domain.PharMed;
import at.spengergasse.domain.PharMedException;
import at.spengergasse.repository.PharMedRepisotory;
import com.vaadin.copilot.shaded.javassist.bytecode.Descriptor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

@Service
public class PharMedService
{
    private final PharMedRepisotory repository;

    public PharMedService(PharMedRepisotory repository)
    {
        this.repository = repository;
        if(repository.count() == 0)
        {
            fillTestdatei();
        }
    }

    public void removeAllMedications()
    {
        repository.deleteAll();
    }

    public void add10Medication()
    {
        repository.save(new PharMed(LocalDate.of(2026, 1, 5), "Merck", "Painkiller", 9.99, 120, true));
        repository.save(new PharMed(LocalDate.of(2026, 1, 8), "Bayer", "Antibiotics", 24.50, 80, false));
        repository.save(new PharMed(LocalDate.of(2026, 1, 12), "Pfizer", "Anti-allergy", 14.90, 65, true));
        repository.save(new PharMed(LocalDate.of(2026, 1, 15), "Novartis", "Nasal Spray", 7.99, 150, true));
        repository.save(new PharMed(LocalDate.of(2026, 1, 18), "Roche", "Painkiller", 12.49, 90, false));

        repository.save(new PharMed(LocalDate.of(2026, 2, 1), "Merck", "Antibiotics", 28.99, 70, false));
        repository.save(new PharMed(LocalDate.of(2026, 2, 3), "Bayer", "Anti-allergy", 16.99, 110, true));
        repository.save(new PharMed(LocalDate.of(2026, 2, 6), "Pfizer", "Nasal Spray", 8.49, 130, true));
        repository.save(new PharMed(LocalDate.of(2026, 2, 9), "Novartis", "Painkiller", 10.99, 95, true));
        repository.save(new PharMed(LocalDate.of(2026, 2, 12), "Roche", "Antibiotics", 32.99, 50, false));

    }

    public void increasePrice()
    {
        for(PharMed p : repository.findAll())
        {
            p.setPrice(p.getPrice()+1.0);
            repository.save(p);
        }

    }

    public void removeRezeptpflicht()
    {
        for (PharMed p: repository.findAll())
        {
            if (p.getRezeptFrei() == false)
            {
                repository.deleteById(p.getMedicationId());
            }
        }

    }

    public void addWrong()
    {
        repository.save(new PharMed(LocalDate.of(2026, 1, 5), "Merck", "Painkiller", 1.0, 120, true));

    }

    public void removeMedId(Long medicationId)
    {
        /*PharMed med = null;

        for(PharMed p :pharMeds)
        {
            if(p.getMedicationId().equals(medicationId))
            {
                p = med;
            }
        }
        pharMeds.remove(med);

        if(pharMeds.removeIf(p-> p.getMedicationId().equals(medicationId)) == false)
            throw new PharMedException("Medication ist nicht vorhanden!");*/
        if(medicationId == null)
            throw new PharMedException("Medication ist nicht vorhanden!");
        if(!repository.existsById(medicationId))
            throw new PharMedException("Medication ID Does not Exists");

        repository.deleteById(medicationId);

    }

    public void add1Med(Long medicationId)
    {
        if(medicationId == null)
            throw new PharMedException("kein Medikament ist vorhanden");
        for(PharMed p : repository.findAll())
        {
            if(p.getMedicationId().equals(medicationId))
            {
                p.setStockQuantity(p.getStockQuantity()+1);
                repository.save(p);
            }
        }
    }

    public void add1Order(PharMed pharMed)
    {
        if(pharMed==null)
        {
            throw new PharMedException("No Medication!!");
        }
        repository.save(pharMed);
    }


    public void fillTestdatei()
    {
        repository.save(new PharMed(LocalDate.of(2026, 1, 5), "Merck", "Painkiller", 9.99, 120, true));
        repository.save(new PharMed(LocalDate.of(2026, 1, 8), "Bayer", "Antibiotics", 24.50, 80, false));
        repository.save(new PharMed(LocalDate.of(2026, 1, 12), "Pfizer", "Anti-allergy", 14.90, 65, true));
        repository.save(new PharMed(LocalDate.of(2026, 1, 15), "Novartis", "Nasal Spray", 7.99, 150, true));
        repository.save(new PharMed(LocalDate.of(2026, 1, 18), "Roche", "Painkiller", 12.49, 90, false));

        repository.save(new PharMed(LocalDate.of(2026, 2, 1), "Merck", "Antibiotics", 28.99, 70, false));
        repository.save(new PharMed(LocalDate.of(2026, 2, 3), "Bayer", "Anti-allergy", 16.99, 110, true));
        repository.save(new PharMed(LocalDate.of(2026, 2, 6), "Pfizer", "Nasal Spray", 8.49, 130, true));
        repository.save(new PharMed(LocalDate.of(2026, 2, 9), "Novartis", "Painkiller", 10.99, 95, true));
        repository.save(new PharMed(LocalDate.of(2026, 2, 12), "Roche", "Antibiotics", 32.99, 50, false));

        repository.save(new PharMed(LocalDate.of(2026, 2, 15), "Merck", "Anti-allergy", 13.49, 140, true));
        repository.save(new PharMed(LocalDate.of(2026, 2, 18), "Bayer", "Nasal Spray", 6.99, 180, true));
        repository.save(new PharMed(LocalDate.of(2026, 2, 21), "Pfizer", "Painkiller", 18.90, 60, false));
        repository.save(new PharMed(LocalDate.of(2026, 2, 24), "Novartis", "Antibiotics", 22.50, 75, false));
        repository.save(new PharMed(LocalDate.of(2026, 2, 27), "Roche", "Anti-allergy", 15.90, 100, true));

        repository.save(new PharMed(LocalDate.of(2026, 3, 2), "Merck", "Nasal Spray", 9.20, 170, true));
        repository.save(new PharMed(LocalDate.of(2026, 3, 5), "Bayer", "Painkiller", 11.49, 125, true));
        repository.save(new PharMed(LocalDate.of(2026, 3, 8), "Pfizer", "Antibiotics", 35.99, 45, false));
        repository.save(new PharMed(LocalDate.of(2026, 3, 11), "Novartis", "Anti-allergy", 17.50, 88, true));
        repository.save(new PharMed(LocalDate.of(2026, 3, 14), "Roche", "Nasal Spray", 7.50, 160, true));

        repository.save(new PharMed(LocalDate.of(2026, 3, 17), "Merck", "Painkiller", 19.99, 55, false));
        repository.save(new PharMed(LocalDate.of(2026, 3, 20), "Bayer", "Antibiotics", 26.90, 85, false));
        repository.save(new PharMed(LocalDate.of(2026, 3, 23), "Pfizer", "Anti-allergy", 12.99, 115, true));
        repository.save(new PharMed(LocalDate.of(2026, 3, 26), "Novartis", "Nasal Spray", 8.90, 145, true));
        repository.save(new PharMed(LocalDate.of(2026, 3, 29), "Roche", "Painkiller", 14.75, 92, true));

        repository.save(new PharMed(LocalDate.of(2026, 4, 2), "Merck", "Antibiotics", 29.90, 68, false));
        repository.save(new PharMed(LocalDate.of(2026, 4, 5), "Bayer", "Anti-allergy", 18.25, 105, true));
        repository.save(new PharMed(LocalDate.of(2026, 4, 8), "Pfizer", "Nasal Spray", 9.99, 135, true));
        repository.save(new PharMed(LocalDate.of(2026, 4, 11), "Novartis", "Painkiller", 13.50, 99, true));
        repository.save(new PharMed(LocalDate.of(2026, 4, 14), "Roche", "Antibiotics", 31.50, 52, false));

    }

    public ArrayList<PharMed> findAll()
    {
        ArrayList<PharMed> clone;
        clone = (ArrayList<PharMed>) repository.findAll();

        return clone;
    }

    @Override
    public String toString()
    {
        return repository.findAll().stream()
                .map(p -> p.toString())
                .collect(Collectors.joining("\n"));
    }
}

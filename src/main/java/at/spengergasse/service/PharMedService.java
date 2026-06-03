package at.spengergasse.service;

import at.spengergasse.domain.PharMed;
import com.vaadin.copilot.shaded.javassist.bytecode.Descriptor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

@Service
public class PharMedService
{
    public static ArrayList<PharMed> pharMeds;

    public PharMedService()
    {
        pharMeds = new ArrayList<>(10000);
        fillTestdatei();
    }

    public static void removeAllMedications()
    {
        pharMeds.clear();
    }

    public static void add10Medication()
    {
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 5), "Merck", "Painkiller", 9.99, 120, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 8), "Bayer", "Antibiotic", 24.50, 80, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 12), "Pfizer", "Anti-allergy", 14.90, 65, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 15), "Novartis", "Nasal Spray", 7.99, 150, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 18), "Roche", "Painkiller", 12.49, 90, false));

        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 1), "Merck", "Antibiotic", 28.99, 70, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 3), "Bayer", "Anti-allergy", 16.99, 110, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 6), "Pfizer", "Nasal Spray", 8.49, 130, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 9), "Novartis", "Painkiller", 10.99, 95, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 12), "Roche", "Antibiotic", 32.99, 50, false));
    }

    public static void increasePrice()
    {
        for(PharMed p : pharMeds)
        {
            p.setPrice(p.getPrice()+1.0);
        }
    }

    public static void removeRezeptpflicht()
    {
       Iterator<PharMed> t;
       t = pharMeds.iterator();

       PharMed p;

       while(t.hasNext())
       {
           p = t.next();

           if(p.getRezeptFrei() == false)
           {
               t.remove();
           }
       }

    }

    public static void addWrong()
    {
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 5), "Merck", "Painkiller", 1.0, 120, true));

    }

    public void fillTestdatei()
    {
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 5), "Merck", "Painkiller", 9.99, 120, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 8), "Bayer", "Antibiotic", 24.50, 80, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 12), "Pfizer", "Anti-allergy", 14.90, 65, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 15), "Novartis", "Nasal Spray", 7.99, 150, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 1, 18), "Roche", "Painkiller", 12.49, 90, false));

        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 1), "Merck", "Antibiotic", 28.99, 70, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 3), "Bayer", "Anti-allergy", 16.99, 110, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 6), "Pfizer", "Nasal Spray", 8.49, 130, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 9), "Novartis", "Painkiller", 10.99, 95, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 12), "Roche", "Antibiotic", 32.99, 50, false));

        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 15), "Merck", "Anti-allergy", 13.49, 140, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 18), "Bayer", "Nasal Spray", 6.99, 180, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 21), "Pfizer", "Painkiller", 18.90, 60, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 24), "Novartis", "Antibiotic", 22.50, 75, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 2, 27), "Roche", "Anti-allergy", 15.90, 100, true));

        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 2), "Merck", "Nasal Spray", 9.20, 170, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 5), "Bayer", "Painkiller", 11.49, 125, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 8), "Pfizer", "Antibiotic", 35.99, 45, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 11), "Novartis", "Anti-allergy", 17.50, 88, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 14), "Roche", "Nasal Spray", 7.50, 160, true));

        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 17), "Merck", "Painkiller", 19.99, 55, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 20), "Bayer", "Antibiotic", 26.90, 85, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 23), "Pfizer", "Anti-allergy", 12.99, 115, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 26), "Novartis", "Nasal Spray", 8.90, 145, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 3, 29), "Roche", "Painkiller", 14.75, 92, true));

        pharMeds.add(new PharMed(LocalDate.of(2026, 4, 2), "Merck", "Antibiotic", 29.90, 68, false));
        pharMeds.add(new PharMed(LocalDate.of(2026, 4, 5), "Bayer", "Anti-allergy", 18.25, 105, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 4, 8), "Pfizer", "Nasal Spray", 9.99, 135, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 4, 11), "Novartis", "Painkiller", 13.50, 99, true));
        pharMeds.add(new PharMed(LocalDate.of(2026, 4, 14), "Roche", "Antibiotic", 31.50, 52, false));

    }

    public static ArrayList<PharMed> findAll()
    {
        ArrayList<PharMed> clone;
        clone = new ArrayList<>(pharMeds);

        return clone;
    }

    @Override
    public String toString()
    {
        return pharMeds.stream()
                .map(p -> p.toString())
                .collect(Collectors.joining("\n"));
    }
}

package at.spengergasse.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PharMedTest
{

    @Test
    void testToString()
    {
        PharMed a = new PharMed(LocalDate.now(), "Merck", "Schmerzmittel", 15.25, 100, false);
        System.out.println(a);
        System.out.println(a.getOrderDate());
        System.out.println(a.getSupplierName());
        a.setSupplierName("Bayer");
        System.out.println(a.getSupplierName());
    }

    @Test
    void testWrongPrice()
    {
        try
        {
            PharMed a = new PharMed(LocalDate.now(), "Merck", "Schmerzmittel", 500.25, 100, false);
            System.out.println(a);
            assertEquals(1,0);
        } catch (PharMedException e)
        {
            System.out.println(e.getMessage());
            assertEquals(1,1);
        }
    }
}
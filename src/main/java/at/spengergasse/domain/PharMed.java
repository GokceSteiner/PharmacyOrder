package at.spengergasse.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = "medicationId", callSuper = false)

@Entity
public class PharMed implements Cloneable
{
    @Id
    private Long medicationId;
    private LocalDate orderDate;
    private String supplierName;
    private String medicationType;
    private Double price;
    private Integer stockQuantity;
    private Boolean rezeptFrei;

    private static final AtomicLong sequence = new AtomicLong(1000);
    private static final String[] medicationTypes = {"Painkiller", "Antibiotic", "Anti-allergy" ,"Nasal Spray"};

    public PharMed()
    {
        // Wir brauchen kein Set MEthode in dem voll Konstruktor!!
        //setMedicationId();
        //setOrderDate(LocalDate.now());
        //setSupplierName("Merck");
        //setMedicationType("Schmerzmittel");
        //setPrice(15.99);
        //setStockQuantity(105);
        //setRezeptFrei(false);
    }

    public PharMed(LocalDate orderDate, String supplierName, String medicationType, Double price, Integer stockQuantity, Boolean rezeptFrei)
    {
        setMedicationId();
        setOrderDate(orderDate);
        setSupplierName(supplierName);
        setMedicationType(medicationType);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setRezeptFrei(rezeptFrei);
    }

    public PharMed(Long medicationId, LocalDate orderDate, String supplierName, String medicationType, Double price, Integer stockQuantity, Boolean rezeptFrei)
    {
        setMedicationId(medicationId);
        setOrderDate(orderDate);
        setSupplierName(supplierName);
        setMedicationType(medicationType);
        setPrice(price);
        setStockQuantity(stockQuantity);
        setRezeptFrei(rezeptFrei);
    }

    public void setMedicationId() // weil Id von AtomicLong vergegeben wird, brauchen wir hier kein parameter
    {
        medicationId = sequence.getAndIncrement();  // get die Nummer und erhöhe
    }

    public void setPrice(Double price)
    {
        if(price<2.0)
        {
            throw new PharMedException("Price is too low");
        }
        if(price>300)
        {
            throw new PharMedException("Price is too high");
        }

        this.price = price;
    }

    public void setMedicationTypes(String medicationType)
    {
        if(!Arrays.asList(medicationTypes).contains(medicationType))
        {
            throw new PharMedException("Unknown medication type!");
        }

        this.medicationType = medicationType;
    }

    @Override
    public PharMed clone()
    {
        return new PharMed(medicationId, orderDate, supplierName,medicationType, price, stockQuantity,rezeptFrei);
    }

}

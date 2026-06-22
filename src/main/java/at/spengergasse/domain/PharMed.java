package at.spengergasse.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "Order Date is required!")
    @Past(message = "Order Date must be in the past!")
    private LocalDate orderDate;
    @NotBlank(message = "Supplier Name is required!")
    private String supplierName;
    @NotNull(message = "Medication Type is required!")
    @Pattern(
            regexp = "Painkiller|Antibiotics|Anti-allergy|Nasal Spray",
            message = "The medication types must be Painkiller, Antibiotics, Anti-allergy and Nasal Spray!"
    )
    private String medicationType;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "2.0", message = "Minimum Price is 2 Euro!")
    @DecimalMax(value = "300.0", message = "Maximum Price is 300 Euro")
    private Double price;
    @NotNull(message = "Stock quantity is required!")
    private Integer stockQuantity;
    @NotNull(message = "Precription Requirement must be registered ")
    private Boolean rezeptFrei;

    private static final AtomicLong sequence = new AtomicLong(1000);

    public PharMed()
    {
        setMedicationId();
        setOrderDate(LocalDate.now());
        setSupplierName("Unknown");
        setMedicationType("Painkiller");
        setPrice(15.99);
        setStockQuantity(105);
        setRezeptFrei(false);
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

        this.medicationType = medicationType;
    }

    @Override
    public PharMed clone()
    {
        return new PharMed(medicationId, orderDate, supplierName,medicationType, price, stockQuantity,rezeptFrei);
    }

}

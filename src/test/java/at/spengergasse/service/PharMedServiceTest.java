package at.spengergasse.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PharMedServiceTest
{

    @Test
    void testToString()
    {
        PharMedService steiner = new PharMedService();
        steiner.fillTestdatei();
        System.out.println(steiner);
    }
}
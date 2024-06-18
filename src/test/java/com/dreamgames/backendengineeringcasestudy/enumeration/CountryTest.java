package com.dreamgames.backendengineeringcasestudy.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {
    @Test
    void enumsTest(){
        Country country1 = Country.Turkey;

        assertEquals(Country.Turkey, country1);
    }

}
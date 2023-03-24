package com.mycompany.bowling;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PlayerNGTest {
    
    private Player instance;
    
    @BeforeMethod
    public void initialize() {
        this.instance = new Player("Pat");
    }
    
    public PlayerNGTest() {
    }

    @Test(dataProvider = "correctDataForGetValueFirstThrowInRound")
    public void getValueFirstThrowInRound_returnsCorrectValues_correctParametersGiven(String s, int expResult) {
        int result = instance.getValueFirstThrowInRound(s);
        assertEquals(result, expResult);
    }
    
    @DataProvider(name = "correctDataForGetValueFirstThrowInRound")
    public Object[][] providerForCorrectDataGetValueFirstThrowInRound() {
        Object[][] data = {
            {"0", 0}, {"5", 5}, {"10", 10}
        };
        return data;
    }
    
    @Test(dataProvider = "incorrectDataForGetValueFirstThrowInRound")
    public void getValueFirstThrowInRound_returnsCorrectValues_incorrectParametersGiven(String s, int expResult) {
        int result = instance.getValueFirstThrowInRound(s);
        assertEquals(result, expResult);
    }
    
    @DataProvider(name = "incorrectDataForGetValueFirstThrowInRound")
    public Object[][] providerForIncorrectDataGetValueFirstThrowInRound() {
        Object[][] data = {
            {"-1", 0}, {"11", 0}, {"5.43", 0},{"/", 0}, {"", 0}, {".0", 0}, {"sqrt", 0}
        };
        return data;
    }


    @Test(dataProvider = "correctDataForGetValueSecondThrowInRound")
    public void getValueSecondThrowInRound_returnsCorrectValues_correctParametrsGiven(String s, int roundNumber, int firstThrow, int expResult) {
        int result = instance.getValueSecondThrowInRound(s, roundNumber, firstThrow);
        assertEquals(result, expResult);
    }
    
    @DataProvider(name = "correctDataForGetValueSecondThrowInRound") 
    public Object[][] providerForCorrectDataGetValueSecondThrowInRound() {
        Object[][] data = {
            //s, roundNumber (liczone od 0 => 0 - 1runda, 9 - 10runda), firstThrow, expResult
            {"0", 0, 10, 0}, {"5", 0, 5, 5}, {"0", 4, 9, 0}, {"1", 4, 9, 1}, {"0", 8, 10, 0}, {"2", 8, 3, 2}, 
            {"3", 8, 7, 3}, {"0", 9, 10, 0}, {"0", 9, 4, 0}, {"8", 9, 2, 8}, {"10", 9, 10, 10}
        };
        return data;
    }
    
    @Test(dataProvider = "incorrectDataForGetValueSecondThrowInRound")
    public void getValueSecondThrowInRound_returnsCorrectValues_incorrectParametrsGiven(String s, int roundNumber, int firstThrow, int expResult) {
        int result = instance.getValueSecondThrowInRound(s, roundNumber, firstThrow);
        assertEquals(result, expResult);
    }
    
    @DataProvider(name = "incorrectDataForGetValueSecondThrowInRound") 
    public Object[][] providerForIncorrectDataGetValueSecondThrowInRound() {
        Object[][] data = {
            //s, roundNumber (liczone od 0 => 0 - 1runda, 9 - 10runda), firstThrow, expResult
            {"-1", 0, 5, 0}, {"11", 0, 5, 0}, {"4", 0, 7, 0}, {"11", 9, 10, 0}, {"-0.5", 9, 6, 0}, {"7", 9, 6, 0}
        };
        return data;
    }

    @Test(dataProvider = "correctDataForGetValueThirdThrowInRound")
    public void getValueThirdThrowInRound_returnsCorrectValues_correctParametersGiven(String s, int secondThrow, int expResult) {
        int result = instance.getValueThirdThrowInRound(s, secondThrow);
        assertEquals(result, expResult);
    }
    
    @DataProvider(name = "correctDataForGetValueThirdThrowInRound") 
    public Object[][] providerForCorrectDataGetValueThirdThrowInRound() {
        Object[][] data = {
            //s, secondThrow, expResult
            {"0", 10, 0}, {"5", 10, 5}, {"10", 10, 10}, {"0", 0, 0}, {"10", 0, 10}, {"4", 6, 4}
        };
        return data;
    }
    
    @Test(dataProvider = "incorrectDataForGetValueThirdThrowInRound")
    public void getValueThirdThrowInRound_returnsCorrectValues_incorrectParametersGiven(String s, int secondThrow, int expResult) {
        int result = instance.getValueThirdThrowInRound(s, secondThrow);
        assertEquals(result, expResult);
    }
    
    @DataProvider(name = "incorrectDataForGetValueThirdThrowInRound") 
    public Object[][] providerForIncorrectDataGetValueThirdThrowInRound() {
        Object[][] data = {
            //s, secondThrow, expResult
            {"-1", 10, 0}, {"11", 10, 0}, {"-5", 5, 0}, {"7", 6, 0} 
        };
        return data;
    }

    @Test(dataProvider = "correctDataForGetCheckedThrowValue")
    public void getCheckedThrowValue_returnsCorrectValues_correctParametrsGiven(String s, int expResult) {
        int result = instance.getCheckedThrowValue(s);
        assertEquals(result, expResult);
    }
    
    @DataProvider(name = "correctDataForGetCheckedThrowValue")
    public Object[][] providerForCorrectDataForGetCheckedThrowValue() {
        Object[][] data = {
            {"0", 0}, {"5", 5}, {"10", 10}
        };
        return data;
    }
     
    @Test(dataProvider = "incorrectDataForGetCheckedThrowValue")
    public void getCheckedThrowValue_returnsCorrectValues_incorrectParametrsGiven(String s, int expResult) {
        int result = instance.getCheckedThrowValue(s);
        assertEquals(result, expResult);
    }
    
    @DataProvider(name = "incorrectDataForGetCheckedThrowValue")
    public Object[][] providerForIncorrectDataForGetCheckedThrowValue() {
        Object[][] data = {
            {"aaa", 0}, {"", 0}, {".", 0}, {",", 0}, {"12e", 0}, {";", 0}
        };
        return data;
    }

    //Dodać testy do makeThrow - sprawdzenie czy dobrze są wywoływane rzuty
    @Test(dataProvider = "correctDataForMakeThrow")
    public void makeThrow_returnsCorrectValues_correctParametersGiven(int roundNumber, int firstThrow, int secondThrow, int expResult) {
        //expResult 0: brak akcji, 1: 10 za pierwszym razem dla kolejki != ostatniej, 2: 1rzut != 10 dla kolejki != ostatniej,
        //3: 1rzut = 10 dla kolejki ostatniej, 4: 1rzut != 10 i 1rzut+2rzut != 10 dla osotaniej kolejki, 5: 1rzut != 10 i 1rzut+2rzut = 10 dla kolejki ostatniej
        //roundNumber 0: 1 runda, 9: 10 runda
        //10 rund
        Player player = new Player(firstThrow, secondThrow) {
            @Override
            public int makeFirstThrowInRound(int roundNumber) {
                return this.getFirstThrowTest();
            } 
            @Override
            public int makeSecondThrowInRound(int roundNumber, int firstThrow) {
                return this.getSecondThrowTest();
            } 
            @Override
            public int makeThirdThrowInRound(int roundNumber, int firstThrow, int secondThrow) {
                return 0;
            }  
        };
        int result = player.makeThrow(roundNumber);
        assertEquals(result, expResult);
    } 
    
    @DataProvider(name = "correctDataForMakeThrow")
    public Object[][] providerForCorrectDataForMakeThrow() {
        Object[][] data = {
            //numer rundy od 0, firstThrow, secondThrow, expResult
            {0, 10, 0, 1}, {0, 8, 1, 2}, {0, 4, 3, 2}, {4, 10, 0, 1}, {4, 0, 10, 2}, {4, 4, 6, 2}, {8, 10, 0, 1}, {8, 0, 10, 2}, {8, 4, 6, 2},
            {9, 0, 0, 4}, {9, 5, 4, 4}, {9, 10, 5, 3}, {9, 4, 6, 5}
        };
        return data;
    }
}
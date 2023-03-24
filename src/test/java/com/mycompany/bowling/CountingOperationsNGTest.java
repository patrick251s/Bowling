package com.mycompany.bowling;

import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;

public class CountingOperationsNGTest {
    
    public CountingOperationsNGTest() {
    }

    @org.testng.annotations.Test(dataProvider = "throwsDataForTotalPoints")
    public void countRoundPoints_returnsCorrectValues_correctParameterGiven(DataForCountingTests data) {
        int totalRoundNumber = 10;
        CountingOperations instance = new CountingOperations();
        int result = instance.countRoundPoints(data.playerThrows, totalRoundNumber, data.possibleThirdThrow);
        assertEquals(result, data.expResult);
    }

    @org.testng.annotations.Test(dataProvider = "throwsDataForOrdinaryPoints")
    public void countOrdinaryPoints_returnsCorrectValue_correctParametersGiven(DataForCountingTests data) {
        int totalRoundNumber = 10;
        CountingOperations instance = new CountingOperations();
        int result = instance.countOrdinaryPoints(data.playerThrows, totalRoundNumber);
        assertEquals(result, data.expResult);
    }

    @org.testng.annotations.Test(dataProvider = "throwsDataForStrike")
    public void countStrike_returnsCorrectValue_correctParametersGiven(DataForCountingTests data) {
        int totalRoundNumber = 10;
        CountingOperations instance = new CountingOperations();
        int result = instance.countStrike(data.playerThrows, totalRoundNumber, data.possibleThirdThrow);
        assertEquals(result, data.expResult);
    }

    @org.testng.annotations.Test(dataProvider = "throwsDataForSpare")
    public void countSpare_returnsCorrectValue_correctParametersGiven(DataForCountingTests data) {
        int totalRoundNumber = 10;
        CountingOperations instance = new CountingOperations();
        int result = instance.countSpare(data.playerThrows, totalRoundNumber, data.possibleThirdThrow);
        assertEquals(result, data.expResult);
    }
    
    @DataProvider(name = "throwsDataForTotalPoints")
    public Object[][] myThrowsProviderForTotalPoints() {
        int[][] throws1 = {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
        int[][] throws2 = {{1, 0}, {3, 7}, {5, 3}, {10, 0}, {8, 1}, {0, 2}, {5, 1}, {4, 0}, {1, 3}, {9, 0}};
        int[][] throws3 = {{7, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}};
        int[][] throws4 = {{10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 3}};
        int[][] throws5 = {{10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 10}};
        
        Object[][] data = {
            {new DataForCountingTests(throws1, 0, 0)},
            {new DataForCountingTests(throws2, 0, 63+5+9)},
            {new DataForCountingTests(throws3, 0, 97+150)},
            {new DataForCountingTests(throws4, 7, 100+160+13+10)},
            {new DataForCountingTests(throws5, 10, 300)}
        };
        return data;
    }
    
    @DataProvider(name = "throwsDataForOrdinaryPoints")
    public Object[][] myThrowsProviderForOrdinaryPoints() {
        int[][] throws1 = {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
        int[][] throws2 = {{1, 0}, {3, 7}, {5, 3}, {10, 0}, {8, 1}, {0, 2}, {5, 1}, {4, 0}, {1, 3}, {9, 0}};
        int[][] throws3 = {{10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}};
        int[][] throws4 = {{10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 10}};
        

        Object[][] data = {
            {new DataForCountingTests(throws1, 0, 0)},
            {new DataForCountingTests(throws2, 0, 63)},
            {new DataForCountingTests(throws3, 0, 100)},
            {new DataForCountingTests(throws4, 5, 100)}
        };
        return data;
    }
    
    @DataProvider(name = "throwsDataForStrike")
    public Object[][] myThrowsProviderForStrike() {
        int[][] throws1 = {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
        int[][] throws2 = {{4, 4}, {5, 5}, {6, 3}, {8, 2}, {9, 1}, {6, 2}, {2, 3}, {3, 3}, {1, 8}, {9, 1}};
        int[][] throws3 = {{10, 0}, {5, 5}, {6, 3}, {10, 0}, {10, 0}, {4, 2}, {2, 3}, {3, 3}, {1, 8}, {7, 2}};
        int[][] throws4 = {{10, 0}, {5, 5}, {6, 3}, {10, 0}, {10, 0}, {4, 2}, {2, 3}, {3, 3}, {1, 8}, {10, 3}};
        int[][] throws5 = {{10, 0}, {5, 5}, {6, 3}, {10, 0}, {10, 0}, {4, 2}, {2, 3}, {3, 3}, {1, 8}, {10, 10}};
        int[][] throws6 = {{10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 8}};
        int[][] throws7 = {{10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 10}};

        Object[][] data = {
            {new DataForCountingTests(throws1, 0, 0)},
            {new DataForCountingTests(throws2, 5, 0)},
            {new DataForCountingTests(throws3, 0, 30)},
            {new DataForCountingTests(throws4, 3, 36)},
            {new DataForCountingTests(throws5, 3, 43)},
            {new DataForCountingTests(throws6, 1, 187)},
            {new DataForCountingTests(throws7, 10, 200)}
        };
        return data;
    }
    
    @DataProvider(name = "throwsDataForSpare")
    public Object[][] myThrowsProviderForSpare() {
        int[][] throws1 = {{10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}, {10, 0}};
        int[][] throws2 = {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
        int[][] throws3 = {{4, 5}, {5, 1}, {2, 7}, {5, 0}, {9, 0}, {10, 0}, {10, 0}, {5, 1}, {1, 2}, {5, 1}};
        int[][] throws4 = {{4, 5}, {5, 1}, {2, 7}, {5, 0}, {9, 0}, {10, 0}, {10, 0}, {5, 1}, {1, 2}, {5, 5}};
        int[][] throws5 = {{5, 5}, {5, 1}, {2, 8}, {9, 0}, {9, 0}, {10, 0}, {4, 6}, {5, 1}, {1, 2}, {5, 3}};
        int[][] throws6 = {{5, 5}, {5, 1}, {2, 8}, {9, 0}, {9, 0}, {10, 0}, {4, 6}, {5, 1}, {1, 2}, {5, 5}};
        Object[][] data = {
            {new DataForCountingTests(throws1, 0, 0)},
            {new DataForCountingTests(throws2, 0, 0)},
            {new DataForCountingTests(throws3, 0, 0)},
            {new DataForCountingTests(throws4, 0, 0)},
            {new DataForCountingTests(throws5, 0, 19)},
            {new DataForCountingTests(throws6, 6, 25)}
        };
        return data;
    }
    
    public class DataForCountingTests {
        public int[][] playerThrows;
        public int possibleThirdThrow;
        public int expResult;

        public DataForCountingTests(int[][] playerThrows, int possibleThirdThrow, int expResult) {
            this.playerThrows = playerThrows;
            this.possibleThirdThrow = possibleThirdThrow;
            this.expResult = expResult;
        }
        
    }
}
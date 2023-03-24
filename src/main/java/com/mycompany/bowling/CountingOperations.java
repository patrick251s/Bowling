package com.mycompany.bowling;

public class CountingOperations {
    public int countRoundPoints(int[][] playerThrows, int totalRoundNumber, int possibleThirdThrow) {
        int score = 0;
        //roundNumber => od 0 do 9
        //playerThrows => domyślne wartości 0
        
        score += this.countOrdinaryPoints(playerThrows, totalRoundNumber);
        score += this.countStrike(playerThrows, totalRoundNumber, possibleThirdThrow);
        score += this.countSpare(playerThrows, totalRoundNumber, possibleThirdThrow);
        
        return score;
    }
    
    public int countOrdinaryPoints(int[][] playerThrows, int totalRoundNumber) {
        int points = 0;
        for(int i=0; i<playerThrows.length; i++) {
            //Kiedy w ostatniej rundzie, pierwszy rzut = 10p, wtedy 2 i 3 rzut liczony do strike
            if(totalRoundNumber == i+1 && playerThrows[i][0] == 10) {
                points += playerThrows[i][0];
            }
            else {
                points += playerThrows[i][0] + playerThrows[i][1];
            }  
        }
        return points;
    }
    
    public int countStrike(int[][] playerThrows, int totalRoundNumber, int possibleThirdThrow) {
        int strikeScore = 0;
        //Sprawdzamy dla każdej rundy
        for(int i=0; i<playerThrows.length; i++) {
            //Kiedy pierwszy rzut wynosi 10
            if(playerThrows[i][0] == 10) {
                //Kiedy jest ostatnia runda
                if(i == totalRoundNumber-1) {
                    strikeScore += playerThrows[i][1] + possibleThirdThrow;
                }
                //Kiedy jest przedostatnia runda
                else if(i == totalRoundNumber-2) {
                    strikeScore += playerThrows[i+1][0] + playerThrows[i+1][1];
                }              
                else {
                    //Kiedy następny rzut wynosi 10 - sumujemy wynik tego rzutu i następnego
                    if(playerThrows[i+1][0] == 10) {
                        strikeScore += playerThrows[i+1][0] + playerThrows[i+2][0];
                    }
                    //Kiedy rzut nie wynosi 10 - sumujemy ten rzut i kolejny w tej samej rundzie
                    else {
                        strikeScore += playerThrows[i+1][0] + playerThrows[i+1][1];
                    }
                }
            }
        }
        return strikeScore;
    }
    
    public int countSpare(int[][] playerThrows, int totalRoundNumber, int possibleThirdThrow) {
        //Spare to suma rzutów = 10, gdzie pierwszy rzut jest różny od 10, bierzemy wartość kolejnego rzutu
        int spareScore = 0;
        //Sprawdzamy dla każdej rozegranej rundy
        for(int i=0; i<playerThrows.length; i++) {
            if(playerThrows[i][0] != 10 && playerThrows[i][0]+playerThrows[i][1] == 10) {
                //Kiedy jest ostatnia runda
                if(i == totalRoundNumber-1) {
                    spareScore += possibleThirdThrow;
                }
                else {
                    spareScore += playerThrows[i+1][0];
                }
            }
        }
        return spareScore;
    }
}

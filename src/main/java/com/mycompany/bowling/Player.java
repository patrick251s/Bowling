package com.mycompany.bowling;

import java.util.Scanner;


public class Player {
    public int[][] playerThrows;
    private int possibleThirdThrow;
    private int playerScore;
    private String playerTable;
    private String playerName;
    private int totalRoundNumber;
    
    //Pola do testów
    private int firstThrowTest;
    private int secondThrowTest;
    
    public Player(String playerName, int roundNumber) {
        this.playerThrows = new int[roundNumber][2]; //[0][0]-pierwsza runda pierwszy rzut, [0][1]-pierwsza runda drugi rzut
        this.possibleThirdThrow = 0;
        this.playerScore = 0;
        this.playerName = playerName;
        this.totalRoundNumber = roundNumber;
    }
    
    public Player(String playerName) {
        this.playerThrows = new int[10][2]; //[0][0]-pierwsza runda pierwszy rzut, [0][1]-pierwsza runda drugi rzut
        this.possibleThirdThrow = 0;
        this.playerScore = 0;
        this.playerName = playerName;
        this.totalRoundNumber = 10;
    }
    
    //Konstruktor do testów
    public Player(int firstThrowTest, int secondThrowTest) {
        this.playerThrows = new int[10][2];
        this.firstThrowTest = firstThrowTest;
        this.secondThrowTest = secondThrowTest;
        this.possibleThirdThrow = 0;
        this.totalRoundNumber = 10;
    }

    public int getPossibleThirdThrow() {
        return possibleThirdThrow;
    }

    public void setPossibleThirdThrow(int possibleThirdThrow) {
        this.possibleThirdThrow = possibleThirdThrow;
    }

    public String getPlayerTable() {
        return playerTable;
    }

    public void setPlayerTable(String playerTable) {
        this.playerTable = playerTable;
    }
    
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTotalRoundNumber() {
        return totalRoundNumber;
    }

    public void setTotalRoundNumber(int totalRoundNumber) {
        this.totalRoundNumber = totalRoundNumber;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getFirstThrowTest() {
        return firstThrowTest;
    }

    public void setFirstThrowTest(int firstThrowTest) {
        this.firstThrowTest = firstThrowTest;
    }

    public int getSecondThrowTest() {
        return secondThrowTest;
    }

    public void setSecondThrowTest(int secondThrowTest) {
        this.secondThrowTest = secondThrowTest;
    }
     
    public int makeFirstThrowInRound(int roundNumber) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player "+this.getPlayerName()+" first throw:");
        String s = in.nextLine();
        int score = this.getValueFirstThrowInRound(s);
        return score;
    }
    
    public int getValueFirstThrowInRound(String s) {
        int score = this.getCheckedThrowValue(s);
        //Sprawdzenie poprawności pierwszego rzutu
        if(score < 0 || score > 10) {
            score = 0;
        } 
        return score;
    }
    
    public int makeSecondThrowInRound(int roundNumber, int firstThrow) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player "+this.getPlayerName()+" second throw:");
        String s = in.nextLine();
        int score = this.getValueSecondThrowInRound(s, roundNumber, firstThrow);
        
        return score;
    }
    
    public int getValueSecondThrowInRound(String s, int roundNumber, int firstThrow) {
        int score = this.getCheckedThrowValue(s); 
        
        //Sprawdzenie poprawności wyników, szukamy błędnych wariantów i wtedy zerujemy wynik
        //Dla ostatniej kolejki
        if(this.getTotalRoundNumber() == roundNumber+1 && firstThrow == 10) {
            if(score < 0 || score > 10) {
                score = 0;
            }
        }
        //Dla wszystkich nieostatnich kolejek i dla ostatniej kolejki jeśli pierwszy rzut był różny od 0
        else {
            if(score < 0 || score > 10 || firstThrow+score > 10) {
                score = 0;
            }
        }  
        return score;   
    }
    
    public int makeThirdThrowInRound(int roundNumber, int firstThrow, int secondThrow) {
        Scanner in = new Scanner(System.in);
        System.out.println("Player "+this.getPlayerName()+" third throw:");
        String s = in.nextLine();
        int score = this.getValueThirdThrowInRound(s, secondThrow);
           
        return score; 
    }
    
    public int getValueThirdThrowInRound(String s, int secondThrow) {
        int score = this.getCheckedThrowValue(s);
        
        //Sprawdzenie poprawności trzeciego rzutu
        if(secondThrow == 10 && (score < 0 || score > 10)) {
            score = 0;
        }
        else if(secondThrow != 10 && (score < 0 || score > 10 || secondThrow+score > 10)) {
            score = 0;
        }
        return score;
    }
    
    public int getCheckedThrowValue(String s) {
        int score;
        try {
            score = Integer.parseInt(s);    
        }
        catch(NumberFormatException e) {
            score = 0;
        }
        return score;
    }
    
    public int makeThrow(int roundNumber) { 
        int scenarioResult = 0;
        int firstThrow = this.makeFirstThrowInRound(roundNumber);
        this.playerThrows[roundNumber][0] = firstThrow;
        int secondThrow;
        //Ostatnia kolejka
        if(roundNumber == this.getTotalRoundNumber()-1) { 
            //Jeśli gracz w ostatniej kolejce wyrzucił za pierwszym razem 10 - ma dwa dodatkowe rzuty
            if(this.playerThrows[roundNumber][0] == 10) {
                secondThrow = this.makeSecondThrowInRound(roundNumber, firstThrow);
                this.playerThrows[roundNumber][1] = secondThrow;
                this.possibleThirdThrow = this.makeThirdThrowInRound(roundNumber, firstThrow, secondThrow);
                scenarioResult = 3;
            }
            //Jeśli w graz w ostatniej kolejce za pierwszym razem nie wyrzucił 10 - rzuca drugi raz
            else if(this.playerThrows[roundNumber][0] != 10) {
                secondThrow = this.makeSecondThrowInRound(roundNumber, firstThrow);
                this.playerThrows[roundNumber][1] = secondThrow;
                scenarioResult = 4;
                //Jeśli suma pierwszego rzutu (różnego od 10) i drugiego rzutu == 10 - rzuca trzeci raz
                if(this.playerThrows[roundNumber][0]+this.playerThrows[roundNumber][1] == 10) {
                    this.possibleThirdThrow = this.makeThirdThrowInRound(roundNumber, firstThrow, secondThrow);
                    scenarioResult = 5;
                }
            } 
        }
        // Wszystkie kolejki nieostatnie
        else {
            //Jeśli pierwszy rzut był równy 10
            if(this.playerThrows[roundNumber][0] == 10) {
                this.playerThrows[roundNumber][1] = 0;
                scenarioResult = 1;
            }
            else {
                this.playerThrows[roundNumber][1] = this.makeSecondThrowInRound(roundNumber, firstThrow);
                scenarioResult = 2;
            }
        }
      
        //Liczymy punkty w ostatniej rundzie dla gracza
        if(this.getTotalRoundNumber() == roundNumber+1) {
            CountingOperations co = new CountingOperations();
            this.setPlayerScore(co.countRoundPoints(this.playerThrows, this.getTotalRoundNumber(), this.getPossibleThirdThrow()));
        }
        
        return scenarioResult;
    }
    
    public static void showResultTable(Player p1, Player p2, int roundNumber) {
        String header = "   |";
        for(int i=0; i<p1.getTotalRoundNumber(); i++) {
            header += " R"+Integer.toString(i+1)+"  |";
        }
        
        //W ostatniej rundzie wyświetlamy wyniki
        if(p1.getTotalRoundNumber() == roundNumber+1) {
            header += "  Total Points";
        }
        
        String firstPlayerRow = Player.getPlayerRowInResultTable(p1, roundNumber);
        String secondPlayerRow = Player.getPlayerRowInResultTable(p2, roundNumber);
       
        System.out.println(header);
        System.out.println(firstPlayerRow);
        System.out.println(secondPlayerRow);
    }
    
    public static String getPlayerRowInResultTable(Player p, int roundNumber) {
        String playerRow = p.getPlayerName()+"|";

        for(int i=0; i<p.playerThrows.length; i++) {
            //Kiedy jest Runda 10 (ostatnia)
            if(i == p.getTotalRoundNumber()-1) { 
                //Jeśli gracz rzucił strike lub spare w ostatniej rundzie - wyświetlamy 3 liczby
                if(p.playerThrows[i][0] == 10 || (p.playerThrows[i][0] != 10 && p.playerThrows[i][0]+p.playerThrows[i][1] == 10)) {
                    //Jeśli w ostatniej kolejce, gracz rzucił 10 i 10, piszemy 10 10 x
                    playerRow += " "+Integer.toString(p.playerThrows[i][0])+" "+Integer.toString(p.playerThrows[i][1])+" "+Integer.toString(p.getPossibleThirdThrow())+"| ";
                }
                else {
                    playerRow += " "+Integer.toString(p.playerThrows[i][0])+" "+Integer.toString(p.playerThrows[i][1])+"  | ";
                } 
            }
            else {
                // Jeśli pierwszy rzut to 10, piszemy x dla drugiego, dla nieostatniej rundy
                if(p.playerThrows[i][0] == 10) { 
                    playerRow += " "+Integer.toString(p.playerThrows[i][0])+" x|";
                }
                else {
                    playerRow += " "+Integer.toString(p.playerThrows[i][0])+" "+Integer.toString(p.playerThrows[i][1])+" |";
                }
            }       
        }
        
        //W ostatniej rundzie doklejamy do napisu wynik
        if(p.getTotalRoundNumber() == roundNumber+1) {
            playerRow += Integer.toString(p.getPlayerScore());
        }
        
        return playerRow;
    }
      
}

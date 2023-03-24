package com.mycompany.bowling;

public class Bowling {

    public static void main(String[] args) {
        System.out.println("Let's play bowling!");
        System.out.println("-----------------------------");
        
        final int roundNumber = 10;
        Player p1 = new Player("Pat");
        Player p2 = new Player("Mat");
        
        for(int i=0; i<roundNumber; i++) {
            System.out.println("Round "+Integer.toString(i+1));
            p1.makeThrow(i);
            p2.makeThrow(i);
            System.out.println("----------------------------------------------------------------------");
            Player.showResultTable(p1, p2, i);
            System.out.println("----------------------------------------------------------------------");
        } 
    }
}
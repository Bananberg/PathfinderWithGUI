package com.company;

public class CombatSimulator {

    Character char1;
    Character char2;
    InitiativeTracker initiativeTracker = new InitiativeTracker();

    public CombatSimulator(Character char1, Character char2) {
        this.char1 = char1;
        initiativeTracker.add(this.char1);
        this.char2 = char2;
        initiativeTracker.add(this.char2);

        System.out.println(this.char1.toString());
        System.out.println(this.char2);

    }

    public void runCombat() {
        //contestants turas om att slå varandra, tills en dör.
        while (runningCombat()) {
            char1.tryAttack(char2);
            char2.tryAttack(char1);
            System.out.println("\n");

        }
    }

    private boolean runningCombat() {
        if (char1.isDead() || char2.isDead()) {
            return false;
        }
        return true;
    }
}

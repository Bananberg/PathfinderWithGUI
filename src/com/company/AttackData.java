package com.company;

// Class containing any interesting info about
// an attempted attack by one character against another
public class AttackData {
    public boolean hit = false;
    public int attackRoll;
    public int dmg;
    public String attacker;

    public String target;
    public int targetAC;

    //primarily for testing purposes.
    public boolean couldAttack = false;

    public AttackData(String attacker, String target){
        this.attacker = attacker;
        this.target = target;
    }
}

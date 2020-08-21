package com.company;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CharacterTest {


    @Test
    public void testIsDead() {
        Character charDead = new Character.Builder(new int[]{18, 16, -14000, 12, 10, 8})
                .build();
        Character charAlive = new Character.Builder(new int[]{18, 16, 14000, 12, 10, 8})
                .build();
        Assert.assertEquals(charAlive.isDead(), false);

        Assert.assertEquals(charDead.isDead(), true);
    }

    @Test
    public void testReceiveDmg() {
        Character charAlive = new Character.Builder(new int[]{18, 16, 10, 12, 10, 8})
                .build();
        int skada = 2;
        int x = charAlive.getCurrentHp();
        charAlive.receiveDmg(skada);
        int y = charAlive.getCurrentHp();
        Assert.assertEquals(x, y + skada);


    }

    @Test
    public void testTryAttack() {
        Character charAlive = new Character.Builder(new int[]{18, 16, 10, 12, 10, 8})
                .build();
        Character charDead = new Character.Builder(new int[]{18, 16, -14000, 12, 10, 8})
                .build();

        testCanAttack(charAlive, charDead);

        //testing to hit a muvvafucka
        testingRollToHit(charAlive);
    }

    private void testingRollToHit(Character charAlive) {
        Character charTarget = new Character.Builder(new int[]{18, 16, 10, 12, 10, 8})
                .setName("Min Bästa Vän")
                .build();

        boolean belowTested = false, onTester = false, overTested = false;
        while(!belowTested || !onTester || !overTested){
            AttackData ad = charAlive.tryAttack(charTarget);
            System.out.println("AttackRoll: " + ad.attackRoll + " TargetAC: " + ad.targetAC);
            if(!belowTested && ad.attackRoll < ad.targetAC){
                System.out.println("Asserting below");
                assertEquals(ad.hit, false);
                belowTested = true;
            }
            if(!onTester && ad.attackRoll == ad.targetAC){
                System.out.println("Asserting On");
                assertEquals(ad.hit, true);
                onTester = true;
            }
            if(!overTested && ad.attackRoll > ad.targetAC){
                System.out.println("Asserting Over");
                assertEquals(ad.hit, true);
                overTested = true;
            }
        }
    }

    private void testCanAttack(Character charAlive, Character charDead) {
        System.out.println("Testing if could attack when dead...");
        AttackData attackData = charDead.tryAttack(charDead);
        assertEquals(attackData.couldAttack, false);

        System.out.println("Testing attacking when alive...");
        attackData = charAlive.tryAttack(charAlive);
        assertEquals(attackData.couldAttack, true);
    }
}
package com.company;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.company.DieRoller.rollDie;


public class PathfinderTest {

    Character char1;
    Character char2;

    @BeforeTest
    public void doBeforeTest() {
          char1 = new Character.Builder(new int []{9, 7 , 5, 3, 1, 0})
                .setName("Yxmördar-Kalle")
              .build();

        char2 = new Character.Builder(new int[]{10, 12, 14, 16, 18, 20})
                .setName("Yxmördar-Greger")
                .build();
    }

    @Test
    public void characterbuild() {
        System.out.println(char2);
    }

    @Test
    public void testmetod() {
        for (int i = 0; i < 10; i++) {
            System.out.println(rollDie(6));
        }
    }

    @Test
    public void combatSimulatorTest() {
        CombatSimulator combatsimulator = new CombatSimulator(char1, char2);
        combatsimulator.runCombat();
    }

    @Test
    public void oneCombatRoundTest() {
        while (activeCombat(char1, char2)) {
            //En metod for en runda som görs om och om igen
            char1.tryAttack(char2);
            char2.tryAttack(char1);
            System.out.println("\n");
        }

    }

    private boolean activeCombat(Character cont1, Character cont2) {
        return !cont1.isDead() && !cont2.isDead();
    }

    @Test
    public void testAttributes() {
        System.out.println(char1);
    }
}

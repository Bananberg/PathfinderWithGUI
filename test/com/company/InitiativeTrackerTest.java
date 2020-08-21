package com.company;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.*;

public class InitiativeTrackerTest {

    InitiativeTracker initLista = new InitiativeTracker();

    @BeforeMethod
    public void setUp() {
        initLista = new InitiativeTracker();
    }

    @Test
    public void testAdd() {
        for (int i = 0; i < 10; i++) {
            int[] statArray = new int[]{
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest()
            };
            Character c = new Character.Builder(statArray).build();
            initLista.add(c);
        }

        System.out.println(initLista);
        ;
    }

    @Test
    public void testRemove() {
        UUID idToRemove = null;
        Character c = null;
        for (int i = 0; i < 5; i++) {
            int[] statArray = new int[]{
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest(),
                    DieRoller.roll4D6DropLowest()
            };
            c = new Character.Builder(statArray).build();
            initLista.add(c);
            idToRemove = c.getId();
        }
        System.out.println("ID that should be missing: " + idToRemove.toString());
        initLista.remove(c);
        for (InitData id : initLista.initOrder) {
            if(id.id.equals(idToRemove)){
                System.out.println("ERROR FOUND ID THAT SHOULD BE GONE WTF");
            }
        }
        System.out.println(initLista);
    }
}
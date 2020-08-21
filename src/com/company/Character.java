package com.company;

import java.util.HashMap;
import java.util.UUID;

import static com.company.DieRoller.rollDie;

public class Character extends CharacterData {

    private String name = "Yxmördar-Jonny";
    private SecondaryStats secondaryStats;
    private AttackData attackData;
    private UUID id;
    //private Attributes attributes;

    private HashMap<Attribute.AttributeName, Attribute> attributes;

    public static class Builder {
        String name;
        HashMap<Attribute.AttributeName, Attribute> attributes;

        private void setAttributes(int[] attributes) {
            if (this.attributes == null) {
                this.attributes = new HashMap<>();
            }
            this.attributes.put(Attribute.AttributeName.STR, new Attribute("Strength", attributes[0]));
            this.attributes.put(Attribute.AttributeName.DEX, new Attribute("Dexterity", attributes[1]));
            this.attributes.put(Attribute.AttributeName.CON, new Attribute("Constitution", attributes[2]));
            this.attributes.put(Attribute.AttributeName.INT, new Attribute("Wisdom", attributes[3]));
            this.attributes.put(Attribute.AttributeName.WIS, new Attribute("Intelligence", attributes[4]));
            this.attributes.put(Attribute.AttributeName.CHA, new Attribute("Charisma", attributes[5]));

        }


        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Character build() {
            Character character = new Character();
            if (this.name != null && !this.name.isEmpty()) {
                character.name = this.name;
            }
            character.attributes = attributes;
            character.secondaryStats = new SecondaryStats(attributes, 1);
            return character;
        }

        public Builder(int[] attributes) {
            if (attributes.length != 6) {
                System.out.println("ERROR! attributes need to be a int[6]");
                setAttributes(new int[]{10, 10, 10, 10, 10, 10});
            } else {
                setAttributes(attributes);
            }
        }
    }


    private Character() {
        id = UUID.randomUUID();
    }


    public boolean isDead() {
        return secondaryStats.currentHp < 0;
    }

    public void receiveDmg(int dmgRoll) {
        secondaryStats.currentHp = secondaryStats.currentHp - dmgRoll;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", \nattributes=" + attributes +
                ", \nsecondarystats" + secondaryStats +
                '}';
    }

    public AttackData tryAttack(Character target) {
        attackData = new AttackData(this.name, target.name);

        if (canAttack()) {
            attack(target);
        }

        return attackData;
    }

    private AttackData attack(Character target) {
        AttackData attackData = new AttackData(this.name, target.name);
        if (rollToHit(target.getAC())) {
            dealDamage(target);
        }
        return attackData;
    }

    private boolean rollToHit(int targetAC) {
        attackData.attackRoll = rollDie(20) + attributes.get(Attribute.AttributeName.STR).mod;
        attackData.hit = attackData.attackRoll >= targetAC;
        attackData.targetAC = targetAC;
        return attackData.hit;
    }

    private void dealDamage(Character target) {
        attackData.dmg = rollDamage();
        target.receiveDmg(attackData.dmg);
    }


    private int rollDamage() {
        int dmgRoll = rollDie(5) + attributes.get(Attribute.AttributeName.STR).mod;
        System.out.println(this.name + " HIT! " + dmgRoll + " damage!");
        return dmgRoll;
    }

    private boolean canAttack() {
        attackData.couldAttack = !this.isDead();
        return attackData.couldAttack;
    }

    public int getCurrentHp() {
        return secondaryStats.currentHp;
    }

    public int getInit() {
        return secondaryStats.init;
    }

    public int getAC() {
        return secondaryStats.AC;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}

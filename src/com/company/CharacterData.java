package com.company;

import java.util.HashMap;

public class CharacterData {

    protected static class SecondaryStats {
        int maxHp;
        int currentHp;
        int AC;
        int baseToHit;
        int init;

        public SecondaryStats(HashMap<Attribute.AttributeName, Attribute> attributes, int hitDie) {
            setMaxHp(attributes.get(Attribute.AttributeName.CON).mod, hitDie);
            setCurrentHp(maxHp);
            setAC(attributes.get(Attribute.AttributeName.DEX).mod);
            setBaseToHit(attributes.get(Attribute.AttributeName.STR).mod); //här kan vi checka mot vapen någon gång typ? kanske?
            setInit(attributes.get(Attribute.AttributeName.DEX).mod);
        }

        public void setMaxHp(int conMod, int hitDie) {
            maxHp = conMod + hitDie;
        }

        public void setCurrentHp(int currentHp) {
            this.currentHp = currentHp;
        }

        public void setAC(int dexMod) {
            this.AC = 10 + dexMod;
        }

        public void setInit(int dexMod) {
            this.init = dexMod;
        }

        public void setBaseToHit(int hitMod) {
            this.baseToHit = hitMod;
        }

        @Override
        public String toString() {
            return "SecondaryStats{" +
                    "maxHp=" + maxHp +
                    ", currentHp=" + currentHp +
                    ", AC=" + AC +
                    ", baseToHit=" + baseToHit +
                    '}';
        }
    }

    protected static class Attribute {
        String name;
        int score;
        int mod;

        enum AttributeName {STR, DEX, CON, WIS, INT, CHA}

        ;

        public Attribute(String name, int score) {
            this.name = name;
            this.score = score;
            attrModCalc();
        }

        private void attrModCalc() {
            if (score >= 10) {
                mod = (score - 10) / 2;
            } else {
                mod = (score - 11) / 2;
            }
        }

        @Override
        public String toString() {
            return "Attribute{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    ", mod=" + mod +
                    '}';
        }
    }
}

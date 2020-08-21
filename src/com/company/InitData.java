package com.company;

import java.util.Objects;
import java.util.UUID;

public class InitData implements Comparable<InitData> {
    public int init;
    public String name;
    public UUID id;
    public int initMod;

    public InitData(Character char1) {
        this.init = char1.getInit() + DieRoller.rollDie(20);
        this.name = char1.getName();
        this.id = char1.getId();
        this.initMod = char1.getInit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InitData initData = (InitData) o;
        return id == initData.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(InitData other) {
        if (this.init == other.init) {
            if (this.initMod + DieRoller.rollDie(20) > other.initMod + DieRoller.rollDie(20)) {
                return -1;
            }
            return 1;
        }
        if (this.init > other.init) {
            return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "InitData{" +
                "init=" + init +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", initMod=" + initMod +
                "}\n";
    }
}

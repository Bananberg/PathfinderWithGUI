package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitiativeTracker {
    ArrayList<InitData> initOrder = new ArrayList<>();


    public void add(Character character){
        InitData initData= new InitData(character);
        initOrder.add(initData);
        Collections.sort(initOrder);
    }

    public void remove(Character character){
        InitData id = new InitData(character);
        initOrder.remove(id);
    }
    //removeContestant
    //delay


    @Override
    public String toString() {
        return "InitiativeTracker{" +
                "initOrder=" + initOrder +
                '}';
    }
}

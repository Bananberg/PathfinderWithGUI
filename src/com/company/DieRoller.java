package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class DieRoller {
    private static Random random = new Random();

    public static int rollDie(int i) {
        //nextInt är 0-inclusive och bound-inclusive men vi vill ha ett värde mellan 1 och i
        return random.nextInt(i - 1) + 1;
    }

    public static int roll4D6DropLowest(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            arrayList.add(rollDie(6));
        }
        Collections.sort(arrayList);
        arrayList.remove(0);
        return arrayList.stream().mapToInt(Integer::intValue).sum();
    }

}

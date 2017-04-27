package app.classes.other;

import app.classes.rulesGaming.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelpClass {
    private HelpClass() {
        //do not use
    }

    public static int StringToInt(Object object) {
        return Integer.valueOf((String) object);
    }
    public static int LongToInt(Object object){
        return Math.toIntExact((Long) object);
    }
    public static List<Integer> random(int size) {
        List<Integer> numbers = new ArrayList<>(size);
        int index = 0;
        while (index++ < size) {

            int in = new Random().nextInt(50);
            while (in == 0)
                in = new Random().nextInt(50);
            numbers.add(in);
        }
        return numbers;
    }
    public static Card copyCard(Card card){
        return new Card(card.getId(),card.getDamage(),card.getDescription(),card.getRare(),card.getColor(),card.getType());
    }
}

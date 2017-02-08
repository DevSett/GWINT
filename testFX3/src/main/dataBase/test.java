package main.dataBase;

import java.io.*;
import java.util.Random;

/**
 * Created by kills on 16.01.2017.
 */

public class test {
    private static int randomNumber() {
        int min = 100000000;
        int max = 999999999;
        Random rnd = new Random(System.currentTimeMillis());
        return min + rnd.nextInt(max - min + 1);
    }

    private static int randomNumber2() {
        int min = 0;
        int max = 1;
        Random rnd = new Random(System.currentTimeMillis());
        return min + rnd.nextInt(max - min + 1);
    }

    private static BufferedReader myfile;

    private static int indexSub=0;
    public static void main(String[] args) throws Exception {
        myfile = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/database/words.txt"), "windows-1251"));
        DataBase base = new DataBase();

        for (int index = 1; index <= 20; index++) {
            base.insertColumnFakultet(myfile.readLine(), myfile.readLine(), myfile.readLine(), String.valueOf(randomNumber()), myfile.readLine(), myfile.readLine());
            System.out.println("+Fakult");
            for (int indexKaf = 1; indexKaf <= 5; indexKaf++) {
                base.insertColumnKafedra(index, myfile.readLine(), myfile.readLine(), myfile.readLine(), String.valueOf(randomNumber()), myfile.readLine(), myfile.readLine());
                System.out.println("+Kafedra");
                indexSub+=1;
                for (int indexSpecialnost = 1; indexSpecialnost <= 5; indexSpecialnost++) {
                    base.insertColumnSpecialnost(indexSub, myfile.readLine(), myfile.readLine());
                    System.out.println("+Specialnost");
                }
            }
        }
        for (int index = 1; index <= 1000; index++) {
            boolean sex = false;
            if (randomNumber2() == 1) sex = true;
            base.insertColumnStudent(myfile.readLine(), myfile.readLine(), myfile.readLine(), String.valueOf(randomNumber()), myfile.readLine(), sex);
            System.out.println("+Students " + index);
        }


        //        base.createDB(tables[0]);
//        base.createDB(tables[1]);
//        base.insertColumnFakultet("Иванов", "Ленина", "123", "312", "231", "123");
//        base.insertColumnFakultet("Иванов", "Ленина", "123", "312", "231", "123");
//
//        base.insertColumnKafedra(1, "asd", "dsa", "fas", "fas", "fsa", "fsa");
//        base.insertColumnKafedra(1, "asd", "dsa", "fas", "fas", "fsa", "fsa");
//        base.insertColumnKafedra(1, "asd", "dsa", "fas", "fas", "fsa", "fsa");
//        base.insertColumnKafedra(1, "asd", "dsa", "fas", "fas", "fsa", "fsa");
//        base.deleteColumnFakultet(1);
////        base.deleteColumnKafedra(1);
//
//        HashMap map1 = base.findKafedrByID(1);
//        System.out.println(map1.get("id"));
//        System.out.println(map1.get("id_Факультета"));
//                  base.insertColumnStudent("Вася","Иванов","Иванович","512126","Ленина 1",true);
//      base.insertColumnFakultet("ФМФ","fasfasf","fasfasf","f412421","asg232","gasgasg");
//        base.insertColumnFakultet("Пимно","fasfasf","fasfasf","f412421","asg232","gasgasg");
//        base.insertColumnFakultet("ФИЯ","fasfasf","fasfasf","f412421","asg232","gasgasg");
//        base.insertColumnKafedra(1,"ФМФ_1","fasfasf","fasfsfsa","124124","fgagawegw","gasgsa");
//        base.insertColumnKafedra(2,"Пимно_1","fasfasf","fasfsfsa","124124","fgagawegw","gasgsa");
//        base.insertColumnKafedra(3,"Фия_1","fasfasf","fasfsfsa","124124","fgagawegw","gasgsa");
//        base.insertColumnKafedra(1,"ФМФ_2","fasfasf","fasfsfsa","124124","fgagawegw","gasgsa");
//        base.insertColumnKafedra(2,"Пимно_2","fasfasf","fasfsfsa","124124","fgagawegw","gasgsa");
//        base.insertColumnKafedra(3,"Фия_2","fasfasf","fasfsfsa","124124","fgagawegw","gasgsa");
//        base.insertColumnKafedra(1,"ФМФ_3","fasfasf","fasfsfsa","124124","fgagawegw","gasgsa");
//        base.insertColumnKafedra(2,"Пимно_3","fasfasf","fasfsfsa","124124","fgagawegw","gasgsa");
//        base.insertColumnKafedra(3,"Фия_3","fasfasf","fasfsfsa","124124","fgagawegw","gasgsa");
//
//        base.insertColumnSpecialnost(1,"Мат._1","Специалитет");
//        base.insertColumnSpecialnost(2,"ПедП._1","Специалитет");
//        base.insertColumnSpecialnost(3,"ПедА_1.","Магистратура");
//        base.insertColumnSpecialnost(4,"Мат._2","Специалитет");
//        base.insertColumnSpecialnost(5,"ПедП._2","Специалитет");
//        base.insertColumnSpecialnost(6,"ПедА_2.","Магистратура");
//        base.insertColumnSpecialnost(7,"Мат._3","Специалитет");
//        base.insertColumnSpecialnost(8,"ПедП._3","Специалитет");
//        base.insertColumnSpecialnost(9,"ПедА_3.","Магистратура");
//
//        base.insertColumnStudent("Ира","Иванова1","Ивановнаfsa","552352","Ленина 1",false);
//        base.insertColumnStudent("Ира1","Иванова2","Ивановнfsaа","552352","Ленина 1",false);
//        base.insertColumnStudent("Ира2","Иванова3","Иванfasвна","552352","Ленина 1",false);
//        base.insertColumnStudent("Ира3","Иванова4","Иваноfaвна","552352","Ленина 1",false);

        //        base.insertColumnLearning(1,1,3,"Очный");
//        base.insertColumnLearning(2,1,2,"ЗаОчный");
//        base.insertColumnLearning(3,1,1,"ЗаОчный");
//        base.insertColumnLearning(2,2,4,"ЗаОчный");
//        base.insertColumnLearning(3,2,1,"ЗаОчный");


        //        base.insertColumnFakultet("ИСТОРИКО-ФИЛОЛОГИЧЕСКИЙ ФАКУЛЬТЕТ", "Ауд. 226, центральный корпус", "istfil@bgpu.ru", "8 (416 2) 77-16-62", "http://www.bgpu.ru/facs/iff.html", "Гуськов Вячеслав Владимирович");
//        base.insertColumnFakultet("ФИЗИКО-МАТЕМАТИЧЕСКИЙ ФАКУЛЬТЕТ","Ауд. 104, центральный корпус","fmfbgpu@mail.ru","8 (416 2) 77-16-53","http://www.bgpu.ru/facs/fmf.html","Василенко Алевтина Викторовна");
    }
}

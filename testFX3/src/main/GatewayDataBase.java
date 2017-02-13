package main;

import main.dataBase.DataBase;
import main.objects.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kills on 23.01.2017.
 */
public class GatewayDataBase {


    private DataBase dataBase = new DataBase();

    //геттеры
    public Student getStudent(int id) {
        HashMap map = dataBase.findStudentByID(id);
        Student student =
                new Student(
                        Integer.valueOf(String.valueOf(map.get("id"))),
                        (String) map.get("Имя"),
                        (String) map.get("Фамилия"),
                        (String) map.get("Отчество"),
                        (String) map.get("Телефон"),
                        (String) map.get("Адресс"),
                        (boolean) map.get("Пол"));
        return student;
    }

    public Fakultet getFakultet(int id) {
        HashMap map = dataBase.findFakultByID(id);
        if (map == null) return null;
        return new Fakultet(
                Integer.valueOf(String.valueOf(map.get("id"))),
                String.valueOf(map.get("Наименование")),
                String.valueOf(map.get("Адресс")),
                String.valueOf(map.get("Почта")),
                String.valueOf(map.get("Телефон")),
                String.valueOf(map.get("Ссылка")),
                String.valueOf(map.get("Декан")));
    }


    public Kafedra getKafedra(int id) {
        HashMap map = dataBase.findKafedrByID(id);
        return new Kafedra(Integer.valueOf(String.valueOf(map.get("id"))),
                Integer.valueOf(String.valueOf(map.get("id_Факультета"))),
                String.valueOf(map.get("Наименование")),
                String.valueOf(map.get("Адресс")),
                String.valueOf(map.get("Почта")),
                String.valueOf(map.get("Телефон")),
                String.valueOf(map.get("Ссылка")),
                String.valueOf(map.get("Заведущий")));
    }

    public Specialnost getSpecialnost(int id) {
        HashMap map = dataBase.findSpecialnostByID(id);
        return new Specialnost(
                Integer.valueOf(String.valueOf(map.get("id"))),
                Integer.valueOf(String.valueOf(map.get("id_Кафедры"))),
                String.valueOf(map.get("Наименование")),
                String.valueOf(map.get("Квалификация")));
    }

    public UserRight getRightUser(String login, String password) {
        HashMap map = dataBase.findLogin(login);
        if (map == null) {
            System.out.println("Пользователь не найден!");
            return new UserRight("Нет доступа!", false);
        }
        if (map.get("password").equals(password)) {
            return new UserRight(String.valueOf(map.get("right")), true);
        }
        return new UserRight("Нет доступа!", false);
    }


    public Student[] getListStudents() {
        HashMap[] map = dataBase.listNamesStudents();
        int index = 0;
        Student[] returnMap = new Student[map.length];
        while (index < map.length) {
            int id = (int) map[index].get("id");
            String name = (String) map[index].get("Имя");
            String firstName = (String) map[index].get("Фамилия");
            String secondName = (String) map[index].get("Отчество");

            returnMap[index++] = new Student(id, name, firstName, secondName);
        }
        return returnMap;
    }

    public Fakultet[] getListFakults() {
        HashMap[] map = dataBase.listNamesFakult();
        int index = 0;
        Fakultet[] returnMap = new Fakultet[map.length];
        while (index < map.length) {
            returnMap[index] = new Fakultet(Integer.parseInt(String.valueOf(map[index].get("id"))), String.valueOf(map[index++].get("Наименование")));
        }
        return returnMap;
    }

    public Specialnost[] getListSpecialnost() {
        HashMap[] map = dataBase.listNamesSpecial();
        int index = 0;
        Specialnost[] returnMap = new Specialnost[map.length];
        while (index < map.length) {
            returnMap[index] = new Specialnost(Integer.parseInt(String.valueOf(map[index].get("id"))), String.valueOf(map[index++].get("Наименование")));
        }
        return returnMap;
    }

    public Kafedra[] getListKafedrs() {
        HashMap[] map = dataBase.listNamesKafedrs();
        int index = 0;
        Kafedra[] returnMap = new Kafedra[map.length];
        while (index < map.length) {
            returnMap[index] = new Kafedra(Integer.parseInt(String.valueOf(map[index].get("id"))), String.valueOf(map[index++].get("Наименование")));
        }
        return returnMap;
    }

    public Learning[] getListLearning(int idStudent) {
        HashMap[] learningsH = dataBase.listidSpecialnostForLearningByIdStudents(idStudent);

        Learning[] learnings = new Learning[learningsH.length];
        int index = 0;
        for (HashMap idS : learningsH) {
            learnings[index++] = new Learning(Integer.valueOf(String.valueOf(idS.get("id"))),
                    Integer.valueOf(String.valueOf(idS.get("id_Специальности"))),
                    Integer.valueOf(String.valueOf(idS.get("id_Студента"))),
                    Integer.valueOf(String.valueOf(idS.get("Курс"))),
                    (String) idS.get("Форма реализации"));
        }
        return learnings;
    }

    public String getNameFakultet(int idSpecialnost) {
        HashMap map = dataBase.findFakultByID_kafedr((Integer) dataBase.findKafedrByID_Specialnost(idSpecialnost).get("id_Факультета"));
        return (String) map.get("Наименование");
    }

    public String getNameKafedra(int idSpecialnost) {
        HashMap map = dataBase.findKafedrByID_Specialnost(idSpecialnost);
        return (String) map.get("Наименование");
    }

    public String getNameSpecialnost(int idSpecialnost) {
        HashMap map = dataBase.findSpecialnostByID(idSpecialnost);
        return (String) map.get("Наименование");
    }

    //update
    public void updateLearning(int id, String kurs, String formReal) {
        HashMap map = dataBase.findLeaningByID(id);
        dataBase.updateColumnLearning(
                Integer.parseInt(String.valueOf(map.get("id"))),
                Integer.parseInt(String.valueOf(map.get("id_Специальности"))),
                Integer.parseInt(String.valueOf(map.get("id_Студента"))),
                Integer.parseInt(kurs),
                formReal);
    }

    public void updateStudent(String id, String name, String fName, String sName, String addresses, String phoneNumber, boolean sexB) {
        dataBase.updateColumnStudent(
                Integer.parseInt(id),
                name,
                fName,
                sName,
                addresses,
                phoneNumber,
                sexB);
    }

    public void updateFakultet(int i, String name, String addresses, String phoneNumber, String mail, String link, String mainMan) {
        dataBase.updateColumnFakultet(i, name, addresses, mail, phoneNumber, link, mainMan);
    }

    public void updateSpecialnost(int i, int selectedItem, String text, String text1) {
        dataBase.updateColumnSpecialnost(i, selectedItem, text, text1);
    }

    public void updateKafedra(int id, int iSpec, String name, String addresses, String phoneNumber, String mail, String link, String mainMan) {
        dataBase.updateColumnKafedra(id, iSpec, name, addresses, mail, phoneNumber, link, mainMan);
    }

    //удаление
    public void deleteLearning(int id) {
        dataBase.deleteColumnLearning(id);
    }

    public void deleteStudent(int id) {
        dataBase.deleteColumnStudent(id);
    }

    //добавление
    public void addStudent(String name, String fName, String sName, String addresses, String phoneNumber, boolean sexB) {
        dataBase.insertColumnStudent(name, fName, sName, addresses, phoneNumber, sexB);
    }

    public void addFakultet(String name, String addresses, String phoneNumber, String mail, String link, String mainMan) {
        dataBase.insertColumnFakultet(name, addresses, mail, phoneNumber, link, mainMan);
    }

    public void addLearning(int i, int idStudent, String kurs, String form) {
        dataBase.insertColumnLearning(i, idStudent, Integer.parseInt(kurs), form);
    }

    public void addSpecialnost(int selectedItem, String text, String text1) {
        dataBase.insertColumnSpecialnost(selectedItem, text, text1);
    }

    public void addKafedra(int iSpec, String name, String addresses, String phoneNumber, String mail, String link, String mainMan) {
        dataBase.insertColumnKafedra(iSpec, name, addresses, mail, phoneNumber, link, mainMan);
    }

    //кастомные гетеры
    public Student[] getStudentFilterFakultet(int id) {
        HashMap[] map = dataBase.listStudentsByFakults(id);
        int index = 0;
        if (map != null) {

            Student[] returnMap = new Student[map.length];
            while (index < map.length) {
                int idS = (int) map[index].get("id");
                String name = (String) map[index].get("Имя");
                String firstName = (String) map[index].get("Фамилия");
                String secondName = (String) map[index].get("Отчество");

                returnMap[index++] = new Student(idS, name, firstName, secondName);
            }
            return returnMap;
        }
        return null;
    }

    public Kafedra[] getListKafedrsFromFakult(int idFakult) {
        int indexsKaf[] = dataBase.findKafedrsForFilterFakultets(idFakult);
        if (indexsKaf == null)
            return null;
        Kafedra[] kafedras = new Kafedra[indexsKaf.length];
        int i = 0;
        for (int indexKaf : indexsKaf) {
            HashMap map = dataBase.findKafedrByID(indexKaf);
            kafedras[i++] = new Kafedra(
                    Integer.valueOf(String.valueOf(map.get("id"))),
                    String.valueOf(map.get("Наименование"))
            );
        }
        return kafedras;
    }

    public Student[] getStudentFilterKafedra(int id) {
        HashMap[] map = dataBase.listStudentsByKafedrs(id);
        int index = 0;
        if (map != null) {
            Student[] returnMap = new Student[map.length];
            while (index < map.length) {
                int idS = (int) map[index].get("id");
                String name = (String) map[index].get("Имя");
                String firstName = (String) map[index].get("Фамилия");
                String secondName = (String) map[index].get("Отчество");
                returnMap[index++] = new Student(idS, name, firstName, secondName);
            }
            return returnMap;
        }
        return null;
    }

    public Specialnost[] getListSpecialnostFromKafedrs(int idKafedr) {
        int indexsKaf[] = dataBase.findSpecForFilterKafdrs(idKafedr);
        if (indexsKaf == null)
            return null;
        Specialnost[] specialnosts = new Specialnost[indexsKaf.length];
        int i = 0;
        for (int indexKaf : indexsKaf) {
            HashMap map = dataBase.findSpecialnostByID(indexKaf);
            specialnosts[i++] = new Specialnost(
                    Integer.valueOf(String.valueOf(map.get("id"))),
                    String.valueOf(map.get("Наименование"))
            );
        }
        return specialnosts;
    }

    public Student[] getStudentFilterSpecialnost(int id) {
        HashMap[] map = dataBase.listStudentsBySpecialnost(id);
        int index = 0;
        if (map != null) {
            Student[] returnMap = new Student[map.length];
            while (index < map.length) {
                int idS = (int) map[index].get("id");
                String name = (String) map[index].get("Имя");
                String firstName = (String) map[index].get("Фамилия");
                String secondName = (String) map[index].get("Отчество");
                returnMap[index++] = new Student(idS, name, firstName, secondName);
            }
            return returnMap;
        }
        return null;
    }

    public String[] getKursFilterSpecialnost(int idSpec) {
        int indexsKaf[] = dataBase.findKursForFilterSpec(idSpec);
        if (indexsKaf == null) return null;
        String indexesKafForString[] = new String[indexsKaf.length];
        int i = 0;
        for (int index : indexsKaf) {
            indexesKafForString[i++] = String.valueOf(index);
        }
        return indexesKafForString;
    }

    public Student[] getStudentFilterKursAndSpec(int kurs, int spec) {
        HashMap[] map = dataBase.listStudentsBySpecialnostAndKurs(kurs, spec);
        int index = 0;
        if (map != null) {
            Student[] returnMap = new Student[map.length];
            while (index < map.length) {
                int idS = (int) map[index].get("id");
                String name = (String) map[index].get("Имя");
                String firstName = (String) map[index].get("Фамилия");
                String secondName = (String) map[index].get("Отчество");
                returnMap[index++] = new Student(idS, name, firstName, secondName);
            }
            return returnMap;
        }
        return null;
    }

    public Specialnost[] getListSpecialnostFromFakultet(int id) {
        int indexsKaf[] = dataBase.findKafedrsForFilterFakultets(id);
        ArrayList<Specialnost[]> list = new ArrayList<>();
        if(indexsKaf==null) return null;
        for (int index : indexsKaf) {
            list.add(getListSpecialnostFromKafedrs(index));
        }
        ArrayList<Specialnost> listF = new ArrayList<>();
        for (Specialnost[] specialnosts : list) {
            if (specialnosts != null)
                for (Specialnost specialnost : specialnosts) {
                    listF.add(specialnost);
                }
        }
        Specialnost[] specialnosts = new Specialnost[listF.size()];
        int i = 0;
        for (Specialnost specialnost : listF)
            specialnosts[i] = listF.get(i++);
        return specialnosts;
    }


    public int getIdSpecianostByName(String nameSpecial) {
        return (int) dataBase.findSpecialnostByName(nameSpecial).get("id");
    }


    //кастомное удаление
    public void deletePackNotFakultet(int idKafedr) {
        int[] indexIdSpecialnost = dataBase.findSpecForFilterKafdrs(idKafedr);
        if (indexIdSpecialnost != null) {
            for (int indexSpec : indexIdSpecialnost) {
                deleteSpecialnost(indexSpec);
            }
        }
        dataBase.deleteColumnKafedra(idKafedr);
    }


    public void deleteSpecialnost(int id) {

        int[] indexesLearningStudents = dataBase.findStudentForLearning(id);
        int[] indexesLearningId = dataBase.findIdLearning(id);
        if (indexesLearningId != null) {
            for (int indexLearnId : indexesLearningId)
                dataBase.deleteColumnLearning(indexLearnId);
        }
        if (indexesLearningStudents != null) {
            for (int indexLearnStudens : indexesLearningStudents) {
                dataBase.deleteColumnStudent(indexLearnStudens);
                int i[] = dataBase.listLearningId(indexLearnStudens);
                if (i != null) {
                    for (int index : i) {
                        dataBase.deleteColumnLearning(index);
                    }
                }
            }
        }
        dataBase.deleteColumnSpecialnost(id);
    }

    public void deleteAllPack(int idFakult) {
        int[] indexIdKafedrs = dataBase.findKafedrsForFilterFakultets(idFakult);
        if (indexIdKafedrs != null) {
            for (int indexKaf : indexIdKafedrs) {
                deletePackNotFakultet(indexKaf);
            }
        }
        dataBase.deleteColumnFakultet(idFakult);
    }


}

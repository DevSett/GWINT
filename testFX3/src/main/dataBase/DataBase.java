package main.dataBase;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kills on 16.01.2017.
 */
public class DataBase {

    private static Connection conn;
    private String[] tablesNames = {"Факультет", "Кафедра", "Специальность", "Обучение", "Студент", "PersonAuth"};
    private String[] tablesCreate = {
            "CREATE TABLE `Факультет` (" +
                    "`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`Наименование` TEXT NOT NULL," +
                    "`Адресс` TEXT NOT NULL," +
                    "`Почта` TEXT NOT NULL," +
                    "`Телефон` TEXT NOT NULL," +
                    "`Ссылка` TEXT NOT NULL," +
                    "`Декан` TEXT NOT NULL" +
                    ");",

            "CREATE TABLE `Кафедра` (" +
                    "`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`id_Факультета` INTEGER NOT NULL," +
                    "`Наименование` TEXT NOT NULL," +
                    "`Адресс` TEXT NOT NULL," +
                    "`Почта` TEXT NOT NULL," +
                    "`Телефон` TEXT NOT NULL," +
                    "`Ссылка` TEXT NOT NULL," +
                    "`Заведущий` TEXT NOT NULL," +
                    "FOREIGN KEY(`id_Факультета`) REFERENCES Факультет(`id`)" +
                    ");",

            "CREATE TABLE `Специальность` (" +
                    "`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`id_Кафедры` INTEGER NOT NULL," +
                    "`Наименование` TEXT NOT NULL," +
                    "`Квалификация` TEXT NOT NULL," +
                    "FOREIGN KEY(`id_Кафедры`) REFERENCES Кафедра(`id`)" +
                    ");",

            "CREATE TABLE `Обучение` (" +
                    "`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`id_Специальности` INTEGER NOT NULL," +
                    "`id_Студента` INTEGER NOT NULL," +
                    "`Курс` INTEGER NOT NULL," +
                    "`Форма реализации` TEXT NOT NULL," +
                    "FOREIGN KEY(`id_Специальности`) REFERENCES Специальность(`id`)," +
                    "FOREIGN KEY(`id_Студента`) REFERENCES Студент(`id`)" +
                    ");",

            "CREATE TABLE `Студент` (" +
                    "`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`Имя` TEXT NOT NULL," +
                    "`Фамилия` TEXT NOT NULL," +
                    "`Отчество` TEXT NOT NULL," +
                    "`Телефон` TEXT NOT NULL," +
                    "`Адресс` TEXT NOT NULL," +
                    "`Пол` BOOLEAN NOT NULL" +
                    ");",
            "CREATE TABLE 'PersonAuth' (" +
                    "`id` INTEGER PRIMARY KEY NOT NULL," +
                    "`login` TEXT NOT NULL," +
                    "`password` TEXT NOT NULL," +
                    "`right` TEXT NOT NULL" +
                    ");"
    };
    private String jarPath()
    {
        String myJarPath = DataBase.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String dirPath = new File(myJarPath).getParent();
        return dirPath;
    }
    public DataBase() {
        try {
            conn = null;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:"+jarPath()+"/DB.s3db");
//            conn = DriverManager.getConnection("jdbc:sqlite:DB.s3db");

           System.out.println("База Подключена!");
            for (String table : tablesCreate) {
                createDB(table);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createDB(String textCommand) {
        try {
            Statement statmt = conn.createStatement();
            statmt.execute(textCommand);
            if(textCommand.indexOf("PersonAuth")!=-1)
            {
                insertPerson("admin", "78963", "ADMIN");
                insertPerson("adminStudent", "98741", "Guest");
            }
            statmt.close();
            System.out.println("Таблица создана!");
            statmt.execute("PRAGMA foreign_keys = TRUE;");
        } catch (SQLException e) {
            System.out.println("бд уже существует!");
        }
    }

    public void dropTable(String nameTable) {
        try {
            String sql = "DROP TABLE " + nameTable;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int insertTemplate(String sql, int[] columnsInt, String[] columnsString, boolean[] columnBoolean) {
        try {
            PreparedStatement insertStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int indexColumn = 1;
            if (columnsInt != null)
                for (int intColumns : columnsInt) {
                    insertStmt.setInt(indexColumn++, intColumns);
                }
            if (columnsString != null)
                for (String nameColumn : columnsString) {
                    insertStmt.setString(indexColumn++, nameColumn);
                }
            if (columnBoolean != null)
                for (Boolean varColumnBoolean : columnBoolean) {
                    insertStmt.setBoolean(indexColumn++, varColumnBoolean);
                }
            insertStmt.executeUpdate();

            ResultSet set = insertStmt.getGeneratedKeys();
            int i = set.getInt(1);
            insertStmt.close();
            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private boolean deleteAndUpdateTemplate(String sql, int[] columnsInt, String[] columnsString, boolean[] columnBoolean, Integer whereNumber) {
        try {
            PreparedStatement insertStmt = conn.prepareStatement(sql);
            int indexColumn = 1;
            if (columnsInt != null)
                for (int intColumns : columnsInt) {
                    insertStmt.setInt(indexColumn++, intColumns);
                }
            if (columnsString != null)
                for (String nameColumn : columnsString) {
                    insertStmt.setString(indexColumn++, nameColumn);
                }
            if (columnBoolean != null)
                for (Boolean varColumnBoolean : columnBoolean) {
                    insertStmt.setBoolean(indexColumn++, varColumnBoolean);
                }
            if (whereNumber != null) insertStmt.setInt(indexColumn++, whereNumber);
            insertStmt.executeUpdate();
            insertStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private HashMap findTemplate(String sql, Integer whereId,String whereName,String[] namesForReturning, int valuesInteger, int valuesString, int valuesBoolean) {
        HashMap map = new HashMap();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            if(whereId!=null)statement.setInt(1, whereId);
            if (whereName!=null)statement.setString(1,whereName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.isClosed()) return null;
            int indexColumn = 1;
            int lengthInt = 0;
            int lengthBool = 0;
            int lengthString = 0;
            for (String nameFR : namesForReturning) {
                if (lengthInt++ < valuesInteger) {
                    map.put(nameFR, resultSet.getInt(indexColumn++));
                    continue;
                }
                if (lengthString++ < valuesString) {
                    map.put(nameFR, resultSet.getString(indexColumn++));
                    continue;
                }
                if (lengthBool++ < valuesBoolean) {
                    map.put(nameFR, resultSet.getBoolean(indexColumn++));
                    continue;
                }
            }
            statement.close();
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Integer findCustomTemplate(String sql, int whereId, int gettingId) {
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, whereId);

            ResultSet resultSet = statement.executeQuery();

            int id = resultSet.getInt(gettingId);
            resultSet.close();
            statement.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int insertColumnFakultet(String name, String adresses, String mail, String phoneNumber, String link, String mainMan) {
        String sql = "INSERT INTO '" + tablesNames[0] + "'('Наименование','Адресс','Почта','Телефон','Ссылка','Декан') VALUES(?,?,?,?,?,?) ";
        return insertTemplate(sql, null, new String[]{name, adresses, mail, phoneNumber, link, mainMan}, null);
    }

    public int insertColumnKafedra(int id_Fakukt, String name, String adresses, String mail, String phoneNumber, String link, String mainMan) {
        String sql = "INSERT INTO '" + tablesNames[1] + "'('id_Факультета','Наименование','Адресс','Почта','Телефон','Ссылка','Заведущий') VALUES(?,?,?,?,?,?,?) ";
        return insertTemplate(sql, new int[]{id_Fakukt}, new String[]{name, adresses, mail, phoneNumber, link, mainMan}, null);
    }

    public int insertColumnSpecialnost(int id_Kafedr, String name, String kval) {
        String sql = "INSERT INTO '" + tablesNames[2] + "'('id_Кафедры','Наименование','Квалификация') VALUES(?,?,?) ";
        return insertTemplate(sql, new int[]{id_Kafedr}, new String[]{name, kval}, null);
    }

    public int insertColumnStudent(String name, String firstName, String secondName, String phoneNumber, String adresses, boolean sex) {
        String sql = "INSERT INTO '" + tablesNames[4] + "'('Имя','Фамилия','Отчество','Телефон','Адресс','Пол') VALUES(?,?,?,?,?,?) ";
        return insertTemplate(sql, null, new String[]{name, firstName, secondName, phoneNumber, adresses}, new boolean[]{sex});
    }

    public int insertColumnLearning(int id_Special, int id_Student, int kurs, String formReal) {
        String sql = "INSERT INTO '" + tablesNames[3] + "'('id_Специальности','id_Студента','Курс','Форма реализации') VALUES(?,?,?,?) ";
        return insertTemplate(sql, new int[]{id_Special, id_Student, kurs}, new String[]{formReal}, null);
    }

    public boolean deleteColumnFakultet(int id) {
        String sql = "DELETE FROM '" + tablesNames[0] + "' WHERE id = ?";
        return deleteAndUpdateTemplate(sql, new int[]{id}, null, null, null);
    }

    public boolean deleteColumnKafedra(int id) {
        String sql = "DELETE FROM '" + tablesNames[1] + "' WHERE id = ?";
        return deleteAndUpdateTemplate(sql, new int[]{id}, null, null, null);
    }

    public boolean deleteColumnSpecialnost(int id) {
        String sql = "DELETE FROM '" + tablesNames[2] + "' WHERE id = ?";
        return deleteAndUpdateTemplate(sql, new int[]{id}, null, null, null);
    }

    public boolean deleteColumnLearning(int id) {
        String sql = "DELETE FROM '" + tablesNames[3] + "' WHERE id = ?";
        return deleteAndUpdateTemplate(sql, new int[]{id}, null, null, null);
    }

    public boolean deleteColumnStudent(int id) {
        String sql = "DELETE FROM '" + tablesNames[4] + "' WHERE id = ?";
        return deleteAndUpdateTemplate(sql, new int[]{id}, null, null, null);
    }

    public boolean updateColumnFakultet(int id, String name, String addresses, String mail, String phoneNumber, String link, String mainMan) {
        String sql = "UPDATE " + tablesNames[0] +
                " SET Наименование = ?, Адресс = ?, Почта = ?, Телефон = ?, Ссылка = ?, Декан = ? WHERE id = ?";
        return deleteAndUpdateTemplate(sql, null, new String[]{name, addresses, mail, phoneNumber, link, mainMan}, null, id);
    }

    public boolean updateColumnKafedra(int id, int id_Fakult, String name, String addresses, String mail, String phoneNumber, String link, String mainMan) {
        String sql = "UPDATE " + tablesNames[1] +
                " SET id_Факультета = ?, Наименование = ?, Адресс = ?, Почта = ?, Телефон = ?, Ссылка = ?, Заведущий = ? WHERE id = ?";
        return deleteAndUpdateTemplate(sql, new int[]{id_Fakult}, new String[]{name, addresses, mail, phoneNumber, link, mainMan}, null, id);
    }

    public boolean updateColumnSpecialnost(int id, int id_Kafed, String name, String kval) {
        String sql = "UPDATE " + tablesNames[2] + " SET id_Кафедры = ?, Наименование = ?, Квалификация = ? WHERE id = ?";
        return deleteAndUpdateTemplate(sql, new int[]{id_Kafed}, new String[]{name, kval}, null, id);
    }

    public boolean updateColumnStudent(int id, String name, String firstName, String secondName, String phoneNumber, String addresses, boolean sex) {
        String sql = "UPDATE " + tablesNames[4] + " SET Имя = ?, Фамилия = ?, Отчество = ?, Телефон = ?, Адресс = ?, Пол = ? WHERE id = ?";
        return deleteAndUpdateTemplate(sql, null, new String[]{name, firstName, secondName, phoneNumber, addresses}, new boolean[]{sex}, id);
    }

    public boolean updateColumnLearning(int id, int id_Spec, int id_Student, int kurs, String form_Real) {
        String sql = "UPDATE " + tablesNames[3] + " SET id_Специальности = ?, id_Студента = ?, Курс = ?, 'Форма реализации' = ? WHERE id = ?";
        return deleteAndUpdateTemplate(sql, new int[]{id_Spec, id_Student, kurs}, new String[]{form_Real}, null, id);
    }

    //кастомный запрос для поиска студента
    public HashMap findStudentByName(String name, String firstName, String secondName) {
        String sql = "SELECT * FROM " + tablesNames[4] + " WHERE Имя = ?, Фамилия = ?, Отчество = ?";
        HashMap map = new HashMap();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, firstName);
            statement.setString(3, secondName);

            ResultSet resultSet = statement.executeQuery();
            map.put("id", resultSet.getInt(1));
            map.put("Имя", resultSet.getString(2));
            map.put("Фамилия", resultSet.getString(3));
            map.put("Отчество", resultSet.getString(4));
            map.put("Телефон", resultSet.getString(5));
            map.put("Адресс", resultSet.getString(6));
            map.put("Пол", resultSet.getBoolean(7));
            statement.close();
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap findFakultByID(int id) {
        String sql = "SELECT * FROM " + tablesNames[0] + " WHERE id = ?";
        return findTemplate(sql, id,null, new String[]{"id","Наименование","Адресс","Почта","Телефон","Ссылка","Декан"}, 1, 6, 0);
    }


    public HashMap findFakultByID_kafedr(int idKafedr) {
        String sql = "SELECT * FROM " + tablesNames[1] + " WHERE id= ?";
        Integer id_fakult = findCustomTemplate(sql, idKafedr, 2);
        if (id_fakult != null) return findFakultByID(id_fakult);
        return null;
    }

    public HashMap findKafedrByID(int id) {
        String sql = "SELECT * FROM " + tablesNames[1] + " WHERE id = ?";
        return findTemplate(
                sql,
                id,
                null,
                new String[]{"id", "id_Факультета", "Наименование", "Адресс", "Почта", "Телефон", "Ссылка", "Заведущий"},
                2, 6, 0);
    }

    public HashMap findKafedrByID_Specialnost(int id_Specialnost) {
        String sql = "SELECT * FROM " + tablesNames[2] + " WHERE id= ?";
        Integer id_kafedr = findCustomTemplate(sql, id_Specialnost, 2);
        if (id_kafedr != null)
            return findKafedrByID(id_kafedr);
        return null;
    }

    public HashMap findSpecialnostByID(int id) {
        String sql = "SELECT * FROM " + tablesNames[2] + " WHERE id = ?";
        return findTemplate(
                sql,
                id,
                null,

                new String[]{"id", "id_Кафедры", "Наименование", "Квалификация"},
                2, 2, 0);

    }
    public HashMap findSpecialnostByName(String name) {
        String sql = "SELECT * FROM " + tablesNames[2] + " WHERE Наименование = ?";
        return findTemplate(
                sql,
                null,
                name,
                new String[]{"id", "id_Кафедры", "Наименование", "Квалификация"},
                2, 2, 0);

    }

    public HashMap findSpecialnostByID_learning(int id_learning) {
        String sql = "SELECT * FROM " + tablesNames[3] + " WHERE id= ?";
        Integer id_spec = findCustomTemplate(sql, id_learning, 2);
        if (id_spec != null) return findSpecialnostByID(id_spec);
        return null;
    }

    public HashMap findLeaningByID(int id) {
        String sql = "SELECT * FROM " + tablesNames[3] + " WHERE id = ?";
        return findTemplate(
                sql,
                id,
                null,
                new String[]{"id", "id_Специальности", "id_Студента", "Форма реализации"},
                3, 1, 0);
    }

    public HashMap findStudentByID(int id) {

        String sql = "SELECT * FROM " + tablesNames[4] + " WHERE id = ?";
        return findTemplate(
                sql,
                id,
                null,
                new String[]{"id", "Имя", "Фамилия", "Отчество", "Адресс", "Телефон", "Пол"},
                1, 5, 1);
    }

    //кастом
    public HashMap findStudentByID_learning(int id_learning) {
        String sql = "SELECT * FROM " + tablesNames[3] + " WHERE id= ?";
        Integer id_Stud = findCustomTemplate(sql, id_learning, 3);
        if (id_Stud != null) findStudentByID(id_Stud);
        return null;
    }


    public HashMap[] listNamesStudents() {
        String sql = "SELECT id, Имя, Фамилия, Отчество FROM Студент ";
        ArrayList<Integer> name0 = new ArrayList<>();
        ArrayList<String> name1 = new ArrayList<>();
        ArrayList<String> name2 = new ArrayList<>();
        ArrayList<String> name3 = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                name0.add(resultSet.getInt(1));
                name1.add(resultSet.getString(2));
                name2.add(resultSet.getString(3));
                name3.add(resultSet.getString(4));
            }
            HashMap[] map = new HashMap[name0.size()];
            for (int index = 0; index < map.length; index++) {
                map[index] = new HashMap();
            }

            int i = 0;
            for (int name : name0) {
                map[i++].put("id", name);
            }
            i = 0;
            for (String name : name1) {
                map[i++].put("Имя", name);
            }
            i = 0;
            for (String name : name2) {
                map[i++].put("Фамилия", name);
            }
            i = 0;
            for (String name : name3) {
                map[i++].put("Отчество", name);
            }
            statement.close();
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap[] listNamesFakult() {
        ArrayList<Integer> timeS = new ArrayList<>();
        ArrayList<String> timeS1 = new ArrayList<>();

        String sql = "SELECT id, Наименование FROM Факультет ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                timeS.add(resultSet.getInt(1));
                timeS1.add(resultSet.getString(2));
            }
            HashMap[] lists = new HashMap[timeS.size()];
            int i = 0;
            for (HashMap list : lists) {
                lists[i++] = new HashMap();
            }
            i = 0;
            for (int s : timeS) {
                lists[i++].put("id", s);
            }
            i = 0;
            for (String s : timeS1) {
                lists[i++].put("Наименование", s);
            }
            statement.close();
            return lists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap[] listNamesKafedrs() {
        ArrayList<Integer> timeS = new ArrayList<>();
        ArrayList<String> timeS1 = new ArrayList<>();

        String sql = "SELECT id, Наименование FROM Кафедра ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                timeS.add(resultSet.getInt(1));
                timeS1.add(resultSet.getString(2));
            }
            HashMap[] lists = new HashMap[timeS.size()];
            int i = 0;
            for (HashMap list : lists) {
                lists[i++] = new HashMap();
            }
            i = 0;
            for (int s : timeS) {
                lists[i++].put("id", s);
            }
            i = 0;
            for (String s : timeS1) {
                lists[i++].put("Наименование", s);
            }
            statement.close();
            return lists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap[] listNamesSpecial() {
        ArrayList<Integer> timeS = new ArrayList<>();
        ArrayList<String> timeS1 = new ArrayList<>();

        String sql = "SELECT id, Наименование FROM Специальность ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                timeS.add(resultSet.getInt(1));
                timeS1.add(resultSet.getString(2));
            }
            HashMap[] lists = new HashMap[timeS.size()];
            int i = 0;
            for (HashMap list : lists) {
                lists[i++] = new HashMap();
            }
            i = 0;
            for (int s : timeS) {
                lists[i++].put("id", s);
            }
            i = 0;
            for (String s : timeS1) {
                lists[i++].put("Наименование", s);
            }
            statement.close();
            return lists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] listNameLearningByIdStudents(int idStudent) {
        ArrayList<String> timeS = new ArrayList<>();
        String sql = "SELECT id_Специальности FROM Обучение ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                timeS.add((String) findSpecialnostByID(resultSet.getInt(1)).get("Наименование"));
            }
            String[] list = new String[timeS.size()];
            int i = 0;
            for (String s : timeS) {
                list[i++] = s;
            }
            statement.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap[] listidSpecialnostForLearningByIdStudents(int idStudent) {
        String sql = "SELECT * FROM " + tablesNames[3] + " WHERE id_Студента= ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idStudent);

            ResultSet resultSet = statement.executeQuery();
            ArrayList<Integer> list1 = new ArrayList();
            ArrayList<Integer> list2 = new ArrayList();
            ArrayList<Integer> list3 = new ArrayList();
            ArrayList<Integer> list4 = new ArrayList();
            ArrayList<String> list5 = new ArrayList();

            while (resultSet.next()) {
                list1.add(resultSet.getInt(1));
                list2.add(resultSet.getInt(2));
                list3.add(resultSet.getInt(3));
                list4.add(resultSet.getInt(4));
                list5.add(resultSet.getString(5));
            }
            HashMap[] list = new HashMap[list1.size()];
            for (int index = 0; index < list.length; index++) {
                list[index] = new HashMap();
            }
            int index = 0;
            for (int i : list1) {
                list[index++].put("id", i);
            }
            index = 0;
            for (int i : list2) {
                list[index++].put("id_Специальности", i);
            }
            index = 0;
            for (int i : list3) {
                list[index++].put("id_Студента", i);
            }
            index = 0;
            for (int i : list4) {
                list[index++].put("Курс", i);
            }
            index = 0;
            for (String i : list5) {
                list[index++].put("Форма реализации", i);
            }


            statement.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int[] findKafedrsForFilterFakultets(int id_fakult) {
        return findingShablons(tablesNames[1], id_fakult, "id", "id_Факультета");

    }

    public int[] findSpecForFilterKafdrs(int id_kafed) {
        return findingShablons(tablesNames[2], id_kafed, "id", "id_Кафедры");
    }

    public int[] findStudentForLearning(int idSpecialnost) {
        return findingShablons(tablesNames[3], idSpecialnost, "id_Студента", "id_Специальности");
    }
    public int[] findIdLearning(int idSpecialnost) {
        return findingShablons(tablesNames[3], idSpecialnost, "id", "id_Специальности");
    }
    public int[] findKursForFilterSpec(int idSpecialnost) {
        return findingShablons(tablesNames[3], idSpecialnost, "Курс", "id_Специальности");
    }

    public int[] findKursForFilterSpec(int idSpecialnost, int kurs) {
        return findingShablons2(tablesNames[3], idSpecialnost, kurs, "id_Студента", "id_Специальности", "Курс");
    }

    private int[] findingShablons2(String tableName, int id, int kurs, String select, String where, String where2) {
        String sql = "SELECT " + select + " FROM " + tableName + " WHERE " + where + " = ? AND " + where2 + "=?";
        PreparedStatement statement = null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(2, kurs);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() > 0) {
            int[] result = new int[list.size()];
            int i = 0;
            for (int res : list) {
                result[i++] = res;
            }
            return result;
        }
        return null;
    }

    private int[] findingShablons(String tableName, int id, String select, String where) {
        String sql = "SELECT " + select + " FROM " + tableName + " WHERE " + where + "=?";
        PreparedStatement statement = null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() > 0) {
            int[] result = new int[list.size()];
            int i = 0;
            for (int res : list) {
                result[i++] = res;
            }
            return result;
        }
        return null;
    }

    public HashMap[] listStudentsByFakults(int idFakult) {
        int fakultsID[] = findKafedrsForFilterFakultets(idFakult);
        ArrayList<int[]> listSpecs = new ArrayList<>();
        if(fakultsID==null) return null;
        for (int indexFakultID : fakultsID) {
            int[] tempVar = findSpecForFilterKafdrs(indexFakultID);
            if (tempVar != null) listSpecs.add(tempVar);
        }
        ArrayList<Integer> studentsId = new ArrayList();
        for (int[] indexListSpecs : listSpecs) {
            for (int indexEliment : indexListSpecs) {
                int[] tempVar = findStudentForLearning(indexEliment);
                if (tempVar != null) {
                    for (int index : tempVar)
                        studentsId.add(index);
                }
            }
        }
        HashMap[] listSt = null;
        if (studentsId.size() != 0) listSt = new HashMap[studentsId.size()];
        else return null;
        int i = 0;
        for (HashMap list : listSt) {
            listSt[i++] = new HashMap();
        }
        i = 0;
        for (int indexStudent : studentsId) {
            listSt[i++] = findStudentByID(indexStudent);
        }
        return listSt;
    }

    public HashMap[] listStudentsByKafedrs(int id) {

        int[] kafedrs = findSpecForFilterKafdrs(id);
        ArrayList<Integer> studentsId = new ArrayList();
        if (kafedrs == null) return null;
        for (int i : kafedrs) {
            int[] indexes = findStudentForLearning(i);
            if (indexes != null) {
                for (int i2 : indexes) {
                    studentsId.add(i2);
                }
            }
        }
        HashMap[] listSt = null;
        if (studentsId.size() != 0) listSt = new HashMap[studentsId.size()];
        else return null;

        int i = 0;
        for (HashMap list : listSt) {
            listSt[i++] = new HashMap();
        }
        i = 0;
        for (int indexStudent : studentsId) {
            listSt[i++] = findStudentByID(indexStudent);
        }
        return listSt;
    }

    public HashMap[] listStudentsBySpecialnost(int id) {
        int[] kafedrs = findStudentForLearning(id);
        if (kafedrs == null) return null;
        ArrayList<Integer> studentsId = new ArrayList();
        for (int indexKaf : kafedrs) {
            studentsId.add(indexKaf);
        }

        HashMap[] listSt = null;
        if (studentsId.size() != 0) listSt = new HashMap[studentsId.size()];
        else return null;

        int i = 0;
        for (HashMap list : listSt) {
            listSt[i++] = new HashMap();
        }
        i = 0;
        for (int indexStudent : studentsId) {
            listSt[i++] = findStudentByID(indexStudent);
        }
        return listSt;
    }

    public HashMap[] listStudentsBySpecialnostAndKurs(int kurs, int spec) {
        int[] kurses = findKursForFilterSpec(spec, kurs);
        if (kurses == null) return null;
        ArrayList<Integer> studentsId = new ArrayList();
        for (int indexKurses : kurses) {
            studentsId.add(indexKurses);
        }

        HashMap[] listSt = null;
        if (studentsId.size() != 0) listSt = new HashMap[studentsId.size()];
        else return null;

        int i = 0;
        for (HashMap list : listSt) {
            listSt[i++] = new HashMap();
        }
        i = 0;
        for (int indexStudent : studentsId) {
            listSt[i++] = findStudentByID(indexStudent);
        }
        return listSt;

    }

    public void listLearning() {
        String sql = "SELECT " + "id" + " FROM " + tablesNames[3];
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int[] listLearningId(int indexLearnStudent) {
        ArrayList<Integer> timeS = new ArrayList<>();
        String sql = "SELECT id FROM Обучение WHERE id_Студента = ? ";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,indexLearnStudent);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                timeS.add(resultSet.getInt(1));
            }
            int[] list = new int[timeS.size()];
            int i = 0;
            for (int s : timeS) {
                list[i++] = s;
            }
            statement.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public HashMap findLogin(String login) {
        String sql = "SELECT * FROM " + tablesNames[5] + " WHERE login = ?";
        return findTemplate(
                sql,
                null,
                login,
                new String[]{"id", "login", "password", "right"},
                2, 2, 0);
    }
    public int insertPerson(String login, String password,String right)
    {
        String sql = "INSERT INTO '" + tablesNames[5] + "'('login','password','right') VALUES(?,?,?) ";
        return insertTemplate(sql, null, new String[]{login, password, right}, null);
    }

}

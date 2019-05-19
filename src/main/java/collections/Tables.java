package collections;

import entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tables {
    private static List<User> table1;
    private static List<User> table2;
    private static List<User> table3;

    static {
        table1 = new CopyOnWriteArrayList<>();
        table2 = new CopyOnWriteArrayList<>();
        table3 = new CopyOnWriteArrayList<>();
    }

    public Tables() {
    }

    public static List<User> getTable1() {
        return table1;
    }

    public static List<User> getTable2() {
        return table2;
    }

    public static List<User> getTable3() {
        return table3;
    }


    public static int size1() {
        return table1.size();
    }

    public static int size2() {
        return table2.size();
    }

    public static int size3() {
        return table3.size();
    }

    public static Collection<List<User>> getTables() {
        Collection<List<User>> out = new ArrayList<>();
        Collections.addAll(out, table1, table2, table3);
        return out;
    }

}


package com.example.wholesalemanagmentsystem.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    public static <T> List<T> resultSetToArrayList(ResultSet resultSet, Class<T> type) throws SQLException {
        List<T> list = new ArrayList<>();

            while (resultSet.next()) {
                T value = resultSet.getObject(1, type);
                list.add(value);
            }
        return list;
    }
}

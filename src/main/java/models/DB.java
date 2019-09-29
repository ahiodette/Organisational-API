package models;

import org.sql2o.Sql2o;

public class DB {
public static Sql2o sql2o = new Sql2o("jdbc:postgresql?//localhost5432/organisational_api", "wecode", "12345");
}

package model;

import java.sql.SQLException;

public class TestDatabase {

    public static void main(String[] args) {
        System.out.println("Running databsse");

        Database db = new Database();
        try {
            db.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.addPerson(new Person("Joe", "lion tamer", AgeCategory.adult,
                EmploymentCategory.employed, "777", true, Gender.male));
        db.addPerson(new Person("Sue", "artist", AgeCategory.adult,
                EmploymentCategory.employed, null, false, Gender.female));

        try {
            db.save();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        db.disconnect();
    }
}

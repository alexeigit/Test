package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Сделать возраст опциональным
 * Отсортировать пользователей по убыванию по возрасту, без возраста в начало списка
 */
public class Main {

    private static final int USER_STRING_COUNT = 4;

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String sCurrentLine;
            int count = 0;
            List<String> userData = new ArrayList<>(4);
            while ((sCurrentLine = br.readLine()) != null) {
                count++;
                userData.add(sCurrentLine);
                if (count % USER_STRING_COUNT == 0) {
                    User user = new User();
                    user.setLastName(userData.get(UserFields.LAST_NAME.ordinal()));
                    user.setFirstName(userData.get(UserFields.FIRST_NAME.ordinal()));
                    String age = userData.get(UserFields.AGE.ordinal());
                    user.setAge(age.isEmpty() ? 0 : Integer.parseUnsignedInt(age));
                    user.setPassport(userData.get(UserFields.PASSPORT_NUMBER.ordinal()));
                    users.add(user);
                    userData.clear();
                    count = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        users.forEach(System.out::println);

        users.sort((u1, u2) -> {
            int age1 = u1.getAge() == 0 ? Integer.MAX_VALUE : u1.getAge();
            int age2 = u2.getAge() == 0 ? Integer.MAX_VALUE : u2.getAge();
            return Integer.compare(age2, age1);
        });

        System.out.println("\nSorted: ");
        users.forEach(System.out::println);

    }
}

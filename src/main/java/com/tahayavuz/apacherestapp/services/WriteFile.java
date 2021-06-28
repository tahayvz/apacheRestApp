package com.tahayavuz.apacherestapp.services;

import com.tahayavuz.api.domain.User;

import java.io.*;

public class WriteFile {

    public void write(String s, User user, Integer contribution) {

        File file = new File("output.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        try {
            bWriter.newLine();
            bWriter.write("'repo': " + s +" user: " + user.getLogin()
                    + " company: " + user.getCompany() + " location: "+user.getLocation() + " contributions: " + contribution);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
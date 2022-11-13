package com.epam.mjc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();

        try (java.io.FileReader fileReader = new java.io.FileReader(file)) {
            int c;
            String s = "";
            while ((c = fileReader.read()) != -1) {
                char c1 = (char) c;
                if (c1 != '\r') {
                    if (c1 != '\n')
                        s += c1;
                    else {
                        profileMapper(profile, s);
                        s = "";
                    }
                }
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profile;
    }

    private void profileMapper(Profile profile, String line) {
        if (line.startsWith("Name")) {
            profile.setName(line.substring("Name: ".length()));
        } else if (line.startsWith("Age")) {
            profile.setAge(Integer.valueOf(line.substring("Age: ".length())));
        } else if (line.startsWith("Email")) {
            profile.setEmail(line.substring("Email: ".length()));
        } else {
            profile.setPhone(Long.valueOf(line.substring("Phone: ".length())));
        }
    }

}

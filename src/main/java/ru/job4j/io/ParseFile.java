package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }


    public String getContent(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

}

class SaveFile {

    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContentPred(Predicate<Integer> predicate) {
        String output = "";
        try (InputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > 0) {
                if (predicate.test(data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContent() {
        return getContentPred((data) -> true);
    }

    public String getContentWithoutUnicode() {
        return getContentPred((data) -> data < 0x80);
    }
}
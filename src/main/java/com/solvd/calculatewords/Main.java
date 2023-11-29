package com.solvd.calculatewords;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class Main {
    public static void main(String[] args) throws IOException {
        String content = FileUtils.readFileToString(new File("src/main/resources/file.txt"), "UTF-8");
        Set<String> mySet = new HashSet<>(Arrays.asList(StringUtils.split(StringUtils.lowerCase(StringUtils.replaceAll(content, "\\p{Punct}", "")))));
        FileUtils.write(new File("src/main/resources/output.txt"), "Number of unique words: " + mySet.size(), "UTF-8");
    }
}
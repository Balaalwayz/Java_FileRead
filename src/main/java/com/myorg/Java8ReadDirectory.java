package com.myorg;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.nio.file.Files.walk;

public class Java8ReadDirectory {

    public static void main(String[] args) {
        Java8ReadDirectory java8ReadDirectory = new Java8ReadDirectory();
        System.out.println("Enter the Directory");
        Scanner inputReader = new Scanner(System.in);
        String directory = inputReader.next();
        System.out.println("Enter the Extn");
        String extn = inputReader.next();
        java8ReadDirectory.listDirectories(directory,extn.toUpperCase());
    }


    private void listDirectories(String directory,String extn) {
        try ( Stream<Path> walk = walk(Paths.get(directory), Integer.MAX_VALUE, FileVisitOption.FOLLOW_LINKS)){
            walk.map(Path::toFile)
                    .filter(file -> file.getName().toUpperCase().endsWith(FILENAME_EXTN.valueOf(extn).name()))
                    .forEach(System.out::println);
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


}

@AllArgsConstructor
@Getter
enum FILENAME_EXTN {
    PDF("pdf"),
    JAR("jar"),
    TXT("txt");
    private String name;
}

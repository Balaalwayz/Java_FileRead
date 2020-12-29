package com.myorg;

import com.myorg.parser.MP3Parser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.audio.AudioParser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.*;
import java.nio.file.FileVisitOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.nio.file.Files.walk;

public class Java8ReadDirectory {
    MP3Parser mp3Parser = new MP3Parser(new Metadata(),new BodyContentHandler(),new AutoDetectParser(),new ParseContext());


    public static void main(String[] args) {
        Java8ReadDirectory java8ReadDirectory = new Java8ReadDirectory();
        System.out.println("Enter the Directory");
        Scanner inputReader = new Scanner(System.in);
        String directory = inputReader.next();
        System.out.println("Enter the Extn");
        String extn = inputReader.next();
        LocalTime initialTime = LocalTime.now();
        java8ReadDirectory.listDirectories(directory,extn.toUpperCase());
        LocalTime finalTime = LocalTime.now();
        System.out.println("Time taken for processing the records is " + ChronoUnit.SECONDS.between(initialTime, finalTime) + " seconds");

    }


    private void listDirectories(String directory,String extn) {
        try ( Stream<Path> walk = walk(Paths.get(directory), Integer.MAX_VALUE, FileVisitOption.FOLLOW_LINKS)){
            long count = walk.map(path -> path.toFile())
                    .filter(file -> file.getName().toUpperCase().endsWith(FILENAME_EXTN.valueOf(extn).name()))
                    .map(file -> {
                        System.out.println("-----------------------");
                        System.out.println("Reading the META-DATA FOR " + file.getPath());
                        return file;
                    })
                    .map(file -> {
                        Metadata metadata = null;
                        try {
                            FileInputStream fis = new FileInputStream(file);
                            metadata = mp3Parser.mp3Parser(fis);
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return metadata;
                    })
                    //.map(metadata ->  { System.out.println(" == is ==> " +metadata.get("xmpDM:composer")); return metadata;})
                    .map(metadata -> { Arrays.stream(metadata.names()).forEach(s -> System.out.println(s + " == is ==> " + metadata.get(s))); return metadata;  })
                    .count();
            System.out.println("Total Processed records is " + count);
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
    MP3("mp3"),
    TXT("txt");
    private String name;
}

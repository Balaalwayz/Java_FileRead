package com.myorg.parser;

import lombok.AllArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
public class MP3Parser {
    Metadata metadata;
    BodyContentHandler bodyContentHandler;
    Parser p;
    ParseContext pc;


    public Metadata mp3Parser(InputStream is) {
        try {
            p.parse(is,bodyContentHandler, metadata,pc);
        } catch (IOException | TikaException | SAXException e) {
            e.printStackTrace();
        }
        return metadata;
        }
}

package com.kakaopay.hkp.lgs.api.financialsupport.service.csv;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class PathFileCsvReader implements CsvReader<Path> {

    @Override
    public Reader convertReader(Path source) throws IOException {
        return Files.newBufferedReader(source);
    }
}

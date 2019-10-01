package com.kakaopay.hkp.lgs.api.financialsupport.service.csv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Slf4j
public class MultipartFileCsvReader implements CsvReader<MultipartFile> {

    @Override
    public Reader convertReader(MultipartFile source) throws IOException {
        return new InputStreamReader(source.getInputStream());
    }
}

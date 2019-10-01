package com.kakaopay.hkp.lgs.api.financialsupport.service;

import com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.FinancialSupportCsv;
import com.kakaopay.hkp.lgs.api.financialsupport.domain.entity.FinancialSupport;
import com.kakaopay.hkp.lgs.api.financialsupport.repository.FinancialSupportRepository;
import com.kakaopay.hkp.lgs.api.financialsupport.service.csv.CsvReader;
import com.kakaopay.hkp.lgs.api.financialsupport.service.csv.MultipartFileCsvReader;
import com.kakaopay.hkp.lgs.api.financialsupport.service.csv.PathFileCsvReader;
import com.kakaopay.hkp.lgs.exception.ApiException;
import com.kakaopay.hkp.lgs.exception.ApiExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class FinanceSupportFileService {

    private final FinancialSupportRepository financialSupportRepository;

    public FinanceSupportFileService(FinancialSupportRepository financialSupportRepository) {
        this.financialSupportRepository = financialSupportRepository;
    }

    public int insertUploadCsvData(MultipartFile file) {
        return insertCsvData(new MultipartFileCsvReader(), file);
    }

    public int insertPathCsvData() {
        return insertCsvData(new PathFileCsvReader(), getPath());
    }

    private static List<FinancialSupport> convertToFinancialSupportList(Iterator<FinancialSupportCsv> iterator) {
        List<FinancialSupport> list = new ArrayList<>();
        iterator.forEachRemaining((e) -> list.add(new FinancialSupport(e)));
        return list;
    }

    private int insertCsvData(Iterator<FinancialSupportCsv> data) {
        List<FinancialSupport> returnList = financialSupportRepository.saveAll(convertToFinancialSupportList(data));
        return returnList.size();
    }

    private <S> int insertCsvData(CsvReader<S> csvReader, S source) {

        try (Reader reader = csvReader.convertReader(source)) {
            Iterator<FinancialSupportCsv> financialSupportCsvIterator = csvReader.read(reader, FinancialSupportCsv.class);
            return insertCsvData(financialSupportCsvIterator);

        } catch (IOException e) {
            throw new ApiException(ApiExceptionCode.FAIL_READ_FILE, source.toString());
        }
    }

    private static Path getPath() {

        final String csvPath = "csv/data.csv";

        try {
            return Paths.get(ClassLoader.getSystemResource(csvPath).toURI());
        } catch (URISyntaxException e) {
            throw new ApiException(ApiExceptionCode.NOT_FOUND_RESOURCE, csvPath);
        }
    }
}

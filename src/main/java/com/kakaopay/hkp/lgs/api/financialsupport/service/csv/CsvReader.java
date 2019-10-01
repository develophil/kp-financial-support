package com.kakaopay.hkp.lgs.api.financialsupport.service.csv;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public interface CsvReader<S> {

    Reader convertReader(S source) throws IOException;

    default <E> Iterator<E> read(Reader reader, Class<E> target) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(target).withSkipFirstDataRow(true);
        return mapper.readerFor(target).with(schema).readValues(reader);
    }
}

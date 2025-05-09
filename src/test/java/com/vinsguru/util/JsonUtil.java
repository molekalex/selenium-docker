package com.vinsguru.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinsguru.tests.vendorportal.model.vendorportalTestdata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    //El siguiente metodo retorna el objeto con los datos cargados desde el archivo JSON
    //utiliza la clase ResourceLoader que permite leer los archivos json

    //pasamos un tipo generico <T> segun el formato del archivo compilado .class de java.
    public static <T> T getTestData(String path, Class<T>type) {
        try (InputStream stream = ResourceLoader.getResource(path)) {
            return mapper.readValue(stream,type);
        }catch (Exception e){
            log.error("unable to read test data {}", path, e);
        }
        return null;
    }
}
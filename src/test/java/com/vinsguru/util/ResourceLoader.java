package com.vinsguru.util;

import org.openqa.selenium.bidi.module.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/*Esta clase permite leer los datos desde el archivo json,
with this we check the class path and if it could not find it
them It will start digging in the filesystem*/
public class ResourceLoader {
private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

public static InputStream getResource(String path)throws Exception
{
log.info("Bolo!,reading resource from location: {}  ",path);
InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
if(Objects.nonNull(stream)){return stream; }
    return Files.newInputStream(Path.of(path));
}

}


package com.example.cqrssamplecodes.config.axon;

import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.xml.XStreamSerializer;

public class SecureXStreamSerializer {

    private static XStreamSerializer _instance;

    public static XStreamSerializer get() {
        if (_instance == null) {
            _instance = secureXStreamSerializer();
        }
        return _instance;
    }

    private static XStreamSerializer secureXStreamSerializer() {
        XStream xStream = new XStream();
        xStream.setClassLoader(SecureXStreamSerializer.class.getClassLoader());
        xStream.allowTypesByWildcard(new String[]{
                "org.axonframework.**",
                "java.util.**",
                "com.example.**"
        });
        return XStreamSerializer.builder().xStream(xStream).build();
    }

}
package dev.jinkela.demo.jinkelademo.datas.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;


@ReadingConverter
public class StringToEncryptionReadingConverter implements Converter<String, Encryption> {


    @Override
    public Encryption convert(String source) {
        return new Encryption();
    }

}

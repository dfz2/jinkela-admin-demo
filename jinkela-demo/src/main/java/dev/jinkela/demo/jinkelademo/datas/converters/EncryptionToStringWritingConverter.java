package dev.jinkela.demo.jinkelademo.datas.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;


@WritingConverter
public class EncryptionToStringWritingConverter implements Converter<Encryption, String> {


    @Override
    public String convert(Encryption source) {
        return source.getCipherText();
    }
}

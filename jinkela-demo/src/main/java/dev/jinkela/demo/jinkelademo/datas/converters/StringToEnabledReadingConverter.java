package dev.jinkela.demo.jinkelademo.datas.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class StringToEnabledReadingConverter implements Converter<String, Enabled> {

    @Override
    public Enabled convert(String source) {
        return new Enabled(source);
    }

}

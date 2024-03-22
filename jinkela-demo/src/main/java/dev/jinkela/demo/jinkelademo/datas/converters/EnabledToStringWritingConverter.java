package dev.jinkela.demo.jinkelademo.datas.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class EnabledToStringWritingConverter implements Converter<Enabled, String> {

    @Override
    public String convert(Enabled source) {
        return source.getValue();
    }
}

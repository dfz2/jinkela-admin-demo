package dev.jinkela.demo.jinkelademo.datas.converters;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Encryption {
    private String source;
    private String maskText;
    private String cipherText;
}

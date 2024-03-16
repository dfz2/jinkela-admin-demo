package dev.jinkela.demo.jinkelademo.utils;


import lombok.experimental.UtilityClass;

import java.util.Base64;

@UtilityClass
public final class Base64Util {

    public static String byteToBase64String(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(bytes);
        return  new String(encode);
    }


    public static byte[] base64StringToBytes(String base64String) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64String);
    }

}

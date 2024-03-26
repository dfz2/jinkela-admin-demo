package dev.jinkela.demo.jinkelademo.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;;

public class IdenticonsUtil {

  private IdenticonsUtil() {
    throw new IllegalStateException();
  }

  public static String createImageByIdStr(String idstr) throws Exception {

    String sha1Hex = DigestUtils.sha1Hex(idstr);
    char[] chars = sha1Hex.toCharArray();

    int cnt = 5;
    int size = 300;
    int rectSize = size / cnt;
    int lw = size / 2;

    BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_4BYTE_ABGR);
    Graphics g2 = bufferedImage.getGraphics();

    int idx = 0;
    for (int i = 0; i < cnt; i++) {
      for (int j = 0; j < cnt; j++) {
        int num = (int) chars[idx++];
        Color c = num % 2 != 0 ? Color.CYAN : Color.WHITE;
        g2.setColor(c);
        g2.fillRect(i * rectSize, j * rectSize, rectSize, rectSize);
      }
    }

    g2.dispose();

    BufferedImage limage = bufferedImage.getSubimage(0, 0, lw, size);
    int width = limage.getWidth();
    int height = limage.getHeight();

    for (int h = 0; h < height; h++) {
      int l = 0, r = width - 1;
      while (l < r) {
        int lrgb = limage.getRGB(l, h);
        int rrgb = limage.getRGB(r, h);
        bufferedImage.setRGB(lw + r, h, lrgb);
        bufferedImage.setRGB(lw + l, h, rrgb);
        l++;
        r--;
      }
    }



    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", bos);
    return String.format("data:image/png;base64,%s", Base64.encodeBase64String(bos.toByteArray()));
  }

  public static void main(String[] args) throws Exception {

    System.out.println(createImageByIdStr("superadmin"));;
  }

}

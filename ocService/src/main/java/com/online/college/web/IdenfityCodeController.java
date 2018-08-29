package com.online.college.web;

import com.online.college.common.web.SessionContext;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by tx on 2018/8/29.
 */
@Controller
@RequestMapping("/tools/identity")
public class IdenfityCodeController {

    @RequestMapping("/code")
    public void init(HttpServletRequest request, HttpServletResponse response){
        String random = RandomStringUtils.randomAlphabetic(4);
        //将生成的验证码放入session
        SessionContext.setAttribute(request,SessionContext.IDENTITY_CODE_KEY,random);
        response.setContentType("image/jpeg");
        response.addHeader("pragma","No-cache");
        response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        int width = 110,height = 33;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(new Color(225,225,225));
        Font font = new Font("SansSerif",Font.PLAIN,26);
        g.setFont(font);
        g.fillRect(0,0,width,height);
        //设置字体颜色
        g.setColor(Color.BLACK);
        g.drawString(random,20,25);
        g.dispose();

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image,"JPG",outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

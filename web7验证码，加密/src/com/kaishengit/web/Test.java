package com.kaishengit.web;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

public class Test {

	public static void main(String[] args)  throws IOException{
		
	/*	ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
		cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

		
		//修改验证码内容和长度
		RandomWordFactory wordfactory = new RandomWordFactory();
		wordfactory.setMinLength(4);
		wordfactory.setMaxLength(8);
		wordfactory.setCharacters("0123456789走开了十几年的，你的臭豆腐简单健康的接口");
		
		cs.setWordFactory(wordfactory);
		
		FontFactory factory = new FontFactory(){

			@Override
			public Font getFont(int i) {
				
				return new Font("微软雅黑", Font.BOLD, 34);
			}
			
		};
		
		cs.setFontFactory(factory);
		
		
		FileOutputStream fons = new FileOutputStream("F:/yanzheng.png");
		EncoderHelper.getChallangeAndWriteImage(cs, "png", fons);
		
		fons.flush();
		fons.close();*/

		/*String salt = "863e184623980./f,b m./cvljvbojg^^&&#$*&**(VND);";
		String password = "123456" + salt;
		//String src = DigestUtils.md5Hex(password);
		//e10adc3949ba59abbe56e057f20f883e
		//56480262fd5ffee0346cfd74c507f5f9
		
		String src = DigestUtils.sha256Hex(password);
		//8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92
		//253c9560c42e95aee3f46df06b2e3884ee6885332dc9ce64c42812c200335a57
		
		System.out.println(src);*/
		
		
		FileInputStream inputStream1 = new FileInputStream("F:/1.txt");
		FileInputStream inputStream2 = new FileInputStream("F:/2.txt");
		
		String src1 = DigestUtils.md5Hex(inputStream1);
		String src2 = DigestUtils.md5Hex(inputStream2);
		
		System.out.println(src1.equals(src2));
		
	}
}

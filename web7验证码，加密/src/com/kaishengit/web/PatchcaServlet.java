package com.kaishengit.web;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

@WebServlet("/yanzheng.png")
public class PatchcaServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
		cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
		cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

		
		//修改验证码内容和长度
		RandomWordFactory wordfactory = new RandomWordFactory();
		wordfactory.setMinLength(4);
		wordfactory.setMaxLength(8);
		wordfactory.setCharacters("0123456789");
		
		cs.setWordFactory(wordfactory);
		
		FontFactory factory = new FontFactory(){

			@Override
			public Font getFont(int i) {
				
				return new Font("微软雅黑", Font.BOLD, 34);
			}
			
		};
		
		cs.setFontFactory(factory);
		
		//!!!!!!!!!!!!!!! 通过Response对象获取响应输出流
		
		OutputStream outputstream = resp.getOutputStream();
		
		 String code =EncoderHelper.getChallangeAndWriteImage(cs, "png", outputstream);
		
		//将产生的验证码放入session
		
		HttpSession session = req.getSession();
		session.setAttribute("yanzheng",code );
		
		outputstream.flush();
		outputstream.close();
		
		
	}

}

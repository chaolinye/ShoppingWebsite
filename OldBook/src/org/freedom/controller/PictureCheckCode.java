package org.freedom.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 生成验证码
 * @author chaolin
 *
 */
@WebServlet(urlPatterns={"/checkCode"})
public class PictureCheckCode extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置不缓冲图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		//指定生成图片
		int width=86;
		int height=22;
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g=image.getGraphics();
		Graphics2D g2d=(Graphics2D)g;
		Random random=new Random();
		Font mFont=new Font("黑体",Font.BOLD,16);
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		g.setFont(mFont);
		g.setColor(getRandColor(180,200));
		//绘制100根颜色和位置全部为随机产生的线条,该线条为2f
		for(int i=0;i<100;i++){
			int x=random.nextInt(width-1);
			int y=random.nextInt(height-1);
			int x1=random.nextInt(6)+1;
			int y1=random.nextInt(12)+1;
			BasicStroke bs=new BasicStroke(2f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
			Line2D line=new Line2D.Double(x,y,x+x1,y+y1);
			g2d.setStroke(bs);
			g2d.draw(line);
		}
		//输出由英文、数字随机组成的验证文字
		String sRand="";
		String temp="";
		for(int i=0;i<4;i++){
			switch(random.nextInt(2)){
			case 1:
				int r=random.nextInt(26)+65;
				temp=String.valueOf((char)r);
				break;
			default:
				int t=random.nextInt(10);
				temp=String.valueOf(t);
				break;
			}
			sRand+=temp;
			Color color=new Color(20+random.nextInt(110),random.nextInt(110),20+random.nextInt(110));
			g.setColor(color);
			//将文字旋转到指定角度
			Graphics2D g2d_word=(Graphics2D)g;
			AffineTransform trans=new AffineTransform();
			trans.rotate(random.nextInt(45)*3.14/180,15*i+8,7);
			//缩放文字
			float scaleSize=random.nextFloat()+0.8f;
			if(scaleSize>1f)scaleSize=1f;
			trans.scale(scaleSize, scaleSize);
			g2d_word.setTransform(trans);
			g.drawString(temp, 15*i+18, 14);
		}
		//将生成的验证码保存到Session中
		HttpSession session=request.getSession();
		session.setAttribute("checkCode", sRand);
		//输出生成的验证码图片
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		
	}
	//生成随机的颜色
	private Color getRandColor(int s,int e){
		if(s>255) s=255;
		if(e>255) e=255;
		Random random=new Random();
		int r=s+random.nextInt(e-s);
		int g=s+random.nextInt(e-s);
		int b=s+random.nextInt(e-s);
		return new Color(r,g,b);
	}

}

package com.itext.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReadImg {
	
		public static void download(String urlString, String filename,String savePath) throws Exception {
		    // 构造URL
		    URL url = new URL(urlString);
		    //trustAllHosts(); // 开启全部证书信任
		    // 打开连接
		    URLConnection con = url.openConnection();
		    
		    //设置请求超时为5s
		    con.setConnectTimeout(5*1000);
		    // 输入流
		    InputStream is = con.getInputStream();
		
		    // 1K的数据缓冲
		    byte[] bs = new byte[1024];
		    // 读取到的数据长度
		    int len;
		    // 输出的文件流
		   File sf=new File(savePath);
		   if(!sf.exists()){
			   sf.mkdirs();
		   }
		   OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
		    // 开始读取
		    while ((len = is.read(bs)) != -1) {
		      os.write(bs, 0, len);
		    }
		    // 完毕，关闭所有链接
		    os.close();
		    is.close();
		} 
		

	
	public static void main(String[] args) throws Exception {
		
		String filePath = "https://www.indiapost.gov.in/VAS/Pages/trackconsignment.aspx";
		String imgPath = "";
		
		download(filePath, "test123.html","d:\\"); 

        String encoding="utf-8";
        File openFile=new File("d:\\test123.html");

        if(openFile.isFile() && openFile.exists()){ //判断文件是否存在

            InputStreamReader read = new InputStreamReader(new FileInputStream(openFile),encoding);//考虑到编码格式

            BufferedReader bufferedReader = new BufferedReader(read);

            String lineTxt = null;

            while((lineTxt = bufferedReader.readLine()) != null){

                if (lineTxt.indexOf("Captcha.aspx?Ran=") != -1){
                	imgPath = "https://www.indiapost.gov.in/" + lineTxt.substring(lineTxt.indexOf("src=\"../") + 8, lineTxt.indexOf("\" alt=\"")); 
                	System.out.println(imgPath);
                	break;
                }

            }

            read.close();
        }
        
        System.out.println("开始下载验证码");
		download(imgPath, "test123.gif","d:\\"); 
		File file = new File("d:\\test123.gif");
		
		
		BufferedImage img = ImageIO.read(file);
		int width = img.getWidth();
		int height = img.getHeight();
		
		//去除噪点和纯色干扰线
		for(int i = 1;i < width;i++){
			Color colorFirst = new Color(img.getRGB(i, 1));
			int numFirstGet = colorFirst.getRed()+colorFirst.getGreen()+colorFirst.getBlue();
			for (int x = 0; x < width; x++)
			   {
			  for (int y = 0; y < height; y++)
			   {
				  Color color = new Color(img.getRGB(x, y));
			          int num = color.getRed()+color.getGreen()+color.getBlue();

			          Color left = null;
			          int leftNum = 0;
			          
			          Color bottom = null;
			          int bottomNum = 0;
			          
			          Color right = null;
			          int rightNum = 0;
			          
			          Color top = null;
			          int topNum = 0;
			          //去除纯色干扰线
			          if (num == 510) {//干扰线RGB 黄色510
			        	  if (x-1 >= 0) {
				        	  left = new Color(img.getRGB(x-1, y));
				        	  leftNum = left.getRed()+left.getGreen()+left.getBlue();
			        	  }
			        	  if (y < height - 1) {
			        		  bottom  = new Color(img.getRGB(x, y+1));
			        		  bottomNum = bottom.getRed()+bottom.getGreen()+bottom.getBlue();
			        	  }
			        	  if (x < width - 1) {
				        	   right = new Color(img.getRGB(x+1, y));
				        	   rightNum = right.getRed()+right.getGreen()+right.getBlue();
			        	  }
			        	  if (y-1 >= 0) {
				        	   top = new Color(img.getRGB(x, y-1));
				        	   topNum = top.getRed()+top.getGreen()+top.getBlue();
			        	  }
			        	  
			        	  int tempNum = 509;//小于干扰线RGB 黄色510
			        	  
			        	 img.setRGB(x, y, Color.WHITE.getRGB());
			        	 if ((leftNum <= tempNum && rightNum <= tempNum) || (topNum <= tempNum && bottomNum <= tempNum) ||
			        			 (leftNum <= tempNum && bottomNum <= tempNum) || (bottomNum <= tempNum && rightNum <= tempNum) ||
			        			 (rightNum <= tempNum && topNum <= tempNum) || (topNum <= tempNum && leftNum <= tempNum)) {
			        		 img.setRGB(x, y, Color.BLACK.getRGB());
			        	 }
			          }
			          if(num >= numFirstGet){
			        	  img.setRGB(x, y, Color.WHITE.getRGB());
			          }
			      }
			  }
		}
		
		//文字和背景反色
		for(int i = 1;i<width;i++){
            Color color1 = new Color(img.getRGB(i, 1));
            int num1 = color1.getRed()+color1.getGreen()+color1.getBlue();
            for (int x = 0; x < width; x++)
            {
                for (int y = 0; y < height; y++)
                {
                    Color color = new Color(img.getRGB(x, y));
                    int num = color.getRed()+color.getGreen()+color.getBlue();
                    if(num == num1){
                        img.setRGB(x, y, Color.BLACK.getRGB());
                    }else{
                        img.setRGB(x, y, Color.WHITE.getRGB());
                    }
                }
            }
        }
		
		
		File file2 = new File("d:\\test456.gif");
        if (!file2.exists())
        {
            File dir = file2.getParentFile();
            if (!dir.exists())
            {
            dir.mkdirs();
            }
            try
            {
            	file2.createNewFile();
            }
            catch (IOException e)
            {
            e.printStackTrace();
            }
        }
        ImageIO.write(img, "gif", file2);
		
		
		Tesseract instance = new Tesseract(); 
        //instance.setDatapath("F:\\MyWorkSpaces\\STSEclipse\\Test2\\tessdata");//设置训练库的位置
        //将验证码图片的内容识别为字符串 
        try {
			String result = instance.doOCR(file2);
		
			System.out.println(result);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
}

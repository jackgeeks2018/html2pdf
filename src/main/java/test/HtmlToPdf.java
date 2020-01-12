package test;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huishi.HtmlGenerator;
import com.huishi.PDFUtils;
import com.huishi.QRCode;
import com.huishi.pdftoQRcode;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.element.Image;
import entity.ContractChecked;

/**
 * 利用HTML代码片段生成PDF
 *
 * zkh 2018年3月13日 下午2:05:17
 */
public class HtmlToPdf {
	private final static String DEST = "D:/factorContract.pdf";
	private final static String DESTW = "D:/factorContracttest.pdf";
	private final static String img = "D:/test.jpg";
	private final static String SRC = "./src/main/resources/template/factorContract.html";


	public static void main(String[] args) {
		try {
			Map<String,Object> map=new HashMap<>();
			map.put("C000C","1");
			map.put("C001C","1");
			map.put("C002C","1");
			map.put("C003C","1");
			map.put("C004C","1");
			map.put("C005C","1");
			map.put("C006C","1");
			List<ContractChecked> checkeds=new ArrayList<>();
			ContractChecked c=new ContractChecked();
			c.setContractCompleteId(1L);
			ContractChecked c1=new ContractChecked();
			c1.setContractCompleteId(14L);
			ContractChecked c2=new ContractChecked();
			c2.setContractCompleteId(14L);
			ContractChecked c3=new ContractChecked();
			c3.setContractCompleteId(14L);
			ContractChecked c4=new ContractChecked();
			c4.setContractCompleteId(4L);
			checkeds.add(c1);
			checkeds.add(c2);
			checkeds.add(c);
			checkeds.add(c3);
			checkeds.add(c4);
			checkeds.add(c1);
			checkeds.add(c1);
			map.put("list",checkeds);


			String s = HtmlGenerator.generate("T.html",map);
			 System.out.println(s);
			PDFUtils p=new PDFUtils();
			p.createPdf(readFile(SRC),new FileOutputStream(new File(DEST)));
		//	PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
			//Image img= QRCode.getQRCodeImage(pdf,"https://www.baidu.com",80,80);
//			QRCode.createQrCode(new File(img),"http://www.baidu.com",80,80);
//			pdftoQRcode.toPdf(DEST,DESTW,img,300,300);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	public  static   String readFile(String pathname) {
		StringBuffer html= new StringBuffer();
		try (FileReader reader = new FileReader(pathname);
			 BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
		) {
			String line;

			while ((line = br.readLine()) != null) {
				html.append(line);
				;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return  html.toString();
	}

}

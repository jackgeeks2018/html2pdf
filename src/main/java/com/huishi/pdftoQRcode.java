package com.huishi;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author shkstart
 * @create 2019-08-23 14:30
 */

public class pdftoQRcode {

    public static void toPdf(String templatePath , String newPDFPath, String imagePath, final float absoluteX, final float absoluteY) throws Exception {
        PdfReader reader = new PdfReader(templatePath);
        int pages = reader.getNumberOfPages();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        /* 将要生成的目标PDF文件名称 */
        PdfStamper ps = new PdfStamper(reader, bos);
        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf);
            PdfContentByte under = ps.getOverContent(1);
            PdfContentByte under2 = ps.getOverContent(pages);
            Image image = Image.getInstance(imagePath);
            // 根据域的大小缩放图片
            image.scaleToFit(80, 80);
            // 添加图片
            image.setAbsolutePosition(absoluteX, absoluteY);
            under.addImage(image);
            under2.addImage(image);
        /* 必须要调用这个，否则文档不会生成的 */
        ps.setFormFlattening(true);
        ps.close();


        java.io.OutputStream fos = new FileOutputStream(newPDFPath);
        fos.write(bos.toByteArray());
        fos.flush();
        fos.close();
        bos.close();
    }
}

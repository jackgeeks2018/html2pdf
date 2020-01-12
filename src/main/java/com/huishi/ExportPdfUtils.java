package com.huishi;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 * @author shkstart
 * @create 2019-08-15 10:03
 */

public class ExportPdfUtils {
    public static void toPdf(String templatePath , String newPDFPath, String imagePDFPath, Map<String, String> data,final float absoluteX, final float absoluteY) throws Exception {
        PdfReader reader = new PdfReader(templatePath);
        int pages = reader.getNumberOfPages();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        /* 将要生成的目标PDF文件名称 */
        PdfStamper ps = new PdfStamper(reader, bos);
        //ps.getUnderContent(1);

        /* 使用中文字体 */
       // ClassPathResource resource = new ClassPathResource("template" + File.separator + "国家导入模板.xlsx");
        //BaseFont bf = BaseFont.createFont(fontPath + ",0",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);//自定义字体
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf);

        /* 取出报表模板中的所有字段 */
        AcroFields fields = ps.getAcroFields();
        fields.setSubstitutionFonts(fontList);
        if (data!=null&&data.size()!=0) {
            fillData(fields, data);
        }
        for (int i = 1; i <=pages; i++) {
            PdfContentByte under = ps.getOverContent(i);
            Image image = Image.getInstance(imagePDFPath);
            // 根据域的大小缩放图片
            image.scaleToFit(50, 50);
            // 添加图片
            image.setAbsolutePosition(absoluteX, absoluteY);
            under.addImage(image);
        }
        /* 必须要调用这个，否则文档不会生成的 */
        ps.setFormFlattening(true);
        ps.close();


        java.io.OutputStream fos = new FileOutputStream(newPDFPath);
        fos.write(bos.toByteArray());
        fos.flush();
        fos.close();
        bos.close();
    }
    public static void fillData(AcroFields fields, Map<String, String> data)
            throws IOException, DocumentException {
        for (String key : data.keySet()) {
            String value = data.get(key);
            fields.setField(key, value); // 为字段赋值,注意字段名称是区分大小写的
        }
    }

}

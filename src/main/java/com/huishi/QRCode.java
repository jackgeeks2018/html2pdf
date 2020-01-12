package com.huishi;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.util.Hashtable;

/**
 * 二维码
 *
 * zkh
 * 2018年2月26日 下午3:55:14
 */
public class QRCode {

	/**
	 * 生成二维码图片
	 * 描述：黑色
	 * @param pdf
	 * @param content  例："{\"id\":\"467046859098fh8try0h8t\",\"name\":\"小明\"}"
	 * @param width
	 * @param height
	 * @return
	 */
	public static Image getQRCodeImage(PdfDocument pdf, String content, int width, int height) {
		// 生成二维码
        BarcodeQRCode qrcode = new BarcodeQRCode(content);
        // 生成二维码图片
        Image image = new Image(qrcode.createFormXObject(ColorConstants.BLACK, pdf));
        // 设置大小
        image.scaleToFit(width, height);
        return image;
	}
	public static void createQrCode(File imageputFile, String content, int width, int height) {

		try {
			String format = "jpg";// 二维码的图片格式
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			// 生成二维码
			MatrixToImageWriter.writeToFile(bitMatrix, format, imageputFile);
		} catch (Exception e) {
			//log.error("生成二维码发生错误：", e);
		}

	}


}

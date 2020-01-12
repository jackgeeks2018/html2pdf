package com.huishi;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.IOException;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.font.FontProvider;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/**
 * 
 * @author ethan
 *
 */
public class PDFUtils {
	
    public void createPdf(String src, OutputStream outputStream) throws IOException {
        try {

            WriterProperties writerProperties = new WriterProperties();
            writerProperties.addXmpMetadata();
            PdfWriter pdfWriter = new PdfWriter(outputStream, writerProperties);
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            pdfDoc.getPage(1);

            pdfDoc.getCatalog().setLang(new PdfString("UTF-8"));
            pdfDoc.setTagged();
            pdfDoc.getCatalog().setViewerPreferences(new PdfViewerPreferences().setDisplayDocTitle(true));
			pdfDoc.setDefaultPageSize( new PageSize(595, 1000));
            // 
            PdfFont font = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);
            Image i=QRCode.getQRCodeImage(pdfDoc,"https://www.baidu.com",80,80);
           this.setHeaderAndFooter(pdfDoc, font,i);

            // pdf conversion
            ConverterProperties props = new ConverterProperties();
            FontProvider fp = new FontProvider();
            fp.addStandardPdfFonts();
            fp.addSystemFonts();
            props.setFontProvider(fp);

            HtmlConverter.convertToPdf(new ByteArrayInputStream(src.getBytes("UTF-8")), pdfDoc, props);
            
            pdfDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param pdf
     * @param font
     */
    private void setHeaderAndFooter(PdfDocument pdf, PdfFont font,Image img ) {
    	pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new IEventHandler() {
			@Override
			public void handleEvent(Event event) {
				// Retrieve document and
	            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
	            PdfDocument pdf = docEvent.getDocument();
	           
	            PdfPage page = docEvent.getPage();
	            Rectangle pageSize = page.getPageSize();
	           // pageSize.setHeight(300);
	            PdfCanvas pdfCanvas = new PdfCanvas(page.getLastContentStream(), page.getResources(), pdf);
	            Canvas canvas = new Canvas(pdfCanvas, pdf, pageSize);
	            canvas.setFont(font);

	            // logo
	            try {
	            	//ImageData image = ImageDataFactory.create(getClass().getClassLoader().getResource("static/logo.png").getPath());
	                img.scaleAbsolute(80, 80);
	                img.setFixedPosition(400, pageSize.getHeight() - 100);
	                canvas.add(img);
	            } catch (Exception e) {
					e.printStackTrace();
				}
	            

			}
		});
    }
    
}

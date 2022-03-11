import java.io.File;
import java.io.IOException;   

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.PdfUtilities;
  
public class Main {  
  
    public static void main(String[] args)throws IOException {
    	Tesseract tesseract = new Tesseract();
    	tesseract.setTessVariable("user_defined_dpi", "300");
    	tesseract.setDatapath("tessdata");
    	
        File file = new File("mag.pdf");
        
        System.out.println("PDF has been loaded...");
    	try {
    		String text = tesseract.doOCR(PdfUtilities.convertPdf2Tiff(file));
    		System.out.print(text);
    	}
    	catch (TesseractException e){
    		e.printStackTrace();
    	}
        
  
        
        System.out.println("Done");
    }  
}  
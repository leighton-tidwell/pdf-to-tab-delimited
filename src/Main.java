import java.io.File;
import java.io.FileWriter;
import java.io.IOException;   

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.PdfUtilities;
  
public class Main {  
  
    public static void main(String[] args) throws IOException {
    	Tesseract tesseract = new Tesseract();
    	tesseract.setTessVariable("user_defined_dpi", "300");
    	tesseract.setDatapath("tessdata");
    	
    	System.out.println("Looking for ./mag.pdf...");
    	File file = new File("mag.pdf");
    	
        System.out.println("PDF has been loaded...");
        String text = getPdfText(tesseract, file);
        
        if(text != null) {
        	String[] lines = text.split("\\s+");
        	String tabDelimited = String.join("\t", lines);
        	FileWriter output = new FileWriter("output.txt");
        	output.write(tabDelimited);
        	
        	output.close();
        	System.out.println("Done.");        	
        }
    }
    
    private static String getPdfText(Tesseract tess, File file) throws IOException {
    	try {
    		String text = tess.doOCR(PdfUtilities.convertPdf2Tiff(file));
    		return text;
    	}
    	catch (TesseractException e){
    		e.printStackTrace();
    	}
		return null;
    }
}  
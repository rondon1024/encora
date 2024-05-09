
package com.prueba.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GenerateWord {

	private static final String PATH_RELATIVE_WORD  = "/src/test/resources/template/Evidencia.docx";
	private static final String TEMPLATE            = "/src/test/resources/template/Plantilla.jpg";
	private static final String WORD_NAME_STANDAR   = "Evidencia.docx";
	private static final String FILE_PATH_STANDAR   = getProjectFolder() + "/target/resultado/";
	private static final String FILE_TEMP           = System.getProperty("java.io.tmpdir");
	private String TEMP_WORD_FILE;
	public static XWPFDocument document ;
	public static XWPFParagraph paragraph ;
	public static XWPFRun run;
	public static FileOutputStream fileOutputStream;
	 private static GenerateWord server;
	 
	public static String getProjectFolder() {

        return System.getProperty("user.dir");
    }
	
	public static GenerateWord getInstance() {

        if(server==null) {
            server=new GenerateWord();
        }

        return server;
    }

    public static void borrarElementosFolder(String carpeta) {

        // al iniciar borrar todos los archivos png de la carpeta img
        File folder = new File(getProjectFolder() + carpeta);
        if (folder.exists() && folder.isDirectory()) {
            try {
                FileUtils.cleanDirectory(folder);
            } catch (Exception e) {
                System.out.println("[ERROR CRL-946] Error borrando la carpeta " + carpeta + ": " + e.getMessage());
            }
        }
    }


	public void startUpWord(String name) {

		try {
			File fileUnique = new File(getProjectFolder() + PATH_RELATIVE_WORD);

			copyExistentWord(fileUnique);

			document = new XWPFDocument();

			paragraph = document.createParagraph();

			run = paragraph.createRun();

			String carpeta = FILE_PATH_STANDAR;

			FileUtils.forceMkdir(new File(carpeta));
  
		    run = paragraph.createRun();
		    
		    String imgFile = getProjectFolder() + TEMPLATE;
		    FileInputStream is = new FileInputStream(getProjectFolder() + TEMPLATE);
		    run.addBreak();
		    run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(400), Units.toEMU(640)); // 200x200 pixels
		    run.addBreak();
		    run.addBreak();
		    run.addBreak();
		    TEMP_WORD_FILE = FileUtils.getFile(carpeta) + "/"+name +"-" + generarSecuencia() + ".docx";
	        fileOutputStream = new FileOutputStream(TEMP_WORD_FILE);
		    
			
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("[LOG] Word generado");
	}

	public void sendBreak(){run.addBreak();}

	public void copyExistentWord(File file) {

		InputStream inputStream;

		OutputStream outputStream;

		try {

			File fileUnique = new File(file.getPath());

			File copyFile = new File(WORD_NAME_STANDAR);

			inputStream = new FileInputStream(fileUnique);

			outputStream = new FileOutputStream(copyFile);

			byte[] buffer = new byte[1024];

			int length;

			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}

			inputStream.close();

			outputStream.close();

		} catch (IOException r) {

			r.printStackTrace();

		}
	}



    public void addImageToWord(WebDriver driver) {
        try {
            TakesScreenshot screenshot  = ((TakesScreenshot)driver);
            File source                 = screenshot.getScreenshotAs(OutputType.FILE);
            InputStream inputStream  = new FileInputStream(source);
            paragraph = document.createParagraph(); //agregado
            run = paragraph.createRun();//agregado
            paragraph.setAlignment(ParagraphAlignment.CENTER );//agregado
            run.addPicture(inputStream, Document.PICTURE_TYPE_PNG, "1", Units.toEMU(450), Units.toEMU(250));//tamaño de las capturas del word

            run.addBreak();
            run.addBreak();
    		

        } catch (Exception e) {

            e.printStackTrace();

        }



    }
	

    public void sendText(String texto)   {
    	   //getInstance();
//    	   paragraph = document.createParagraph();//agregado
//           run = paragraph.createRun();//agregado
           
    	
    	  run.setText("Fecha : " +generarFecha() + ", Hora : " + generarHora() + " | " + texto);
    		
           run.addTab();
           run.setFontFamily("Century Gothic");
           run.setFontSize(9);
           run.addBreak();
          

       }
    
    
	public void sendBoldText(String texto)  {
		run.setBold(true);
		run.setText("Fecha : " +generarFecha() + ", Hora : " + generarHora() + " | " + texto);
		run.addTab();
		run.setFontFamily("Century Gothic");
		run.setFontSize(9);
	}


	//// armado del Nombre archivo Word
	public void endToWord(String status) throws IOException   {
		try {

			document.write(fileOutputStream);

			File fileWithNewName = new File(TEMP_WORD_FILE.split("\\.docx")[0]+"-"+status.toUpperCase()+".docx");

			if(new File(TEMP_WORD_FILE).renameTo(fileWithNewName)) {

				System.out.println("[LOG] WORD: Evidencia renombrada - Se añadió el estado final del escenario");
			} else {

				System.out.println("[LOG] WORD: Evidencia no pudo ser renombrada - No Se añadió el estado final del escenario");

			}

			File file = new File(getProjectFolder() + "/Evidencia2.docx");

			if (file.exists()){ file.delete(); }

			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("[LOG] Word cerrado");
	}

	private static String generarSecuencia() {

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");

		df.setTimeZone(TimeZone.getTimeZone("America/Bogota"));

		return df.format(new Date());

	}

	private static String generarFecha() {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		df.setTimeZone(TimeZone.getTimeZone("America/Bogota"));

		return df.format(new Date());

	}

	private static String generarHora(){
		DateFormat df = new SimpleDateFormat("hh:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("America/Bogota"));

		return df.format(new Date());
	}

}

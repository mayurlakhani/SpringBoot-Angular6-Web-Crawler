package com.web.restaurant.model;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Reader {

    private PDFParser parser;
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc;
    private COSDocument cosDoc;

    private String Text;
    private String filePath;
    private File file;

    public Reader() {

    }

    public String ToText() throws IOException {
        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;

        file = new File(filePath);
        parser = new PDFParser(new RandomAccessFile(file, "r")); // update for PDFBox V 2.0

        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        int count = pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(2);
        pdfStripper.setEndPage(count);
       
       /*Rectangle2D region = new Rectangle2D.Double(0, 0, 100, 200);
       String regionName = "region";
       PDFTextStripperByArea stripper;

       stripper = new PDFTextStripperByArea();
       stripper.addRegion(regionName, region);
       
       PDPage doc = pdDoc.getPage(0);
       stripper.extractRegions(doc);*/

        //---------------
       /*
       List allPages = pdDoc.getDocumentCatalog().get;

            for (int i = 0; i < allPages.size(); i++) {
                PDPage page = (PDPage) allPages.get(i);
                PDStream contents = page.getContents();

                if (contents != null) {
                    printer.processStream(page, page.findResources(), page.getContents().getStream());
                }
                pageNo += 1;
            }
       */
        //------------------------------------------------------
        // reading text from page 1 to 10
        // if you want to get text from full pdf file use this code
        // pdfStripper.setEndPage(pdDoc.getNumberOfPages());

        Text = pdfStripper.getText(pdDoc);
        // Text = stripper.extractRegions(doc);
        return Text;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

package com.ocr.controller;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/scan")
public class FileScanController {

    @RequestMapping(value = "/document", method = RequestMethod.POST)
    @ResponseBody
    public String singleFileScan(@RequestParam("file")MultipartFile file) throws IOException, TesseractException {


        File convFile = convertFile(file);

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("src\\main\\resources\\testdata");
        tesseract.setLanguage("eng");
        String text = tesseract.doOCR(convFile);

        return text;
    }

    private File convertFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream io = new FileOutputStream(convFile);
        io.write(file.getBytes());
        io.close();
        return convFile;
    }
}

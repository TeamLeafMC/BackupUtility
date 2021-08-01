package net.mov51.helpers;

import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static net.mov51.helpers.dateHelper.getFileSafeDate;
import static net.mov51.helpers.fileHelper.isDirEmpty;
import static net.mov51.helpers.logHelper.*;

public class archiveHelper {

    private static final org.apache.logging.log4j.Logger Logger = LogManager.getLogger("Archive_logger");

    public static void archive(String sourceFile,String archiveName,String backupLocation){
        //todo return bool
        logInfo(Logger,"Starting archival!");

        try{
            if(isDirEmpty(Paths.get(sourceFile))){
                //todo use archive failsafe
                logError(Logger,"Archive Source Directory is empty! Skipping this backup cycle!");
                //return false
            }
        }catch (IOException e) {
            //todo use archive failsafe
            logFatalE(Logger,e,"Archive source could not be checked!");
            //return false
        }

        if(Paths.get(backupLocation).toFile().mkdirs()){
            logInfo(Logger,"Backup location " + backupLocation + " created!");
        }

        Path finalArchiveName = Paths.get(backupLocation + File.separator + archiveName + getFileSafeDate() + ".zip");
        try {

            FileOutputStream fos = new FileOutputStream(String.valueOf(finalArchiveName));
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);

            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
            if(finalArchiveName.toFile().exists()){
                logInfo(Logger,"Archive " + archiveName + " successfully created with full file name " + finalArchiveName);
                //return true
            }else{
                //todo use archive failsafe
                logFatal(Logger,"Archive " + archiveName + " could not be created!");
                //return false
            }
        }catch (IOException e) {
            //todo use archive failsafe
            logFatalE(Logger,e,"Archive file could not be created!");
            //return false
        }
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) {
        try {
            if (fileToZip.isHidden()) {
                return;
            }
            if (fileToZip.isDirectory()) {
                if (fileName.endsWith("/")) {
                    zipOut.putNextEntry(new ZipEntry(fileName));
                } else {
                    zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                }
                zipOut.closeEntry();
                File[] children = fileToZip.listFiles();
                if (children != null) {
                    for (File childFile : children) {
                        zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                    }
                }
                return;
            }
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        } catch (IOException e) {
            //todo use archive failsafe
            logFatalE(Logger,e,"File " + fileName + " could not be zipped!");
        }
    }
}

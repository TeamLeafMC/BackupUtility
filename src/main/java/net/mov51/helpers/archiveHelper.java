package net.mov51.helpers;

import java.io.*;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static net.mov51.helpers.dateHelper.getFileSafeDate;

public class archiveHelper {
    public static void archive(String sourceFile,String archiveName){
        System.out.println("Starting archival!");
        String finalArchiveName = archiveName + getFileSafeDate() + ".zip";
        try {
            FileOutputStream fos = new FileOutputStream(finalArchiveName);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);

            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
            if(Paths.get(finalArchiveName).toFile().exists()){
                System.out.println("Archive " + archiveName + " successfully created with full file name " + finalArchiveName);
            }
        }catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

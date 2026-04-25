package org.example.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FileUtils {

    public static File getLatestFile(String folderPath){
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            LogsUtil.warn("Folder is empty or does not exist: " + folderPath);
            return null;
        }
        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        return latestFile;
    }


    public static void deleteFiles(File directoryPath) {
        if (directoryPath == null || !directoryPath.exists()) {
            LogsUtil.warn("Directory path is null or empty."+ directoryPath);
            return;
        }
        File[] fileList = directoryPath.listFiles();
        if (fileList == null){
            LogsUtil.warn("Directory does not exist or is not a directory: " + directoryPath);
            return;
        }
        for (File file : fileList) {
            if (file.isDirectory()) {
                deleteFiles(file);
            } else {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    LogsUtil.error("Error deleting file: " + file);
                }
            }
        }
    }

    public static void cleanDirectory(File logs) {
        try {
            org.apache.commons.io.FileUtils.cleanDirectory(logs);
        } catch (Exception exception) {
            LogsUtil.error(exception.getMessage());
        }
    }
}

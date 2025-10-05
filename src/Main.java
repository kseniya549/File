
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        // Укажите путь к папке Games в зависимости от вашей операционной системы
//        String gamesPath = "C:\\Users\\1\\IdeaProjects\\File\\Games\\";
//
//        StringBuilder log = new StringBuilder();
//
//        try {
//            // Создаем основную директорию Games (если она еще не существует)
//            File gamesDir = new File(gamesPath);
//            if (!gamesDir.exists()) {
//                GameInstaller.mkdir(gamesDir, log);
//            } else {
//                log.append("Каталог ").append(gamesPath).append(" уже существует\n");
//            }
//
//            // Создаем поддиректории в Games: src, res, savegames, temp
//            String[] subDirs = {"src", "res", "savegames", "temp"};
//            for (String dirName : subDirs) {
//                File dir = new File(gamesPath, dirName);
//                GameInstaller.mkdir(dir, log);
//            }
//
//            // Создаем поддиректории в src: main, test
//            File srcDir = new File(gamesPath, "src");
//            String[] srcSubDirs = {"main", "test"};
//            for (String dirName : srcSubDirs) {
//                File dir = new File(srcDir, dirName);
//                GameInstaller.mkdir(dir, log);
//            }
//
//            // Создаем файлы в main: Main.java, Utils.java
//            File mainDir = new File(srcDir, "main");
//            String[] mainFiles = {"Main.java", "Utils.java"};
//            for (String fileName : mainFiles) {
//                File file = new File(mainDir, fileName);
//                GameInstaller.createFile(file, log);
//            }
//
//            // Создаем поддиректории в res: drawables, vectors, icons
//            File resDir = new File(gamesPath, "res");
//            String[] resSubDirs = {"drawables", "vectors", "icons"};
//            for (String dirName : resSubDirs) {
//                File dir = new File(resDir, dirName);
//                GameInstaller.mkdir(dir, log);
//            }
//
//            // Создаем файл temp.txt в директории temp
//            File tempDir = new File(gamesPath, "temp");
//            File tempFile = new File(tempDir, "temp.txt");
//            GameInstaller.createFile(tempFile, log);
//
//            // Записываем лог в файл temp.txt
//            try (FileWriter writer = new FileWriter(tempFile)) {
//                writer.write(log.toString());
//                System.out.println("Установка завершена успешно!");
//                System.out.println("Лог записан в файл: " + tempFile.getAbsolutePath());
//            }
//
//        } catch (IOException e) {
//            System.err.println("Ошибка при работе с файловой системой: " + e.getMessage());
//            e.printStackTrace();
//        }
        String savePath = "C:\\Users\\1\\IdeaProjects\\File\\Games\\savegames";
        GameProgress game = new GameProgress(100, 5, 10, 120.5);
        GameProgress game1 = new GameProgress(90, 7, 5, 90.2);
        GameProgress game2 = new GameProgress(80, 3, 3, 50.0);

        List<String> saveFiles = new ArrayList<>();
        saveFiles.add(savePath + "/save.data");
        saveFiles.add(savePath + "/save1.data");
        saveFiles.add(savePath + "/save2.data");

        new File(savePath).mkdirs();

        saveGame(saveFiles.get(0), game);
        saveGame(saveFiles.get(1), game1);
        saveGame(saveFiles.get(2), game2);

        String zipFilesPath = savePath + "/saves.zip";
        zipFiles(zipFilesPath, saveFiles);
        for (String filesPath:saveFiles) {
            File file = new File(filesPath);
            if (file.exists()) {
                boolean deleted = file.delete();
                if(deleted) {
                    System.out.println("Фаил удалён" + zipFilesPath);
                } else {
                    System.out.println("Фаил не удалён" + zipFilesPath);
                }

            }
            
        }
        System.out.println("Операция завершена. Сохранения заархивированы в " + zipFilesPath);

    }






    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
            System.out.println("Сохранение записано: " + filePath);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void zipFiles(String zipFilePath, List<String> filePaths) {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (String filePath : filePaths) {
                File file = new File(filePath);
                if (!file.exists()) {
                    System.out.println("Файл не найден и пропущен: " + filePath);
                    continue;
                }

                try (FileInputStream fis = new FileInputStream(file)) {
                    // Создаем запись в архиве с именем файла (без пути)
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    // Копируем содержимое файла в архив
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                    System.out.println("Файл добавлен в архив: " + file.getName());
                } catch (IOException e) {
                    System.err.println("Ошибка при добавлении файла в архив " + filePath + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при создании архива " + zipFilePath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
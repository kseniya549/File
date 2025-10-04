
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Укажите путь к папке Games в зависимости от вашей операционной системы
        String gamesPath = "C:\\Users\\1\\IdeaProjects\\File\\Games\\";

        StringBuilder log = new StringBuilder();

        try {
            // Создаем основную директорию Games (если она еще не существует)
            File gamesDir = new File(gamesPath);
            if (!gamesDir.exists()) {
                GameInstaller.mkdir(gamesDir, log);
            } else {
                log.append("Каталог ").append(gamesPath).append(" уже существует\n");
            }

            // Создаем поддиректории в Games: src, res, savegames, temp
            String[] subDirs = {"src", "res", "savegames", "temp"};
            for (String dirName : subDirs) {
                File dir = new File(gamesPath, dirName);
                GameInstaller.mkdir(dir, log);
            }

            // Создаем поддиректории в src: main, test
            File srcDir = new File(gamesPath, "src");
            String[] srcSubDirs = {"main", "test"};
            for (String dirName : srcSubDirs) {
                File dir = new File(srcDir, dirName);
                GameInstaller.mkdir(dir, log);
            }

            // Создаем файлы в main: Main.java, Utils.java
            File mainDir = new File(srcDir, "main");
            String[] mainFiles = {"Main.java", "Utils.java"};
            for (String fileName : mainFiles) {
                File file = new File(mainDir, fileName);
                GameInstaller.createFile(file, log);
            }

            // Создаем поддиректории в res: drawables, vectors, icons
            File resDir = new File(gamesPath, "res");
            String[] resSubDirs = {"drawables", "vectors", "icons"};
            for (String dirName : resSubDirs) {
                File dir = new File(resDir, dirName);
                GameInstaller.mkdir(dir, log);
            }

            // Создаем файл temp.txt в директории temp
            File tempDir = new File(gamesPath, "temp");
            File tempFile = new File(tempDir, "temp.txt");
            GameInstaller.createFile(tempFile, log);

            // Записываем лог в файл temp.txt
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(log.toString());
                System.out.println("Установка завершена успешно!");
                System.out.println("Лог записан в файл: " + tempFile.getAbsolutePath());
            }

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файловой системой: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
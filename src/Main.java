import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args) {
        String gamesPath = "C:\\Users\\1\\IdeaProjects\\File\\Games";

        // Создаем StringBuilder для логирования
        StringBuilder log = new StringBuilder();

        try {
            // Создаем основную директорию Games
            File gamesDir = new File(gamesPath);
            if (!gamesDir.exists()) {
                if (gamesDir.mkdir()) {
                    log.append("Каталог ").append(gamesPath).append(" создан успешно\n");
                } else {
                    log.append("Не удалось создать каталог ").append(gamesPath).append("\n");
                }
            } else {
                log.append("Каталог ").append(gamesPath).append(" уже существует\n");
            }

            // Создаем поддиректории в Games: src, res, savegames, temp
            String[] subDirs = {"src", "res", "savegames", "temp"};
            for (String dirName : subDirs) {
                File dir = new File(gamesPath + File.separator + dirName);
                if (dir.mkdir()) {
                    log.append("Каталог ").append(dir.getAbsolutePath()).append(" создан успешно\n");
                } else {
                    log.append("Не удалось создать каталог ").append(dir.getAbsolutePath()).append("\n");
                }
            }

            // Создаем поддиректории в src: main, test
            String srcPath = gamesPath + File.separator + "src";
            String[] srcSubDirs = {"main", "test"};
            for (String dirName : srcSubDirs) {
                File dir = new File(srcPath + File.separator + dirName);
                if (dir.mkdir()) {
                    log.append("Каталог ").append(dir.getAbsolutePath()).append(" создан успешно\n");
                } else {
                    log.append("Не удалось создать каталог ").append(dir.getAbsolutePath()).append("\n");
                }
            }

            // Создаем файлы в main: Main.java, Utils.java
            String mainPath = srcPath + File.separator + "main";
            String[] mainFiles = {"Main.java", "Utils.java"};
            for (String fileName : mainFiles) {
                File file = new File(mainPath + File.separator + fileName);
                if (file.createNewFile()) {
                    log.append("Файл ").append(file.getAbsolutePath()).append(" создан успешно\n");
                } else {
                    log.append("Не удалось создать файл ").append(file.getAbsolutePath()).append("\n");
                }
            }

            // Создаем поддиректории в res: drawables, vectors, icons
            String resPath = gamesPath + File.separator + "res";
            String[] resSubDirs = {"drawables", "vectors", "icons"};
            for (String dirName : resSubDirs) {
                File dir = new File(resPath + File.separator + dirName);
                if (dir.mkdir()) {
                    log.append("Каталог ").append(dir.getAbsolutePath()).append(" создан успешно\n");
                } else {
                    log.append("Не удалось создать каталог ").append(dir.getAbsolutePath()).append("\n");
                }
            }

            // Создаем файл temp.txt в директории temp
            String tempPath = gamesPath + File.separator + "temp";
            File tempFile = new File(tempPath + File.separator + "temp.txt");
            if (tempFile.createNewFile()) {
                log.append("Файл ").append(tempFile.getAbsolutePath()).append(" создан успешно\n");
            } else {
                log.append("Не удалось создать файл ").append(tempFile.getAbsolutePath()).append("\n");
            }

            // Записываем лог в файл temp.txt
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(log.toString());
                System.out.println("Установка завершена успешно!");
                System.out.println("Лог записан в файл: " + tempFile.getAbsolutePath());
            }

        } catch (IOException ex) {
            System.err.println("Ошибка при работе с файловой системой: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
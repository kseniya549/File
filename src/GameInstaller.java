import java.io.File;
import java.io.IOException;

public class GameInstaller {

    // Метод для создания директории с логированием
    public static void mkdir(File dir, StringBuilder log) {
        if (dir.mkdir()) {
            log.append("Каталог ").append(dir.getAbsolutePath()).append(" создан успешно\n");
        } else {
            log.append("Не удалось создать каталог ").append(dir.getAbsolutePath()).append("\n");
        }
    }

    // Метод для создания файла с логированием
    public static void createFile(File file, StringBuilder log) throws IOException {
        if (file.createNewFile()) {
            log.append("Файл ").append(file.getAbsolutePath()).append(" создан успешно\n");
        } else {
            log.append("Не удалось создать файл ").append(file.getAbsolutePath()).append("\n");
        }
    }
}
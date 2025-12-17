package ua.volyn.vcolnuft;

import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter {

    public static void write(ApiResponse response) {
        // Відкриття файлу в режимі додавання
        try (FileWriter writer = new FileWriter("fact.csv", true)) {

            // Якщо файл порожній — додати заголовки
            if (new java.io.File("fact.csv").length() == 0) {
                writer.write("fact,length\n");
            }
            // Запис даних з екрануванням
            writer.write("\"" + response.fact().replace("\"", "'") + "\"," + response.length() + "\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package ua.volyn.vcolnuft;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

public class HttpDemoClient {

    public static void main(String[] args) {

        Unirest.config()
                .connectTimeout(5000) // Захист від зависання
                .setObjectMapper(new kong.unirest.core.ObjectMapper() {

                    final ObjectMapper mapper = new ObjectMapper();

                    @Override
                    public String writeValue(Object value) {
                        try {
                            return mapper.writeValueAsString(value);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public <T> T readValue(String value, Class<T> valueType) {
                        try {
                            return mapper.readValue(value, valueType);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
                .setDefaultHeader("Accept", "application/json"); // Вказуємо формат відповіді

        // GET-запит
        HttpResponse<ApiResponse> response = Unirest
                .get("https://catfact.ninja/fact")
                .asObject(ApiResponse.class);

        // Перевірка успіху
        if (response.isSuccess()) {
            ApiResponse body = response.getBody();
            // Вивід результатів в консоль
            System.out.println("Факт про котів: " + body.fact());
            System.out.println("Довжина: " + body.length());
            // Збереження в CSV файл
            CsvWriter.write(body);
            System.out.println("Файл fact.csv оновлено!");
        } else {
            // Обробка помилок HTTP
            System.out.println("Помилка запиту: " + response.getStatus());
        }
    }
}

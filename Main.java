import java.io.FileWriter;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        ex1(ex0());
    }

    static StringBuilder ex0() {
    /*
    Дана json строка {{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
    {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
    {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}} Задача написать метод(ы),
    который распарсить строку и выдаст ответ вида: Студент Иванов получил 5 по предмету
    Математика. Студент Петрова получил 4 по предмету Информатика. Студент Краснов
    получил 5 по предмету Физика. Используйте StringBuilder для подготовки ответа
    */
        String str = "{{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}}";

        for (String s : Arrays.asList("фамилия", "оценка", "предмет")) {
            str = str.replace(s, "");
        }
        str = str.replace(":", "");
        str = str.replace("\"", "");
        str = str.replace("{", "");
        str = str.replace("}}", "");
        String[] array = str.split("},");
        String[] textArray = {"Студент ", " получил ", " по предмету "};
        StringBuilder result = new StringBuilder();
        for (String s : array) {
            array = s.split(",");
            for (int j = 0; j < textArray.length; j++) {
                result.append(textArray[j]);
                result.append(array[j]);
            }
            result.append(". ");
        }
        System.out.println(result);
        return result;
    }

    static void ex1(StringBuilder result) {
    /*
    Создать метод, который запишет результат работы в файл Обработайте исключения и запишите ошибки в лог файл
    */
        Logger logger = Logger.getAnonymousLogger();
        SimpleFormatter formatter = new SimpleFormatter();
        try {
            FileHandler file_Handler = new FileHandler("log.txt");
            file_Handler.setFormatter(formatter);
            logger.addHandler(file_Handler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            FileWriter writer = new FileWriter("text.txt", true);
            writer.write(result + "\n");
            writer.close();
            logger.log(Level.INFO, "Программа работает корректно");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "Возникла ошибка");
        }
    }
}
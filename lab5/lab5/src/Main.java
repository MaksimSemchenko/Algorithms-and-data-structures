import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        // Валидация телефонного номера
        String phoneNumber = "(+380)50-333-33-33";
        String phoneRegex = "^\\(\\+380\\)\\d{2}-\\d{3}-\\d{2}-\\d{2}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher phoneMatcher = phonePattern.matcher(phoneNumber);
        if (phoneMatcher.matches()) {
            System.out.println("Телефонний номер є валідним.");
        } else {
            System.out.println("Телефонний номер є недійсним.");
        }

        // Замена нескольких пробелов на один
        String input = "Я хочу    бути     програмістом";
        String spacesRegex = "\\s+";
        String output = input.replaceAll(spacesRegex, " ");
        System.out.println(output); // "Я хочу бути програмістом"

        // Удаление HTML тегов
        String htmlInput = "<div>\n<p>Символи круглих дужок <code>'('</code> та <code>')'</code>. <br />Ці символи" +
                "\nдозволяють отримати з рядка додаткову інформацію." +
                "\n<br/>Зазвичай, якщо парсер регулярних виразів шукає в тексті інформацію" +
                "\nза заданим виразом і знаходить її - він просто повертає" +
                "\nзнайдений рядок.</p>" +
                "\n<p align=\"right\">А ось тут <a href=\"google.com\">посилання</a>, щоб життя медом не здавалося.</p>" +
                "\n</div>";
        String tagsRegex = "<[^>]*>";
        String htmlOutput = htmlInput.replaceAll(tagsRegex, "");
        System.out.println(htmlOutput); // "Символи круглих дужок '(' та ')'....
    }
}

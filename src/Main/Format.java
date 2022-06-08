package Main;

import java.util.Locale;

public class Format {
    static String name(String next) {
        next = next.replace(" ","");
        next.toLowerCase(Locale.ROOT);
        return next;
    }
    static String phone(String next) {
        next = next.replace(" ","");
        next = next.replace("-","");
        return next;
    }
    static String menu(String next) {
        next = next.replace(" ","");
        return next;
    }

    static int price(String next) {
        next = next.replace(",","");
        next = next.replace(" ", "");
        return Integer.parseInt(next);
    }

    static int rating(String next) {
        next = next.replace(" ", "");
        return Integer.parseInt((next));
    }


}

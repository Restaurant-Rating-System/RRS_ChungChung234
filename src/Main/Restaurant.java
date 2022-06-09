package Main;

import java.io.IOException;
import java.util.*;

public class Restaurant implements Comparable<Restaurant>{
    private static String[] temp;
    String name;
    String phone;
    String signature_Menu;
    int menu_Price;
    int rating;

    static Scanner scanner = new Scanner(System.in);

    /**
     *레스토랑 객체
     * @param name String  식당명
     * @param phone String 전화번호
     * @param signature_Menu    String 시그니처메뉴
     * @param menu_Price    int 메뉴 가격
     * @param rating    int 평점
     */
    Restaurant(String name, String phone, String signature_Menu, int menu_Price, int rating){
        this.name = name;
        this.phone = phone;
        this.signature_Menu=signature_Menu;
        this.menu_Price = menu_Price;
        this.rating = rating;
    }

    public static void pick_Me_One() throws IOException {
        Restaurant restaurant = FileIO.random_Restaurant();
        if(restaurant != null){
            System.out.println(
                    restaurant.getString()
            );
        }else {
            System.out.println("Can't find Restaurant Name");
        }
        System.out.println("Enter 'X' Go Menu");
        String _temp = scanner.next();
    }

    public int getMenu_Price() {
        return menu_Price;
    }

    public long getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSignature_Menu() {
        return signature_Menu;
    }

    public static void add() throws IOException {
        System.out.println("restaurant_name/phone/signature_Menu/menu_Price/rating");
        String restaurant_info = scanner.nextLine();
        String[] temp = restaurant_info.split("/");
        if(check_Format(temp)){
            System.out.println("Please attention restaurant Format");
            add();
        }else {

            String name = Format.name(temp[0]);
            String phone = Format.phone(temp[1]);
            String signature_Menu = Format.menu(temp[2]);
            int menu_Price = Format.price(temp[3]);
            int rating = Format.rating(temp[4]);
            Restaurant restaurant = new Restaurant(name, phone, signature_Menu, menu_Price, rating);
            //id 구현해서 하나 추가할때마다 카운트 증가하게 하고싶은데 텍스트파일을 매번 읽을 수 도 없구 어카지;;;
            FileIO.insert_Restaurant(restaurant);
        }
        System.out.println("Enter 'X' Go Menu");
        String _temp = scanner.next();
    }

    private static boolean check_Format(String[] temp) {
        Restaurant.temp = temp;
        if(temp[0].isBlank()){
            System.out.println("Name doesn't exist");
            return true;
        }
        if(temp[1].isBlank()){
            System.out.println("PhoneNumber doesn't exist");
            return true;
        }
        for (int i = 0; i < temp[1].length(); i++) {
            char c = temp[1].charAt(i);
            if(c=='-')continue;
            if( c<'0' ||c>'9'){
                System.out.println("PhoneNumber insert only numbers");
                return true;
            }
        }
        if(temp[2].isBlank()){
            System.out.println("Signature Menu doesn't exist");
            return true;
        }
        if(temp[3].isBlank()){
            System.out.println("Price doesn't exist");
            return true;
        }

        for (int i = 0; i < temp[3].length(); i++) {
            char c= temp[3].charAt(i);

            if( c<'0' ||c>'9'){
                System.out.println("Price insert only numbers");
                return true;
            }
        }
        if(temp[4].isBlank()){
            System.out.println("Rating doesn't exist");
            return true;
        }
        for (int i = 0; i < temp[3].length(); i++) {
            char c= temp[3].charAt(i);

            if( c<'0' ||c>'9'){
                System.out.println("Price insert only numbers");
                return true;
            }
        }
        return false;
    }


    public static void delete() throws IOException {
        System.out.println("Insert Restaurant Name");
        String name = scanner.next();
        if(check_Format(new String[]{name, "0", "signature_Menu", "0", "0"})){
            delete();
        }

        if(FileIO.delete_Restaurant(name)){
            System.out.println("Delete complete");
        }else {
            System.out.println("Can't find Restaurant Name");
        }
        System.out.println("Enter 'X' Go Menu");
        String _temp = scanner.next();
    }

    public static void search() throws IOException {
        System.out.println("Insert Restaurant Name");
        String name = scanner.next();
        if(check_Format(new String[]{name, "0", "signature_Menu", "0", "0"})){
            search();
        }
        Restaurant restaurant = FileIO.search_Restaurant(name);
        if(restaurant != null){
            System.out.println(
                    restaurant.getString()
            );
        }else {
            System.out.println("Can't find Restaurant Name");
        }
        System.out.println("Enter 'X' Go Menu");
        String _temp = scanner.next();
    }

    public static void modify() throws IOException {
        System.out.println("Insert Modify Restaurant Info");
        System.out.println("restaurant_name/phone/signature_Menu/menu_Price/rating");
        String restaurant_info = scanner.nextLine();
        String[] temp = restaurant_info.split("/");
        if(check_Format(temp)){
            System.out.println("Please attention restaurant Format");
            modify();
        }

        String name = Format.name(temp[0]);
        String phone = Format.phone(temp[1]);
        String signature_Menu = Format.menu(temp[2]);
        int menu_Price = Format.price(temp[3]);
        int rating = Format.rating(temp[4]);
        Restaurant restaurant = new Restaurant(name,phone,signature_Menu,menu_Price,rating);
        //id 구현해서 하나 추가할때마다 카운트 증가하게 하고싶은데 텍스트파일을 매번 읽을 수 도 없구 어카지;;;
        if(FileIO.update_Restaurant(restaurant)){
            System.out.println("Modify Success");
        }
        else{
            System.out.println("Can't find Restaurant Name");
        }
        System.out.println("Enter 'X' Go Menu");
        String _temp = scanner.next();
    }

    public static void all_data() throws IOException {
        ArrayList<Restaurant> data = FileIO.read_Restaurant();

        Collections.sort(data);
        System.out.println("Name\tPhoneNumber\tSignature_menu\tPrice\tRating");
        for (Restaurant datum : data) {
            System.out.println(datum.getString());
        }
        System.out.println("Enter 'X' Go Menu");
        String _temp = scanner.next();
    }

    private String getString() {
        StringBuilder str = new StringBuilder(getName() + "\t/" + getPhone() + "\t/" + getSignature_Menu() + "\t\t/" + getMenu_Price() + "\t/");
        for (int i = 0; i < getRating(); i++) str.append("*");
        return str.toString();
    }

    @Override
    public int compareTo(Restaurant restaurant) {
        if (restaurant.getRating() > rating) {
            return 1;
        } else if (restaurant.getRating() < rating) {
            return -1;
        }
        return 0;

    }
}

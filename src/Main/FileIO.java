package Main;

import java.io.*;
import java.util.ArrayList;

public class FileIO {
    private static File file = new File("restaurant_rating.txt");

    static void insert_Restaurant(Restaurant restaurant) throws IOException {

        ArrayList<Restaurant> data = read_Restaurant();
        data.add(restaurant);
        write_Restaurant(data);


    }
    static boolean delete_Restaurant(String name) throws IOException {
        ArrayList<Restaurant> data = read_Restaurant();
        boolean find = false;
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getName().equals(name)){
                find = true;
                data.remove(i);
                break;
            }
        }
        if(find){
            write_Restaurant(data);
        }

        return find;
    }


    private static void write_Restaurant(ArrayList<Restaurant> data) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < data.size(); i++) {
            bw.write(data.get(i).getName()+"/"+
                    data.get(i).getPhone()+"/"+
                    data.get(i).getSignature_Menu()+"/"+
                    data.get(i).getMenu_Price()+"/"+
                    data.get(i).getRating()+"\r\n");
        }
        bw.close();
    }

    static ArrayList<Restaurant> read_Restaurant() throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
        ArrayList<Restaurant> txt = new ArrayList<Restaurant>();
        String str = "";

        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((str=br.readLine())!=null){
            String temp[] = str.split("/");
            Restaurant restaurant = new Restaurant(
                    temp[0],
                    temp[1],
                    temp[2],
                    Integer.parseInt(temp[3]),
                    Integer.parseInt(temp[4])
            );
            txt.add(restaurant);
        }
        br.close();
        return txt;
    }

    static boolean update_Restaurant(Restaurant restaurant) throws IOException {
        ArrayList<Restaurant> data = read_Restaurant();
        boolean find = false;
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getName().equals(restaurant.getName())){
                find = true;
                data.remove(i);
                data.add(restaurant);
                break;
            }
        }
        if(find){
            write_Restaurant(data);
        }

        return find;
    }


    public static Restaurant search_Restaurant(String name) throws IOException {
        ArrayList<Restaurant> data = read_Restaurant();
        Restaurant restaurant = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getName().equals(name)) {
                restaurant = data.get(i);
                break;
            }
        }
        return restaurant;
    }

    public static Restaurant random_Restaurant() throws IOException {
        ArrayList<Restaurant> data = read_Restaurant();
        if(data != null){
            int i = (int) (Math.random() * data.size());
            return data.get(i);
        }
        else{
            System.out.println("No data");
            return null;
        }


    }
}

package Ticket_Booking_App;

import java.util.HashMap;

public class Movie {
    final static private HashMap<String, HashMap<String, Object>> movies = new HashMap<>();
    final static private int seats = 100;

    public static void add(String name, float price) {
        // Add movie by name and price of ticket.
        HashMap<String, Object> movie_details = new HashMap<>();
        movie_details.put("price", price);
        movie_details.put("seats", seats);

        movies.put(name, movie_details);

        System.out.printf("Movie added: %s\n", name);
    }

    public static void remove(String name) {
        // Removes movie by its name.
        movies.remove(name);
        System.out.printf("Movie Removed: %s\n", name);
    }

    private static void update(String name, String update_parameter, Object newVal) {
        // A more concise way to update old value -> new value with update parameter.
        if (movies.containsKey(name)) {
            float movie_price = (float) movies.get(name).get("price");
            int movie_seats = (int) movies.get(name).get("seats");

            HashMap<String, Object> movie_details = new HashMap<>();

            switch (update_parameter) {
                case "name" -> {
                    movie_details.put("price", movie_price);
                    movie_details.put("seats", movie_seats);
                    movies.put((String) newVal, movie_details);
                    movies.remove(name);
                }
                case "price" -> {
                    movie_details.put("price", newVal);
                    movie_details.put("seats", movie_seats);
                    movies.put(name, movie_details);
                }
                case "seats" -> {
                    movie_details.put("price", movie_price);
                    movie_details.put("seats", newVal);
                    movies.put(name, movie_details);
                }
                default -> System.out.printf("Invalid option! : %s", update_parameter);
            }
        } else {
            System.out.printf("Movie does not exist! : %s\n", name);
        }
    }

    public static void update(String name, String newName) {
        // Update the name of the movie.
        update(name, "name", newName);
    }

    public static void update(String name, float price) {
        // Update the price of the movie.
        update(name, "price", price);
    }

    public static void update(String name, int seats) {
        // Update the seats of the movie.
        update(name, "seats", seats);
    }

    public static void list() {
        // List all the current screening movies with the price and number of remaining seats.
        System.out.println("Movies Currently Screening are:");
        movies.forEach((name, details) -> System.out.printf("%s: %s\n", name, details));
    }

    public static HashMap<String, Object> getMovie(String movieName) {
        // Get a movie by movieName string.
        return movies.get(movieName);
    }

    public static String getMovie(int index) {
        // Get movieName by index.
        Object movie = movies.keySet().toArray()[index];
        return (String) movie;
    }

    public static boolean validate(String movieName, int seats) {
        // Returns a boolean value upon successful validation else false
        if (movies.containsKey(movieName)) {
            return (int) movies.get(movieName).get("seats") >= seats;
        } else {
            System.out.printf("Invalid movie name!!: %s\n", movieName);
        }
        return false;
    }

    public static void main(String[] args) {
        add("Thor", 200);

        update("Thor", "NewThor");
        update("NewThor", 202.4F);
        update("NewThor", 55);

        getMovie(0);
        list();

        update("NewThor", "Thor");
        list();

        remove("Thor");
        list();

    }
}

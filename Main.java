package Ticket_Booking_App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Cinema {
    static ArrayList<HashMap<String, Object>> bookings = new ArrayList<>();

    public void addBooking(String name, String movieName, int seats) {
        HashMap<String, Object> booking = new HashMap<>();
        HashMap<String, Object> bookingDetail = new HashMap<>();

        bookingDetail.put(movieName, seats);
        booking.put(name, bookingDetail);
        bookings.add(booking);
    }

    public void listBookings() {
        bookings.forEach(System.out::println);
    }

    public void bookTicket() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.printf("Welcome %s!!\n\n", name);
        System.out.print("Type movie number: ");
        String movieIndex = sc.nextLine();
        String movieName = Movie.getMovie(Integer.parseInt(movieIndex) - 1);

        int seats;
        do {
            System.out.print("Enter number of seats: ");
            seats = sc.nextInt();
        } while (!Movie.validate(movieName, seats));

        float ticketAmount = seats * (float) Movie.getMovie(movieName).get("price");
        int seatsRemaining = (int) Movie.getMovie(movieName).get("seats");

        Movie.update(movieName, seatsRemaining - seats);
        addBooking(name, movieName, seats);

        sc.nextLine();

        System.out.printf("Total amount: %s\n", ticketAmount);
        System.out.print("Accept payment?(y/n) : ");
        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            System.out.println("Booked successfully!!");
        }
    }

    public void listMovies() {
        Movie.list();
    }
}

class App {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        Cinema cine = new Cinema();

        while(true) {
            System.out.println("\nWelcome to movie booking CLI.\n");
            System.out.print("""
                    1. Book tickets\s
                    2. View movies\s
                    3. List bookings\s
                    4. Exit\s
                    """);
            System.out.print("> ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1" -> cine.bookTicket();
                case "2" -> cine.listMovies();
                case "3" -> cine.listBookings();
                default -> System.exit(0);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Movie.add("Thor", 200);
        Movie.add("Avengers", 300);

        App.run();
    }
}

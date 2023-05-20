package com.example.seatreservation;

import javafx.scene.image.Image;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Objects;

public class DB {
    private static Connection connection;
    String url = "jdbc:mysql://localhost:3306/seatreservation";
    String username = "root";
    String pass = "";

    public DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, pass);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    public static void getALL(){
        globals.seatingClassesLinkedList=getAllSeatingClasses();
        getAllSeats();
        globals.hallsLinkedList=getAllHalls();
        globals.moviesLinkedList=getAllMovies();
        globals.partyLinkedList=getAllParties();
        globals.ticketsLinkedList=getAllTickets();
        globals.userLinkedList=getAllUsers();
    }
    public static void setALL(){
        truncateCC();
        setAllCC();
        truncateSeatingClasses();
        setAllSeatingClasses(globals.seatingClassesLinkedList);
        truncateUsers();
        setAllUsers(globals.userLinkedList);
        truncateHalls();
        setAllHalls(globals.hallsLinkedList);
        truncateMovies();
        setAllMovies(globals.moviesLinkedList);
        truncateParties();
        setAllParties(globals.partyLinkedList);
        truncateTickets();
        setAllTickets(globals.ticketsLinkedList);
        truncateSeats();
        setAllSeats();
    }

    public static void truncateSeatingClasses() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            statement.executeUpdate("TRUNCATE TABLE SeatingClasses");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList<SeatingClasses> getAllSeatingClasses() {
        LinkedList<SeatingClasses> seatingClassesList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SeatingClasses");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                globals.seatingClassesIDs[id]=true;
                int numberOfRows = resultSet.getInt("numberOfRows");
                int seatPricing = resultSet.getInt("seatPricing");
                SeatingClasses seatingClasses = new SeatingClasses(id, numberOfRows, seatPricing);
                seatingClassesList.add(seatingClasses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seatingClassesList;
    }
    public static SeatingClasses getSeatingClassByID(int seatingClassID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM SeatingClasses WHERE ID = ?")) {
            statement.setInt(1, seatingClassID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int numberOfRows = resultSet.getInt("numberOfRows");
                int seatPricing = resultSet.getInt("seatPricing");
                return new SeatingClasses(seatingClassID,numberOfRows, seatPricing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Seating class not found or error occurred
    }
//    public static SeatingClasses getSeatingClassBySeatID(int seatID) {
//        globals.seatingClassesLinkedList=getAllSeatingClasses();
//        for (SeatingClasses sc:globals.seatingClassesLinkedList){
//            if (sc.ge)
//        }
//        return null; // Seat or seating class not found or error occurred
//    }
    public static void setAllSeatingClasses(LinkedList<SeatingClasses> seatingClassesList) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO SeatingClasses (ID, numberOfRows, seatPricing) VALUES (?, ?, ?)")) {
            for (SeatingClasses seatingClasses : seatingClassesList) {
                statement.setInt(1, seatingClasses.getID());
                statement.setInt(2, seatingClasses.getNumberOfRows());
                statement.setInt(3, seatingClasses.getSeatPricing());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void truncateHalls() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Hall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList<Hall> getAllHalls() {
        LinkedList<Hall> hallList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Hall");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                globals.hallsIDs[id]=true;
                int rows = resultSet.getInt("rowsnum");
                int columns = resultSet.getInt("columnsnum");
                String name = resultSet.getString("name");
                int seatingClass1ID = resultSet.getInt("seatingClass1ID");
                int seatingClass2ID = resultSet.getInt("seatingClass2ID");
                int seatingClass3ID = resultSet.getInt("seatingClass3ID");
                SeatingClasses s1=new SeatingClasses(),s2=new SeatingClasses(),s3=new SeatingClasses();
                for (SeatingClasses seatingClasses:globals.seatingClassesLinkedList){
                    if (seatingClasses.getID()==seatingClass1ID){
                        s1=seatingClasses;
                    }
                    if(seatingClasses.getID()==seatingClass2ID){
                        s2=seatingClasses;
                    }
                    if (seatingClasses.getID()==seatingClass3ID){
                        s3=seatingClasses;
                    }
                }
                Hall hall = new Hall(id,name, rows, columns, s1, s2, s3);
                hallList.add(hall);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hallList;
    }
    public static Hall getHallByPartyID(int partyID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hall WHERE ID IN (SELECT hallID FROM Party WHERE ID = ?)")) {
            statement.setInt(1, partyID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int rows = resultSet.getInt("rowsnum");
                int columns = resultSet.getInt("columnsnum");
                String name = resultSet.getString("name");
                int seatingClass1ID = resultSet.getInt("seatingClass1ID");
                int seatingClass2ID = resultSet.getInt("seatingClass2ID");
                int seatingClass3ID = resultSet.getInt("seatingClass3ID");
                SeatingClasses s1=new SeatingClasses(),s2=new SeatingClasses(),s3=new SeatingClasses();
                for (SeatingClasses seatingClasses:globals.seatingClassesLinkedList){
                    if (seatingClasses.getID()==seatingClass1ID){
                        s1=seatingClasses;
                    }
                    if(seatingClasses.getID()==seatingClass2ID){
                        s2=seatingClasses;
                    }
                    if (seatingClasses.getID()==seatingClass3ID){
                        s3=seatingClasses;
                    }
                }
                Hall hall= new Hall(id,name, rows, columns, s1, s2, s3);
                hall.setSeats(getHallSeats(hall.getID()));
                return hall;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Party or hall not found or error occurred
    }
    public static void setAllHalls(LinkedList<Hall> hallList) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Hall (ID, rowsnum, columnsnum, name, seatingClass1ID, seatingClass2ID, seatingClass3ID) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            for (Hall hall : hallList) {
                statement.setInt(1, hall.getID());
                statement.setInt(2, hall.getRows());
                statement.setInt(3, hall.getColumns());
                statement.setString(4, hall.getName());
                statement.setInt(5, hall.getSeatingClass1().getID());
                statement.setInt(6, hall.getSeatingClass2().getID());
                statement.setInt(7, hall.getSeatingClass3().getID());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void truncateCC() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            statement.executeUpdate("TRUNCATE TABLE CreditCard");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList<CreditCard> getAllCC() {
        LinkedList<CreditCard> creditCardList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CreditCard");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int CVV = resultSet.getInt("CVV");
                String cardNumber = resultSet.getString("cardNumber");
                String holderName = resultSet.getString("holderName");
                LocalDate expDate = resultSet.getDate("expDate").toLocalDate();
                CreditCard creditCard = new CreditCard(id,cardNumber,CVV ,expDate, holderName);
                creditCardList.add(creditCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditCardList;
    }
    public static void setAllCC() {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO CreditCard (ID,CVV, cardNumber, holderName, expDate) VALUES (?,?, ?, ?, ?)")) {
            for (User user:globals.userLinkedList) {
                CreditCard creditCard=user.getCard();
                if(creditCard!=null) {
                    statement.setInt(1,creditCard.getID());
                    statement.setInt(2, creditCard.getCVV());
                    statement.setString(3, creditCard.getCardNumber());
                    statement.setString(4, creditCard.getHolderName());
                    statement.setDate(5, Date.valueOf(creditCard.getExpDate()));
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static CreditCard getCreditCardByUserID(int userID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM CreditCard WHERE ID IN (SELECT cardID FROM users WHERE ID = ?)")) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                globals.ccIDs[ID]=true;
                int CVV = resultSet.getInt("CVV");
                String cardNumber = resultSet.getString("cardNumber");
                String holderName = resultSet.getString("holderName");
                LocalDate expDate = resultSet.getDate("expDate").toLocalDate();
                return new CreditCard( ID,cardNumber,CVV ,expDate, holderName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void truncateUsers() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList<User> getAllUsers() {
        LinkedList<User> userList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                globals.usersIDs[ID]=true;
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phoneNumber = resultSet.getString("phoneNumber");
                User user = new User(ID, name, email, password, phoneNumber, getCreditCardByUserID(ID));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public static User getUserByTicketID(int ticketID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE ID IN (SELECT userID FROM Ticket WHERE ID = ?)")) {
            statement.setInt(1, ticketID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phoneNumber = resultSet.getString("phoneNumber");
                return new User(ID, name, email, password, phoneNumber, getCreditCardByUserID(ID));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setAllUsers(LinkedList<User> userList) {
            for (User user : userList) {
                if(user.getCard()!=null) {
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (ID, name, email, password, phoneNumber, cardID) VALUES (?, ?, ?, ?, ?, ?)")) {
                        statement.setInt(1, user.getID());
                        statement.setString(2, user.getName());
                        statement.setString(3, user.getEmail());
                        statement.setString(4, user.getPassword());
                        statement.setString(5, user.getPhoneNumber());
                        statement.setInt(6, user.getCard().getID());
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (ID, name, email, password, phoneNumber) VALUES (?, ?, ?, ?, ?)")) {
                        statement.setInt(1, user.getID());
                        statement.setString(2, user.getName());
                        statement.setString(3, user.getEmail());
                        statement.setString(4, user.getPassword());
                        statement.setString(5, user.getPhoneNumber());
                        statement.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
    }


    public static LocalDateTime getSlotByPartyID(int partyID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Party WHERE ID=?")) {
            statement.setInt(1, partyID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getTimestamp("Slot").toLocalDateTime();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Party or slot not found or error occurred
    }

    public static void truncateMovies() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            statement.executeUpdate("TRUNCATE TABLE Movie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList<Movie> getAllMovies() {
        LinkedList<Movie> movieList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Movie");
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                globals.moviesIDs[ID]=true;
                int screenTime = resultSet.getInt("screenTime");
                String movieName = resultSet.getString("movieName");
                String description = resultSet.getString("description");
                String imgID=resultSet.getString("imgID");
                // Assuming Image is stored as BLOB
                Image img =new Image(imgID); // Retrieve the Image object from the resultSet
                LocalDate releaseDate = resultSet.getDate("releaseDate").toLocalDate();
                Movie movie = new Movie(ID, screenTime, movieName, description, img, releaseDate);
                movieList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }
    public static Movie getMovieByPartyID(int partyID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Movie WHERE ID IN (SELECT movieID FROM Party WHERE ID = ?)")) {
            statement.setInt(1, partyID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                globals.moviesIDs[ID]=true;

                int screenTime = resultSet.getInt("screenTime");
                String movieName = resultSet.getString("movieName");
                String description = resultSet.getString("description");
                String imgID=resultSet.getString("imgID");
                // Assuming Image is stored as BLOB
                Image img =new Image(imgID); // Retrieve the Image object from the resultSet
                LocalDate releaseDate = resultSet.getDate("releaseDate").toLocalDate();
                return new Movie(ID, screenTime, movieName, description, img, releaseDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Party or movie not found or error occurred
    }

    public static void setAllMovies(LinkedList<Movie> movieList) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Movie (ID, screenTime, movieName, description, imgID, releaseDate) VALUES (?, ?, ?, ?, ?, ?)")) {
            for (Movie movie : movieList) {
                statement.setInt(1, movie.getID());
                statement.setInt(2, movie.getScreenTime());
                statement.setString(3, movie.getMovieName());
                statement.setString(4, movie.getDescription());
                // Set the Image object to the statement parameter for BLOB
                statement.setString(5, movie.getImg().getUrl());
                statement.setDate(6, Date.valueOf(movie.getReleaseDate()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void truncateParties() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            statement.executeUpdate("TRUNCATE TABLE Party;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList<Party> getAllParties() {
        LinkedList<Party> partyList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Party");
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                globals.partiesIDs[ID]=true;

                Party party = new Party(ID, getSlotByPartyID(ID), getMovieByPartyID(ID), getHallByPartyID(ID));
                for (Movie movie:globals.moviesLinkedList){
                    if (movie.getID()== Objects.requireNonNull(getMovieByPartyID(ID)).getID()){
                        movie.addToParties(party);
                    }
                }
                partyList.add(party);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Party party:partyList){
            for (Hall hall:globals.hallsLinkedList){
                if(hall.getID()==party.getHall().getID()){
                    party.getHall().markSlotAsBooked(getSlotByPartyID(party.getID()));
                    hall.markSlotAsBooked(getSlotByPartyID(party.getID()));
                }
            }
        }

        return partyList;
    }
    public static Party getPartyByTicketID(int ticketID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Party WHERE ID IN (SELECT partyID FROM Ticket WHERE ID = ?)")) {
            statement.setInt(1, ticketID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                globals.partiesIDs[ID]=true;

                return new Party(ID, getSlotByPartyID(ID), getMovieByPartyID(ID), getHallByPartyID(ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Ticket or party not found or error occurred
    }

    public static void setAllParties(LinkedList<Party> partyList) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Party (ID, slot, movieID, hallID) VALUES (?, ?, ?, ?)")) {
            for (Party party : partyList) {
                statement.setInt(1, party.getID());
                statement.setTimestamp(2, Timestamp.valueOf(party.getSlot()));
                statement.setInt(3, party.getMovie().getID());
                statement.setInt(4, party.getHall().getID());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void truncateSeats() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            statement.executeUpdate("TRUNCATE TABLE Seat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getAllSeats() {
        globals.hallsLinkedList=getAllHalls();
        for (Hall hall:globals.hallsLinkedList){
            hall.setSeats(getHallSeats(hall.getID()));
        }
    }
    public static Seat [][] getHallSeats(int hallID) {
        LinkedList<Seat> seatList = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Seat WHERE hallID = ?")) {
            statement.setInt(1, hallID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int seatID = resultSet.getInt("ID");
                globals.seatsIDs[seatID]=true;
                boolean booked = resultSet.getBoolean("booked");
                int x = resultSet.getInt("x");
                int y = resultSet.getInt("y");
                int seatingClassID = resultSet.getInt("seatingClassID");
                // Retrieve other seat properties as needed
                SeatingClasses seatingClass = getSeatingClassByID(seatingClassID); // Assuming you have a method to retrieve SeatingClass by ID
                Seat seat = new Seat(seatID, x, y,booked, seatingClass);
                seatList.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Hall haller = null;
        globals.hallsLinkedList=getAllHalls();
        for (Hall hall:globals.hallsLinkedList){
            if(hall.getID()==hallID){
                haller=hall;
                break;
            }
        }

        Seat [][] seats=new Seat[haller.getRows()][haller.getColumns()];
        for (Seat seat:seatList){
            seats[seat.getX()][seat.getY()]=seat;
        }
        return seats;

    }

    public static void setAllSeats() {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Seat (ID, booked, x, y,seatingClassID,hallID) VALUES (?, ?, ?, ?, ?, ?)")) {
            for (Hall hall:globals.hallsLinkedList) {
                System.out.println(hall.getID());
                for (int i=0;i<hall.getRows();i++){
                    for (int j=0;j<hall.getColumns();j++){
                        Seat seat=hall.getSeat(i,j);
                        statement.setInt(1, seat.getID());
                        statement.setBoolean(2, seat.isBooked());
                        statement.setInt(3, seat.getX());
                        statement.setInt(4, seat.getY());
                        statement.setInt(5, seat.getSeatingClass().getID());
                        statement.setInt(6,hall.getID());
                        statement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void truncateTickets() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            statement.executeUpdate("TRUNCATE TABLE Ticket");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static LinkedList<Ticket> getAllTickets() {
        globals.userLinkedList=getAllUsers();
        LinkedList<Ticket> ticketList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Ticket");
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                globals.ticketsIDs[ID]=true;
                LocalTime issueTime = resultSet.getTime("issueTime").toLocalTime();
                double price = resultSet.getDouble("price");
                String seats= resultSet.getString("seats");
                Ticket ticket = new Ticket(ID, price, getUserByTicketID(ID), getPartyByTicketID(ID));
                for (User user:globals.userLinkedList){
                    if(user.getID()==getUserByTicketID(ID).getID()){
                        user.addToTickets(ticket);
                    }
                }
                ticket.setIssueTime(issueTime);
                ticket.setSeats(seats);
                ticketList.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;
    }
    public static void setAllTickets(LinkedList<Ticket> ticketList) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Ticket (ID, issueTime, price, userID, partyID,seats) VALUES (?, ?, ?, ?, ?, ?)")) {
            for (Ticket ticket : ticketList) {
                statement.setInt(1, ticket.getID());
                statement.setTime(2, Time.valueOf(ticket.getIssueTime()));
                statement.setDouble(3, ticket.getPrice());
                statement.setInt(4, ticket.getUser().getID());
                statement.setInt(5, ticket.getParty().getID());
                statement.setString(6,ticket.getSeats().toString());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

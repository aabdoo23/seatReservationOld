package com.example.seatreservation;

import javafx.scene.image.Image;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class DB {
    private static Connection connection;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    private static Statement stmt;
    String url = "jdbc:mysql://localhost:3306/ticketingsystem";
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
    public void truncateSeatingClasses() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE SeatingClasses");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<SeatingClasses> getAllSeatingClasses() {
        LinkedList<SeatingClasses> seatingClassesList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SeatingClasses");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
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
    public void setAllSeatingClasses(LinkedList<SeatingClasses> seatingClassesList) {
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


    public void truncateHalls() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Hall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<Hall> getAllHalls() {
        LinkedList<Hall> hallList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Hall");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int rows = resultSet.getInt("rows");
                int columns = resultSet.getInt("columns");
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
    public Hall getHallByPartyID(int partyID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Hall WHERE ID IN (SELECT hallID FROM Party WHERE ID = ?)")) {
            statement.setInt(1, partyID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int rows = resultSet.getInt("rows");
                int columns = resultSet.getInt("columns");
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
                return new Hall(id,name, rows, columns, s1, s2, s3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Party or hall not found or error occurred
    }
    public void setAllHalls(LinkedList<Hall> hallList) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Hall (ID, rows, columns, name, seatingClass1ID, seatingClass2ID, seatingClass3ID) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
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

    public void truncateCC() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE CreditCard");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<CreditCard> getAllCC() {
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
    public void setAllCC(LinkedList<CreditCard> creditCardList) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO CreditCard (CVV, cardNumber, holderName, expDate) VALUES (?, ?, ?, ?)")) {
            for (CreditCard creditCard : creditCardList) {
                statement.setInt(1, creditCard.getCVV());
                statement.setString(2, creditCard.getCardNumber());
                statement.setString(3, creditCard.getHolderName());
                statement.setDate(4, Date.valueOf(creditCard.getExpDate()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public CreditCard getCreditCardByUserID(int userID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM CreditCard WHERE ID IN (SELECT cardID FROM users WHERE ID = ?)")) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("ID");
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


    public void truncateUsers() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<User> getAllUsers() {
        LinkedList<User> userList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
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
    public void setAllUsers(LinkedList<User> userList) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (ID, name, email, password, phoneNumber, cardID) VALUES (?, ?, ?, ?, ?, ?)")) {
            for (User user : userList) {
                statement.setInt(1, user.getID());
                statement.setString(2, user.getName());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getPhoneNumber());
                statement.setInt(6, user.getCard().getID());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public LocalDateTime getSlotByPartyID(int partyID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Party WHERE ID=?")) {
            statement.setInt(1, partyID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getTimestamp("Slot").toLocalDateTime();;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Party or slot not found or error occurred
    }


    public void truncateMovies() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Movie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<Movie> getAllMovies() {
        LinkedList<Movie> movieList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Movie");
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
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
    public Movie getMovieByPartyID(int partyID) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Movie WHERE ID IN (SELECT movieID FROM Party WHERE ID = ?)")) {
            statement.setInt(1, partyID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("ID");
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
    public void setAllMovies(LinkedList<Movie> movieList) {
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


    public void truncateParties() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Party");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<Party> getAllParties() {
        LinkedList<Party> partyList = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Party");
            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                Party party = new Party(ID, getSlotByPartyID(ID), getMovieByPartyID(ID), getHallByPartyID(ID));
                partyList.add(party);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partyList;
    }
    public void setAllParties(LinkedList<Party> partyList) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Party (ID, slotID, movieID, hallID) VALUES (?, ?, ?, ?)")) {
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

}

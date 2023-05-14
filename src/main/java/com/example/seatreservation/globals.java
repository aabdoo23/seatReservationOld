package com.example.seatreservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.util.LinkedList;
import java.util.Random;

public class globals {
    public static LinkedList<User>userLinkedList=new LinkedList<>();
    public static LinkedList<Reservation>ticketsLinkedList=new LinkedList<>();
    public static LinkedList<Movie>moviesLinkedList=new LinkedList<>();
    public static LinkedList<Party>partyLinkedList=new LinkedList<>();

    public static LinkedList<Hall>hallsLinkedList=new LinkedList<>();

    public static boolean[] usersIDs = new boolean[2000];
    public static boolean[] reservationsIDs = new boolean[2000];
    public static boolean[] ticketsIDs = new boolean[2000];
    public static boolean[] hallsIDs = new boolean[2000];
    public static boolean[] partiesIDs = new boolean[2000];

    public static boolean[] moviesIDs = new boolean[2000];

    public static int createNewRandomID(boolean[] v) {
        Random random = new Random();
        int x = random.nextInt(v.length-1);
        while (v[x]) {
            x = random.nextInt(v.length-1);
        }
        v[x] = true;
        return x;
    }
    public static int createNewSeqID(boolean[] v) {
        for (int i=1;i<v.length;i++){
            if(!v[i]){
                return i;
            }
        }
        return 0;
    }
    public static void spinnerTF(Spinner<Integer> spinner) {
        spinner.getEditor().setTextFormatter(new TextFormatter<>(change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        }));
    }
    public static void makeList(LinkedList linkedList, ListView<String> list) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        ObservableList<String>obs= FXCollections.observableArrayList(strings);
        list.setItems(obs);
    }
    public static String[] makeList(LinkedList linkedList) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        return strings;
    }
    public static ObservableList<String> makeObsList(LinkedList linkedList) {
        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        return FXCollections.observableArrayList(strings);
    }
    public static void makeList(LinkedList linkedList, ChoiceBox<String> list) {

        String[] strings = new String[linkedList.size()];
        int i = 0;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }

        ObservableList<String>obs= FXCollections.observableArrayList(strings);
        list.setItems(obs);
    }
    public static void defaultMakeList(LinkedList linkedList, ComboBox<String> list) {
        String[] strings = new String[linkedList.size()+1];
        strings[0]="-";
        int i = 1;
        for (Object ob : linkedList) {
            strings[i] = ob.toString();
            i++;
        }
        ObservableList<String>obs= FXCollections.observableArrayList(strings);
        list.setItems(obs);
    }

}

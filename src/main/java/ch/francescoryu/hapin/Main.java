package ch.francescoryu.hapin;

import javafx.application.Application;

import javax.swing.*;

/**
 * @author: Francesco Ryu
 * @version: 1.0
 * @date 29.09.2022
 * @description Main class to execute the application
 */

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Application.launch(Login.class, args);
    }
}


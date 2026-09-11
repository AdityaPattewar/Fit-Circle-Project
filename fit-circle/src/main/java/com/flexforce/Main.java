package com.flexforce;

import com.flexforce.view.login.Login;

import com.flexforce.config.RazorpayConfig;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {

        RazorpayConfig.testCredentials();

        System.out.println("Hello world from Aditya!");
        Application.launch(com.flexforce.view.SplashScreen.class, args);
    }
}

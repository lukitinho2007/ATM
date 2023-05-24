package com.example.atm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.io.*;
import java.sql.*;

public class HelloApplication extends Application {
    double balance = 0;





    StringBuilder enteredDigits = new StringBuilder();

    String primaryColor = "#FF6600"; // Orange
    String secondaryColor = "#FFFFFF"; // White
    String backgroundColor = "#F6F6F6"; // Light gray




    @Override
    public void start(Stage stage) throws IOException, FileNotFoundException,Exception {


        Image profileImage = new Image("https://cdn-icons-png.flaticon.com/512/3135/3135715.png");
        ImageView profileImageview = new ImageView(profileImage);
        profileImageview.setFitHeight(20);
        profileImageview.setFitWidth(20);
        profileImageview.setPreserveRatio(true);

        Image DepositImage = new Image("https://png.pngtree.com/png-vector/20190926/ourmid/pngtree-money-savings-glyph-icon-vector-png-image_1743161.jpg");
        ImageView depositImageview = new ImageView(DepositImage);
        depositImageview.setFitHeight(20);
        depositImageview.setFitWidth(20);
        depositImageview.setPreserveRatio(true);

        Image withdrawImage= new Image("https://png.pngtree.com/png-vector/20190926/ourlarge/pngtree-atm-credit-card-line-icon-vector-png-image_1744066.jpg");
        ImageView withdrawImageview = new ImageView(withdrawImage);
        withdrawImageview.setFitHeight(20);
        withdrawImageview.setFitWidth(20);
        withdrawImageview.setPreserveRatio(true);




        Label balanceLabel = new Label("Balance: $" + balance);
        balanceLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000000; -fx-background-color: transparent; -fx-padding: 10px; -fx-alignment: center;");




        Button[][] keypadButtons = new Button[4][3];
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 3; column++) {
                keypadButtons[row][column] = createKeypadButton(Integer.toString(row * 3 + column + 1));
                keypadButtons[row][column].setStyle("-fx-font-size: 18px; -fx-base: " + primaryColor + ";");
            }
        }

        Button cancelBtn = createKeypadButton("C");
        cancelBtn.setStyle("-fx-font-size: 18px; -fx-base: #F44336; -fx-text-fill: " + secondaryColor + ";");
        Button zeroBtn = createKeypadButton("0");
        Button okBtn = createKeypadButton("OK");
        okBtn.setStyle("-fx-font-size: 18px; -fx-base: #4CAF50; -fx-text-fill: " + secondaryColor + ";");

        TextField textField = new TextField();
        textField.setEditable(false);
        textField.setPrefSize(10, 10);
        textField.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        textField.setStyle("-fx-background-color: " + primaryColor + "; -fx-text-fill: " + secondaryColor + "; -fx-padding: 10px;");
        textField.setPrefHeight(50);
        textField.setText("Enter Pin: ");

        GridPane keypad = new GridPane();
        keypad.setHgap(10);
        keypad.setVgap(10);
        keypad.setAlignment(Pos.CENTER);
        keypad.setPadding(new Insets(10));
        keypad.add(keypadButtons[0][0], 0, 0);
        keypad.add(keypadButtons[0][1], 1, 0);
        keypad.add(keypadButtons[0][2], 2, 0);
        keypad.add(keypadButtons[1][0], 0, 1);
        keypad.add(keypadButtons[1][1], 1, 1);
        keypad.add(keypadButtons[1][2], 2, 1);
        keypad.add(keypadButtons[2][0], 0, 2);
        keypad.add(keypadButtons[2][1], 1, 2);
        keypad.add(keypadButtons[2][2], 2, 2);
        keypad.add(cancelBtn, 0, 3);
        keypad.add(zeroBtn, 1, 3);
        keypad.add(okBtn, 2, 3);

        keypadButtons[0][0].setOnAction(e -> {
            handleButtonPress("1", textField);
        });
        keypadButtons[0][1].setOnAction(e -> {
            handleButtonPress("2", textField);
        });
        keypadButtons[0][2].setOnAction(e -> {
            handleButtonPress("3", textField);
        });
        keypadButtons[1][0].setOnAction(e -> {
            handleButtonPress("4", textField);
        });
        keypadButtons[1][1].setOnAction(e -> {
            handleButtonPress("5", textField);
        });
        keypadButtons[1][2].setOnAction(e -> {
                handleButtonPress("6", textField);
            });
        keypadButtons[2][0].setOnAction(e -> {

                handleButtonPress("7", textField);
            });
        keypadButtons[2][1].setOnAction(e -> {

                handleButtonPress("8", textField);
            });
        keypadButtons[2][2].setOnAction(e -> {

                handleButtonPress("9", textField);
            });
        zeroBtn.setOnAction(e ->

                handleButtonPress("0", textField));

        cancelBtn.setOnAction(e ->
                handleButtonPress("Cancel", textField));

        okBtn.setOnAction(e -> handleButtonPress("OK", textField));


        HBox actionButtons = new HBox(20);
        actionButtons.setPadding(new Insets(10));
        actionButtons.setAlignment(Pos.CENTER);

        Button depositBtn = createActionButton("Deposit");
        Button withdrawBtn = createActionButton("Withdraw");
        Button ProfileBtn = createActionButton("Profile");
        ProfileBtn.setGraphic(profileImageview);
        depositBtn.setGraphic(depositImageview);
        withdrawBtn.setGraphic(withdrawImageview);
        actionButtons.getChildren().addAll(withdrawBtn, depositBtn, ProfileBtn);
        depositBtn.setStyle("-fx-font-size: 14px; -fx-base: " + primaryColor + "; -fx-text-fill: " + secondaryColor + ";");
        withdrawBtn.setStyle("-fx-font-size: 14px; -fx-base: " + primaryColor + "; -fx-text-fill: " + secondaryColor + ";");
        ProfileBtn.setStyle("-fx-font-size: 14px; -fx-base: " + primaryColor + "; -fx-text-fill: " + secondaryColor + ";");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #00008B;");
        layout.getChildren().addAll(textField, actionButtons, keypad, balanceLabel);


        Label cvvLabel = new Label("cvv:");
        cvvLabel.setPrefSize(80, 80);
        TextField cvvField = new TextField();
        cvvField.setPrefSize(50, 50);
        Label expirationDateLabel = new Label("Expiration Date");
        expirationDateLabel.setPrefSize(90, 90);
        TextField expirationDateField = new TextField();
        expirationDateField.setPrefSize(50, 50);
        Label cardNumberLabel = new Label("Card Number:");
        cardNumberLabel.setPrefSize(80, 80);
        TextField cardNumberField = new TextField();
        cardNumberField.setPrefSize(50, 50);
        Label pinCodeLabel = new Label("PIN Code:");
        pinCodeLabel.setPrefSize(80, 80);
        TextField pinCodeField = new TextField();
        pinCodeField.setPrefSize(50, 50);






        VBox profileLayout = new VBox(10);
        profileLayout.setPadding(new Insets(20));
        profileLayout.setStyle("-fx-background-color: #00008B;");
        profileLayout.getChildren().addAll(
                cardNumberLabel, cardNumberField,
                cvvLabel, cvvField,
                expirationDateLabel, expirationDateField,
                pinCodeLabel, pinCodeField
        );

        layout.setStyle("-fx-background-color: " + backgroundColor + ";");
        profileLayout.setStyle("-fx-background-color: " + backgroundColor + ";");

        Scene scene = new Scene(layout, 500, 500);
        Scene profileScene = new Scene(profileLayout, 500, 500);

       depositBtn.setDisable(true);
       withdrawBtn.setDisable(true);


            depositBtn.setOnAction(e-> {

                    Button fiveBtn = createDepositOptionButton("5$");
                    Button tenBtn = createDepositOptionButton("10$");
                    Button twentyBtn = createDepositOptionButton("20$");
                    Button fiftyBtn = createDepositOptionButton("50$");
                    Button hundredBtn = createDepositOptionButton("100$");
                    Button customBtn = createDepositOptionButton("Custom");
                    fiveBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
                    tenBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
                    twentyBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
                    fiftyBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
                    hundredBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
                    customBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");

                    VBox depositOptionsLayout = new VBox(10);
                    depositOptionsLayout.setPadding(new Insets(20));
                    depositOptionsLayout.setAlignment(Pos.CENTER);
                    depositOptionsLayout.getChildren().addAll(fiveBtn, tenBtn, twentyBtn, fiftyBtn, hundredBtn, customBtn);

                    Scene depositOptionsScene = new Scene(depositOptionsLayout, 500, 500);
                    stage.setScene(depositOptionsScene);

                    fiveBtn.setOnAction(event -> {
                        textField.setText("Successefully Deposited 5$");
                        balance += 5;
                        balanceLabel.setText("Balance: $" + String.valueOf(balance));
                        stage.setScene(scene);
                    });
                    tenBtn.setOnAction(event -> {
                        textField.setText("Successefully Deposited 10$");
                        balance += 10;
                        balanceLabel.setText(String.valueOf(balance+10));
                        stage.setScene(scene);
                    });
                    twentyBtn.setOnAction(event -> {
                        textField.setText("Successefully Deposited 20$");
                        balance += 20;
                        balanceLabel.setText("Balance: $" + String.valueOf(balance));
                        stage.setScene(scene);
                    });
                    fiftyBtn.setOnAction(event -> {
                        textField.setText("Successefully Deposited 50$");
                        balance += 50;
                        balanceLabel.setText
                                ("Balance: $" + String.valueOf(balance));
                        stage.setScene(scene);
                    });
                    hundredBtn.setOnAction(event -> {
                        textField.setText("Successefully Deposited 100$");
                        balance += 100;
                        balanceLabel.setText("Balance: $" + String.valueOf(balance));

                        stage.setScene(scene);
                    });
                    customBtn.setOnAction(event -> {
                        stage.setScene(scene);
                        textField.setText("Enter the custom deposit amount:");
                        StringBuilder customAmountResult = enteredDigits;

                        okBtn.setOnAction(k -> {
                            textField.setText("Custom Deposit Amount: " + customAmountResult);
                            balance += Double.parseDouble(customAmountResult.toString());
                            balanceLabel.setText("Balance: $" + String.valueOf(balance));
                        });
                    });




            });
           ProfileBtn.setOnAction(e -> stage.setScene(profileScene));

        stage.setScene(scene);
        stage.show();


       withdrawBtn.setOnAction(e -> {



               Button fiveBtn = createDepositOptionButton("5$");
               Button tenBtn = createDepositOptionButton("10$");
               Button twentyBtn = createDepositOptionButton("20$");
               Button fiftyBtn = createDepositOptionButton("50$");
               Button hundredBtn = createDepositOptionButton("100$");
               Button customBtn = createDepositOptionButton("Custom");
               fiveBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
               tenBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
               twentyBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
               fiftyBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
               hundredBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");
               customBtn.setStyle("-fx-font-size: 14px; -fx-base: #009B49; -fx-text-fill: white;");

               VBox depositOptionsLayout = new VBox(10);
               depositOptionsLayout.setPadding(new Insets(20));
               depositOptionsLayout.setAlignment(Pos.CENTER);
               depositOptionsLayout.getChildren().addAll(fiveBtn, tenBtn, twentyBtn, fiftyBtn, hundredBtn, customBtn);

               Scene depositOptionsScene = new Scene(depositOptionsLayout, 500, 500);
               stage.setScene(depositOptionsScene);

               fiveBtn.setOnAction(event -> {
                   if (5 <= balance) {
                       balance -= 5;
                       balanceLabel.setText("Balance: $" + String.valueOf(balance));
                       textField.setText("Successfully Withdrawn $" + 5);
                   } else {
                       textField.setText("Insufficient balance for withdrawal");
                   }
                   stage.setScene(scene);
               });
               tenBtn.setOnAction(event -> {
                   if (10 <= balance) {
                       balance -= 10;
                       balanceLabel.setText("Balance: $" + String.valueOf(balance));
                       textField.setText("Successfully Withdrawn $" + 10);
                   } else {
                       textField.setText("Insufficient balance for withdrawal");
                   }
                   stage.setScene(scene);
               });
               twentyBtn.setOnAction(event -> {
                   if (20 <= balance) {
                       balance -= 20;
                       balanceLabel.setText("Balance: $" + String.valueOf(balance));
                       textField.setText("Successfully Withdrawn $" + 20);
                   } else {
                       textField.setText("Insufficient balance for withdrawal");
                   }
                   stage.setScene(scene);
               });
               fiftyBtn.setOnAction(event -> {
                   if (50 <= balance) {
                       balance -= 50;
                       balanceLabel.setText("Balance: $" + String.valueOf(balance));
                       textField.setText("Successfully Withdrawn $" + 50);
                   } else {
                       textField.setText("Insufficient balance for withdrawal");
                   }
                   stage.setScene(scene);
               });
               hundredBtn.setOnAction(event -> {
                   if (100 <= balance) {
                       balance -= 100;
                       balanceLabel.setText("Balance: $" + String.valueOf(balance));
                       textField.setText("Successfully Withdrawn $" + 100);
                   } else {
                       textField.setText("Insufficient balance for withdrawal");
                   }
                   stage.setScene(scene);
               });
               customBtn.setOnAction(event -> {
                   stage.setScene(scene);
                   textField.setText("Enter the custom withdrawn amount:");
                   StringBuilder customAmountResult = enteredDigits;
                   okBtn.setOnAction(x -> {
                       if (Double.parseDouble(customAmountResult.toString()) <= balance) {
                           balance -= Double.parseDouble(customAmountResult.toString());
                           balanceLabel.setText("Balance: $" + String.valueOf(balance));
                           textField.setText("Successfully Withdrawn $" + Double.parseDouble(customAmountResult.toString()));
                       } else {
                           textField.setText("Insufficient balance for withdrawal");
                       }
                   });

               });


        });


        Button ProfileSubmitButton = createActionButton("Submit");
        ProfileSubmitButton.setStyle("-fx-font-size: 14px; -fx-base: " + primaryColor + "; -fx-text-fill: " + secondaryColor + ";");
        ProfileSubmitButton.setOnAction(e -> {
            String cvv = cvvField.getText();
            String expdate = expirationDateField.getText();
            String cardNumber = cardNumberField.getText();
            String pinCode = pinCodeField.getText();

            String url
                    = "jdbc:mysql://localhost:3306/PROFILE";
            String username = "root";
            String password = "password";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = con.prepareStatement("insert into PROFILE.ACCOUNT (CARDNUMBER, CVV, EXPDATE, PINCODE) values(?, ?, ?, ?)");
                preparedStatement.setString(1, cardNumber);
                preparedStatement.setString(2, cvv);
                preparedStatement.setString(3,expdate);
                preparedStatement.setString(4, pinCode);
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }






            boolean isLoggedIn = false;
            depositBtn.setDisable(false);
            withdrawBtn.setDisable(false);



            cvvField.clear();
            expirationDateField.clear();
            cardNumberField.clear();
            pinCodeField.clear();

            stage.setScene(scene);
        });






        profileLayout.getChildren().add(ProfileSubmitButton);
    }






    private void handleButtonPress(String buttonValue, TextField textField)  {
        if (buttonValue.equals("Cancel")) {
            enteredDigits.deleteCharAt(enteredDigits.length() - 1);
        } else if (buttonValue.equals("OK")) {

        } else {
            enteredDigits.append(buttonValue);
        }

        textField.setText(enteredDigits.toString());
    }

    private Button createKeypadButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(60, 60);
        button.setStyle("-fx-font-size: 18px; -fx-base: #E0E0E0;");

        if (text.equals("OK")) {
            button.setStyle("-fx-font-size: 18px; -fx-base: #4CAF50;");
        } else if (text.equals("Cancel")) {
            button.setStyle("-fx-font-size: 18px; -fx-base: #F44336;");
        } else if (text.equals("0")) {
            button.setStyle("-fx-font-size: 18px; -fx-base: #E0E0E0;");
        } else {
            button.setStyle("-fx-font-size: 18px; -fx-base: #BDBDBD;");
        }

        return button;
    }

    private Button createActionButton(String text) {
            Button button = new Button(text);
            button.setPrefSize(120, 40);
            button.setStyle("-fx-font-size: 14px; -fx-base: lightgray;");
            return button;

    }


    private Button createDepositOptionButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(120, 40);
        button.setStyle("-fx-font-size: 14px; -fx-base: lightgray;");
        return button;
    }






    public static void main(String[] args) {
        launch();
    }
}
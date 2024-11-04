package ui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SentimentAnalysisApp extends Application {

    private TextField csvPathField;
    private TextArea tweetDisplayArea;
    private Button loadCsvButton, cleanTweetsButton, classifyButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sentiment Analysis");

        // Text field to display CSV file path
        csvPathField = new TextField();
        csvPathField.setPromptText("No file chosen");
        csvPathField.setEditable(false);
        csvPathField.setMinWidth(300);

        // Button to load CSV file
        loadCsvButton = createRoundedButton("Load CSV");
        loadCsvButton.setOnAction(e -> loadCsvFile());

        // Button to clean tweets
        cleanTweetsButton = createRoundedButton("Clean Tweets");
        cleanTweetsButton.setOnAction(e -> cleanTweets());

        // Button to classify tweets
        classifyButton = createRoundedButton("Classify Tweets");
        classifyButton.setOnAction(e -> classifyTweets());

        // Text area to display tweets
        tweetDisplayArea = new TextArea();
        tweetDisplayArea.setPromptText("Tweets will appear here...");
        tweetDisplayArea.setWrapText(true);
        tweetDisplayArea.setEditable(false);

        // Layout for buttons
        HBox buttonBox = new HBox(10, loadCsvButton, cleanTweetsButton, classifyButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Main layout
        VBox mainLayout = new VBox(15);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(csvPathField, buttonBox, tweetDisplayArea);

        // Scene and Stage
        Scene scene = new Scene(mainLayout, 500, 400);
        scene.getStylesheets().add("style.css"); // Add CSS for more styling
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadCsvFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            csvPathField.setText(selectedFile.getAbsolutePath());
            tweetDisplayArea.setText("Tweets loaded from file: " + selectedFile.getName());
            // Here you would read and display the content of the file
        } else {
            csvPathField.setText("No file chosen");
        }
    }

    private void cleanTweets() {
        tweetDisplayArea.setText("Tweets have been cleaned.");
        // Implement the cleaning logic here
    }

    private void classifyTweets() {
        tweetDisplayArea.setText("Classification results will be displayed here.");
        // Implement the classification logic here
    }

    private Button createRoundedButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 20;");
        button.setMinSize(120, 40);
        button.setShape(new Rectangle(100, 30, 20, 20)); // Create rounded effect with Shape
        return button;
    }
}

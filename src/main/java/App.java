import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    
    private TextField studentNameField;
    private TextField mathField, englishField, scienceField;
    private Label resultLabel;
    private Label gradeLabel;
    private Label percentageLabel;
    private Label performanceLabel;
    private ProgressBar gradeProgressBar;
    private VBox resultBox;
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("📊 Student Grade Calculator - Advanced");
        stage.setWidth(850);
        stage.setHeight(900);
        
        // Main container with beautiful gradient background
        VBox root = new VBox(25);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        root.setAlignment(Pos.TOP_CENTER);
        
        // ========== TITLE SECTION ==========
        VBox titleBox = new VBox(10);
        titleBox.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("📚 Student Grade Calculator");
        titleLabel.setStyle(
            "-fx-font-size: 42; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: white; " +
            "-fx-font-family: 'Arial';"
        );
        Label subtitleLabel = new Label("Advanced Grade Calculation & Performance Analysis");
        subtitleLabel.setStyle(
            "-fx-font-size: 14; " +
            "-fx-text-fill: rgba(255, 255, 255, 0.8); " +
            "-fx-font-family: 'Arial';"
        );
        titleBox.getChildren().addAll(titleLabel, subtitleLabel);
        
        // ========== INPUT SECTION ==========
        VBox inputSection = createStyledBox("#FFFFFF", 20);
        inputSection.setPadding(new Insets(25));
        inputSection.setSpacing(15);
        
        Label inputTitleLabel = new Label("📝 Enter Student Information");
        inputTitleLabel.setStyle(
            "-fx-font-size: 18; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #333333;"
        );
        inputSection.getChildren().add(inputTitleLabel);
        
        // Student Name
        Label nameLabel = createDarkLabel("👤 Student Name:");
        studentNameField = createStyledTextField("Enter student name");
        inputSection.getChildren().addAll(nameLabel, studentNameField);
        
        // Marks Input - Grid Layout
        Label marksTitle = createDarkLabel("📈 Subject Marks (0-100):");
        inputSection.getChildren().add(marksTitle);
        
        GridPane marksGrid = new GridPane();
        marksGrid.setHgap(20);
        marksGrid.setVgap(12);
        marksGrid.setPadding(new Insets(10, 0, 0, 0));
        
        Label mathLabel = createDarkLabel("🔢 Math:");
        mathField = createStyledTextField("Enter marks");
        marksGrid.add(mathLabel, 0, 0);
        marksGrid.add(mathField, 1, 0);
        
        Label englishLabel = createDarkLabel("📖 English:");
        englishField = createStyledTextField("Enter marks");
        marksGrid.add(englishLabel, 0, 1);
        marksGrid.add(englishField, 1, 1);
        
        Label scienceLabel = createDarkLabel("🔬 Science:");
        scienceField = createStyledTextField("Enter marks");
        marksGrid.add(scienceLabel, 0, 2);
        marksGrid.add(scienceField, 1, 2);
        
        inputSection.getChildren().add(marksGrid);
        
        // Buttons HBox
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 0, 0));
        
        Button calculateButton = createStyledButton("✓ Calculate Grade", "#667eea");
        calculateButton.setPrefWidth(200);
        calculateButton.setOnAction(e -> calculateGrade());
        
        Button clearButton = createStyledButton("🔄 Clear All", "#FF6B6B");
        clearButton.setPrefWidth(150);
        clearButton.setOnAction(e -> clearFields());
        
        buttonBox.getChildren().addAll(calculateButton, clearButton);
        inputSection.getChildren().add(buttonBox);
        
        // ========== RESULT SECTION ==========
        resultBox = createStyledBox("#FFFFFF", 20);
        resultBox.setPadding(new Insets(25));
        resultBox.setSpacing(15);
        
        Label resultTitleLabel = new Label("🏆 Results & Analysis");
        resultTitleLabel.setStyle(
            "-fx-font-size: 18; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #333333;"
        );
        
        gradeLabel = new Label("Grade: -");
        gradeLabel.setStyle(
            "-fx-font-size: 56; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #667eea; " +
            "-fx-font-family: 'Arial';"
        );
        
        percentageLabel = new Label("Average: 0.00%");
        percentageLabel.setStyle(
            "-fx-font-size: 20; " +
            "-fx-text-fill: #764ba2; " +
            "-fx-font-weight: bold;"
        );
        
        gradeProgressBar = new ProgressBar(0);
        gradeProgressBar.setPrefHeight(20);
        gradeProgressBar.setStyle(
            "-fx-accent: #667eea; " +
            "-fx-padding: 5;"
        );
        
        performanceLabel = new Label("Enter marks and click Calculate");
        performanceLabel.setStyle(
            "-fx-font-size: 14; " +
            "-fx-text-fill: #333333; " +
            "-fx-font-weight: bold;"
        );
        performanceLabel.setWrapText(true);
        
        resultLabel = new Label();
        resultLabel.setStyle(
            "-fx-font-size: 13; " +
            "-fx-text-fill: #555555;"
        );
        resultLabel.setWrapText(true);
        
        resultBox.getChildren().addAll(
            resultTitleLabel,
            new Separator(),
            gradeLabel,
            percentageLabel,
            gradeProgressBar,
            performanceLabel,
            new Separator(),
            resultLabel
        );
        resultBox.setVisible(false);
        
        // ========== SCROLL PANE ==========
        ScrollPane scrollPane = new ScrollPane(new VBox(25, titleBox, inputSection, resultBox));
        scrollPane.setStyle("-fx-control-inner-background: transparent; -fx-padding: 0;");
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        
        root.getChildren().add(scrollPane);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    // Create styled box with shadow
    private VBox createStyledBox(String bgColor, int radius) {
        VBox box = new VBox();
        box.setStyle(
            "-fx-background-color: " + bgColor + "; " +
            "-fx-border-color: #e0e0e0; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: " + radius + "; " +
            "-fx-background-radius: " + radius + ";"
        );
        
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.color(0, 0, 0, 0.15));
        shadow.setRadius(12);
        shadow.setOffsetY(4);
        box.setEffect(shadow);
        
        return box;
    }
    
    // Create dark label
    private Label createDarkLabel(String text) {
        Label label = new Label(text);
        label.setStyle(
            "-fx-font-size: 13; " +
            "-fx-text-fill: #333333; " +
            "-fx-font-weight: bold;"
        );
        return label;
    }
    
    // Create styled text field
    private TextField createStyledTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setPrefHeight(40);
        field.setStyle(
            "-fx-font-size: 14; " +
            "-fx-padding: 10; " +
            "-fx-background-color: #f5f5f5; " +
            "-fx-text-fill: #333333; " +
            "-fx-border-color: #ddd; " +
            "-fx-border-width: 1; " +
            "-fx-border-radius: 8; " +
            "-fx-background-radius: 8; " +
            "-fx-control-inner-background: #f5f5f5;"
        );
        return field;
    }
    
    // Create styled button
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setPrefHeight(45);
        button.setStyle(
            "-fx-font-size: 14; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: " + color + "; " +
            "-fx-border-radius: 8; " +
            "-fx-background-radius: 8; " +
            "-fx-cursor: hand; " +
            "-fx-padding: 12;"
        );
        
        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-font-size: 14; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: " + adjustBrightness(color) + "; " +
            "-fx-border-radius: 8; " +
            "-fx-background-radius: 8; " +
            "-fx-cursor: hand; " +
            "-fx-padding: 12;"
        ));
        
        button.setOnMouseExited(e -> button.setStyle(
            "-fx-font-size: 14; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: white; " +
            "-fx-background-color: " + color + "; " +
            "-fx-border-radius: 8; " +
            "-fx-background-radius: 8; " +
            "-fx-cursor: hand; " +
            "-fx-padding: 12;"
        ));
        
        return button;
    }
    
    // Adjust brightness for hover effect
    private String adjustBrightness(String color) {
        // Simple adjustment - in production, use proper color manipulation
        return color;
    }
    
    // Calculate grade logic
    private void calculateGrade() {
        try {
            String studentName = studentNameField.getText().trim();
            if (studentName.isEmpty()) {
                showAlert("Please enter student name!", Alert.AlertType.WARNING);
                return;
            }
            
            double math = Double.parseDouble(mathField.getText());
            double english = Double.parseDouble(englishField.getText());
            double science = Double.parseDouble(scienceField.getText());
            
            if (math < 0 || math > 100 || english < 0 || english > 100 || science < 0 || science > 100) {
                showAlert("Marks must be between 0 and 100!", Alert.AlertType.WARNING);
                return;
            }
            
            double average = (math + english + science) / 3.0;
            String grade = getGrade(average);
            
            gradeLabel.setText(grade);
            percentageLabel.setText(String.format("Average: %.2f%%", average));
            gradeProgressBar.setProgress(average / 100.0);
            performanceLabel.setText(getPerformanceText(average));
            
            resultLabel.setText(
                "Student: " + studentName + "\n\n" +
                "Subject Scores:\n" +
                "  • Math: " + math + "/100\n" +
                "  • English: " + english + "/100\n" +
                "  • Science: " + science + "/100\n\n" +
                "Grade Status: " + getGradeStatus(average)
            );
            resultBox.setVisible(true);
            
        } catch (NumberFormatException e) {
            showAlert("Please enter valid numbers for marks!", Alert.AlertType.ERROR);
        }
    }
    
    // Get grade status
    private String getGradeStatus(double average) {
        if (average >= 90) return "🌟 Outstanding! Excellent work!";
        else if (average >= 80) return "✅ Great job! Well done!";
        else if (average >= 70) return "👍 Good! Keep improving!";
        else if (average >= 60) return "⚠️ Fair. Study harder!";
        else return "❌ Need major improvement!";
    }
    
    // Get performance description
    private String getPerformanceText(double average) {
        if (average >= 90) return "Outstanding Performance";
        else if (average >= 80) return "Great Performance";
        else if (average >= 70) return "Good Performance";
        else if (average >= 60) return "Fair Performance";
        else return "Needs Improvement";
    }
    
    // Get grade
    private String getGrade(double average) {
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 70) return "B";
        else if (average >= 60) return "C";
        else if (average >= 50) return "D";
        else return "F";
    }
    
    // Clear all fields
    private void clearFields() {
        studentNameField.clear();
        mathField.clear();
        englishField.clear();
        scienceField.clear();
        resultBox.setVisible(false);
        gradeProgressBar.setProgress(0);
    }
    
    // Show alert dialog
    private void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Error" : "Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
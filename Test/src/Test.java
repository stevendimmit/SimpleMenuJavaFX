import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;


public class Test extends Application {
	private int i = 0;
    
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        final BorderPane bPane = new BorderPane();
    	final double randomHue = randomGreenHue();
    	
    	primaryStage.setTitle("Menu Example");
        TextField textField = new TextField();
        MenuItem menuItem1 = new MenuItem("Show date and time");
        MenuItem menuItem2 = new MenuItem("Write to file log.txt");
        MenuItem menuItem3 = new MenuItem("Random Green Hue. Initial Value: " + randomHue);
        MenuItem menuItem4 = new MenuItem("Close Program");
        
        //Set up action events for menu buttons
        menuItem1.setOnAction(event -> {
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Format the date and time using a formatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy     HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            textField.setText(formattedDateTime);
            
        });

        menuItem2.setOnAction(event -> {
            String desktopPath = System.getProperty("user.home") + "/Desktop/";
            String fileName = desktopPath + "log.txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                // Write some text to the file
                writer.write(textField.getText());
                System.out.println("Successfully wrote to the file: " + fileName);
            } catch (IOException e) {
                System.out.println("Error writing to the file: " + fileName);
            }
        });
        
        menuItem3.setOnAction(event -> { 
        	// if statement to use the shown Initial random value first then use new random values
        	if (i == 0) {
        		Color color = Color.hsb(randomHue, 1.0, 1.0); // Create color with full saturation and brightness
        		bPane.setBackground(new Background(new BackgroundFill(color, null, null)));
        		i = 1 + i;
        	} else {
        		Color color = Color.hsb(randomGreenHue(), 1.0, 1.0); // Create color with full saturation and brightness
        		bPane.setBackground(new Background(new BackgroundFill(color, null, null)));
        	}
        }); 
        
        menuItem4.setOnAction(event -> {
           Platform.exit();
        });
        
        MenuButton menuButton = new MenuButton("Options",null, menuItem1, menuItem2, menuItem3, menuItem4);
        bPane.setTop(menuButton);  
        bPane.setCenter(textField);

        Scene scene = new Scene(bPane, 200, 100);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Method to generate a random hue that is "green"
    public static double randomGreenHue() {
    	double hue = 90.0 + Math.random() * 90.0; // Random value between 90.0 and 180.0 s
    	return hue;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

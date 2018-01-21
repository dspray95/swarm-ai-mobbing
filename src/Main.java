import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import simulation.Logger;
import simulation.Simulator;
import simulation.config.SimulationOptions;
import visualiser.Visualiser;

import java.util.ArrayList;

public class Main extends Application {

    //Config
    static SimulationOptions options;
    //GUI controls
    static StackPane homePane;
    static Button btnRunSimulation;
    static Button btnRunVisualiser;
    static Label lblBanner;
    static Logger logger;

    public static void main(String args[]){
        options = new SimulationOptions();
        homePane = new StackPane();
        btnRunSimulation = new Button();
        btnRunVisualiser = new Button();
        lblBanner = new Label();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ArrayList<Control> vControls = new ArrayList<>();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vControls.add(lblBanner);
        vControls.add(btnRunSimulation);
        vControls.add(btnRunVisualiser);

        primaryStage.setTitle("Mobbing Simulation");
        lblBanner.setText("Swarm AI - Mobbing Simulation");
        lblBanner.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnRunSimulation.setText("Run simulation");
        btnRunSimulation.setOnAction(event -> ButtonEvent(btnRunSimulation));
        btnRunVisualiser.setText("Run visualiser");
        btnRunVisualiser.setOnAction(event -> ButtonEvent(btnRunVisualiser));

        for (Control control : vControls) {
            VBox.setMargin(control, new Insets(8, 0, 8, 0));
            vBox.getChildren().add(control);
        }

        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(vBox);

        primaryStage.setScene(new Scene(root, 0, 0));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void ButtonEvent(Button eventButton) {
        if(eventButton == btnRunSimulation){
            try{
                logger = new Logger();
            }catch (Exception e){
                return;
            }
            System.out.println("Running simulation...");
            Simulator simulator = new Simulator(logger, options);
            simulator.runSimulationForTicks(20);
        }
        else if(eventButton == btnRunVisualiser){
            System.out.println("Running visualiser...");
            Visualiser visualiser = new Visualiser(logger.loadStates());
        }
    }
}

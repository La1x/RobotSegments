package laix.robotsegments.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import laix.robotsegments.MainApp;
import laix.robotsegments.model.LawLinear;
import laix.robotsegments.model.Piston;
import laix.robotsegments.model.Task;
import laix.robotsegments.util.DrawTool;

public class LineSettingsController {
    @FXML
    private Canvas canvas;
    @FXML
    private TextField hightField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField x0Field;
    @FXML
    private TextField xkField;
    @FXML
    private TextField NField;
    @FXML
    private TextField aField;
    @FXML
    private TextField tAccField;
    @FXML
    private TextField tStMField;
    @FXML
    private TextField tBakField;
    @FXML
    private Button drawButton;

    private MainApp mainApp;
    //private static float X;
    private static Timeline timeline;
    private DrawTool drawtool;

    public LineSettingsController() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        drawtool = new DrawTool(canvas);
        //initial draws
        drawtool.clear();
        drawtool.drawBasicLine();
    }

    @FXML
    private void handleDraw() {
        drawButton.setDisable(true);

        //get values from fields
        // TODO xk from tasks
        double Xk = Double.valueOf( xkField.getText() ); //law

        Piston piston = new Piston(Double.valueOf( x0Field.getText() ),
                canvas.getHeight()-15,
                Double.valueOf( widthField.getText() ),
                Double.valueOf( hightField.getText() ),
                Color.GREEN
        );

        Task task = new Task();
        task.setX0( Double.valueOf( x0Field.getText() ) );
        task.setXk( Double.valueOf( xkField.getText() ) );
        task.setN( Double.valueOf( NField.getText() ) );
        task.setLawLinear(
                new LawLinear(0,
                        0,
                        80)
        );

        Piston piston2 = new Piston(Double.valueOf( x0Field.getText() ),
                canvas.getHeight()-115,
                Double.valueOf( widthField.getText() ),
                Double.valueOf( hightField.getText() ),
                Color.DARKRED
        );

        Task task2 = new Task();
        task2.setX0( Double.valueOf( x0Field.getText() ) );
        task2.setXk( Double.valueOf( xkField.getText() ) );
        task2.setN( Double.valueOf( NField.getText() ) );
        task2.settAcceleration(Double.valueOf( tAccField.getText() ));
        task2.settSteadyMotion(Double.valueOf( tStMField.getText() ));
        task2.settBaking(Double.valueOf( tBakField.getText() ));
        task2.setLawLinear(
                new LawLinear(0,
                                  Double.valueOf( aField.getText() ),
                               0
                )
        );

        timeline  = new Timeline();
        //set number of draws
        timeline.setCycleCount( Timeline.INDEFINITE ); // (int) N + 1

        KeyFrame kf = new KeyFrame(
                Duration.millis(55),                // 1 per 55 ms
                a -> {
                        //TODO полиморфизм для рисования разных обхектов
                        drawtool.clear();
                        drawtool.drawBasicLine();
                        drawtool.draw(piston);
                        drawtool.draw(piston2);

                        // --- TEST BLOCK ---
                        if (piston.getX() < Xk) {
                            double nextX = task.getNextX();
                            piston.moveTo( nextX );

                        }

                        if (piston2.getX() < Xk) {
                            double nextX = task2.getNextX();
                            piston2.moveTo( nextX );

                        }
                        if (piston.getX() >= Xk && piston2.getX() >= Xk) {
                            timeline.stop(); // TODO set flag "stop" in each piston's block
                            drawButton.setDisable(false);
                            System.out.println("timeline stopped");
                        }
                        // --- END ---
                    }
                );

        timeline.getKeyFrames().add( kf );
        timeline.play();
    }
}

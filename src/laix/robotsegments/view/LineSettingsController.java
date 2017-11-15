package laix.robotsegments.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import laix.robotsegments.MainApp;
import laix.robotsegments.model.Piston;
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
    private Button drawButton;

    private MainApp mainApp;
    private static float X;
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
        float height = Float.valueOf( hightField.getText() ); //rect
        float width = Float.valueOf( widthField.getText() ); //rect
        float X0 = Float.valueOf( x0Field.getText() ); //law
        float Xk = Float.valueOf( xkField.getText() ); //law
        float N = Float.valueOf( NField.getText() );   //law
        X = X0;
        float deltaX = (Xk - X0) / N;
        Piston piston = new Piston(X, canvas.getHeight()-15, width, height);

        Timeline timeline  = new Timeline();
        //set number of draws
        timeline.setCycleCount( (int) N + 1 );

        KeyFrame kf = new KeyFrame(
                Duration.millis(55),                // 1 per 55 ms
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        drawtool.clear();
                        drawtool.drawBasicLine();
                        drawtool.draw(piston);

                        X = (X + deltaX); // law
                        piston.setX(X);

                        if (X >= Xk) {
                            drawButton.setDisable(false);
                        }
                    }
                });

        timeline.getKeyFrames().add( kf );
        timeline.play();
    }
}

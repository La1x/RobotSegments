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
    private Button drawButton;

    private MainApp mainApp;
    private static float X;
    private static int step;
    private static int Ti;
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
        // TODO fields for v0, a, tAcceleration
        // TODO double
        float height = Float.valueOf( hightField.getText() ); //rect
        float width = Float.valueOf( widthField.getText() ); //rect
        float X0 = Float.valueOf( x0Field.getText() ); //law
        float Xk = Float.valueOf( xkField.getText() ); //law
        float N = Float.valueOf( NField.getText() );   //law
        X = X0;
        float deltaX = (Xk - X0) / N;
        Piston piston = new Piston(X, canvas.getHeight()-15, width, height);

        // --- TEST BLOCK ---

        Task task = new Task();
        task.setX0( Double.valueOf( x0Field.getText() ) );
        task.setXk( Double.valueOf( xkField.getText() ) );
        task.setN( Double.valueOf( NField.getText() ) );
        task.settAcceleration(1000);
        // task.settSteadyMotion();

        LawLinear linear = new LawLinear(15,30,
                                        0,0);
        task.setLawLinear(linear);
        Piston piston2 = new Piston(X, canvas.getHeight()-115, width, height);

        // --- END ---

        timeline  = new Timeline();
        //set number of draws
        timeline.setCycleCount( (int) N + 1 );

        KeyFrame kf = new KeyFrame(
                Duration.millis(55),                // 1 per 55 ms
                a -> {
                        //TODO полиморфизм для рисования разных обхектов
                        /*
                            // при инициализации
                            Piston piston = new Piston()
                            piston.setLaw(new LawLinear(0,20,55,2000))

                            clear()
                            drawLine()
                            for (Piston p : listOfPistons ) {
                                drawtool.draw(p);
                                p.calculateNextStep(); // вычисляется новый X и делается setX()
                                // TODO возложить проверку координаты на выход за Xk на закон(а значение брать в таске?) в момент вычисления нового X
                            }
                            if(all pistons at end) {
                                timeline.stop();
                                drawButton.setDisable(false)
                            }
                         */
                        drawtool.clear();
                        drawtool.drawBasicLine();
                        drawtool.draw(piston);
                        drawtool.draw(piston2);

                        if (X < Xk) {
                            X = (X + deltaX); // law
                            piston.setX(X);
                        }

                        if (X >= Xk) {
                            drawButton.setDisable(false);
                        }

                        // --- TEST BLOCK ---
                        if (piston2.getX() < Xk) {
                            piston2.moveTo( task.getNextX() );
                        }
                        if (piston.getX() > Xk && piston2.getX() > Xk) {
                            timeline.stop();
                            System.out.println("timeline stopped");
                        }
                        // --- END ---
                    }
                );

        timeline.getKeyFrames().add( kf );
        timeline.play();
    }
}

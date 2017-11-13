package laix.robotsegments.util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawTools {
    private static int DOWN_BORDER = 15;

    public static void basicLine(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setStroke(Color.BLACK);
        gc.strokeLine(0 + DOWN_BORDER,canvas.getHeight() - DOWN_BORDER,
                      canvas.getWidth() - DOWN_BORDER, canvas.getHeight() - DOWN_BORDER);
    }

    public static void clear(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill( Color.WHITE );
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public static void rect(Canvas canvas, double x1, double y1, double w, double h) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill( Color.BLUE );
        gc.fillRect(x1, y1-h-1, w, h);
    }
}

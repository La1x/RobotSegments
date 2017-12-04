package laix.robotsegments.util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import laix.robotsegments.model.Piston;

/*
 * DrawTool class for drawing on canvas.
 */
public class DrawTool {
    private GraphicsContext graphicsContext;
    private Canvas canvas;
    public final int DOWN_BORDER = 15;

    public DrawTool(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public void draw(Piston piston) {
        graphicsContext.setFill(piston.getColor());
        this.rect(piston.getX(), piston.getY(),
                  piston.getWidth(), piston.getHeight());
    }

    public void drawBasicLine(Piston piston) {
        graphicsContext.setStroke(Color.BLACK);

        double i = 0;
        double stop = (double) canvas.getWidth() - DOWN_BORDER - 15;
        do {
            graphicsContext.strokeLine(i + DOWN_BORDER,
                                        piston.getY() + 5,
                                        i + DOWN_BORDER + 5,
                                        piston.getY());
            i+=5;
        } while (i < stop);

        graphicsContext.strokeLine(0 + DOWN_BORDER, piston.getY(),
                canvas.getWidth() - DOWN_BORDER, piston.getY());
    }

    public void clear() {
        graphicsContext.setFill( Color.WHITE );
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void rect(double x1, double y1, double w, double h) {
        graphicsContext.fillRect(x1, y1-h-1, w, h);
    }

    private void line(double x1, double y1, double x2, double y2) {
        graphicsContext.strokeLine(x1,y1,x2,y2);
    }
}

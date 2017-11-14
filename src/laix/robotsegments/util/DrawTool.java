package laix.robotsegments.util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import laix.robotsegments.model.Piston;
import laix.robotsegments.model.Point;

/*
 * DrawTool class for drawing on canvas.
 */
public class DrawTool {
    private GraphicsContext graphicsContext;
    private Canvas canvas;
    private final int DOWN_BORDER = 15;

    public DrawTool(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public void draw(Piston piston) {
        this.rect(piston.getX(), piston.getY(),
                  piston.getWidth(), piston.getHeight());
    }

    public void drawBasicLine() {
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.strokeLine(0 + DOWN_BORDER,canvas.getHeight() - DOWN_BORDER,
                canvas.getWidth() - DOWN_BORDER, canvas.getHeight() - DOWN_BORDER);
    }

    public void clear() {
        graphicsContext.setFill( Color.WHITE );
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void rect(double x1, double y1, double w, double h) {
        graphicsContext.setFill( Color.BLUE );
        graphicsContext.fillRect(x1, y1-h-1, w, h);
    }

    private void line(double x1, double y1, double x2, double y2) {
        graphicsContext.strokeLine(x1,y1,x2,y2);
    }
}

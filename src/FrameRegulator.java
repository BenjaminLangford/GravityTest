import javafx.scene.canvas.*;

import java.util.Deque;
import java.util.ArrayDeque;

class FrameRegulator {
    private Deque<Long> frameLengths = new ArrayDeque<>();
    private Integer frameCounter = 0;
    private Long tick = 0L;
    private double frameLength = 0;

    void updateFPS(long now, GraphicsContext gc) {
        frameCounter += 1;
        Long tock = now / 1000000;
        if (tick != 0) {
            frameLength = (tock - tick) / 10000.0;
            frameLengths.addLast(tock - tick);
            if (frameLengths.size() > 30) frameLengths.removeFirst();
            Long total = 0L;
            for (Long x : frameLengths) total += Math.round(1000.0 / x);
            Long fps = total / frameLengths.size();
            gc.fillText("Frame " + frameCounter.toString() + " | " + fps.toString() + " FPS", 10, 20);
        }
        tick = tock;
    }

    double getFrameLength() {
        return frameLength;
    }
}

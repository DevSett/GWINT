package app.view.optionGame;

/**
 * Created by kills on 02.03.2017.
 */
public class ScreenResolution {
    public final int[] SIZEX = {1920, 1600, 1366, 1280, 1024, 960, 864, 720, 640};
    public final int[] SIZEY= {1080, 900, 768, 720, 576, 540, 486, 405, 360};
    public double[] DEL;
    public ScreenResolution()
    {
            DEL = new double[]{
                    (double) SIZEX[0] / SIZEX[0],
                    (double) SIZEX[0] / SIZEX[1],
                    (double) SIZEX[0] / SIZEX[2],
                    (double) SIZEX[0] / SIZEX[3],
                    (double) SIZEX[0] / SIZEX[4],
                    (double) SIZEX[0] / SIZEX[5],
                    (double) SIZEX[0] / SIZEX[6],
                    (double) SIZEX[0] / SIZEX[7],
                    (double) SIZEX[0] / SIZEX[8],
        };
    }
}

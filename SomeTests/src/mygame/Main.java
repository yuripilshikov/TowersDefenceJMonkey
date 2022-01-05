package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private Box b = new Box(1, 1, 1);
    private int[][] map = {{1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, {1, 2, 1, 2, 3, 2, 1, 2, 1, 2}, {1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, {1, 3, 1, 2, 1, 2, 1, 2, 1, 2}};

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {

        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);

        drawMap();


    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void drawMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Geometry geom = new Geometry("Box", b);
                geom.setLocalTranslation(i * 2, -2, j * 2);

                Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                if (map[i][j] == 1) {
                    mat.setColor("Color", ColorRGBA.Gray);
                } else if (map[i][j] == 2) {
                    mat.setColor("Color", ColorRGBA.Green);
                } else if (map[i][j] == 3) {
                    mat.setColor("Color", ColorRGBA.Yellow);
                }

                geom.setMaterial(mat);

                rootNode.attachChild(geom);
            }
        }
    }
}

// Cube chaser 2

package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class CubeChaser2 extends SimpleApplication {
    
    // make geoms accessible
    private static Box mesh = new Box(Vector3f.ZERO, 1, 1, 1);
    private Ray ray = new Ray();

    public static void main(String[] args) {
        // settings
        AppSettings settings = new AppSettings(true);
        settings.setTitle("Cube Chaser");
        //settings.setSettingsDialogImage("");
        CubeChaser2 app = new CubeChaser2();
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        makeCubes(40);
    }
    
    // cube generator
    public Geometry myBox(String name, Vector3f loc, ColorRGBA color) {
        Geometry geom = new Geometry(name, mesh);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);
        geom.setLocalTranslation(loc);
        return geom;
    }
    
    // make some random cubes
    private void makeCubes(int number) {
        for(int i = 0; i < number; i++) {
            // randomize coordinates
            Vector3f loc = new Vector3f(
                FastMath.nextRandomInt(-20, 20),
                FastMath.nextRandomInt(-20, 20),
                FastMath.nextRandomInt(-20, 20));
            rootNode.attachChild(myBox("Cube" + i, loc, ColorRGBA.randomColor()));
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        CollisionResults results = new CollisionResults();
        ray.setOrigin(cam.getLocation());
        ray.setDirection(cam.getDirection());
        rootNode.collideWith(ray, results);
        if(results.size() > 0) {
            Geometry target = results.getClosestCollision().getGeometry();
            if(cam.getLocation().distance(target.getLocalTranslation()) < 10) {
                target.move(cam.getDirection());
            }
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}

// The template

package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
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
public class Main extends SimpleApplication {
    

    public static void main(String[] args) {
        // settings
        AppSettings settings = new AppSettings(true);
        settings.setTitle("My awesome game");
        //settings.setSettingsDialogImage("");
        Main app = new Main();
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
        
        // second cube
        Box b1 = new Box(1, 1, 1);
        Geometry geom1 = new Geometry("Box1", b1);

        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Yellow);
        geom1.setMaterial(mat1);

        rootNode.attachChild(geom1);
        
        // position a cube
        Vector3f v = new Vector3f(2.0f, 1.0f, -3.0f);
        geom1.setLocalTranslation(v);
        
        // scale cube
        geom.setLocalScale(0.5f); // absolute scale
        geom1.scale(2.0f);  // relative scale
        
        // rotate
        float r = FastMath.DEG_TO_RAD * 45f;
        geom.rotate(r, r, r);
        
        Quaternion roll045 = new Quaternion();
        roll045.fromAngleAxis(45*FastMath.DEG_TO_RAD, Vector3f.UNIT_X);
        geom1.setLocalRotation(roll045);
        
        // camera
        //cam.setLocation(v);
        
        
        // nodes
        Node pivot = new Node("pivot node");
        pivot.attachChild(geom);
        pivot.attachChild(geom1);
        pivot.rotate(00, 0, FastMath.DEG_TO_RAD * 45);
        rootNode.attachChild(pivot);
        
        
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}

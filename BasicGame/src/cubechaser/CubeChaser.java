// Cube chaser 2

package cubechaser;

import mygame.*;
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
public class CubeChaser extends SimpleApplication {
    public static void main(String[] args) {
        // settings
        AppSettings settings = new AppSettings(true);
        settings.setTitle("Cube Chaser");
        //settings.setSettingsDialogImage("");
        CubeChaser app = new CubeChaser();
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {        
        flyCam.setMoveSpeed(100f);
        CubeChaserState state = new CubeChaserState();
        stateManager.attach(state);
    }
    
    

    @Override
    public void simpleUpdate(float tpf) {
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}

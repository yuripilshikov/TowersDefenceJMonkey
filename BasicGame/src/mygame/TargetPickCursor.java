// Target pick Center
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.Trigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class TargetPickCursor extends SimpleApplication {

    private final static Trigger TRIGGER_COLOR = new KeyTrigger(KeyInput.KEY_SPACE);
    private final static Trigger TRIGGER_COLOR1 = new KeyTrigger(KeyInput.KEY_T);
    private final static Trigger TRIGGER_ROTATE = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    private final static String MAPPING_COLOR = "Toggle Color";
    private final static String MAPPING_ROTATE = "Rotate";

    // make geoms accessible
    private static Box mesh = new Box(Vector3f.ZERO, 1, 1, 1);

    public static void main(String[] args) {
        // settings
        AppSettings settings = new AppSettings(true);
        settings.setTitle("TargetPickCursor");
        //settings.setSettingsDialogImage("");
        TargetPickCursor app = new TargetPickCursor();
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // add mappings
        inputManager.addMapping(MAPPING_COLOR, TRIGGER_COLOR, TRIGGER_COLOR1);
        inputManager.addMapping(MAPPING_ROTATE, TRIGGER_ROTATE);

        // register mappings
        inputManager.addListener(actionListener, new String[]{MAPPING_COLOR});
        inputManager.addListener(analogListener, new String[]{MAPPING_ROTATE});

        rootNode.attachChild(myBox("Red cube", new Vector3f(0, 1.5f, 0), ColorRGBA.Red));
        rootNode.attachChild(myBox("Blue cube", new Vector3f(0, -1.5f, 0), ColorRGBA.Blue));
        
        // show cursor and turn off mouse look
        flyCam.setDragToRotate(true);
        inputManager.setCursorVisible(true);

    }

    // Listeners for mappings
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals(MAPPING_COLOR) && !isPressed) {
                //mesh.getMaterial().setColor("Color", ColorRGBA.randomColor());
            }
        }

    };

    private AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float intensity, float tpf) {
            if (name.equals(MAPPING_ROTATE)) {
                CollisionResults results = new CollisionResults();
                Vector2f click2d = inputManager.getCursorPosition();
                Vector3f click3d = cam.getWorldCoordinates(new Vector2f(click2d.getX(), click2d.getY()), 0f);
                Vector3f dir = cam.getWorldCoordinates(new Vector2f(click2d.getX(), click2d.getY()), 1f).subtractLocal(click3d);
                Ray ray = new Ray(click3d ,dir);
                rootNode.collideWith(ray, results);
                
                if(results.size() > 0) {
                    Geometry target = results.getClosestCollision().getGeometry();
                    if(target.getName().equals("Red cube")) {
                        target.rotate(0, -intensity * 2, 0);                        
                    } else if(target.getName().equals("Blue cube")) {
                        target.rotate(0, intensity * 2, 0);
                    }                    
                } else {
                    System.out.println("Selection: nothing");
                }
                
                        

            }
        }
    };

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
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

}

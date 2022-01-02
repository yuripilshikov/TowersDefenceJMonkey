// User input

package mygame;

import com.jme3.app.SimpleApplication;
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
public class UserInput extends SimpleApplication {
    private final static Trigger TRIGGER_COLOR = new KeyTrigger(KeyInput.KEY_SPACE);
    private final static Trigger TRIGGER_COLOR1 = new KeyTrigger(KeyInput.KEY_T);
    private final static Trigger TRIGGER_ROTATE = new MouseButtonTrigger(MouseInput.BUTTON_LEFT);
    private final static String MAPPING_COLOR = "Toggle Color";
    private final static String MAPPING_ROTATE = "Rotate";
    
    // make geoms accessible
    private Geometry geom, geom1;
    

    public static void main(String[] args) {
        // settings
        AppSettings settings = new AppSettings(true);
        settings.setTitle("User Input tests");
        //settings.setSettingsDialogImage("");
        UserInput app = new UserInput();
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
        
        
        Box b = new Box(1, 1, 1);
        geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
        
        // second cube
        Box b1 = new Box(1, 1, 1);
        geom1 = new Geometry("Box1", b1);

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
    
    // Listeners for mappings
    private ActionListener actionListener = new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if(name.equals(MAPPING_COLOR) && !isPressed) {
                    geom.getMaterial().setColor("Color", ColorRGBA.randomColor());
                }
            }
                    
    };
    
    private AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float intensity, float tpf) {
            if(name.equals(MAPPING_ROTATE)) {
                geom1.rotate(0, intensity, 0);
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
}

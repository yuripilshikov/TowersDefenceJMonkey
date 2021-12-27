package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
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
        AppSettings settings = new AppSettings(true);
        settings.setTitle("Towers Defence");        
        Main app = new Main();        
        app.setSettings(settings);        
        app.start();
    }

    @Override
    public void simpleInitApp() {        
        Box floor = new Box(33, 1, 33);
        Geometry floorGeom = new Geometry("Floor", floor);
        Material floorMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        floorMat.setColor("Color", ColorRGBA.Orange);
        floorGeom.setMaterial(floorMat);       
        floorGeom.setLocalTranslation(0, -2, 0);
        rootNode.attachChild(floorGeom);
        
        Node playerNode = new Node();
        Node towerNode = new Node();
        Node creepNode = new Node();
        
        Vector3f towerV = new Vector3f(-10, 0, -10);
        Vector3f creepV = new Vector3f(10, 0, 10);
        
        towerNode.setLocalTranslation(towerV);
        creepNode.setLocalTranslation(creepV);
        
        Box baseBox = new Box(2, 2, 4);
        Geometry base = new Geometry("Base", baseBox);
        Material baseMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        baseMat.setColor("Color", ColorRGBA.Yellow);
        base.setMaterial(baseMat);
        playerNode.attachChild(base);
        
        createGeometryForTowerNode(towerNode);
        
        
        Box creepBox = new Box(0.5f, 0.5f, 0.5f);
        Geometry creep = new Geometry("Base", creepBox);
        Material creepMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        creepMat.setColor("Color", ColorRGBA.Black);
        creep.setMaterial(creepMat);
        creepNode.attachChild(creep);
        
        
        //playerNode.setLocalTranslation();
        
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
        
        rootNode.attachChild(playerNode);
        rootNode.attachChild(towerNode);
        rootNode.attachChild(creepNode);
        
        //setDisplayFps(false);
        //setDisplayStatView(false);
    }
    
    void createGeometryForTowerNode(Node towerNode) {
        Box towerBox = new Box(1, 3, 1);
        Geometry tower = new Geometry("Tower", towerBox);
        Material towerMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        towerMat.setColor("Color", ColorRGBA.Green);
        tower.setMaterial(towerMat);
        towerNode.attachChild(tower);
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

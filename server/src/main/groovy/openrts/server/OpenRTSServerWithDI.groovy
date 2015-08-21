package openrts.server

import java.util.logging.Logger
import java.util.prefs.BackingStoreException

import app.OpenRTSApplicationWithDI

import com.google.inject.Injector
import com.google.inject.Module
import com.jme3.app.state.AppStateManager
import com.jme3.asset.AssetManager
import com.jme3.audio.AudioRenderer
import com.jme3.input.InputManager
import com.jme3.network.Server
import com.jme3.renderer.Camera
import com.jme3.renderer.RenderManager
import com.jme3.renderer.ViewPort
import com.jme3.scene.Node
import com.jme3.system.AppSettings
import com.jme3.system.JmeSystem

import exception.TechnicalException
import geometry.math.RandomUtil
import groovy.transform.CompileStatic

@CompileStatic
abstract class OpenRTSServerWithDI extends OpenRTSApplicationWithDI {

	private static final Logger logger = Logger.getLogger(OpenRTSServerWithDI.class.getName());

	static final String gameName = "OpenRTS";
	static int version = 1;

	static Server myServer;
	static final int PORT = 6143;
	Map<Integer, Game> games = new HashMap<Integer, Game>();

	protected Injector injector;
	protected Collection<Module> modules;

	@Override
	public void start() {
		super.start();
		// set some default settings in-case
		// settings dialog is not shown
		if (settings == null) {
			setSettings(new AppSettings(true));
			settings.setWidth(1024);
			settings.setHeight(768);
			// try {
			// settings.load("openrts.example");
			// } catch (BackingStoreException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}

	}


	@Override
	public void initialize() {
		//		bulletAppState = new BulletAppState();
		//		bulletAppState.startPhysics();

		super.initialize();

//		guiNode.setQueueBucket(Bucket.Gui);
//		guiNode.setCullHint(CullHint.Never);
//
//		//		loadStatsView();
//		viewPort.attachScene(rootNode);
//		guiViewPort.attachScene(guiNode);
		//
		//		if (inputManager != null) {
		//			flyCam = new AzertyFlyByCamera(cam);
		//			flyCam.setMoveSpeed(1f);
		//			flyCam.registerWithInput(inputManager);
		//
		//			if (context.getType() == Type.Display) {
		//				inputManager.addMapping("SIMPLEAPP_Exit", new KeyTrigger(KeyInput.KEY_ESCAPE));
		//			}
		//
		//			inputManager.addMapping("SIMPLEAPP_CameraPos", new KeyTrigger(KeyInput.KEY_C));
		//			inputManager.addMapping("SIMPLEAPP_Memory", new KeyTrigger(KeyInput.KEY_M));
		//			inputManager.addListener(actionListener, "SIMPLEAPP_Exit", "SIMPLEAPP_CameraPos", "SIMPLEAPP_Memory");
		//		}

		// call user code
//		simpleInitApp();
		//		stateManager.attach(bulletAppState);
		//		getPhysicsSpace().addTickListener(this);
	}

	public abstract void simpleInitApp();


	public void simpleUpdate(float tpf) {
	}

	public void simpleRender(RenderManager rm) {
	}

	//	public void simplePhysicsUpdate(float tpf) {
	//	}

	//	@Override
	//	public void physicsTick(PhysicsSpace space, float f) {
	//		simplePhysicsUpdate(f);
	//	}

	//	public PhysicsSpace getPhysicsSpace() {
	//		return bulletAppState.getPhysicsSpace();
	//	}
	//
	//	@Override
	//	public void prePhysicsTick(PhysicsSpace arg0, float arg1) {
	//	}

	public static void main(OpenRTSServerWithDI app) {
		OpenRTSApplicationWithDI.appInstance = app;
		logger.info("seed : " + RandomUtil.SEED);

		app.start();
	}

	public void changeSettings() {
		JmeSystem.showSettingsDialog(settings, false);
		if (settings.isFullscreen()) {
			logger.info("Fullscreen not yet supported");
			settings.setFullscreen(false);
		}

		try {
			settings.save("openrts.example");
		} catch (BackingStoreException e) {
			throw new TechnicalException(e);
		}
		appInstance.setSettings(settings);
		appInstance.restart(); // restart the context to apply changes
		cam.resize(settings.getWidth(), settings.getHeight(), true);
	}


}
package sp6.engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sp6.engine.controller.AnimationController;
import sp6.engine.controller.AudioController;
import sp6.engine.controller.CollisionController;
import sp6.engine.controller.Component;
import sp6.engine.controller.InputController;
import sp6.engine.controller.PhysicsController;
import sp6.testgame.Boulder;
import sp6.testgame.Flamingo;
import sp6.testgame.GreenBall;
import sp6.testgame.Player;
import sp6.testgame.SpaceBackground;

/**
 * Loads all resources from XML file into engine.
 */
public class Loader {
    private static final String PROPERTIES_FILE = "sp6.settings";
    private static final String GAME_DATA_PROPERTY = "gamedata";
    private XMLParser xmlParser = new XMLParser();


    private final String gameData;


    public Loader() throws MissingResourceException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE);
        try {
            gameData = resourceBundle.getString(GAME_DATA_PROPERTY);
        } catch (NullPointerException npe) {
            throw new MissingResourceException("", "", GAME_DATA_PROPERTY);
        }
    }

    public void parse() {
        xmlParser.parseGameData();
    }

    // @TODO only for test!
    public SortedMap<Integer, List<BaseObject>> getBaseObjectMap() {
        return xmlParser.getBaseObjectMap();
    }

    // @TODO only for test!
    public GameWindow getGameWindow() {
        return xmlParser.getGameWindow();
    }

    // @TODO Create xsd and Verify it
    private class XMLParser {
        private static final String ROOT_NODE = "game";
        private static final String SPLASH_NODE = "splash";
        private static final String INTRO_NODE = "intro";
        private static final String MAIN_MENU_NODE = "mainmenu";
        private static final String OUTRO_NODE = "outro";
        private static final String LEVEL_NODE = "level";
        private static final String LEVEL_MENU_NODE = "levelmenu";
        private static final String SCENE_NODE = "scene";
        private static final String OSD_NODE = "osd";
        private static final String OBJECTS_NODE = "objects";
        private static final String GAME_OBJECT_NODE = "gameobject";
        private static final String COORDINATE_NODE = "coordinate";
        private static final String X_NODE = "x";
        private static final String Y_NODE = "y";
        private static final String PHYSICS_NODE = "physics";
        private static final String VELOCITY_NODE = "velocity";
        private static final String ACCELERATION_NODE = "acceleration";
        private static final String GRAVITY_NODE = "gravity";
        private static final String SPEED_NODE = "speed";
        private static final String TYPE_NODE = "type";
        private static final String FORCE_TRANSMITTANCE_NODE = "forcetransmittance";
        private static final String MOVEMENT_NODE = "movement";
        private static final String KEY_MAP_NODE = "keymap";
        private static final String KEY_NODE = "key";
        private static final String SPRITE_SHEET_NODE = "spritesheet";
        private static final String TILE_NODE = "tile";
        private static final String WIDTH_NODE = "width";
        private static final String HEIGHT_NODE = "height";
        private static final String FILE_NODE = "file";
        private static final String ANIMATION_NODE = "animation";
        private static final String START_NODE = "start";
        private static final String ROW_NODE = "row";
        private static final String COL_NODE = "col";
        private static final String END_NODE = "end";
        private static final String ACUSTICS_OBJECT_NODE = "acusticsobject";
        private static final String SOUND_NODE = "sound";
        private static final String PLAY_MODE_NODE = "sound";
        private static final String TRIGGER_NODE = "trigger";
        private static final String RADIUS_NODE = "radius";
        private static final String TILE_MAP_NODE = "tilemap";

        // @TODO test only
        private GameWindow gameWindow;
        public XMLParser() {
            gameWindow = new GameWindow();
        }


        // dummy for now
        public void parseGameData() {
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(new File(gameData));
                doc.getDocumentElement().normalize();

                NodeList nodeList = doc.getElementsByTagName(ROOT_NODE);
                ArrayList<BaseObject> objects = new ArrayList<>();

                for (int i = 0; i < nodeList.getLength(); i++) {
                    System.out.println("nodelist: " + nodeList.getLength());

                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        System.out.println(getTagValue(SPEED_NODE, element));
//                        System.out.println(getTagValue("rule", element));
//                        System.out.println(getTagValue("message", element));
                    }
                }
            } catch (Exception e) {
//                System.out.println(e);
            }
        }

        // @TODO only test!
        public GameWindow getGameWindow() {
            return gameWindow;
        }

        /**
         * Adds object to object list for its Z-level, object list is within map with Z-level as key.
         * @param baseObjectMap
         * @param baseObject
         */
        private void addBaseObjectMap(SortedMap<Integer, List<BaseObject>> baseObjectMap, BaseObject baseObject) {
            List<BaseObject> baseObjects = baseObjectMap.get(baseObject.getZOrder());

            if (baseObjects == null) {
                baseObjects = new ArrayList<>();
            }
            baseObjects.add(baseObject);
            baseObjectMap.put(baseObject.getZOrder(), baseObjects);
        }


        // @TODO only test!
        public SortedMap<Integer, List<BaseObject>> getBaseObjectMap() {
            InputController inputController = new InputController();
            gameWindow.setInputController(inputController);

            SortedMap<Integer, List<BaseObject>> baseObjectMap = new TreeMap<>();

//            SortedSet<BaseObject> baseObjects = new TreeSet<>();



            List<Component> playerComponents = new ArrayList<>();
            playerComponents.add(new AnimationController());
            playerComponents.add(new AudioController());
            playerComponents.add(new CollisionController());
            playerComponents.add(inputController);
            playerComponents.add(new PhysicsController());
            Player player = new Player(playerComponents);
            player.setName("player");
            addBaseObjectMap(baseObjectMap, player);

            List<Component> flamingoComponents = new ArrayList<>();
            flamingoComponents.add(new AnimationController());
            flamingoComponents.add(new CollisionController());
            flamingoComponents.add(inputController);
            Flamingo flamingo = new Flamingo(flamingoComponents);
            flamingo.setName("flamingo");
            addBaseObjectMap(baseObjectMap, flamingo);

            List<Component> boulderComponents = new ArrayList<>();
            boulderComponents.add(new AnimationController());
            boulderComponents.add(new CollisionController());
            Boulder boulder = new Boulder(boulderComponents);
            boulder.setName("boulder");
            addBaseObjectMap(baseObjectMap, boulder);
            
            List<Component> backgroundComponents = new ArrayList<>();
            backgroundComponents.add(new AnimationController());
            backgroundComponents.add(new CollisionController());
            SpaceBackground spaceBackground = new SpaceBackground(backgroundComponents);
            spaceBackground.setName("background");
            addBaseObjectMap(baseObjectMap, spaceBackground);

            List<Component> ballComponents = new ArrayList<>();
            ballComponents.add(new AnimationController());
            GreenBall greenBall = new GreenBall(ballComponents);
            greenBall.setName("greenball");
            addBaseObjectMap(baseObjectMap, greenBall);
            
            
            return baseObjectMap;
        }

        private String getTagValue(String tag, Element element) {
            NodeList nlList = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node nValue = nlList.item(0);
            return nValue.getNodeValue();
        }

        // @TODO Not implemented
        private String getAttributeValue(String tag, String attribute, Element element) {
            return null;
        }

    }


}

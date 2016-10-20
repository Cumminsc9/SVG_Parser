import creation.BuildClass;
import creation.ClassWriter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import objects.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import parsers.ParseConstructor;
import parsers.ParseMethod;
import parsers.ParseVariable;

import java.io.File;
import java.util.*;


/**
 * Created by c015406c on 18/10/2016.
 */

public class Main extends Application
{
    static Stage stage;
    static StackPane pane;
    static TextField inputField;
    static GridPane grid;
    static  Label startLabel;

    private static Map<String, String[]> elementMap = new HashMap<>();
    private static List<Relation> relations = new ArrayList<>();
//
//    public static void main(String[] args) {
//        final Matcher matcher = Pattern.compile( "\\((.*?)\\)" ).matcher( "+ getName()");
//        System.out.println(matcher.find());
//    }

    public static void main( String[] args ) throws Exception {
        //new Main();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Sets
        pane = new StackPane();
        stage = primaryStage;
        inputField = new TextField();

        //setup grid
        grid = new GridPane();
        grid.setAlignment((Pos.CENTER));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));

        Scene scene = new Scene(grid, 340, 200);

        //create controls
        Text title = new Text("Welcome to SVG to Java");
        Button startBtn = new Button();
        startBtn.setText("Start Conversion");


        //set action on button click
        startBtn.setOnAction(event -> {
            try {
                beginConversion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        startLabel = new Label("Begin by entering the path of the .svg file");

        //add controls to grid
        grid.add(title, 0, 0, 2, 1);
        grid.add(inputField, 0, 2);
        grid.add(startBtn, 1, 2);
        grid.add(startLabel, 0, 1, 2, 1);

        //for debugging
        //grid.setGridLinesVisible(true);

        primaryStage.setTitle("SVG to Java");
        //pane.getChildren().add(startBtn);

        //create window and show
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void beginConversion() throws Exception{
        //Create starting label
        if(inputField.getText().isEmpty()){
            startLabel.setText("Please enter path...");
            pane.requestLayout();
        }else {
            startLabel.setText("Starting");
            pane.requestLayout();


            final File file = new File(inputField.getText());//"src/main/resources/DiagramToCodeSVG.svg" );
            try {
                final Document doc = Jsoup.parse(file, "UTF-8", "http://example.com/");
                //Elements textTag = doc.getElementsByTag( "text" );

                for (Element element : doc.getAllElements()) {
                    Elements locationElement = element.select("[transform*=translate]");
                    if (!locationElement.isEmpty()) {
                        String locationAttribute = locationElement.select("g").attr("transform");
                        Elements textElements = locationElement.select("text");

                        Elements titleElements = locationElement.select("title");
                        String enumTitle = CheckInput.checkTitle(titleElements.text());
                        if (enumTitle != null) {
                            String theText = textElements.get(0).text();
                            String[] stringArray = {theText, enumTitle};
                            elementMap.put(locationAttribute, stringArray);
                        }
                    }
                }

                for (Map.Entry<String, String[]> m : elementMap.entrySet()) {
                    String location = m.getKey().replaceAll("[^\\d.]", " ").trim().replace(" ", ",").split(",")[0];
                    String value = m.getValue()[0];
                    String type = m.getValue()[1];

                    relations.add(new Relation(location, value, type));
                }

                List<ClazzToBuild> clazzToBuilds = new ArrayList<>();

                for (ArrayList<ClassMember> classMembers : SortClasses.arrangeMethodAndVariables(relations)) {
                    for (ClassMember tempObject : classMembers) {
                        CheckInput.checkMember(tempObject);
                    }

                    clazzToBuilds.add(new ClazzToBuild(classMembers.get(0).getClassName(), ParseVariable.parseVariable(classMembers), ParseMethod.parseMethod(classMembers), ParseConstructor.parseConstructor(classMembers)));
                }

                // Always goes after the above loop.
                //OutputClasses.outputClasses( clazzToBuilds );

                for (ClazzToBuild clazzToBuild : clazzToBuilds) {
                    //            BuildClass.buildClass(clazzToBuilds.get(2));
                    //            break;
                    String clazz = BuildClass.buildClass(clazzToBuild);
                    ClassWriter.classWriter(clazz, clazzToBuild.getClassName());
                    //break;
                }
                startLabel.setText("Complete");
                pane.requestLayout();
            }catch(Exception ex){
                startLabel.setText("Invalid file path");
                pane.requestLayout();
                System.out.println(ex);
            }
        }
    }
}

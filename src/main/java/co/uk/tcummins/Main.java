package co.uk.tcummins;

import java.io.File;
import java.util.*;

import co.uk.tcummins.enums.Title;
import co.uk.tcummins.helpers.OutputClasses;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import co.uk.tcummins.creation.BuildClass;
import co.uk.tcummins.creation.ClassWriter;
import co.uk.tcummins.objects.ClassMember;
import co.uk.tcummins.objects.ClazzToBuild;
import co.uk.tcummins.objects.Relation;
import co.uk.tcummins.parsers.ParseConstructor;
import co.uk.tcummins.parsers.ParseMethod;
import co.uk.tcummins.parsers.ParseVariable;

import static co.uk.tcummins.CheckInput.checkMember;

/**
 * Created by c015406c on 18/10/2016. Last Edited 21/10/2016 by Giovanni
 */
public class Main extends Application
{
    private static final Logger logger = Logger.getLogger( Main.class.getName() );

    private static Stage stage;
    private static StackPane pane;
    private static String inputPath = "";
    private static Label startLabel;

    private static Map<String, String[]> elementMap = new HashMap<>();
    private static List<Relation> relations = new ArrayList<>();
    private static List<ClazzToBuild> clazzToBuilds = new ArrayList<>();
    private static int cntIterations[];
    private static int i = 0;

    public static void main( String[] args ) throws Exception
    {
        logger.info( "Application started... Loading GUI" );
        launch( args );
    }

    @Override
    public void start( Stage primaryStage ) throws Exception
    {
        //Sets
        pane = new StackPane();
        stage = primaryStage;

        //setup grid
        GridPane grid = new GridPane();
        grid.setAlignment( (Pos.CENTER) );
        grid.setHgap( 10 );
        grid.setVgap( 10 );
        grid.setPadding( new Insets( 25, 25, 25, 25 ) );

        Scene scene = new Scene(grid, 400, 140 );

        stage.setResizable( false );

        //create controls
        Text title = new Text( "UML Diagram .SVG to Java" );
        title.setFont( Font.font( "Verdana", 20 ) );
        title.setFill( Color.BLUE );
        Label madeBy = new Label( "Built by Tom Cummins, Giovanni Lenguito, and Anil Rahman" );
        madeBy.setFont( Font.font( "Verdana", 7 ) );

        Button startBtn = new Button();
        startBtn.setText( "Start Conversion" );


        //set action on button click
        startBtn.setOnAction( event -> {
            try
            {
                beginConversion();
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }
        } );

        startLabel = new Label( "Begin by getting the .svg file of your UML diagram" );


        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter filterChooser = new FileChooser.ExtensionFilter( "SVG files (*.svg)", "*.svg" );
        fileChooser.getExtensionFilters().add( filterChooser );


        Button browseBtn = new Button( "Get the SVG File" );

        browseBtn.setOnAction( e -> {
            File file = fileChooser.showOpenDialog( stage );
            if( file != null )
            {
                openSVG( file );
            }
        } );

        //add controls to grid
        grid.add( title, 0, 0, 2, 1 );
        grid.add( browseBtn, 0, 2 );
        grid.add( startBtn, 1, 2 );
        grid.add( startLabel, 0, 1, 2, 1 );

        grid.add( madeBy, 0, 3, 2, 1 );

        //for debugging
        //grid.setGridLinesVisible(true);

        primaryStage.setTitle( "SVG to Java" );
        //pane.getChildren().add(startBtn);

        //create window and show
        primaryStage.setScene( scene );
        primaryStage.show();
    }


    private void openSVG( File file )
    {
        inputPath = file.getPath();
        startLabel.setText( file.getPath() );
        pane.requestLayout();
    }


    private static void beginConversion() throws Exception
    {
        //Create starting label
        if( inputPath.isEmpty() )
        {
            startLabel.setText( "Please select a .svg file" );
            pane.requestLayout();
        }
        else
        {
            startLabel.setText( "Starting" );
            pane.requestLayout();

            try
            {
                // final File file = new File( "src/main/resources/PersonStudentLesson.svg" );//DEBUG PURPOSE

                final File file = new File( inputPath );

                final Document doc = Jsoup.parse( file, "UTF-8", "http://example.com/" );
                //Elements textTag = doc.getElementsByTag( "text" );

                for( Element element : doc.getAllElements() )
                {
                    Elements locationElement = element.select( "[transform*=translate]" );
                    if( !locationElement.isEmpty() )
                    {
                        String locationAttribute = locationElement.select( "g" ).attr( "transform" );
                        Elements textElements = locationElement.select( "text" );

                        Elements titleElements = locationElement.select( "title" );
                        String enumTitle = CheckInput.checkTitle( titleElements.text() );
                        if( enumTitle != null )
                        {
                            String theText = textElements.get( 0 ).text();
                            if( textElements.size() >= 2 )
                            {
                                theText = textElements.get( 1 ).text();
                            }

                            if( !theText.equals("<<Enumeration>>")  )
                            {
                                if( !theText.equals("<<Interface>>") )
                                {
                                    String[] stringArray = { theText, enumTitle,  };
                                    elementMap.put( locationAttribute, stringArray );
                                }
                            }
                        }
                    }
                }

                int currentIteration = 0;
                cntIterations = new int[elementMap.size()];

                for( Map.Entry<String, String[]> m : elementMap.entrySet() )
                {
                    ++currentIteration;
                    String[] values = checkForClass( currentIteration, m );
                    //if the class matches another class location then for the first class keep the location the same
                    //but for the other class switch the location to the second value (-279.99..)
                    relations.add( new Relation( values[0], values[1], values[2] ) );
                }

                final List<ArrayList<ClassMember>> sorted = SortClasses.arrangeMethodAndVariables( relations );

                for( ArrayList<ClassMember> classMembers : sorted )
                {
                    for( ClassMember tempObject : classMembers )
                    {
                        checkMember( tempObject );
                    }

                    clazzToBuilds.add( new ClazzToBuild(
                            classMembers.get( 0 ).getClassType(),
                            classMembers.get( 0 ).getClassName(),
                            ParseVariable.parseVariable( classMembers ),
                            ParseMethod.parseMethod( classMembers ),
                            ParseConstructor.parseConstructor( classMembers ) ) );
                }

                // Always goes after the above loop.
                OutputClasses.outputClasses( clazzToBuilds );

                for( ClazzToBuild clazzToBuild : clazzToBuilds )
                {
                    String clazz = BuildClass.buildClass( clazzToBuild );
                    ClassWriter.classWriter( clazz, clazzToBuild.getClassName() );
                }

                if( clearCollections() )
                {
                    logger.info( "Collections cleared..." );
                }

                startLabel.setText( "Complete" );
                pane.requestLayout();
            }
            catch( Exception ex )
            {
                startLabel.setText( ex.toString() );
                pane.requestLayout();
                logger.error( "Error: " + ex.toString() );
            }
        }
    }

    private static String[] checkForClass( int currentIteration, Map.Entry<String, String[]> m )
    {
        final String firstClass;
        final String firstName;
        final String firstClassLoc;
        int firstClassFound = 0;

        if( m.getValue()[1].contains( Title.CLAZZ.getType() ) ) //currentType = Class
        {
            firstClassFound = 1;
            if( cntIterations != null )
            {
                cntIterations[i] = currentIteration;
            }

            firstClass = m.getValue()[1];//firstClassType
            firstClassLoc = m.getKey();//firstLoc
            firstName = m.getValue()[0];//firstName

            if( firstClassFound == 1 && firstClass != null && firstClassLoc != null ) //after first iteration
            {
                if( firstClass.contains( m.getValue()[1] ) ) //firstClass = anotherClass
                {
                    if( firstClassLoc.equals( m.getKey() ) )//if the location of the first class matches location of another
                    {
                        //System.out.println( "firstName: " + firstName + "| matchingName: " + m.getValue()[0] );
                        //System.out.println( "firstClass: " + firstClass + "| matchingClass: " + m.getValue()[1] );
                        //System.out.println( "firstLoc :" + firstClassLoc + "| matchingLoc: " + m.getKey() + "\n" );
                    }
                }
            }
        }

        String location = m.getKey().replaceAll( "[^\\d.]", " " ).trim().replace( " ", "," ).split( "," )[0];
        String value = m.getValue()[0];
        String type = m.getValue()[1];

        i++;

        return new String[] { location, value, type };
    }


    private static boolean clearCollections()
    {
        try
        {
            // Main.class
            elementMap.clear();
            relations.clear();
            clazzToBuilds.clear();
            Arrays.fill( cntIterations, 0 );
            i = 0;

            // SortClasses.class
            SortClasses.classMap.clear();
            SortClasses.hashMapList.clear();
        }
        catch( Exception e )
        {
            logger.error( "Unable to clear collections... " + e.toString() );
            return false;
        }

        return true;
    }
}

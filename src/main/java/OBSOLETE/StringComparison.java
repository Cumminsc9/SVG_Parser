//import helpers.StringConstants;
//import objects.Clazz;
//import objects.Attribute;
//import objects.Method;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static helpers.StringConstants.*;
//import static helpers.StringConstants.ST3;
//
//
///**
// * Created by Tom on 18/10/2016.
// */
//public class StringComparison
//{
//
//    private Document document;
//
//    List<Clazz> clazzList = new ArrayList<>();
//    List<Method> methodList = new ArrayList<>();
//    List<Attribute> attributeList = new ArrayList<>();
//    private static Map<Integer, String> locations = new HashMap<>();
//
//
//    public Object foo( Elements text, Map<Integer, String> location, Elements desc, Document document )
//            throws InterruptedException
//    {
//        this.document = document;
//
//        int id = 0;
//
//        for( Element ele : text )
//        {
//            Elements clazz = ele.getElementsByAttributeValue( CLASSTAG, ST3 );
//            if( !clazz.text().isEmpty() )
//            {
//                clazzList.add( new Clazz( id, clazz.text().trim() ) );
//            }
//
//
//            Elements value = ele.getElementsByAttributeValue( CLASSTAG, ST6 );
//
//            if( !value.text().isEmpty() )
//            {
//                if( value.text().contains( "(" ) && value.text().contains( ")" ) )
//                {
//                    methodList.add( new Method( id, value.text().trim() ) );
//                }
//                else
//                {
//                    attributeList.add( new Attribute( id, value.text().trim() ) );
//                }
//            }
//        }
//
//        //get the locations
//        for( Element ele : document.getAllElements() )
//        {
//            //            if(ele.getElementsByTag("desc").text().equalsIgnoreCase("Person"))
//            //            {
//            //                System.out.println(ele.text());
//            //                final String loc = ele.select("g").attr("transform");
//            //                System.out.println(loc);
//            //
//            //            }
//            //            else if (ele.getElementsByTag("desc").text().equalsIgnoreCase("Student"))
//            //            {
//            //                System.out.println(ele.text());
//            //            }
//            //            else if (ele.getElementsByTag("desc").text().equalsIgnoreCase("Lesson"))
//            //            {
//            //                System.out.println(ele.text());
//            //
//            //            }
//            final String loc = ele.select( "g" ).attr( "transform" );
//            if( !loc.isEmpty() )
//            {
//                System.out.println( loc.replaceAll( "[^\\d.]", " " ).trim().replace( " ", "," ).split( "," )[0] );
//                locations.put( 0, loc.replaceAll( "[^\\d.]", " " ).trim().replace( " ", "," ).split( "," )[0] );
//            }
//
//        }
//
//
//        //if in the description tag there is a class name matching to the class list then do stuff
//        //        for(Element e : desc)
//        //        {
//        //            for(int i = 0; i < clazzList.size(); i++) {
//        //                //System.out.println(e.getElementsByTag("desc"));
//        //                if (e.text().equals(clazzList.get(i).getClassName())) {
//        //                    //give location id
//        //
//        //                    for( Element ele : document.getAllElements() )
//        //                    {
//        //                        final String loc = ele.select( "g" ).attr( "transform" );
//        //                        if( !loc.isEmpty() )
//        //                        {
//        //                            System.out.println(loc);
//        //                            locations.put( i,loc.replaceAll( "[^\\d.]", " " ).trim().replace( " ", "," ).split( "," )[0] );
//        //                        }
//        //                    }
//        //
//        //                    System.out.printf("match: " + e.text() + "id: " + i + "\n");
//        //                }
//        //            }
//        //
//        //        }
//
//        //                for (Map.Entry<Integer, String> entry : locations.entrySet())
//        //                {
//        //                    System.out.println(entry.getKey() + "/" + entry.getValue() + "\n");
//        //                }
//
//
//
////        for( Clazz clazz : clazzList )
////        {
////            System.out.println( "Class: " + clazz );
////        }
////        for( Method clazz : methodList )
////        {
////            System.out.println( "Method: " + clazz );
////        }
////        for( Attribute clazz : attributeList )
////        {
////            System.out.println( "Attribute: " + clazz );
////        }
//
//        return new Object();
//    }
//}

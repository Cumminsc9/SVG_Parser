# SVG_Parser

## Quick Summary
The SVG Parser allows you to convert your SVG exported Unified Modeling Language (UML) class diagrams to Java class template files. The UML class diagrarm must be created in Microsoft Visio 2016, and exported as an SVG file without Visio data. To ensure the Java template classes are correctly generated the UML class diagram needs to follow a set of **strict** guidelines.

## Developers
_Name:_ Tom Cummins

_Name:_ Anil Raham

## Required Dependencies & Frameworks
1. [Java 1.8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. [Apache Maven] (https://maven.apache.org/)

## Coding Style Guidelines
The coding style for the artefact should be kept to specific guidelines.
```java
final List<String> stringList = new ArrayList<>();
for( String str : stringList )
{
    if( str.equalsIgnoreCase( "Hello World!" ) )
    {
        System.out.println( str );
    }
    else
    {
        System.out.println( "No luck!" );
    }
}
```

1. `{` and `}` should always be on a new line.
2. After `(` and before `)` a ` ` (space) should exist 
3. Between each method and class declaration there should be two line breaks
4. Any arguments, variables or methods should be declared final if they are not going to be reassigned 
`public boolean isUserLoggedIn( final String userID )`
5. Each tab is equal to **4 spaces**, and everything should be tabbed correctly
6. Any new code should be commented if immediately it does not make logical sense

## Microsoft Visio 2016 SVG Document Guidelines
To ensure that the Java template classes are generated correctly from the exported SVG diagram you will need to ensure the UML class diagram is formatted to the following specifications.

1. Classes cannot be inline with each other
2. Ensure that variables and methods contain all relevant information
  * Including; Access type, return type, name, and any arguments required. (See screenshot below)
3. Ensure spacing is correct on all members and methods
4. Ensure Classes, Interfaces and Enums are created by using the correct identifiers in Visio

#### UML SVG class diagram guideline example
![alt text](https://github.com/Cumminsc9/SVG_Parser/blob/master/src/main/resources/svgDiagrams/PersonStudentLesson.svg "Person Student Lesson UML Class Diagram")


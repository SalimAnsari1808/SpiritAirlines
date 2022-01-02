package com.spirit.runner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CreateXML {
    //Step 1 : Enter suite name (BAT, SMOKE , REGRESSION)
    static String suite = "testcasesGoldFinger";
    //Step 2 : Enter directory of the test cases
    static String directory = "C:\\Users\\limsunnyrasok\\GoldFinger\\src\\test\\java\\com\\spirit\\testcasesGoldFinger";
    //Step 3: Enter directory of where you want the output file to be stored
    static String outputDirectory = "C:\\Users\\limsunnyrasok\\GoldFinger\\src\\test\\java\\com\\spirit\\runner";
    public static void main(String[] args)
    {
        File[] listOfFiles = new File(directory).listFiles();
        System.out.println("creating files in: " + outputDirectory );
        //Condition to change the name of regression critical
        if(suite.toLowerCase().contains("GOLDFINGER"))
        {
            suite = "GoldFinger";
        }
        createFies(listOfFiles);
    }
    //////////////////Methods////////////////////////////////
    public static void createFies(File[] listOfFiles)
    {
        int arrayUsed = 0 ;
        //get number of files needed
        for (int i = 0; i <= listOfFiles.length; i++)
        {
            if(i % 200 == 0)
            {
                arrayUsed++;
            }
        }
        //loop through number of files needed and create the file
        for(int i = 1 ; i <= arrayUsed ; i++)
        {
            //create files in this directory
            File file = new File(outputDirectory + "//" + suite + "suite" + i + ".xml");
            //Create the file
            try {
                if (file.createNewFile())
                {
                    System.out.println("File is created!");
                } else {
                    throw new FileAlreadyExistsException("File (" + file + ") already exists.\n"
                            + "Delete these files and try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addTestCases(arrayUsed , listOfFiles);
    }
    //add test cases to each file
    public static void addTestCases(int iterator , File[] listOfFiles)
    {
        int xmlNum = 1;//keeps count of the xml page
        boolean headerFlag = true;//will keep track whether the header of the XML is needed
        int testcasecounter = 1; //test counter will be between 1 and 0, this will assign browser type
        String xmlContent = "";   // this string will be used to concatenate the XML content
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (headerFlag)//if header flag is true, create a new Header
            {
                xmlContent = null;//reset xmlContent to empty string
                xmlContent = (
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                                "  <!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\r\n" +
                                "  <suite name=\"" + suite + xmlNum + "\" parallel=\"tests\" thread-count=\"17\">\r\n" +
                                "  <listeners>\r\n" +
                                "      <listener class-name=\"com.spirit.testNG.AnnotationTransformer\" />\r\n" +
                                "  </listeners>\r\n");
                headerFlag = false;
            }
            if (listOfFiles[i].isFile())//if File[i] is a file add the test tag to the XML
            {
                xmlContent = xmlContent + (
                        "  <test name=\"" + suite + "Test" + (i + 1) + "\"> \r\n " +
                                "      <parameter name=\"platform\" value=\"" + getBrowserType(testcasecounter) + "\"></parameter> \r\n" +
                                "      <classes>\r\n"+
                                "          <class name=\"com.spirit." + suite + "."+ listOfFiles[i].getName().replace(".java", "") + "\" />\r\n" +
                                "      </classes>\r\n" +
                                "  </test>\r\n");
            }
            // if 50th test case create close of suite tag , set headerFlag to true, and increment xml number
            if (((i+1) % 50) == 0 || i == listOfFiles.length - 1)
            {
                xmlContent = xmlContent + (
                        "\r\n</suite> <!-- Suite -->\n\n");
                writeToFile(new File(outputDirectory + "//" + suite + "suite" + xmlNum + ".xml") , xmlContent);
                testcasecounter = 0; //reset test case counter to 0
                headerFlag = true; //reset header flag to true so it prints on next iteration
                xmlNum++; //increase to the next XML number
            }
            testcasecounter++;
        }
    }
    public static String getBrowserType(int testCaseNum)
    {
//        //set count for browser by test case number
//        Set<Integer> Safari             = new HashSet<Integer>(Arrays.asList(2, 7, 12 , 16 , 19 , 21 , 23 , 25 , 27 , 29 , 31 , 33 , 35 , 37 , 39 , 41));
//        Set<Integer> InternetExplorer    = new HashSet<Integer>(Arrays.asList(3, 8, 13 , 17));
        Set<Integer>  FireFox              = new HashSet<Integer>(Arrays.asList(2, 7, 12 , 16 , 19 , 21 , 23 , 25 , 27 , 29 , 31 , 33 , 35 , 37 , 39 , 41));
//        Set<Integer> FireFox            = new HashSet<Integer>(Arrays.asList(5, 10, 15));
//
//        if (suite.toUpperCase().equals("BAT"))
//        {
//            Safari             = new HashSet<Integer>(Arrays.asList(2, 7, 12 , 16 , 19 , 21 , 23 , 25 , 27 , 29 , 31));
//            InternetExplorer   = new HashSet<Integer>(Arrays.asList(3, 8, 13));
//            Edge               = new HashSet<Integer>(Arrays.asList(4, 9));
//            FireFox            = new HashSet<Integer>(Arrays.asList(5, 10));
//        }
//        if(Safari.contains(testCaseNum) )
//        {
//            return "Safari";
//        }
//        else if(InternetExplorer.contains(testCaseNum) )
//        {
//            return "InternetExplorer";
//        }
//        else if(Edge.contains(testCaseNum))
//        {
//            return "Edge";
//        }
        if(FireFox.contains(testCaseNum) )
        {
            return "FireFox";
        }
        else
        {
            return "Chrome" ;}
    }
    //Writes to XML File using Writer class
    public static void writeToFile(File file , String xmlContext)
    {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(xmlContext);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
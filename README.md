# FBLA_Advisor_Contact_Info

School Name: Northview High School

Name: Nikita Jha

Application Username: NORTHVIEW
Application Passwork: PASSWORD

Language: Java and JavaFX
Dependencies: Java 8, JavaFX 12, and MSAccess (as Database), Excel (for CSV)

Project Build Instructions: 

1). Download project from github URL (green CLONE/DOWNLOAD button on top right side of page) 

2). Project is set up for IntelliJ IDE (.idea included). Open project in IntelliJ or Eclpise 

3). Ensure dependecies from libraries are fully resolved. Open Module Settings and import the libraries to run JavaFX projects. 

4). The Application uses MSAccess DB. https://sourceforge.net/projects/ucanaccess/files/. You have to download UCanAccess distribution and add the following JAR files to the classpath (In Intellij add these libraries as external dependencies):
    ucanaccess-4.0.4.jar
    hsqldb-2.3.1.jar
    jackcess-2.1.11.jar
    commons-lang-2.6.jar
    commons-logging-1.1.3.jar
    The MSAaccess DB file (FBLAAdvisorContact1.accdb) is included in the project under Database. Go to DBConnection.java and correct the file name where ths file is copied.)
    
5). Compile and build the project. Ensure no error remains.

6). Look for Main.java file and run (In Intellij, right click and select Run Main.java.

5). A JavaFX standalone application Login page will open. Follow Application Instructions from here.


Application Instruction: 

1). Type username and password to enter program.

2). The new screen will have user entry fields and a grid where you can view all the advisors. Most of the information are intutive.
  The grid has a column to show Primary Advisor and archived information. The default search will prevent any archived information in 
  the grid.
  
3). You can perform the following operations:

  a). Add Advisor - When adding advisor you have an option to select primary advisor checkbox. The application will only store one
primary advisor. If you already have a primary advisor from previous entry and still check the primary advisor checkbox. The new entry will become the Primary advisor for this chaptor.
    
  b). Search Advisor - You can search an advisor by entry Advisor ID or Advisor name or both. 
    
  c). Save Advisor - If you need to edit/udpate an existing entry, first search or locate it in the grid. Select the entry from the grid.
Make necessary changes and click on the save button. The Save will create a new entry and the previous information will be saved as 
archived.
    
  d). Delete Advisor - First search and select the Advisor in the Grid. Press Delete to delete the advisor. The delete will archive the     entry.
    
  e). Export CSV - Any information that is visible in the Grid can also be exported as a csv file. YOu can limit/expand information in 
the grid before you export.
    
  f). To show archive information, check the 'Show Archive' checkbox and press search button. The Grid will show archived information      for the selected search criteria.
    
4). Once exported as CSV file, you can now view the exported information in an excel format for future reference. 


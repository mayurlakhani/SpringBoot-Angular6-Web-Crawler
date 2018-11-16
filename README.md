Restaurant Web Project  startup  requirements: 
 Backend: Spring Boot 2.0.3 Step 1:  Java Project needs JDK 8 and above version. Install java runtime environment in OS.  Make sure your path as JAVA HOME is set in environment path.  Step 2: Install Eclipse IDE to run the Java project. If you have another tool to run Java project then make sure everything are configured properly for port numbers.  Step 3: In Eclipse IDE install Spring Tool Suit to run Spring Boot application.  Open your Eclipse IDE and click Help -> Install New Software…  Click Help > Eclipse Marketplace… from Eclipse’s main menu. The Eclipse Marketplace dialog appears, type Spring Tool Suite or STS into the Find text field and hit Enter. Eclipse will send query to its server and display results as shown below:   
 
Step 4: Install Maven in your system and set path as Maven Home in environment variable. Step 5: M2 Eclipse installation (Maven) 1. Go to Eclipse Market place and search M2Eclipse 2. On the opened pop-up, click on the Add button to add a new repository 3. Fill the form with the information below and press Ok Name:M2Eclipse Location : http://download.eclipse.org/technology/m2e/releases 

 Check Prerequisites : 
 
Before attempting to create a Maven project in Eclipse, ensure that Eclipse is configured with the all the above requirements. 
 
    Import the project: 
 
1.  From the File menu, select Import. The Select screen opens.
2. Expand Maven and select Existing Maven Projects. 
3. Go to the extracted file path to import the project. 4. Install tomcat apache server into system and configure the local host server. 5. Now for running spring boot application right click on project folder and run as a Spring Boot application with Spring Tool Suit. 
 
Note: If you use the Spring Boot Maven or Gradle plugins to create an executable jar, you can run your application using java –jar using Maven Plugin 
 
The Spring Boot Maven plugin includes a run goal that can be used to quickly compile and run your application. Applications run in an exploded form, as they do in your IDE. The following example shows a typical Maven command to run a Spring Boot application:  mvn spring-boot:run 

Front-End :  Angular 6.1.0 

 Set up the Development Environment 
Step 1:  Install Node.js® and npm if they are not already on your machine. Verify that you are running at least Node.js version 8.x or greater and npm version 5.x or greater by running node -v and npm -v in a terminal/console window. Older versions produce errors, but newer versions are fine. 

Step 2: Installation Angular CLI         
npm install -g @angular/cli  at Root Folder         
Go to cd RootPath        
Enter Command ng serve for running  Angular application  Navigate to http://localhost:4200/. 
The app will automatically reload if  you change any of the source files.

Step 3:  for DataTable.net  install dependencies using NPM. 
npm install jquery --save 
npm install datatables.net --save 
npm install datatables.net-dt --save 
npm install angular-datatables --save npm install @types/jquery --save-dev
npm install @types/datatables.net --save-dev 

Usage 

ng help for more information. 


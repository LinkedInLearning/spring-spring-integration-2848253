# Spring: Spring Integration
This is the repository for the LinkedIn Learning course Spring: Spring Integration. The full course is available from [LinkedIn Learning][lil-course-url].

![Spring: Spring Integration][lil-thumbnail-url] 
In this course, instructor Kathy Flint shows how Spring Integration fits into the overall Spring framework. Kathy covers realistic use cases that warrant the use of Spring Integration, such as APIs, reactive websites, and database-integrated systems. Kathy demonstrates the full capabilities of Spring Integration by building a demonstration application; she starts with an empty Spring Boot application and adds Spring Integration components in increasing complexity, ending with a substantive demonstration application. She covers key features like message channels, message transformation, routing, and aggregation. Kathy finishes the course by identifying practical challenges and choices that an architect or engineer may encounter during the design and implementation of a Spring Integration system.

## Instructions
This repository has branches for each of the code lesson videos in the course. You can use the branch pop up menu in github to switch to a specific branch and take a look at the course at that stage, or you can add `/tree/BRANCH_NAME` to the URL to go to the branch you want to access.

## Branches
The branches are structured to correspond to the videos in the course. The naming convention is `CHAPTER#_MOVIE#`. As an example, the branch named `02_03` corresponds to the second chapter and the third video in that chapter.   

Some branches will have a beginning and end state. These are marked with the letters `b` for "beginning" and `e` for "end". The `b` branch contains the code as it is at the beginning of the movie. The `e` branch contains the code as it is at the end of the movie.  

The `main` branch holds the final state of the code when the course is complete.

When switching from one exercise files branch to the next after making changes to the files, you may get a message like this:

    error: Your local changes to the following files would be overwritten by checkout:        [files]
    Please commit your changes or stash them before you switch branches.
    Aborting

To resolve this issue:
	
    Add changes to git using this command: git add .
	Commit changes using this command: git commit -m "some message"

## Installing
1. To use these exercise files, you must have the following installed:
	- Java 8 or higher
	- Maven
2. Clone this repository into your local machine using the terminal (Mac), CMD (Windows), or a GUI tool like SourceTree.

## Run and Edit

### Intellij IDEA

1. From IDEA Welcome screen, select **Open or Import**  
2. Choose the root directory of your newly cloned repository: `spring-spring-integration-28748253`
3. Within IntelliJ, make sure your Project SDK is set to Java 1.8 or higher. (File > Project Structure)
3. From the Project View, context-click on the file `dashboard/pom.xml`
4. Select **+ Add as Maven project**. This will cause project dependencies to download from the internet. Minimize the resulting Maven view pane if you wish. 
5. Context-click on the file `dashboard/src/main/java/com.lil.springintegration.DashboardApplication.java`
6. Select **Run**
7. Open the application in your browser at `http://localhost:9090`

### Run from Command Line

1. In your terminal, navigate to directory `spring-spring-integration-28748253/dashboard`  
2. Execute `mvn clean package`  
3. Execute `mvn spring-boot:run`
4. Open the app in your browser at `http://localhost:9090`


### Instructor

**Kathy D. Flint**

_Software Engineer and Application Architect_

Check out my other courses on [LinkedIn Learning](https://www.linkedin.com/learning/instructors/kathy-flint?u=104).

[lil-course-url]: https://www.linkedin.com/learning/spring-spring-integration
[lil-thumbnail-url]: https://cdn.lynda.com/course/2848253/2848253-1611257542249-16x9.jpg
    

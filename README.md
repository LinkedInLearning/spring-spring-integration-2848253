# COURSENAME
This is the repository for the LinkedIn Learning course `[COURSENAME]`. The full course is available from [LinkedIn Learning](https://linkedin.com/learning/).

[![COURSENAME](COURSEIMAGE)](LICOURSEURL)

[COURSEDESCRIPTION]

## Instructions
This repository has branches for each of the videos in the course. You can use the branch pop up menu in github to switch to a specific branch and take a look at the course at that stage, or you can add `/tree/BRANCH_NAME` to the URL to go to the branch you want to access.

## Branches
The branches are structured to correspond to the videos in the course. The naming convention is `CHAPTER#_MOVIE#`. As an example, the branch named `02_03` corresponds to the second chapter and the third video in that chapter.   

Some branches will have a beginning and an end state. These are marked with the letters `b` for "beginning" and `e` for "end". The `b` branch contains the code as it is at the beginning of the movie. The `e` branch contains the code as it is at the end of the movie.  

The `main` branch holds the final state of the code when in the course.

## Installing
1. To use these exercise files, you must have the following installed:
	- Java 8 or higher
2. Clone this repository into your local machine using the terminal (Mac), CMD (Windows), or a GUI tool like SourceTree.

## Run and Edit

### Intellij IDEA

1. From IDEA Welcome screen, select **Open or Import**  
2. Select the root directory of your newly cloned repository: **spring-spring-integration-28748253**
3. Context-click on the file **dashboard/pom.xml**.
4. Select **+ Add as Maven project**. This will cause project dependencies to download from the internet. Minimize the resulting Maven view pane if you wish. 
5. Context-click **dashboard/src/main/java/com.kathyflint.lil.dashoard.DashboardApplication.java**
6. Select **Run**
7. Open the app in your browser at `http://localhost:9090`

### Visual Studio Code

1. From VisualStudio Welcome screen, select **Open Folder**
2. Select the root directory of your newly cloned repository: **spring-spring-integration-28748253**
3. Context-click on the file **dashboard/pom.xml**.

### Run from Command Line

1. In your terminal, navigate to `spring-spring-integration-28748253/dashboard`  
2. Execute `mvn clean package`  
3. Execute `mvn spring-boot:run`
4. Open the app in your browser at `http://localhost:9090`

    

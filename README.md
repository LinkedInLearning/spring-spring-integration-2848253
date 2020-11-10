# Spring: Spring Integration
This is the repository for the LinkedIn Learning course Spring: Spring Integration. The full course is available from [LinkedIn Learning](https://linkedin.com/learning/).

[![Spring: Spring Integration](COURSEIMAGE)](LICENSE)

In this course you will learn to deliver stable, resilient, scalable distributed systems using Spring Integration. You will build a demonstration dashboard application that implements the Components of a Spring Integration project: Messages, Channels, and Endpoints.

## Instructions
This repository has branches for each of the code lesson videos in the course. You can use the branch pop up menu in github to switch to a specific branch and take a look at the course at that stage, or you can add `/tree/BRANCH_NAME` to the URL to go to the branch you want to access.

## Branches
The branches are structured to correspond to the videos in the course. The naming convention is `CHAPTER#_MOVIE#`. As an example, the branch named `02_03` corresponds to the second chapter and the third video in that chapter.   

Some branches will have a beginning and end state. These are marked with the letters `b` for "beginning" and `e` for "end". The `b` branch contains the code as it is at the beginning of the movie. The `e` branch contains the code as it is at the end of the movie.  

The `main` branch holds the final state of the code when the course is complete.

## Installing
1. To use these exercise files, you must have the following installed:
	- Java 8 or higher
	- Maven
2. Clone this repository into your local machine using the terminal (Mac), CMD (Windows), or a GUI tool like SourceTree.

## Run and Edit

### Intellij IDEA

1. From IDEA Welcome screen, select **Open or Import**  
2. Choose the root directory of your newly cloned repository: `spring-spring-integration-28748253`
3. From the Project View, context-click on the file `dashboard/pom.xml`
4. Select **+ Add as Maven project**. This will cause project dependencies to download from the internet. Minimize the resulting Maven view pane if you wish. 
5. Context-click on the file `dashboard/src/main/java/com.lil.springintegration.DashboardApplication.java`
6. Select **Run**
7. Open the app in your browser at `http://localhost:9090`

### Run from Command Line

1. In your terminal, navigate to directory `spring-spring-integration-28748253/dashboard`  
2. Execute `mvn clean package`  
3. Execute `mvn spring-boot:run`
4. Open the app in your browser at `http://localhost:9090`

    

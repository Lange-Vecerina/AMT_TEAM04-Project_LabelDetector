# AMT_TEAM04-Project_LabelDetector

**Authors : Yanik Lange, Ivan Vecerina**

## **Introduction**


Hello there !

Welcome to the AMT Team 04 Project LabelDetector repository.
The following repository is part of a bigger multi-repository project that we are working on, consisting of 3 repositories :
* [API](https://github.com/Lange-Vecerina/AMT_TEAM04-Project_API)
* [Simple Storage Microservice](https://github.com/Lange-Vecerina/AMT_TEAM04-Project_DataObject) 
* [Label Detection Microservice](https://github.com/Lange-Vecerina/AMT_TEAM04-Project_LabelDetector) (you are here)

## **Repository description**

> This repository is used to store the Simple Storage Microservice of our project.

The Simple Storage Microservice is a microservice that allows us to get labels of an image with Amazon Label Rekognition. It is implemented using the Spring Boot framework.

## **Microservice endpoints**

### **POST {host}/label-detector/analyze**

This endpoint allows us to do a Amazon Rekognition Label Detection request.

The body of the request must contain the data of the data we want to analyze. The data can be sent in the body of the request as a byte array or as a URL to a data object.

The response of the request is a 200 OK response if the data object could be labelized. Otherwise, the response returns an error code and a message explaining the error.

**Request Example**


    POST {host}/label-detector/analyze

    {
        uri: "https://www.example.com/data.jpg"
    }


## **Dependencies**

*The following prerequisite are necessary to run the project on your machine :*

* Java 11 (or higher)
* Maven
* AWS credentials
* Docker

### **Why Maven ?**

> Maven enables us to manage the project's build, reporting and documentation from a central piece of information. Maven is a build automation tool used primarily for Java projects. Maven addresses two aspects of building software: first, it describes how software is built, and second, it describes its dependencies. Maven is used to build and manage projects based on the Project Object Model (POM).

### **Why Spring Boot ?**

> Spring Boot is an open source Java-based framework used to create a Micro Service. It is developed by Pivotal Team and is used to build stand-alone and production ready spring applications. It is used to create a microservice that can be deployed on the cloud platform. It is easy to create a RESTful web service using Spring Boot.

### **Why Docker ?**

> Docker enables us to package an application with all of its dependencies into a standardized unit for software development that includes everything it needs to run: code, runtime, system tools, system libraries and settings. Making our application portable and easy to run on any machine.

### **Why AWS S3 ?**

> Amazon Simple Storage Service (Amazon S3) is an object storage service that offers industry-leading scalability, data availability, security, and performance. This means customers of all sizes and industries can use it to store and protect any amount of data for a range of use cases, such as websites, mobile applications, backup and restore, archive, enterprise applications, IoT devices, and big data analytics.

### **Install Dependencies**

- To install Java 11 on your machine you can follow the following tutorial : https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
- To install Maven on your machine you can follow the following tutorial : https://maven.apache.org/install.html
- To install Docker on your machine you can follow the following tutorial : https://docs.docker.com/get-docker/

## **How to Contribute**

>TODO all below

### **Check the project's backlog**

See the project's backlog on the following link : [Backlog](https://github.com/orgs/Lange-Vecerina/projects/2)

This will allow you to see the current state of the project and the tasks that are currently being worked on. This will also allow you to see the tasks that are available to be worked on.

### **Fork/Clone the project**


### **Adapt personal settings**

To run the application with your own AWS credentials you need to add the following credentials as environnement path of
your machine :

Variable : **AWS_ACCESS_KEY_ID** Value : **<your_aws_access_key>**

Variable : **AWS_SECRET_ACCESS_KEY** Value : **<your_aws_secret_key>**

Example how to set environment variable on windows :
https://docs.oracle.com/en/database/oracle/machine-learning/oml4r/1.5.1/oread/creating-and-modifying-environment-variables-on-windows.html#GUID-DD6F9982-60D5-48F6-8270-A27EC53807D0

To get AWS and S3 dependencies, run the following command :

```mvn install```

To compile the project, use the following command :

```mvn compile```

To generate a package use :

```mvn package```

To run the tests use :

```mvn test```


## **Run the Test Scenarios on your machine**

Now that you have everything set up, you can run the test scenarios on your machine.


### **Run the microservice**

Download the latest docker package in github (you can use docker pull command).

to run a container :

```docker run --name=labe_detector_v2 -p 8081:8081 -e AWS_ACCESS_KEY_ID=<AWS_KEY> -e AWS_SECRET_ACCESS_KEY=<AWS_SECRET> ghcr.io/lange-vecerina/amt_team04-project_labeldetector```

If you don't want to use the package you can build the image yourself with our Dockerfile :

```docker build -t <NOM_DE_IMAGE> --build-arg aws_access_key_id=<AWS_KEY> --build-arg aws_secret_access_key=<AWS_SECRET> --target production .```

If you want only to run the tests in docker :

```docker build -t <NOM_DE_IMAGE> --build-arg aws_access_key_id=<AWS_KEY> --build-arg aws_secret_access_key=<AWS_SECRET> --target test .```

Your microservice should be running and is listening to port 8081.

   [![Build Status](https://travis-ci.org/ziaagikian/Vehicle-Simulator.svg?branch=master)](https://travis-ci.org/ziaagikian/Vehicle-Simulator)

# Vehicle Simulator

This `Java` based desktop commandline app to simulate real time vehicle(s) connected with `IoT cloud`. The app can run  in parallel with [this  repo](https://github.com/ziaagikian/ConnectedVehicles) . 

The  simulator app is developed  in Java. It  can be  run in two modes. i-e 1)- Execute for Specific time based on user input. 2)- Default simulation time is **10 minutes**. Its major functionality is given below. 

1. It is multithreaded application and is used to post random data against each vehicle to backend.
2. User can provide simulation duration, as  command  arguments, in Seconds, Minutes and Hours. Input should follow pattern i-e Number with Suffix[S/s, H/h, M/m] e.g 1H, 30m etc.
3. Default simulation interval is  [10 minutes](src/main/java/com/swedq/challenge/vehicle/simulator/utils/Constants.java).
4. [Config API](http://localhost:8080/api/vehicles/v1/ping) is first called to retrieve number of vehicles. 
5. In case of  failures  the  backend is check for 5 times at 10 second  intervals each time. After that Exception can be thrown.
6. Each vehicle data is randomly generated.
7. Disconnect is  simulated using random float  number of its threshold is 0.1 i-e 10%.
8. Gradle is used as build tool 
9. The project is dockerised  and third party tool i-e [gradle-docker](https://github.com/bmuschko/gradle-docker-plugin) for docker implementation.

<p align="center">State  machine diagram is given below. </p>

![State Machine](architectural_notes/Vehicle&#32;Simlutor.png)

## Unit Tests
Sample unit tests are written  using Junit frameworks. These tests can be exected using `./gradlew test`. These are tests are automated with Travis CI.

## Automation
The project can be build using gradle and can be auatomated with [Travis](https://travis-ci.org/ziaagikian) easily. 
 
Static code analysis is carried out  once commit is made on master repo. 
The multi stage  tarvis script  is developed. The travis job cycle is composed in `travis.yml` file.

Custom Jenkins based job  pipeline script is  also written and is given in [`custom-jenkins.yml`](custom-jenkins.yml) 

## Docker  Support
The appliactions are fully dockerised and common docker attributes are implemented i-e Proper  hierarchy, dependencies and  automation is fully supported. The entire proecss, build steps are written in `docker-compose.yml`

The project is  Gradle based but Docker Image can also be generated by runing the following scripts.

`sh  docker-build.sh`

## Non Docker Build
The project can be build using gradle utility and via this script `sh  gradle-build.sh` 

## Code  Execution
Output jar file can be executed from command line (with/without CMD Args). Sample commandline scripts for Mac/*nix operating systems are.
1. `java -jar VehicleSimulator.jar` (w/o command line args, default simulation time is  10 minutes)
    
    OR
2. `java -jar VehicleSimulator.jar 20m` (Simulation time is 20 minutes)  
  
   OR
3. `java -jar VehicleSimulator.jar 1H` (Simulation time is 1 hour)  etc...

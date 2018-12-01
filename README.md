# JavaRAT

## Description
### What?

`Java Rat` is a remote administration tool in java to allow someone to pull information from a larege group of computers relatively quickly.

### Why?

This program serves as a way for us to increase our fundamental understanding of network programming, and creates a great tool that is relelvant in some real world settings

### How?

This is designed as a Remote Administration Tool (RAT) that calls back to a Command and Control Server (CNC Server). The CNC server will allow a user to send commands/requests to individual clients and get a reply based on the request; whether it be for a file, OS Version, or other information. The RAT will perform actions on the machine it is running on and send data back to the CNC server when finished.

## Getting Started
### Installation/Running
* RAT
    - The RAT client will work on virtually any machine regardless of it's network settings. Just download the file and run it.
    - If you run into any trouble, run it in a terminal to ensure it is starting using "java -jar RAT.jar"
* Server
    - The server must be run in a terminal. If you are on a 192.168.1.x network, download Server.jar and run using "java -jar Server.jar"
    - the server will search the network for clients that are currently running and allow you to interface with them.
    
    - If you are not on a 192.168.1.x network, you will either need to change IP_BASIS in line 17 of the porScanner.java file, and run in your IDE of choice, or wait for release 0.2


## Features/Usage
* RAT
    - The rat runs automatically without requiring input from the user.
* Server
    - There are 4 commands currently supported when working with the Server
        - \<clientName\> pull keyLog - pulls the keylog file from the specified client
        - server list clients - outputs all possible clients to interface with
        
## Demo Video
<iframe width="560" height="315" src="https://www.youtube.com/embed/TkXCiV39xZo" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

## Deliverables

1. CNC Server
2. Client / RAT
3. Documentation 

## Plan

* **Week 1 (Oct 29 - Nov 5):**
- Research current solutions
- Find third party libraries needed
- Create a user-story
- Major/Big-Picture design choices
* **Week 2 (Nov 5 - Nov 12):**
- Smaller design choices
- Start writing UML documentation
- Start writing manual / user-guide
* **Week 3 (Nov 12 - Nov 19)**
- Finalize design / documentation
- Start writing pseudocode
* **Week 4 (Nov 19 - Nov 26)**
- Development
- Adding documentation to github
* **Week 5 (Nov 26 - Nov 29)**
- Finish MVP
- Bug testing / fixing
- Create presentation
- Create virtual environment for presentation
* **Future releases**
- allow larger keylog files
- dynamic network scanning without hardcoding

## Team members

* Andrew Bissel, Team Lead, Networking
* Leon Chen, CNC Server
* Connor Babcock, RAT / Client

# Comments
1. On the plan, need to speed up the process in design and try to start implementing ASAP. It is better to finish designing this week and start implementing next week.
2. Expand the deriverables as discussed offline. It is good to see you have a features list for MVP and you can just bring the list into this page.
3. Think about what you are going to demo in the final presentation and compile the MVP features accordingly. If you have more time, you can always add more features at the end and it will not affect the overall progress of the project, but make sure the core should work first

## Credit
- Credit for the keylogger: https://github.com/kwhat/jnativehook

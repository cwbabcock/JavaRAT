## System command format
Commands  will be fomrated in a certain way to enable easy use.

They will be broken up into sections separated by a space.

* Commands will start by specifyng the machine we will perform them on. (server, client1, etc.)
* then a specific command will be run on the specified machine 
* finally we have specific options based on the command

\<machine\> \<command\> \<options\> ....

### Examples

client1 get file keylog
client1 get IP
client1 test -- tests connection to RAT
client1 delete rat -- RAT on victim deletes itself
server shutdown
client1 send file RAT -- sends RAT to bootstrapper
server startnetworkscan









For leon:

list of methods:
getCommand() take user input as string, turn it into a String[]
parseMachine(String[] args) 
checks first word to see if = server or != server

if != server, send the rest of the command to the specified client.
...
...
...


Server.parseCommand()
check first word of array
if = server
    Server.parseSecondWord()
else
    find client with correct name
    run Client.parseSecondWord()
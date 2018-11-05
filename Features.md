
# Potential Features
* Features required for MVP are bold.
## Client
* **Keylogger**
* File Operations
   * Pull
   * **Push**
   * **Create**
   * Edit
   * Delete (Including client self-deletion)
* Scan Network
    * Check for bootstrap client on machines
    * Upload client to bootstrap
* Persistence
* Self-Replication

## Server
* Client List
    * **Scan network for bootstrap and RAT clients**
        * Look for machines with arbitrary port open, try an arbitrary
                call and response that will indicate the client is running or 
                ready to receive files/commands.
    * **IP Info (Including port)**
    * Status (Connected/not connected)
* Delegation
    * **Send commands to clients**
* Receive and organize data
    * **Pull files from clients**
    * Files received are organized to indicate where it came from
* **Send client to bootstrap**

## Bootstrap
* **Open port on machine**
* **Receive client file over network**
* **Install and run client**
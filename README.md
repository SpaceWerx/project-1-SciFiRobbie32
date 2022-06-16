# Reimbursement System
project-1-SciFiRobbie32 created by GitHub Classroom

## Project Description

Application to allow users to sign in and submit reimbursements while allowing managers to sign in and update user submitted reimbursements.

## Technologies Used

* Javalin
* Java
* HTML
* CSS
* Bootstrap
* JDBC
* AWS RDS
* JavaScript
* PostgreSQL
* Postman

## Features

List of features ready and TODOs for future development
* User Login/Register
* Manager Login/Register
* User Reimbursement creation
* Manager Reimbursement validation

To-do list:
* Finish HTML front end

## Getting Started

> GitHub CLI:
> gh repo clone SpaceWerx/project-1-SciFiRobbie32

> GitBash:
> git clone [SpaceWerx/project-1-SciFiRobbie32](https://github.com/SpaceWerx/project-1-SciFiRobbie32.git)

* Enviornment Setup:

* Step 1: Download and install gitBash.

* Step 2: Install Java and the jdk:
          * Go to [Oracle Java download page](https://www.oracle.com/java/technologies/downloads/#java8).
          * Create a new account filling out needed information.
          * Verify your email.
          * Download jdk-8u251-windows-x64.exe.
          * Review the oracal technology network license agreement and download.
          * Next install the java SE dev kit through the install wizard.

* Step 3: Edit system enviornment variables:
          * Search for the enviornment variables of your computer and click on them.
          * Under the system variables add a new variable named "JAVA_HOME" with the variable value "C:\Program Files\Java\jdk1.8.0_251".
          * Next go to path "C:\Program Files (x86)\Common Files\Oracle\Java\Javapath..." and click the edit button.
          * A new pop up will display, click edit then click the new button adding the following "C:\Program Files\Java\jdk1.8.0_211\bin" then click okay.
          * Open Git Bash and type "Java -version" (It should look something like this)
          * Do not close the environment variable editor
![172038922-24163149-4c8a-4a0e-ad43-9bf10fcdd128](https://user-images.githubusercontent.com/44649589/174106694-a3515034-b174-4851-9d80-f6b7c312a17d.png)

* Step 4: Installing Spring Tool Suite 4:
          * navigate to the [Spring Website](https://spring.io/tools) and download the windows version of STS unless you use another OS.
          * Install STS, once this is done a directory will be created with the STS application, create a desktop shortcut if needed from there.

* Step 5: Installing Maven:
          * Go to the [Maven Download Page](https://maven.apache.org/download.cgi) and download apache-maven-3.6.3-bin.zip.
          * open and extract all files from the downloaded zip folder giving the extraction location the destination of "C:\Maven" and select the box to show extracted files when completed.
          * Copy the file link after the extraction process
          * Go back into the system envionment variables click new and add the variable name of "M2_HOME" using the link you copied as the variable value.
          * Next make another new system variable name "M2" with the variable "%M2_HOME%\bin" and click ok.
![172039472-c31420cb-8847-4738-9e25-19890184f56f](https://user-images.githubusercontent.com/44649589/174113817-87be8c83-a62c-4838-901e-75243de1f2f6.png)
![172039478-705db5c3-1701-4bee-a75e-5f162ef8abc5](https://user-images.githubusercontent.com/44649589/174113846-c299bcf8-1a01-4245-a6bd-3115104c8517.png)
![172039482-c160fde6-5c5c-47ee-a34e-8f62783b0f7a](https://user-images.githubusercontent.com/44649589/174113854-8d4afab6-5cb6-421d-b4d9-bd60970d074a.png)
![172039488-52f3c735-eef8-4ccb-a2f0-de47e69a2be5](https://user-images.githubusercontent.com/44649589/174113875-e280ea76-4db9-47c5-b840-7811c1a8f2da.png)
          * next go to the path and click edit.
          * Click new and add the following "%M2%" then click okay.
![172039513-bfc4c848-0ba9-4706-8dbf-b4907d07c1f1](https://user-images.githubusercontent.com/44649589/174113938-ac20cd52-726a-47b9-8dcf-d4b9704e6e2b.png)
          * Go into CMD or Git Bash and type "mvn --version" and hit enter. You should see this:
![172039540-d8e4f004-1cd2-4c31-9668-b8d960799de1](https://user-images.githubusercontent.com/44649589/174112403-d5097d9d-4803-4ed7-92c4-7cda2a8ac96e.png)

* Step 6: Install PostgreSQL:
          * Go to the [PostgreSQL Site](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) and install version 10.21
          * Install the wizard and run, you will be tasked with a few menus.
          * Hit next until you hit this screen:
![172039730-7b87e4fa-1332-4365-82b6-921950e24ae6](https://user-images.githubusercontent.com/44649589/174113192-64bc199f-ed54-4b84-91fc-76919b18ecf2.png)
          * Select components(PostgreSQL Server, Stack Builder, Command Line Tools) then click next
          * Continue hitting next until the password menu appears.
          * Set up a password and default user; DO NOT FORGET THE PASSWORD.
![172039799-5ec96dae-7b13-4e3d-b6be-f80b28c6487b](https://user-images.githubusercontent.com/44649589/174113559-aee492ce-6866-47be-ba2a-56bf18cac7da.png)
          * After choosing a password that you won't forget because changing it can be a hastle, hit next on the remaining menuse and allow postrgre to install.
          * Once installed you may be asked to lanch the stack builder, you can say "no"
* Step 7: Install DBeaver:
          * Go to the [DBeaver Site](https://dbeaver.io/download/) and install the Community edition for free.
          * You'll find the installers if you scroll down, you should see them under a box that says "Community Edition 21.XX.XX". Install the version that is correct for your system, if on windows you'll most likely install the 64 bit installer.
          * Launch the installer, it should take you through setup of DBeaver. You'll be asked for an instance, language of choice and to review license terms before you install.
          * When Prompted to "Choose User", you may select "For me (...)"
          * When you reach the "Choose Components", select: DBeaver Community, Include Java, and Associated .SQL files
![172039927-d57b428b-7b0f-48b6-9fb2-14457156e59f](https://user-images.githubusercontent.com/44649589/174116562-68527388-45d9-42f2-9e90-31a17767276f.png)

* Step 8: Installing Postman:
          * Go to the [Postman website](https://www.postman.com/downloads/) and download postman for your system. note: default system is windows, clock the macOS or Linux links if you dont use windows.
          * Launch the install wizard

* Step 9: Install VSCode:
          * Go to the [VSCode Official Site](https://code.visualstudio.com/download) and install for your system.
          * Launch the install wizard, and after accepting the license agreement you'll proceed to the "Select Aditional Tasks" menu and select the following.
![172040178-53d135b9-0322-4ff1-98c5-eb2f7f1bfed1](https://user-images.githubusercontent.com/44649589/174117923-40de3cb7-099f-487e-a164-b47d084b7179.png)

## Usage

After installing the project you'll need to create a database conection in DBeaver.
* Click the database button in DBeaver and click "New Database Connection"
* Select PostgreSQL and click next
* you will be promted for a server, fill in the relevant Host and Database names dbp-1.cajgurwleadc.us-east-2.rds.amazonaws.com DatabaseP1
* You will then need to input the Username and Password of the database owner to access the database.(robertP1  Password)
![172084279-f64d535f-e1e9-4acf-a3f4-3dfd3679b42e](https://user-images.githubusercontent.com/44649589/174118994-baf0b196-ad85-4962-9c3a-e14bfa9615f4.png)

once the database conection is done you'll need to create a schema.
* Hover over the file under the database tab: For me this is DatabaseP1
* Right click and select create shcema
![172095874-3128603e-b435-4f05-9c62-3e31c15ea73f](https://user-images.githubusercontent.com/44649589/174119277-9a4dacdf-5bec-474a-bbcb-b6720d1b988d.png)
* The shcema is title project1

Next you'll create postman requests.
* Launch postman
* Create a new file
* The files should look something like this:
![Screenshot 2022-06-16 115925](https://user-images.githubusercontent.com/44649589/174126687-d8dad349-f0c0-471a-891b-713d81e70c10.png)
Create Reimbursement:
![Screenshot 2022-06-16 120006](https://user-images.githubusercontent.com/44649589/174126907-cdaa8d18-a08a-428d-ad45-d84c6ea08bf2.png)
Update Reimbursement:
![Screenshot 2022-06-16 120321](https://user-images.githubusercontent.com/44649589/174127093-de29db79-b979-4a69-bf46-c70772c5f2a1.png)
Register:
![Screenshot 2022-06-16 120101](https://user-images.githubusercontent.com/44649589/174126957-453dade4-4851-4006-9484-fb252521e97e.png)
Login:
![Screenshot 2022-06-16 120351](https://user-images.githubusercontent.com/44649589/174126984-e7b7e8e6-59b7-4f24-9720-d89b34b7efe6.png)

Get Reimbursements:
http://localhost:3000/getReimbursements

Get Pending Reimbursements:
http://localhost:3000/getPendingReimbursements

Get User:
http://localhost:3000/getUsers


Note: Make sure to shut down the server when restarting.
## License

* license: postgresql
* license: apache-2.0


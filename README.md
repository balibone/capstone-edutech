# :books: EduBox :books: 

## Login Page
![Login Page](https://i.imgur.com/qqMZiaf.png)

## Landing Page
![Welcome Page](https://i.imgur.com/d6EIEyo.png)

# Setup Instructions 
Jump to [application setup](#application-setup)

## Git instructions for beginners
1. Create GitHub account.
2. Fork this repo (top right button) into your account. So now you have an exact copy of the group repo in your personal online repo. 
3. Download your preferred Git desktop client. I recommend [GitKraken](https://www.gitkraken.com/download). 
4. Go back to the "practice-repo" in your **_personal account_** (not the group's one). 
5. Get the clone URL.
6. Clone this repository on GitKraken. Cloning just means that now you are "downloading" your online files into an offline location. Choose a folder in your PC to store these files. 
7. Add the group's online repo as a remote repo. Go to GitKraken and click the '+' sign beside 'Remote'. Select the group's repo and click ok. 
8. **Now you will have 2 remote repos, 1 personal & 1 group. You will use the personal repo to sync your code between offline & online. The group repo should only be used by the group as a storage for the latest working codebase. It exists here on GitKraken so that you can easily see the current state of the group repo compared to your personal repo on the tree diagram. You can only create a pull request from personal repo to group repo through drag and drop in GitKraken (read below).**

### What Now? 
1. This offline location you have selected to download your online files into is called your **_local repo_**. The repo you just cloned from your account on GitHub is your **_remote repo_**. Lastly, the repo which you have forked from in step 2 of "How To Start". is the **_group's remote repo_**. Let's call it **_group repo_** for short. **Group's repo = our source code for the whole project.** 
2. Now, ANY changes to ANY files in your local repo (create, update, delete), will be automatically detected and shown on GitKraken. 

### 3 Basic Processes 
#### 1. I want to push some updates in my code to the project code:
1. Commit changes to local repo. Now, local repo has been updated with the changes.
2. Push local repo to remote repo. Now, your online files on GitHub have been updated with the changes. Can think of it like overwriting your online drive, but this overwriting can be reversed to any point in the project if the code you've submitted online is buggy (see section 3 below).
3. Submit a pull request to the group repo. On the tree, drag the icon of your personal repo to the group repo icon. This is a request for the group to review your code and merge your code with the group's codebase once it is verified to be ok (i.e Ask the group to pull your changes).
4. Once pull request approved by admin, changes from you are now included in the main codebase. 

#### 2. I want to update my code with the latest project code:
1. Rebase your local repo with group repo. (updates local files with group's files)
2. Push changes in local repo to remote repo. (updates online files with local files)

#### 3. I want to reverse some changes (buggy code/mistakes etc.):
1. Find the commit(s) you want to undo. Right click the commit(s) on the GitKraken tree diagram. Click Revert commit.
2. After all intended commit(s) have been reverted, perform the same procedure as pushing updates (section 1 above). Push to online repo & make a pull request to the group repo. 
3. Once admin approves the pull request, the commit(s) will now be undone on the group repo too. 
#### Note: Think of reverting commits as a set of undo operations. Like if you have pushed a buggy file online, reverting this commit will just delete that buggy file. 

## Application Setup
1. Install Netbeans 8.1, Node.js and Extract Glassfish 4.1 from the zipped folder
2. Open Netbeans 8.1 and Open Projects from the zipped folder:
  - EduTech, and “Open All Required Projects”
  - EduTech Remote Interface
  - NOTE: Set Netbeans Compatibility Properties to “Run this program as administrator”.
3. Open GlasshFish Server's "View Domain Admin Console
  - JDBC Connection Pool
    - PoolName: CapstonePool
    - Resource Type: javax.sql.DataSource
    - Database Driver Vendor: JavaDB
    - Database Name: CapstoneDB
    - User: APP
    - Password: app
    - Description: Capstone Connected Pool
    - Connection Attributes: create=true
    - ServerName: localhost
    - PortNumber: 1527
    - Create and Ping
  - JDBC Resource
    - JNDI Name: jdbc/capstone
    - PoolName: CapstonePool
    - Description: capstoneDB
4. Open Cmd and go to “..\EduTechFrontEnd” and run “npm install”, and then run “npm start”
5. Open Cmd and go to “..\EduTechCollabServer” and run “npm install”, and then “node index.js”
6. In NetBeans, “Clean & Build” the EduTech Application, and then “Deploy” it
7. Connect to the jdbc/CapstoneDB
  - Right click the jdbc/CapstoneDB and click on "Execute Command"
  - Insert all SQL statements from the "SQL.txt" file from the zipped folder
  - Click "Run SQL"
8. Run the EduTech Application through <IPAddress>/EduTechWebApp-wa

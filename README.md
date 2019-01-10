 Team10 client project repository.
 
 ##Setup
 
 Firstly you need to create a schema called asgproject in your mysql server.
 
 You then need generate the jar, to do this run (replace the database password with yours)
 
 `./gradlew build -Ppassword=admin`
 
 Then to execute it run (again replace the database password as appropriate)
 
 `java -jar build/libs/asgproject-0.0.1.jar --spring.profiles.active=development --spring.datasource.password=admin`
 
 
 ##Manual tests
 Go to localhost:8080
 You will be redirected to localhost:8080/account/login
 
 You will be greeted with a login screen.
 
 In the input labeled as 'Email Address', enter 'admin@example.com'.
 
 In the input labeled as 'Password', enter 'password'.
 
 Then press the login button.
 
 This will then redirect you to localhost:8080.
 
 On this screen if you are in fullscreen on desktop, you will be presented with the sidebar which will have Dashboard and Admin.
 
 And the main dashboard area will be visible. This will show the number of candidates at each stage in a bar chart.
 
 You will see 4 bars on the left hand side of the graph.
 
 On the sidebar click 'Admin', and then 'Users' in the dropdown.
 
 This will take you to the page localhost:8080/admin/users.
 
 This will display a table of users, with 10 users, with William Smith at the top and Abbey Foster at the bottom.
 
 If you click show disabled, it will show Sally Smith as the only user. Click on her row,
 
 You will be taken to localhost:8080/admin/users/6.
 
 On here you will be able see the details for Sally Smith.
 
 Her email will show as 'sallys@example.com', and the phone number will be '03069990980'.
 
 Her role will show as an 'Instructor', and she will show as activated but not enabled.
 
 Click the 'Enable' button, and then confirm on the modal that shows, and the enabled will change from an X to a tick.

 On the sidebar click 'Admin', and then 'Users' in the dropdown.
 
 Sally Smith will be second down in the table now.
 
 Clicking on the 'Surname' header twice will sort alphabetically descending (a-z).
 So you will see Jodie Bright at the top and Admin User at the bottom.
 
 
 
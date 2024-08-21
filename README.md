

### Communicating with the Admin Server from the User Server 
# code is in the "UserToAdminCommunication" Branch

To enable secure communication between the user server and the admin server, follow the steps outlined below:

1. **Admin Credentials Configuration:**
   - The user server requires specific credentials to login to the admin server. These credentials should be saved in the `application.properties` file of the user server as follows:
     ```
     AdminToUserAuthentication.email=admin@gmail.com
     AdminToUserAuthentication.password=admin123
     ```
   - These credentials will be used to authenticate with the admin server and must match a registered admin account in the admin server's database.

2. **Admin Account Registration:**
   - On the admin server, ensure that an admin account is registered with the following credentials:
     ```
     Email: admin@gmail.com
     Password: admin123
     ```
   - This account should be saved in the admin server's database to allow the user server to successfully log in and interact with the admin server.

3. **Login to Admin Server:**
   - The user server will log in to the admin server using the above credentials.
   - Upon successful login, the admin server will return a JWT token that will be used for further communication.

4. **Using the JWT Token:**
   - The JWT token obtained from the login process must be included in the headers of any subsequent API requests made to the admin server. This token ensures that the requests are authenticated.

5. **Implementation Details:**
   - The `AdminLoginService` class is responsible for managing the login process from the user server to the admin server. It sends a login request to the admin server, receives the JWT token, and processes the response.
   - For practical examples and testing, refer to the `TestAdminLoginService` class in the `UserToAdminCommunication` branch of this repository.

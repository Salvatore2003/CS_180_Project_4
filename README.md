# CS_180_Project_4

Compiling and Running instructions
The code can be run by running the storefront program. The interface should be self-explanatory 
with instructions regarding what needs to be done. User information is stored in the userInfo.txt file
while the store information is stored in various files in the ./src/ folder. Admin and user accounts need to be made
and even stores can be created with relative ease to allow for more operations to be conducted.

This program runs a messaging system that connects buyers and sellers. These buyers and sellers will be able to message each other. The first step that the program takes
is to see if there are any accounts. The first account that is made is the admin account. The admin account main functionality is to shut down the program.
Once an admin is made the user can create a new user. They will be taken through a number of prompts to create their account. Once their account is created
they can sign in to their account. Once signed in to their account they are taken to a user interface. Here they can edit their settings, create a store, message people, or log out. 
If the user chooses to go to settings then they can edit everything within their account. This includes their username, password, email, and if they are a buyer and seller. They also have the ability
to completely delete their account. Deleting their account gets rid of their account information, and cannot be logged into again. Once the 
user edits the settings they will be taken to back to the user interface that allows them to choose to change their settings, messaging, and creating a store.
Once they are done the user will be able to sign out.

Seller and Consumer Core Functionality - Dhruv Wadhwa
If the user is a seller, they can choose to create, edit or delete stores. Editing stores give a seller more options including adding products or editing specific products. Products can be added from files or manually and the 
choice is left to the user. To delete or edit a product in a store, the store name and the product name must be specified.
If the user is a customer, they can search for the store they would like to see more about. After searching for that store, they can view the products, the product descriptions, and the prices of the products in that store.
Class - Marketplace
Runs the interface of seller and consumer. Is run by the storefront class with seperate methods for seller and consumer.
Class - Store
Is the object class for a store
Class Product
It is the object class for a product

User settings
The user settings allows for the user to change their information this includes, their username, password, email, and their role. 
The user can also delete their account in this class. When deleted the account will no longer be able to sign in.
The account will remain in the settings until the user chooses to leave the settings ui. This  was confirmed by using the 
test cases that we made, and testing the class by making accounts and changes the user settings. This class is called from the main method.
It edits the user that is logged in and changes their information. If their information is changed in the settings class then
that changed is  then applied in the main method.

StoreFront
Store front contains the main method of the code. There are many methods to support the main method. These methods
allow for the user to sign in and create an account. If the user signs into an existing account then they can go to messages,view
or edit stores depending on their role, and go to their settings. The main method calls other methods from different classes such as run settings and running
the messages. runSettings is the UserSettings class and runSeller and runBuyer are in the MarketPlace class. The class was tested by using test cases
that Rakshit made, and running it ourselves to make sure we get the expected output.

InvalidNumber
If an invalid number is entered for an output an exception is thrown to this class. This class was tested
using test cases Rakshit made and throwing errors to the class to make sure we got the expected results.

InvalidLogIn
If there is an invalid login then there is an exception thrown to this class. This class was tested by using
Rakshit test cases and throwing errors to the class to make sure we got the expected results.

Messaging Functionality
Users are able to message other users provided they are not of the same type as the other user (Sellers cannot message sellers, etc.) This messaging system implements two boxes, one inbox which contains mail the user has received, and one outbox which contains mail the user has sent. The messaging system allows users to view their inbox, send messages to other users, edit messages they have sent to other users, or delete messages from either their inbox or outbox (This is client side only and does not remove the message from the other user's mailbox.) The messaging UI allows users to select any one of these options, in which they will be prompted to enter information relevant to their choice.

Bryce is in charge of submitting to Voceraum and the project report to brightspace.

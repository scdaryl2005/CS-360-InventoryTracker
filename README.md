# CS-360-InventoryTracker

# Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?
The main requirements of this application was to design an Inventory Tracker that utilized a user log in, SQLite database that utilizes CRUD functionality to update the inventory and finally an SMS permission feature to send the user notifications when an inventory item reached zero.

# What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?
The screens that were needed were a login screen, a screen to register new users, an inventory overview list that housed the recycler view, and finally a screen to add new inventory.  The login screen allows a user to either login or register.  The register screen asks for a new user name, password, and to confirm password to create a new user in the DB.  The inventory overview displayed all inventory items currently in the DB.  It also gives the user the ability to increase, decrease, and delete an item.  The UI was designed to be self explanatory while prompting the user with errors if they didnt enter all required data in certain fields.  The design was successfull because it was clear and concise with the least amount of screens possible to achieve the requirements while adhearing to industry best practices.

# How did you approach the process of coding your app? What techniques or strategies did you use? How could those be applied in the future?
The approach started by understanding the requirements.  From there, I developed the screens using the .xml files and finally went back in and incorporated the code to tie in the functionality of the different screens.  

# How did you test to ensure your code was functional? Why is this process important and what did it reveal?
To ensure that the code was functional, I built and ran the code often.  This would allow me to see if new changes made to the code had unintended affects in other parts of the application.  Once the code was where I wanted it, I would create a new user each time to ensure that I could register a user, login, create an inventory item, and then update it as required.  I would often put the quantity of the item at 1 so that I could quickly decrease the quantity to zero to ensure I got the SMS message telling me the quantity was now zero.

# Considering the full app design and development process, from initial planning to finalization, where did you have to innovate to overcome a challenge?
This was my first time using Android Studio or developing a mobile application.  The biggest part for me was learning the functionality of Android Studio and the basics of how mobile development works.  From there it was refining my java skills to be able to produce a fully functional android application.

# In what specific component from your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?
The SQLite DB functtion was a specific component that was particularly successful as it housed both the items and users of the application.  This serves as the backbone of the application and demonstrates my knoledge, skills, and experience when developing an app.

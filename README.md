## Film Query Project

#### This was initially an intimidating project, working with the different classes listed and mapping the class fields to the database columns, but throught this project, I gained more familiarity with these concepts. 
I also became more familiar with the skeleton of a database accessor object class and learned how one can store a connection in a field so that it can be used for multipul queries in all methods used in that class. It is notable we receive exceptions where the connection goes away, it can be helpful to recreate the connection so that the user can continue when the database becomes available again. One important thing that has been accomplished in this project is when the app is finished, the connection is closed cleanly as Dee had spoken about. 

#### connection pools
I spoke with a friend in the industry about how connection pools can be used for performance and failure recovery and likened this concept to a self-checkout line where as long as there are enough kiosks available, the next person in line (or client) does not have to wait; database connection pools work metaphorically-speaking as a bunch of automatically-replaced kiosks. No database connetion pools were used, in this project, but it was an interesting concept to learn about for potential future use in the industry. 

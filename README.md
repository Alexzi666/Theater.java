**Movie Theater Seating Challenge**

**Language:**  Java

**Application Description:**

This Java Application takes a .txt input file from the command line argument and output a Seating_Assignment_Report.txt file. The application will process movie ticket reservations one by one and assign seats in the theater.

This Java Application consist of 4 .java files. App.java is the main function; FileProcessor handles parsing input file and building output file; Theater.java contains the algorithms and data structures for reservation tickets handling; TheaterTester.java tests Theater.java and makes sure the output meets expectation.

This Application follow the below rules:

1. The Application assigning seats based on first come, first served manner.
2. Customer who come first will be served in the middle rows for ensuring customer satisfaction purpose. 
3. The Application tend to put a group of customer into the same row for better ensuring customer satisfaction, since people within a group tend to sit next to each other. If this is not possible, the application will split the group and try to put people in the middle rows if there are vacancy seats while maintain 3 buffer seats.
4. The Application will try to fill as many customer as possible while maintain a buffer of 3 seats between groups.
5. If the numbers of request seats are not available due to insufficient seats available, the application will inform the customer about reservation does not complete.


**Assumptions:**
1. I assume that people would get better experience sitting in the middle rows watching movies.
2. I assume that people from the same group tend to sit next to each other.
3. I assume that the movie theater will not complete request for reservation of size larger than current available seats. In this case, customer will be informed that insufficient number of available seats
4. I assume that the movie theater will not complete request for reservation of size of 0. In this case, customer will be informed that she/he made an invalid request.
5. I assume that reservation identifier(R###) will always be in sequential order such that: R001, R002, R003...

**Steps for executing the application**
1. Save the src folder into local device
2. Go to command line enter this folder and execute all the .java files by typing javac src/App.java src/Theater.java src/FileProcessor.java src/TheaterTester.java
3. Go into src folder and type "java App input.txt" to run the application
4. Output file is then generated within the src folder, filename: "Seating_Assignment_Report.txt"
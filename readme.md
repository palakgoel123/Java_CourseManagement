## Course Management System in Java
The program allows a admin to sign in, add and edit courses as well he has special rights to see all the information regarding the courses which are full and those which have some seats left. Also the admin can view all students registered and their information. On the other hand the students added to the system can sign in and register or withdraw from courses and view data regarding the courses and available seats left. After program end, all data is serialized into storage for retrieval at run-time.



—Application
	The main class, which contains methods for log-in, menu, and serialization.
–Course
	A course entry class, which contains data fields describing a simple course. Includes an array-list of registered students, with methods for searching and editing the registry.
–CourseList
	An object containing an array-list of courses, which contains methods for searching, viewing, editing, and sorting the course list. A method also allows for an initial list of courses to be read into the array-list from a file.
–User
	A class containing data fields which describe a basic user.
–Admin
	A user class which generates a fixed username and password through the default constructor.
–Student
	A user class which contains additional data-fields. Includes an array-list of registered courses, with methods for searching and editing the registry.
–StudentList
	An object containing an array-list of students, which contains methods for searching, viewing, editing, and sorting the student pool.
–AInterface
	An interface describing the basic functions of an admin (empty, because admin-access to methods is determined in menu w/ reference to methods in other classes, CourseList + StudentList)
–SInterface
	An interface describing the basic functions of a student.
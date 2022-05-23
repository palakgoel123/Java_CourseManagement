## Course Management System in Java
The program allows a admin to sign in, add and edit courses as well he has special rights to see all the information regarding the courses which are full and those which have some seats left. Also the admin can view all students registered and their information. On the other hand the students added to the system can sign in and register or withdraw from courses and view data regarding the courses and available seats left. After program end, all data is serialized into storage for retrieval at run-time.

* **Application** <br />
	The main class, which contains methods for log-in, menu, and serialization.
* **Course** <br />
	A course entry class, which contains data fields describing a simple course. Includes an array-list of registered students, with methods for searching and editing the registry.
* **CourseList** <br />
	An object containing an array-list of courses, which contains methods for searching, viewing, editing, and sorting the course list. A method also allows for an initial list of courses to be read into the array-list from a file.
* **User** <br />
	A class containing data fields which describe a basic user.
* **Admin** <br />
	A user class which generates a fixed username and password through the default constructor.
* **Student** <br />
	A user class which contains additional data-fields. Includes an array-list of registered courses, with methods for searching and editing the registry.
* **StudentList** <br />
	An object containing an array-list of students, which contains methods for searching, viewing, editing, and sorting the student pool.
* **AInterface** <br />
	An interface describing the basic functions of an admin (empty, because admin-access to methods is determined in menu w/ reference to methods in other classes, CourseList + StudentList)
* **SInterface** <br />
	An interface describing the basic functions of a student.
	

![1](https://user-images.githubusercontent.com/77844280/169871340-00f2a1da-cb29-4e1a-96aa-13ca5554eb5c.jpg)
![2](https://user-images.githubusercontent.com/77844280/169871380-8552a265-c69b-4fce-bf17-43113f82007e.jpg)
![3](https://user-images.githubusercontent.com/77844280/169871387-b151a9ac-14eb-4ede-ae3a-7804febd3793.jpg)
![4](https://user-images.githubusercontent.com/77844280/169871408-7dff83cd-71a5-450d-a7f2-74e691b243fd.jpg)
![5](https://user-images.githubusercontent.com/77844280/169871788-afdafc83-729b-4db0-baca-d21be1218ead.jpg)



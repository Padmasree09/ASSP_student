<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Student</title>
    </head>
    <body>
        <form action="update-student" method="post">
            <p>
                Name : <input type="text" name="name" value="${student.name}" >
            </p>
            <p>
                Email : <input type="email" name="email" value="${student.email}">
            </p>
            <p>
                Phone : <input type="text" name="phone" value="${student.phone}">
            </p>
            <p>
                password : <input type="password" name="password" value="${student.password}">
            </p>
            <input type="hidden" name="id" value="${student.id}">
            <input type="submit" value="Edit Student">
        </form>
    </body>
</html>
select student.name as student_name, student.age as student_age, faculty.name as faculty_name
from student
inner join faculty on student.faculty_id = faculty.id ;

select student.name as student_name, student.age as student_age
from student
inner join avatar on student.id = avatar.student_id;
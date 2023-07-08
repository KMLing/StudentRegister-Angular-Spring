import { Router, ActivatedRoute } from '@angular/router';
import { StudentService } from './../student.service';
import { Student } from './../student';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css'],
})
export class StudentListComponent implements OnInit {
  students: Student[];
  id: number;

  constructor(
    private studentService: StudentService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getStudents();

    console.log('router', this.router.url);
  }
  private getStudents() {
    this.studentService.getStudentList().subscribe((data) => {
      this.students = data;
    });
  }

  updateStudent(id: number) {
    this.router.navigate(['update', id]);
  }

  deleteStudent(id: number) {
    this.studentService.deleteStudent(id).subscribe((data) => {
      console.log('The Id to delete -->' + id);
      this.getStudents();
    });

    this.getStudents();
  }

  gotoList() {
    this.router.navigate(['/students']);
  }
}

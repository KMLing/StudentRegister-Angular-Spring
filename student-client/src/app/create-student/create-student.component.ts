import { SuccessAlertComponent } from './../success-alert/success-alert.component';
import { StudentService } from './../student.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from '../student';
@Component({
  selector: 'app-create-student',
  templateUrl: './create-student.component.html',
  styleUrls: ['./create-student.component.css'],
})
export class CreateStudentComponent implements OnInit {
  student: Student = new Student();

  constructor(private studentService: StudentService, private router: Router) {}

  ngOnInit(): void {
    console.log('router', this.router.url);
  }

  save() {
    this.studentService.createStudent(this.student).subscribe(
      (data) => {
        console.log(data);
        this.student = new Student();
        this.gotoList();
      },
      (error) => console.log(error)
    );
  }

  OnSubmit() {
    this.save();
  }

  gotoList() {
    this.router.navigate(['/students']);
  }
}

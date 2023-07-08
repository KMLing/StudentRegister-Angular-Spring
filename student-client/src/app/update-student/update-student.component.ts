import { Router } from '@angular/router';
import { StudentService } from './../student.service';
import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css']
})
export class UpdateStudentComponent implements OnInit {

  student:Student=new Student();
  id:number;
  constructor(private studentService:StudentService,private router:Router,private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.id=this.route.snapshot.params['id'];

    console.log("The id --- "+this.id);
    this.studentService.getStudent(this.id)
    .subscribe(data =>{
      this.student = data;
      console.log("Data Email ---> "+data.email+" Data id ---> "+data.id);
    });

  }
OnSubmit(){
  this.update();
}
  update(){
    this.studentService
    .updateStudent(this.student,this.id)
    .subscribe(data => {
      console.log("The Data --->"+data.toString);
      this.student = new Student();
      this.gotoList();
    });
  }

gotoList(){
  this.router.navigate(['/students']);
}

}

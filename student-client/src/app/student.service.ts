import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './student';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private baseURL = 'http://localhost:8080/api/students';

  constructor(private httpClient: HttpClient) {}

  getStudentList(): Observable<Student[]> {
    return this.httpClient.get<Student[]>(`${this.baseURL}`);
  }

  getStudent(id: number): Observable<Student> {
    return this.httpClient.get<Student>(`${this.baseURL}/student/${id}`);
  }

  createStudent(student: Object): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/create`, student);
  }

  updateStudent(student: Object, id: number): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/update/${id}`, student);
  }

  deleteStudent(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/delete/${id}`);
  }
}

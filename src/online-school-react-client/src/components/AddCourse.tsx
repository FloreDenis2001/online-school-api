import React, { useState, useEffect,useContext } from 'react';
import Book from '../models/Book';
import BookService from '../services/BookService';
import { ContextLogin } from '../context/LoginProvider';
import LoginContextType from '../models/LoginContextType';
import BookRequest from '../models/BookRequest';
import StudentService from '../services/StudentService';
import CourseRequest from '../dtos/CourseRequest';

interface AddCourseProps {
    handleCloseModal: () => void;
  }
  

const AddCourse: React.FC<AddCourseProps> = ({handleCloseModal}) => {
  const [name, setName] = useState("Name");
  const [department, setDepartment] = useState("Department");
  const { studentLogin } = useContext(ContextLogin) as LoginContextType;
  const studentService = new StudentService();
  const handleSave = async () => {

       let course = {
          name:name,
          department:department,
          studentId:studentLogin.studentId
       } as CourseRequest;
       await studentService.addCourse(studentLogin.token,course);
       handleCloseModal();

  };

  return (
    <div className="modal-container">
      <div className="modal-content">
        <h2>Add Course</h2>
        <label htmlFor="">Name</label>
        <input type="text" name="name" value={name} onChange={(e)=>{
           setName(e.target.value);
        }} />
        <label htmlFor="">Department</label>
        <input type="text" name="author" value={department} onChange={(e)=>{
           setDepartment(e.target.value);
        }} />
    


        <div className="modal-buttons">
          <button className="save-button" onClick={handleSave}>Save</button>
          <button className="cancel-button" onClick={handleCloseModal}>Cancel</button>
        </div>
      </div>
    </div>
  );
};

export default AddCourse;

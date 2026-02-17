import React, { useEffect, useState } from 'react'
import { getAllDepartments, deleteDepartment } from '../services/DepartmentService';
import { Link, useNavigate } from 'react-router-dom';

const ListDepartmentComponent = () => {


    const [departments, setDepartments] =  useState([]);
    const navigator = useNavigate();

    useEffect( () => {
        listOfDepartments();
    }, [] )


    function updateDepartment(id)
    {
        navigator(`/edit-department/${id}`)   // Syntax to pass id in the url
    }

    function handleDeleteDepartment(id)
    {
        deleteDepartment(id).then((response) => {
            console.log(response.data);
            listOfDepartments();
        }).catch(error => {
            console.error(error);
        })
        
    }


    function listOfDepartments()
    {
        getAllDepartments().then((response) => {
            console.log(response.data);
            setDepartments(response.data);
        }).catch(error => {
            console.error(error);
        })
    }


  return (
    <div className='container'>
        <h2 className='text-center'>List of Departments</h2>
        <Link to='/add-department' className='btn btn-primary mb-2'>Add Department</Link>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Department Id</th>
                        <th>Department Name</th>
                        <th>Department Desc</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        departments.map( dept =>  
                            <tr key={dept.id}>
                                <td> {dept.id} </td>
                                <td> {dept.departmentName} </td>
                                <td> {dept.departmentDescription} </td>
                                <td>
                                    <button onClick={() => updateDepartment(dept.id)} className='btn btn-info'>Update</button>
                                    <button onClick={() => handleDeleteDepartment(dept.id)} className='btn btn-danger' 
                                    
                                    style={{marginLeft: "10px"}}>
                                        Delete</button>
                                </td>
                            </tr>
                        )
                    }


                </tbody>
            </table>

    </div>
  )
}

export default ListDepartmentComponent
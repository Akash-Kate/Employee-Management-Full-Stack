import React, { useEffect, useState } from 'react'
import { getAllDepartments } from '../services/DepartmentService';
import { Link } from 'react-router-dom';

const ListDepartmentComponent = () => {


    const [departments, setDepartments] =  useState([]);


    useEffect( () => {
        getAllDepartments().then((response) => {
            console.log(response.data);
            setDepartments(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [] )

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
                    </tr>
                </thead>
                <tbody>
                    {
                        departments.map( dept =>  
                            <tr key={dept.id}>
                                <td> {dept.id} </td>
                                <td> {dept.departmentName} </td>
                                <td> {dept.departmentDescription} </td>
                            </tr>
                        )
                    }


                </tbody>
            </table>

    </div>
  )
}

export default ListDepartmentComponent
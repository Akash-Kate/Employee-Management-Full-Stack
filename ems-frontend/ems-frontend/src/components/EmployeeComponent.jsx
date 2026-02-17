import React, { useEffect, useState } from 'react'
import { createEmployee, getEmployee, updateEmployee } from '../services/EmployeeService'

import { useNavigate, useParams } from 'react-router-dom'
import { getAllDepartments } from '../services/DepartmentService'

const EmployeeComponent = () => {

  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const [deptId, setdeptId] = useState('');   // deptId has to match with backedn DTO object field name
  const [departments, setDepartments] = useState([]);

  useEffect(() => {
    getAllDepartments().then((response) => {
      console.log("Check response",response);
      setDepartments(response.data);
    }).catch(error => {
      console.error(error);
    })

  }, []);

  const { id } = useParams();

  const [erros, setErrors] = useState({
    firstName: '',
    lastName: '',
    email: '',
    department: ''
  });

  const navigator = useNavigate();

  useEffect(() => {

    if (id) {
      getEmployee(id).then((response) => {
        setFirstName(response.data.firstName);
        setLastName(response.data.lastName);
        setEmail(response.data.email);
        setdeptId(response.data.deptId);

      }).catch(error => {
        console.error(error);
      })
    }

  }, [id])

  function saveOrUpdateEmployee(e) {
    e.preventDefault();

    if (validateForm()) {

      const employee = { firstName, lastName, email, deptId};
      console.log("Check Employee Object ->",employee)


      if (id) {
        updateEmployee(id, employee).then((response) => {
          console.log(response.data);
          navigator('/employees');
        }).catch(error => {
          console.error(error);
        })
      } else {
        createEmployee(employee).then((response) => {
          console.log(response.data);
          navigator('/employees')
        }).catch(error => {
          console.error(error);
        })
      }


    }

  }

  function validateForm() {
    let valid = true;

    const errorsCopy = { ...erros }

    if (firstName.trim()) {
      errorsCopy.firstName = ''
    } else {
      errorsCopy.firstName = 'First Name is Required'
      valid = false;
    }

    if (lastName.trim()) {
      errorsCopy.lastName = '';
    } else {
      errorsCopy.lastName = 'Last Name is required';
      valid = false;
    }

    if (email.trim()) {
      errorsCopy.email = '';
    } else {
      errorsCopy.email = 'Email is required';
      valid = false;
    }

    if (deptId){
      errorsCopy.department = '';
    }else{
      errorsCopy.department = 'Select Department';
      valid = false;
    }

    setErrors(errorsCopy);

    return valid;

  }

  function pageTitle() {
    if (id) {
      return <h2 className='text-center'>Update Employee</h2>
    } else {
      return <h2 className='text-center'>Add Employee</h2>
    }

  }

  return (
    <div className='conatiner'>
      <br /> <br />
      <div className='row'>
        <div className='card col-md-6 offset-md-3 offset-md-3'>
          {
            pageTitle()
          }
          <div className='card-body'>
            <form>

              <div className='form-group mb-2'>
                <label className='form-label'>First Name</label>
                <input
                  type='text'
                  placeholder='Enter Employee First Name'
                  name='firstName'
                  value={firstName}
                  className={`form-control ${erros.firstName ? 'is-invalid' : ''}`}
                  onChange={(e) => setFirstName(e.target.value)}
                >
                </input>
                {erros.firstName && <div className='invalid-feedback'>{erros.firstName}</div>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Last Name</label>
                <input
                  type='text'
                  placeholder='Enter Employee Last Name'
                  name='lastName'
                  value={lastName}
                  className={`form-control ${erros.lastName ? 'is-invalid' : ''}`}
                  onChange={(e) => setLastName(e.target.value)}
                >
                </input>
                {erros.lastName && <div className='invalid-feedback'>{erros.lastName}</div>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Email</label>
                <input
                  type='text'
                  placeholder='Enter Employee Email'
                  name='email'
                  value={email}
                  className={`form-control ${erros.email ? 'is-invalid' : ''}`}
                  onChange={(e) => setEmail(e.target.value)}
                >
                </input>
                {erros.email && <div className='invalid-feedback'>{erros.email}</div>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Select Department</label>
                <select 
                value = {deptId}
                className={`form-control ${erros.department ? 'is-invalid' : ''}`}
                onChange={(e) => setdeptId(e.target.value)}
                >
                  <option value="">Select Department</option>
                  {
                    departments.map(dept => 
                      <option key={dept.id} value={dept.id}>{dept.departmentName} </option>
                    )
                  }
                </select>
                { erros.department && <div className='invalid-feedback'>{erros.department}</div>}
              </div>

              <button className='btn btn-success' onClick={saveOrUpdateEmployee}>Submit</button>

            </form>

          </div>

        </div>
      </div>


    </div>
  )
}

export default EmployeeComponent
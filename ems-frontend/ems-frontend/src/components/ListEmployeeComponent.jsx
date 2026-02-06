import React from 'react'

const ListEmployeeComponent = () => {

    const dummyData = [
        {
            "id" : 1,
            "firstname" : "Ramesh",
            "lastName" : "Fadtare",
            "email" : "ramesh@gmail.com"
        },
        {
            "id" : 2,
            "firstname" : "Umesh",
            "lastName" : "Barge",
            "email" : "ramesh@gmail.com"
        },
        {
            "id" : 3,
            "firstname" : "Akash",
            "lastName" : "Kate",
            "email" : "ak@gmail.com"
        }
    ];

  return (
    <div className='container'>
        <h2 className='text-center'> List of Employees </h2>
        <table className='table table-striped table-bordered'>
        <thead>
            <tr>
                <th>Employee Id</th>
                <th>Employee First Name</th>
                <th>Employee Last Name</th>
                <th>Employee Email Address</th>
            </tr>
        </thead>

        <tbody>
            {
                dummyData.map(employee => 
                    <tr key={employee.id}>
                        <td>{employee.id}</td>
                        <td>{employee.firstname}</td>
                        <td>{employee.lastName}</td>
                        <td>{employee.email}</td>
                    </tr>
                )
            }
        </tbody>

        </table>
    </div>
  )
}

export default ListEmployeeComponent
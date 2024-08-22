import React from 'react'
import { Route, Routes } from 'react-router-dom'
import AdminDashBoard from './AdminDashBoard'
import AdminNavBar from './AdminNavBar';
import AddBus from './Bus Component/AddBus';
import ViewAllBus from './Bus Component/ViewAllBus'
import EditBus from './Bus Component/EditBus';
import AdminProfile from './AdminProfile';

function AdminHomePage() {
  return (
    <div>
        <AdminNavBar/>
        <Routes>
            <Route path='/' element={<AdminDashBoard/>}></Route>
            <Route path='/addbus' element={<AddBus/>}></Route>
            <Route path='/viewbus' element={<ViewAllBus/>}/>
            <Route path="/editadmin/:id" element={<AdminProfile/>} />
            <Route path='/editbus/:id' element={<EditBus/>}/>
        </Routes>
    </div>
  )
}

export default AdminHomePage
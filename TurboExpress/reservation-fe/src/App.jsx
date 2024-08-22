import React from "react";

import { BrowserRouter, Route, Routes } from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.min.css';

import AdminLogin from './Component/AdminLogin';
import AdminSignUp from './Component/AdminSignUp';
import ProtectAdmin from './Component/ProtectAdmin';
import AdminHomePage from './Component/AdminHomePage';

import ResetPassword from "./Component/ResetPassword";

import ProtectUser from "./Component/ProtectUser";
import PageNotFound from "./Component/PageNotFound";

import UserSignup from "./Component/UserSignup";
import UserLogin from "./Component/UserLogin";
import UserHomepage from "./Component/UserHomepage";
import LandingPage from "./Component/LandingPage";
import BookBus from "./Component/Bus Component/BookBus";
import Bookings from "./Component/Bus Component/Bookings";
// import Bookings from "./Component/Bus Component/Bookings";
// import Navbar from "./Component/Navbar";

const App = () => {
    return (
        <div>
            <BrowserRouter>
                {/* <Navbar /> */}
                <Routes>
                    <Route element={<PageNotFound />} path="/*" />
                    <Route element={<LandingPage />} path="/" />
                    <Route element={<AdminLogin />} path="/adminlogin/*" />
                    <Route element={<AdminSignUp />} path="/adminsignup" />
                    <Route element={<ProtectAdmin Child={AdminHomePage} />} path="/adminhomepage/*"/>
                    <Route element={<UserLogin />} path="/userlogin/*" />
                    <Route element={<UserSignup />} path="/usersignup" />
                    <Route element={<ProtectUser Child={UserHomepage} />} path="/userhomepage/*" />
                    <Route element={<ResetPassword />} path="/resetpassword" />
                    <Route element={<BookBus />} path='/bookbus/:id' />
                    <Route  element={<Bookings />} path="/bookings" />
                </Routes>
            </BrowserRouter>
        </div>
    )
}
export default App
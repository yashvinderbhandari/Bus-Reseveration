import React, { useState } from 'react';
import   './ResetPassword.css'; // Import the CSS module

const ResetPassword = () => {
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleConfirmPasswordChange = (e) => {
        setConfirmPassword(e.target.value);
    };

    const handleTogglePassword = () => {
        setShowPassword(!showPassword);
    };

    const handleResetPassword = () => {
        // Add logic to handle password reset (e.g., API call)
        console.log('New Password:', password);
    };

    return (
        <div className='reset-password-container'>
            <h2>Reset Password</h2>
            <div>
                <label htmlFor="password">New Password:</label>
                <input
                    type={showPassword ? 'text' : 'password'}
                    id="password"
                    className='reset-password-input'
                    value={password}
                    onChange={handlePasswordChange}
                />
                <button
                    className='toggle-password-button'
                    onClick={handleTogglePassword}
                >
                    {showPassword ? 'Hide' : 'Show'} Password
                </button>
            </div>
            <div>
                <label htmlFor="confirmPassword">Confirm Password:</label>
                <input
                    type="password"
                    id="confirmPassword"
                    className='reset-password-input'
                    value={confirmPassword}
                    onChange={handleConfirmPasswordChange}
                />
            </div>
            <button className='button' onClick={handleResetPassword}>
                Reset Password
            </button>
        </div>
    );
};

export default ResetPassword;

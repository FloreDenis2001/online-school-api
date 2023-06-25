import React, { useContext } from 'react';
import { LogoutOutlined, UserOutlined } from '@ant-design/icons';
import { ContextLogin } from '../context/LoginProvider';
import LoginContextType from '../models/LoginContextType';
import { useNavigate } from 'react-router-dom';
import StudentLogin from '../dtos/StudentLogin';
import Cookies from 'js-cookie';

const Header: React.FC = () => {
  const { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;
  const navigate = useNavigate();

  const handleNavigation = (event: React.MouseEvent<HTMLAnchorElement>, path: string) => {
    event.preventDefault();
    if (studentLogin !== undefined) {
      navigate(path);
    }
  };

  const handleLogOut = async (): Promise<void> => {
    try {
     
      Cookies.remove("authentificatedUser");
      navigate("/login");
    } catch (err) {
      console.log('Error log out : ', err);
    }

  }

  return (
    <header>
      <div className="logo-container">
        <p><span>Online</span> School</p>
      </div>

      <div className="nav-container">
        <nav className="nav-bar">
          <ul>
            <li>
              <a href="/" onClick={(event) => handleNavigation(event, '/home/:studentId')}>
                Home
              </a>
            </li>
            <li>
              <a href="/" onClick={(event) => handleNavigation(event, '/mybooks/:studentId')}>
                My Books
              </a>
            </li>
            <li>
              <a href="/" onClick={(event) => handleNavigation(event, '/mycourses/:studentId')}>
                My Courses
              </a>
            </li>
          </ul>
        </nav>
      </div>

      <div className="auth-container">
        <div className="sign-in">
          <UserOutlined />
          <span onClick={() => (navigate('/profile/:studentId'))}>{studentLogin?.firstName} {studentLogin?.lastName}</span>

          <LogoutOutlined onClick={(handleLogOut)} />
        </div>
      </div>
    </header>
  );
};

export default Header;

import React, { useContext, useState } from 'react';
import { ContextLogin } from '../context/LoginProvider';
import LoginContextType from '../models/LoginContextType';
import { Image } from 'react-bootstrap';
import Header from './Header';
import Footer from './Footer';
import AddBook from './AddBook';
const Profile : React.FC = () => {
  const { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;
  const [showAddedModal, setShowAddedModal] = useState(false);

  const handleAddBook = (): void => {
    setShowAddedModal(true);
};

const handleCloseModal = (): void => {
    setShowAddedModal(false);
};

  return (
    <>
    <Header />
    <div className="profile">
      <div className="avatar">
      <Image className='profile-img' src="/img/library2.jpeg" alt="" fluid />
      </div>
      <div className="details">
        <h2>{studentLogin.firstName}  {studentLogin.lastName}</h2>
        <p>Email : {studentLogin.email}</p>
      </div>
      <button className='book-add' onClick={handleAddBook}>Add Book</button>
      <button className='update-profile'>Update</button>
      {showAddedModal && (
                <AddBook  handleCloseModal={handleCloseModal} />
      )}
    </div>
    <Footer />
    </>
  );
};

export default Profile;

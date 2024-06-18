import React from 'react';
import { Navigate } from 'react-router-dom';

const isAuthenticated = (): boolean => {
  return localStorage.getItem('authToken') !== null;
};

interface PrivateRouteProps {
  element: JSX.Element;
}

const PrivateRoute: React.FC<PrivateRouteProps> = ({ element }) => {
  return isAuthenticated() ? element : <Navigate to="/login" />;
};

export default PrivateRoute;

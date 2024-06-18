import React, { useState } from 'react';
import { Container, Form, Input, Button } from './styles';
import { useNavigate } from 'react-router-dom';
import { api2 } from '../../services/api2';

export const Login: React.FC = () => {
  const navigate = useNavigate();
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    
    const response = await api2.post('/auth/login', {
      login: login,
      password: password
    }).then(response => response.data)

    localStorage.setItem('authToken', response.token);
    navigate('/dashboard')
  };

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Input
          type="text"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
          placeholder="Login"
          required
        />
        <Input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Password"
          required
        />
        <Button type="submit">Login</Button>
      </Form>
    </Container>
  );
};
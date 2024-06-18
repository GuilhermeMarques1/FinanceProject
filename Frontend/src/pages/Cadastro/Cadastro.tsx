// RegisterPage.tsx
import React, { useState } from 'react';
import { Container, Form, Input, Button } from './styles';
import { useNavigate } from 'react-router-dom';
import { api2 } from '../../services/api2';

export const Cadastro: React.FC = () => {
  const navigate = useNavigate();
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    
    const response = await api2.post('/auth/register', {
      nome: name,
      login: email,
      password: password
    }).then(response => response);

    if(response.status === 200) {
      navigate('/login')
    }
  };

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Name"
          required
        />
        <Input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="Email"
          required
        />
        <Input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Password"
          required
        />
        <Button type="submit">Register</Button>
      </Form>
    </Container>
  );
};
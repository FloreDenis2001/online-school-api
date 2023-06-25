import React from 'react';
import { useForm } from 'react-hook-form';

type FormData = {
  name: string;
  email: string;
  password: string;
};

const FormExample: React.FC = () => {
  const { register, handleSubmit, formState: { errors } } = useForm<FormData>();

  const onSubmit = (data: FormData) => {
    // console.log('Form submitted:', data);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <label>
        Name:
        <input type="text" {...register('name', { required: true })} />
        {errors.name && <span>This field is required</span>}
      </label>
      <br />
      <label>
        Email:
        <input type="email" {...register('email', { required: true, pattern: /^\S+@\S+$/i })} />
    
        
        {errors.email && <span>Please enter a valid email address</span>}
      </label>
      <br />
      <label>
        Password:
        <input type="password" {...register('password', { required: true, minLength: 6 })} />
        {errors.password && <span>Password must be at least 6 characters long</span>}
      </label>
      <br />
      <button type="submit">Submit</button>
    </form>
  );
};

export default FormExample;
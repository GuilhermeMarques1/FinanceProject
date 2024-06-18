import { createBrowserRouter, Navigate, RouterProvider } from 'react-router-dom';
import ReactDOM from 'react-dom/client';
import { createServer, Model } from 'miragejs';
import App from './App';

import { Login } from './pages/Login/Login';
import { Cadastro } from './pages/Cadastro/Cadastro';
import PrivateRoute from './privateRoutes';

// createServer({
//   models: {
//     transaction: Model,
//   },

//   seeds(server) {
//     server.db.loadData({
//       transactions: [
//         {
//           id: 1,
//           title: "Freelance de website",
//           type: "deposit",
//           category: "Dev",
//           amount: 6000,
//           createAt: new Date("2022-10-14 23:00:00")
//         },

//         {
//           id: 2,
//           title: "Aluguel",
//           type: "withdraw",
//           category: "Casa",
//           amount: 800,
//           createAt: new Date("2022-10-10 09:00:00")
//         },

//         {
//           id: 3,
//           title: "Gasolina",
//           type: "withdraw",
//           category: "Carro",
//           amount: 150,
//           createAt: new Date("2022-10-31 18:00:00")
//         }
//       ]
//     })
//   },

//   routes() {
//     this.namespace = 'api';

//     this.get('/transactions', () => {
//       return this.schema.all('transaction');
//     })

//     this.post('/transactions', (schema, request) => {
//       const data = JSON.parse(request.requestBody);

//       return schema.create('transaction', data);
//     })
//   }
// })

const router = createBrowserRouter([
  {
    children: [
      {
        path: '/dashboard',
        element: <PrivateRoute element={<App />} />
      },
      // {
      //   path: '/dashboard',
      //   element: <App />
      // },
      {
        path: '/login',
        element: <Login />
      },
      {
        path: '/cadastro',
        element: <Cadastro />
      }
    ]
  }
])

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
root.render(
  <RouterProvider router={router}/>
);


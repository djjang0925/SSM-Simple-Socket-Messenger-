import { RouterProvider, createBrowserRouter } from "react-router-dom";
import * as s from "./styles/App";
import Nav from "./components/Nav";
import Home from "./routes/Home";
import Login from "./routes/Login";
import ProtectedRoute from "./routes/ProtectedRoute";

function App() {
  const router = createBrowserRouter([
    {
      path: "/",
      element: (
        <ProtectedRoute>
          <Nav />
        </ProtectedRoute>
      ),
      children: [
        {
          path: "",
          element: <Home />,
        },
      ],
    },
    {
      path: "/login",
      element: <Login />,
    },
  ]);
  return (
    <s.Container>
      <RouterProvider router={router} />
    </s.Container>
  );
}

export default App;

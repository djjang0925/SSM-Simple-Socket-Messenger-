import { ReactNode } from "react";
import { Navigate } from "react-router-dom";
import useAuthStore from "../store/AuthStore";

interface ProtectedRouteProps {
  children: ReactNode;
}

function ProtectedRoute({ children }: ProtectedRouteProps) {
  const { isLogIn } = useAuthStore();

  if (!isLogIn) {
    return <Navigate to="/login" replace />;
  }
  return <>{children}</>;
}

export default ProtectedRoute;

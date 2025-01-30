import { Route, Routes, useLocation } from "react-router";
import Navbar from "./components/Navbar";
import { Toaster } from "./components/ui/toaster";
import { Login, Register, UserDetails, Users, Rents } from "./pages";
import ProtectedRoute from "./components/ProtectedRoute";

const Layout = () => {
  const location = useLocation();
  const hideNavbarPaths = ["/login", "/register"];

  return (
    <>
      {!hideNavbarPaths.includes(location.pathname) && <Navbar />}
      <Routes>
        <Route path="/" element={<div />} />

        <Route element={<ProtectedRoute />}>
          <Route path="/rents" element={<Rents />} />
          <Route path="/users" element={<Users />} />
          <Route path="/users/:id" element={<UserDetails />} />
        </Route>

        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
      </Routes>
      <Toaster />
    </>
  );
};
export default Layout;
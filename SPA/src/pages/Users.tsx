import UsersTable from "@/components/UsersTable";
import { User } from "@/types";
import { useEffect, useState } from "react";
import { Input } from "@/components/ui/input";
import { useNavigate } from "react-router";
import { useToast } from "@/hooks/use-toast";
import apiClient from "@/lib/apiClient";

const Users = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [searchLogin, setSearchLogin] = useState<string>("");
  const [filteredUsers, setFilteredUsers] = useState<User[]>([]);
  const navigate = useNavigate();
  const { toast } = useToast();

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const { data } = await apiClient.get("/users");
        setUsers(data);
        setFilteredUsers(data);
      } catch (error) {
        toast({
          title: "Error",
          description: "There was an error fetching the users.",
          variant: "destructive",
        });
      }
    };

    fetchUsers();
  }, [navigate, toast]);

  useEffect(() => {
    const filtered = users.filter((user) =>
      user.login.toLowerCase().startsWith(searchLogin.toLowerCase())
    );
    setFilteredUsers(filtered);
  }, [searchLogin, users]);

  const handleSearchLoginChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setSearchLogin(event.target.value);
  };

  return (
    <div className="px-16 pb-8">
      <h1 className="text-bold text-5xl text-center my-8">List of Users</h1>
      <div className="flex justify-center mb-4">
        <Input
          placeholder="Search by login"
          value={searchLogin}
          onChange={handleSearchLoginChange}
          className="border rounded px-2 py-1 w-1/3"
        />
      </div>
      <UsersTable users={filteredUsers} />
    </div>
  );
};

export default Users;

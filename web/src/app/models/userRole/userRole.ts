import { Role } from "../role/role";
import { User } from "../user/user";

export interface UserRole {
  id: string;
  user: User;
  roles: Role[];
}
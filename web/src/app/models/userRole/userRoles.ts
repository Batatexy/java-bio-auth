import { Role } from "../role/role";
import { User } from "../user/user";

export interface UserRoles {
  user: User;
  roles: Role[];
}

export interface User {
  id: string;
  fullName: string;
  email: string;
  userImage?: string | null;
  digitalImages?: string[] | null;
}
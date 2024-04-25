export interface User {
  id: number;
  email: string;
  name: string;
  password: string;
  subscribedThemeIds: number[];
}

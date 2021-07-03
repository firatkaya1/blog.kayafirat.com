export class User {
  id: number;
  email: string;
  username: string;
  photo: string;
  contactAddress: string;
  birthDate: string;
  registerDate: string;
  githubAddress: string;
  linkedinAddress: string;
  accountStatus:boolean;
  
  constructor(init?:Partial<User>) {
    Object.assign(this, init);
  }

}
  
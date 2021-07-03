export class Visibility {
    id: number;
    contact: boolean;
    birthdate: boolean;
    github: boolean;
    linkedin: boolean;
    
    constructor(init?:Partial<Visibility>) {
      Object.assign(this, init);
    }
  
  }
    
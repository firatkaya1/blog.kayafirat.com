export class FacebookSEO {
    id:number;
    title: string;
    description: string;
    image: string;
    type: string;
    siteName: string;
    url: string;
    author: string
  
    constructor(init?:Partial<FacebookSEO>) {
      Object.assign(this, init);
    }
  }
  
export class GoogleSEO {
  id: number;
  title: string;
  description: string;
  image: string;
  keywords: string;

  constructor(init?:Partial<GoogleSEO>) {
    Object.assign(this, init);
  }
}

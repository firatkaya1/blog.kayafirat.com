export class Vote {
    id: number;
    date: string;
    user_name: string;
    profile_photo: string;
    user_id: number;
    
    constructor(init?:Partial<Vote>) {
        Object.assign(this, init);
    }
}
      
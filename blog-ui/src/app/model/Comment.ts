export class Comment {
  id: number;
  body: string;
  created_date: Date;
  update_date: string;
  user_name: string;
  profile_photo: string;
  post_id: number;
  total_vote: number;
  
  constructor(init?:Partial<Comment>) {
    Object.assign(this, init);
  }

}
  
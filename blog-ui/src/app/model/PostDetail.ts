import { Meta } from './Meta';
import { Post } from './Post';

export class PostDetail {
        id: number;
        body: string;
        post: Post;
        meta: Meta;

    constructor(init?:Partial<PostDetail>) {
        Object.assign(this, init);
    }
}

import { Category } from "./Category";

export class Post {
    id:number;
    title: string;
    header: string;
    createdDate: string;
    view: number;
    hide: boolean;
    category:Category[];

    constructor(init?:Partial<Post>) {
        Object.assign(this, init);
    }
}

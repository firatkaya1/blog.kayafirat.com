export class Category {
    id: number;
    name: string;
    color: string;
    textColor: string;

    constructor(init?:Partial<Category>) {
        Object.assign(this, init);
    }
}

export class TwitterSEO {
    id: number;
    title: string;
    description: string;
    image: string;
    creator: string;
    card: string;

    constructor(init?:Partial<TwitterSEO>) {
        Object.assign(this, init);
    }
}

export class Notification {
    id: number;
    message: string;
    link: string;
    title: string;
    created_Date: string;
    modified_Date: string;
    is_Read: boolean;

    constructor(init?:Partial<Notification>) {
        Object.assign(this, init);
    }
}

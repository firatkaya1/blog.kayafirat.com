export class Alert {
    id:number;
    isSuccess: boolean;
    message: string;
    isClose:boolean;

    constructor(init?:Partial<Alert>) {
        Object.assign(this, init);
    }
}

export class NotificationPermission {
    id: number;
    loginAttempt: boolean;
    loginNotification: boolean;
    postNotification: boolean;
    passChange: boolean;

    constructor(init?:Partial<NotificationPermission>) {
        Object.assign(this, init);
    }
}

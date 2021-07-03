import { TwitterSEO } from './TwitterSEO';
import { FacebookSEO } from './FacebookSEO';
import { GoogleSEO } from './GoogleSEO';

export class Meta {
        id: number;
        googleSEO:GoogleSEO;
        twitterSEO:TwitterSEO;
        facebookSEO: FacebookSEO;

    constructor(init?:Partial<Meta>) {
        Object.assign(this, init);
    }
}

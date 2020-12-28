/* Generated from Java with JSweet 3.0.0 - http://www.jsweet.org */
import { APIs } from '../apis/APIs';

export class Bilan {
    public id: number;

    public personneId: number;

    public occurenceDate: Date;

    public writingSpeedNormal: number;

    public writingSpeedMax: number;

    constructor() {
        if (this.id === undefined) { this.id = null; }
        if (this.personneId === undefined) { this.personneId = null; }
        if (this.occurenceDate === undefined) { this.occurenceDate = null; }
        if (this.writingSpeedNormal === undefined) { this.writingSpeedNormal = null; }
        if (this.writingSpeedMax === undefined) { this.writingSpeedMax = null; }
    }
}
Bilan["__class"] = "io.github.pascalheraud.diaps.db.Bilan";




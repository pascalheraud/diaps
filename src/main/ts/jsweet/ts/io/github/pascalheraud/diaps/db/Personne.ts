/* Generated from Java with JSweet 3.0.0 - http://www.jsweet.org */
import { APIs } from '../apis/APIs';

export class Personne {
    public id: number;

    public dateOfReport: Date;

    public firstName: string;

    public lastName: string;

    public dateOfBirth: Date;

    public handed: Personne.Handed;

    public handedInfo: string;

    public classRoom: string;

    constructor() {
        if (this.id === undefined) { this.id = null; }
        if (this.dateOfReport === undefined) { this.dateOfReport = null; }
        if (this.firstName === undefined) { this.firstName = null; }
        if (this.lastName === undefined) { this.lastName = null; }
        if (this.dateOfBirth === undefined) { this.dateOfBirth = null; }
        if (this.handed === undefined) { this.handed = null; }
        if (this.handedInfo === undefined) { this.handedInfo = null; }
        if (this.classRoom === undefined) { this.classRoom = null; }
    }
}
Personne["__class"] = "io.github.pascalheraud.diaps.db.Personne";


export namespace Personne {

    export enum Handed {
        LEFT, RIGHT, UNKNOWN
    }
}




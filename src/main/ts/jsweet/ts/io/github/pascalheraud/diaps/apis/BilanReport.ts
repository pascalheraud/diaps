/* Generated from Java with JSweet 3.0.0 - http://www.jsweet.org */
import { Bilan } from '../db/Bilan';
import { BilanItemReport } from '../db/BilanItemReport';
import { Item } from '../db/Item';
import { Personne } from '../db/Personne';

export class BilanReport {
    public bilan: Bilan;

    public personne: Personne;

    public hasFM: boolean;

    public formeItems: any;

    public mouvementItems: any;

    public hasDys: boolean;

    public dysItems: any;

    constructor() {
        if (this.bilan === undefined) { this.bilan = null; }
        if (this.personne === undefined) { this.personne = null; }
        if (this.hasFM === undefined) { this.hasFM = false; }
        if (this.formeItems === undefined) { this.formeItems = null; }
        if (this.mouvementItems === undefined) { this.mouvementItems = null; }
        if (this.hasDys === undefined) { this.hasDys = false; }
        if (this.dysItems === undefined) { this.dysItems = null; }
    }
}
BilanReport["__class"] = "io.github.pascalheraud.diaps.apis.BilanReport";




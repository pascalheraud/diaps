/* Generated from Java with JSweet 3.0.0 - http://www.jsweet.org */
export class Item {
    public id: number;

    public number: number;

    public label: string;

    public rate: number;

    public category: Item.Category;

    public dysGroup: Item.DysGroup;

    public dysRate: number;

    public dysNumber: number;

    constructor() {
        if (this.id === undefined) { this.id = null; }
        if (this.number === undefined) { this.number = null; }
        if (this.label === undefined) { this.label = null; }
        if (this.rate === undefined) { this.rate = null; }
        if (this.category === undefined) { this.category = null; }
        if (this.dysGroup === undefined) { this.dysGroup = null; }
        if (this.dysRate === undefined) { this.dysRate = null; }
        if (this.dysNumber === undefined) { this.dysNumber = null; }
    }
}
Item["__class"] = "io.github.pascalheraud.diaps.db.Item";


export namespace Item {

    export enum Category {
        EM, EF, OTHER
    }

    export enum DysGroup {
        PAGE, MALADRESSE, FORMES_ET_PROPORTIONS
    }
}




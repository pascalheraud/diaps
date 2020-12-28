/* Generated from Java with JSweet 3.0.0 - http://www.jsweet.org */
import { BilanItem } from './BilanItem';

export class BilanItemReport extends BilanItem {
    public item: Item;

    public getItemCategory(): Item.Category {
        return this.item.category;
    }

    public getItemDysGroup(): Item.DysGroup {
        return this.item.dysGroup;
    }

    public getDescription(): string {
        return this.description;
    }

    public getItemName(): string {
        if (this.item.category === Item.Category.OTHER){
            return "D" + this.item.dysNumber;
        } else if (this.item.category === Item.Category.EF){
            return "D" + this.item.dysNumber + "(F" + this.item.number + ")";
        } else {
            return "D" + this.item.dysNumber + "(M" + this.item.number + ")";
        }
    }

    constructor() {
        super();
        if (this.item === undefined) { this.item = null; }
    }
}
BilanItemReport["__class"] = "io.github.pascalheraud.diaps.db.BilanItemReport";




import { Item } from './Item';


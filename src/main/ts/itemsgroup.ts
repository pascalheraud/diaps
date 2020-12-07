import Component from 'vue-class-component'
import BaseVueJS from './diapslib';
@Component({
	name:'itemsgroup',
	template: '#items-group-template',
	props: ["itemsGroupTitle", "itemsGroupPrefix", "itemsGroupItems", "itemsGroupDys"]
})
export default class ItemsGroupComponent extends BaseVueJS {
	editedBilanItem: any = null
	scrollPosition: number = 0

	editBilanItem(bilanItem: Object) {
		this.scrollPosition = window.pageYOffset;
		this.editedBilanItem = this.deepCopy(bilanItem);
		this.$nextTick(function() { (<HTMLElement>this.$refs['description']).focus(); });
	}

	cancelEditBilanItem() {
		this.editedBilanItem = null;
		this.$nextTick(function() {
			window.scrollTo(0, this.$data.scrollPosition);
		})
	}
	updateBilanItem() {
		this.callApi('/apis/bilan/item/update', this.editedBilanItem).then(this.onUpdatedBilanItem);
	}
	onUpdatedBilanItem(result: any) {
		this.$emit("reload", this.cancelEditBilanItem);
	}
	formeTotal(allItems: any, itemsGroupDys: boolean): number {
		var result: number = 0;
		for (let key of Object.keys(allItems)) {
			const items = allItems[key];
			items.forEach((bilanItem: any) => {
				if (itemsGroupDys) {
					result += bilanItem.note * bilanItem.item.dysRate;
				} else {
					result += bilanItem.note * bilanItem.item.rate;
				}
			});
		}
		return result;
	}
	itemLabel(bilanItem: any): string {
		var result: string;
		if (this.$props['itemsGroupDys']) {
			result = '' + bilanItem.item.dysNumber;
			if (bilanItem.item.category != 'OTHER') {
				result += ' (' + this.getPrefix(bilanItem.item) + '' + bilanItem.item.number + ')';
			}
		} else {
			result = this.getPrefix(bilanItem.item) + '' + bilanItem.item.number;
		}
		return result;
	}
	getPrefix(item: any): string {
		return item.category == 'EF' ? 'F' : 'M';
	}
}


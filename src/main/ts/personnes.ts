import moment from 'moment';

import Vue from 'vue';
import Component from 'vue-class-component'
import BaseVueJS from './diapslib';


@Component({
	filters: {
		frenchDate: function(dateISO: string): string {
			return moment(dateISO, 'YYYY-MM-DD').format("DD/MM/YYYY");
		}
	}
})
class PersonnesScreen extends BaseVueJS {
	personnes: any[] = new Array()
	state: string = 'list'
	currentPersonne: any = {}
	displayDeleteConfirmation: boolean = false
	mounted() {
		document.querySelector("main.container")!.classList.remove("is-hidden");
		this.refresh();
	}
	refresh(): Promise<Object[]> {
		return new Promise<any>((resolve, reject) => {
			this.callApi('/apis/personnes/all').then((result) => {
				this.$data.personnes = result;
				this.showList();
				resolve(undefined);
			});
		});
	}
	submit() {
		var url = this.state == 'add' ? '/apis/personnes/add' : '/apis/personnes/update';
		this.callApi(url, this.currentPersonne).then(this.refresh);
	}
	showFormEdit(personne: Object) {
		this.state = 'edit';
		this.currentPersonne = this.deepCopy(personne);
		Vue.nextTick(() => {
			(<HTMLInputElement>this.$refs.dateReport).focus();
		});
	}
	showFormAdd() {
		this.state = 'add';
		this.currentPersonne = new Object();
		Vue.nextTick(() => {
			(<HTMLInputElement>this.$refs.dateReport).focus();
		});
	}
	showList() {
		this.state = 'list';
		this.currentPersonne = new Object();
	}
	cancel() {
		this.showList();
	}
	remove(personne: Object) {
		this.currentPersonne = personne;
		this.displayDeleteConfirmation = true;
	}
	confirmDelete() {
		this.displayDeleteConfirmation = false;
		this.callApi("/apis/personnes/del", this.currentPersonne).then(this.refresh);
	}
	cancelDelete() {
		this.displayDeleteConfirmation = false;
		this.showList();
	}
};

window.onload = function() {
	new PersonnesScreen({ el: 'main' });
};



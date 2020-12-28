import Vue from 'vue';
import Component from 'vue-class-component'
import BaseVueJS from './diapslib'
import {Personne} from './jsweet/ts/io/github/pascalheraud/diaps/db/Personne'

@Component({
	filters: {
		frenchDate: function(dateISO: string): string {
			var split = dateISO.split('-');
			// One may use moment.js but a bit huge for this...
			return split[2] + "/" + split[1] + "/" + split[0];
		}
	}
})
class PersonnesScreen extends BaseVueJS {
	personnes: Personne[] = new Array()
	state: string = 'list'
	currentPersonne: Personne = new Personne()
	displayDeleteConfirmation: boolean = false
	
	mounted() {
		document.querySelector("main.container")!.classList.remove("is-hidden");
		this.refresh();
	}
	
	refresh(): Promise<Object[]> {
		return new Promise<any>((resolve, reject) => {
			this.callApi('/apis/personnes/all').then((result:Personne[]) => {
				this.personnes = result;
				this.showList();
				resolve(result);
			});
		});
	}
	
	submit() {
		var url = this.state == 'add' ? '/apis/personnes/add' : '/apis/personnes/update';
		this.callApi(url, this.currentPersonne).then(this.refresh);
	}
	
	showFormEdit(personne: Personne) {
		this.state = 'edit';
		this.currentPersonne = this.deepCopy(personne);
		Vue.nextTick(() => {
			(<HTMLInputElement>this.$refs.dateReport).focus();
		});
	}
	
	showFormAdd() {
		this.state = 'add';
		this.currentPersonne = new Personne();
		Vue.nextTick(() => {
			(<HTMLInputElement>this.$refs.dateReport).focus();
		});
	}
	
	showList() {
		this.state = 'list';
		this.currentPersonne = new Personne();
	}
	
	cancel() {
		this.showList();
	}
	
	remove(personne: Personne) {
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



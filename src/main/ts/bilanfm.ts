import Vue from 'vue';
import Component from 'vue-class-component'
import BaseVueJS from './diapslib';
// @ts-ignore because Eclipse Wild Web cannot resolve the import
import ItemsGroupComponent from './itemsgroup'
import {BilanReport} from './jsweet/ts/io/github/pascalheraud/diaps/apis/BilanReport'
import {Personne} from './jsweet/ts/io/github/pascalheraud/diaps/db/Personne'

@Component({
	components: {
		'itemsgroup': ItemsGroupComponent
	}
})
class BilanFMScreen extends BaseVueJS {
	bilanReport: BilanReport|null = null

	mounted() {
		document.querySelector("#mainContent")!.classList.remove("is-hidden");
		this.load().then(() => { });
	}

	load(): Promise<Object[]> {
		return new Promise<any>((resolve: Function, reject) => {
			const index: number = document.location.pathname.lastIndexOf('/');
			const personneId = parseInt(document.location.pathname.substr(index + 1));
			var input:Personne = <Personne>{
				id: personneId
			}
			this.callApi('/apis/bilan/fm/get', input).then((result:BilanReport) => {
				this.bilanReport = result;
				resolve();
			});
		});
	}

	reload(callback: any) {
		this.load().then(callback);
	}
};

window.onload = function() {
	new BilanFMScreen({ el: '#mainContent' });
};


import Vue from 'vue';
import Component from 'vue-class-component'
import BaseVueJS from './diapslib';
// @ts-ignore because Eclipse Wild Web cannot resolve the import
import ItemsGroupComponent from './itemsgroup'
import {BilanReport} from './jsweet/ts/io/github/pascalheraud/diaps/apis/BilanReport'
import {Bilan} from './jsweet/ts/io/github/pascalheraud/diaps/db/Bilan'

@Component({
	components: {
		'itemsgroup': ItemsGroupComponent
	}
})
class BilanDysScreen extends BaseVueJS {
	bilanReport: BilanReport|null = null
	mounted() {
		document.querySelector("#mainContent")!.classList.remove("is-hidden");
		this.load().then(() => { });
	}
	load(): Promise<Object[]> {
		return new Promise<any>((resolve: Function, reject) => {
			const index: number = document.location.pathname.lastIndexOf('/');
			const personneId:number = parseInt(document.location.pathname.substr(index + 1));
			const input:Bilan = <Bilan>{
				id: personneId
			}
			this.callApi('/apis/bilan/dys/get', input).then((result:BilanReport) => {
				this.bilanReport = result;
				resolve();
			});
		})
	}

	reload(callback: any) {
		this.load().then(callback);
	}
}

window.onload = function() {
	new BilanDysScreen({ el: '#mainContent' });
};


import Vue from 'vue';
import BaseVueJS from './diapslib';
import Component from 'vue-class-component'


@Component
class BilanScreen extends BaseVueJS {
	bilanReport: any = null

	mounted() {
		document.querySelector("#mainContent")!.classList.remove("is-hidden");
		this.load().then(() => { });
	}

	load(): Promise<Object[]> {
		return new Promise<any>((resolve: Function, reject) => {
			const index: number = document.location.pathname.lastIndexOf('/');
			const personneId = parseInt(document.location.pathname.substr(index + 1));
			var input = {
				id: personneId
			}
			this.callApi('/apis/bilan/get', input).then((result) => {
				this.bilanReport = result;
				resolve();
			});
		})
	}

	reload(callback: any) {
		this.load().then(callback);
	}
};

window.onload = function() {
	new BilanScreen({
		el: '#mainContent'
	});
};


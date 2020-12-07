import Vue from 'vue';
import Component from 'vue-class-component'
import BaseVueJS from './diapslib';
import ItemsGroupComponent from './itemsgroup'

@Component({
	components: {
		'itemsgroup': ItemsGroupComponent
	}
})
class BilanFMScreen extends BaseVueJS {
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
			this.callApi('/apis/bilan/fm/get', input).then((result) => {
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


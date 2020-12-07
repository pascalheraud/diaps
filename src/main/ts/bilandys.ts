import Vue from 'vue';
import Component from 'vue-class-component'
import BaseVueJS from './diapslib';
import ItemsGroupComponent from './itemsgroup'
//
@Component({
	components: {
		'itemsgroup': ItemsGroupComponent
	}
})
class BilanDysScreen extends BaseVueJS {
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
			this.callApi('/apis/bilan/dys/get', input).then((result) => {
				this.$data.bilanReport = result;
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


import Vue from 'vue';
import BaseVueJS from './diapslib';
import Component from 'vue-class-component'
import {Bilan} from './jsweet/ts/io/github/pascalheraud/diaps/db/Bilan'
import { BilanReport } from './jsweet/ts/io/github/pascalheraud/diaps/apis/BilanReport';

@Component
class BilanGenerationScreen extends BaseVueJS {
	bilanReport: BilanReport|null = null

	mounted() {
		document.querySelector("#mainContent")!.classList.remove("is-hidden");
		this.load();
	}

	getPersonneId(): number {
		const index: number = document.location.pathname.lastIndexOf('/');
		return parseInt(document.location.pathname.substr(index + 1));
	}
	
	onChangeFile() {
		document.querySelector(".file-name").innerHTML= (document.querySelector(".file-input") as HTMLInputElement).files[0].name;
	}

	load() {
		var input = {
			id: this.getPersonneId()
		}
		this.callApi('/apis/bilan/get', input).then((result:BilanReport) => {
			this.bilanReport = result;
		});
	}
};

window.onload = function() {
	new BilanGenerationScreen({
		el: '#mainContent'
	});
};


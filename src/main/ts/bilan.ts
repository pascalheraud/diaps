import Vue from 'vue';
import BaseVueJS from './diapslib';
import Component from 'vue-class-component'
import {Bilan} from './jsweet/ts/io/github/pascalheraud/diaps/db/Bilan'
import { BilanReport } from './jsweet/ts/io/github/pascalheraud/diaps/apis/BilanReport';

@Component
class BilanScreen extends BaseVueJS {
	bilanReport: BilanReport|null = null
	editedBilan: Bilan|null=null
	writingSpeedState: string = 'view'

	mounted() {
		document.querySelector("#mainContent")!.classList.remove("is-hidden");
		this.load();
	}

	getPersonneId(): number {
		const index: number = document.location.pathname.lastIndexOf('/');
		return parseInt(document.location.pathname.substr(index + 1));
	}

	load() {
		var input = {
			id: this.getPersonneId()
		}
		this.callApi('/apis/bilan/get', input).then((result:BilanReport) => {
			this.bilanReport = result;
		});
	}

	cancel() {
		this.writingSpeedState='view';
		this.editedBilan = null;
		this.load();
	}

	submit() {
		this.editedBilan.personneId = this.getPersonneId();
		this.callApi('/apis/bilan/update', this.editedBilan).then((result:Bilan) => {
			this.writingSpeedState='view';
			this.bilanReport.bilan = result;
		});
	}
	
	updateWritingSpeed() {
		this.writingSpeedState= 'edit';
		if (this.bilanReport==null) {
			this.editedBilan = <Bilan>{
				writingSpeedMax:0,
				writingSpeedNormal:0
			}
		} else {
			this.editedBilan = this.deepCopy(this.bilanReport.bilan);
		}
	}
};

window.onload = function() {
	new BilanScreen({
		el: '#mainContent'
	});
};


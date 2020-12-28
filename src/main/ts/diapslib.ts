import Component from 'vue-class-component'
import Vue from 'vue';

@Component
export default class BaseVueJS extends Vue {
	callApi(url: string, body?: any): Promise<any> {
		return new Promise(function(resolve: any, reject: any) {
			fetch(url, {
				headers: {
					'Content-Type': 'application/json'
				},
				method: body ? 'POST' : 'GET',
				body: body ? JSON.stringify(body) : null
			}).then((response) => {
				response.text().then((text) => {
					if (text == '') {
						resolve(undefined);
					} else {
						resolve(JSON.parse(text));
					}
				});
			});
		});
	}

	deepCopy<T>(source: T): T {
		return JSON.parse(JSON.stringify(source));
	}

	goFocus(selector: string) {
		(<HTMLInputElement>window.document.querySelector(selector)!).focus();
	}
}
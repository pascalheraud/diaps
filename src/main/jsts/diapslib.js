"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
var vue_class_component_1 = __importDefault(require("vue-class-component"));
var vue_1 = __importDefault(require("vue"));
var BaseVueJS = /** @class */ (function (_super) {
    __extends(BaseVueJS, _super);
    function BaseVueJS() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    BaseVueJS.prototype.callApi = function (url, body) {
        return new Promise(function (resolve, reject) {
            fetch(url, {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: body ? 'POST' : 'GET',
                body: body ? JSON.stringify(body) : null
            }).then(function (response) {
                response.text().then(function (text) {
                    if (text == '') {
                        resolve(undefined);
                    }
                    else {
                        resolve(JSON.parse(text));
                    }
                });
            });
        });
    };
    BaseVueJS.prototype.deepCopy = function (source) {
        return JSON.parse(JSON.stringify(source));
    };
    BaseVueJS.prototype.goFocus = function (selector) {
        window.document.querySelector(selector).focus();
    };
    BaseVueJS = __decorate([
        vue_class_component_1.default
    ], BaseVueJS);
    return BaseVueJS;
}(vue_1.default));
exports.default = BaseVueJS;
//# sourceMappingURL=diapslib.js.map
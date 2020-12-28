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
var diapslib_1 = __importDefault(require("./diapslib"));
var vue_class_component_1 = __importDefault(require("vue-class-component"));
var BilanScreen = /** @class */ (function (_super) {
    __extends(BilanScreen, _super);
    function BilanScreen() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.bilanReport = null;
        return _this;
    }
    BilanScreen.prototype.mounted = function () {
        document.querySelector("#mainContent").classList.remove("is-hidden");
        this.load().then(function () { });
    };
    BilanScreen.prototype.load = function () {
        var _this = this;
        return new Promise(function (resolve, reject) {
            var index = document.location.pathname.lastIndexOf('/');
            var personneId = parseInt(document.location.pathname.substr(index + 1));
            var input = {
                id: personneId
            };
            _this.callApi('/apis/bilan/get', input).then(function (result) {
                _this.bilanReport = result;
                resolve();
            });
        });
    };
    BilanScreen.prototype.reload = function (callback) {
        this.load().then(callback);
    };
    BilanScreen = __decorate([
        vue_class_component_1.default
    ], BilanScreen);
    return BilanScreen;
}(diapslib_1.default));
;
window.onload = function () {
    new BilanScreen({
        el: '#mainContent'
    });
};
//# sourceMappingURL=bilan.js.map
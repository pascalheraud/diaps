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
var moment_1 = __importDefault(require("moment"));
var vue_1 = __importDefault(require("vue"));
var vue_class_component_1 = __importDefault(require("vue-class-component"));
var diapslib_1 = __importDefault(require("./diapslib"));
var PersonnesScreen = /** @class */ (function (_super) {
    __extends(PersonnesScreen, _super);
    function PersonnesScreen() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.personnes = new Array();
        _this.state = 'list';
        _this.currentPersonne = {};
        _this.displayDeleteConfirmation = false;
        return _this;
    }
    PersonnesScreen.prototype.mounted = function () {
        document.querySelector("main.container").classList.remove("is-hidden");
        this.refresh();
    };
    PersonnesScreen.prototype.refresh = function () {
        var _this = this;
        return new Promise(function (resolve, reject) {
            _this.callApi('/apis/personnes/all').then(function (result) {
                _this.$data.personnes = result;
                _this.showList();
                resolve(undefined);
            });
        });
    };
    PersonnesScreen.prototype.submit = function () {
        var url = this.state == 'add' ? '/apis/personnes/add' : '/apis/personnes/update';
        this.callApi(url, this.currentPersonne).then(this.refresh);
    };
    PersonnesScreen.prototype.showFormEdit = function (personne) {
        var _this = this;
        this.state = 'edit';
        this.currentPersonne = this.deepCopy(personne);
        vue_1.default.nextTick(function () {
            _this.$refs.dateReport.focus();
        });
    };
    PersonnesScreen.prototype.showFormAdd = function () {
        var _this = this;
        this.state = 'add';
        this.currentPersonne = new Object();
        vue_1.default.nextTick(function () {
            _this.$refs.dateReport.focus();
        });
    };
    PersonnesScreen.prototype.showList = function () {
        this.state = 'list';
        this.currentPersonne = new Object();
    };
    PersonnesScreen.prototype.cancel = function () {
        this.showList();
    };
    PersonnesScreen.prototype.remove = function (personne) {
        this.currentPersonne = personne;
        this.displayDeleteConfirmation = true;
    };
    PersonnesScreen.prototype.confirmDelete = function () {
        this.displayDeleteConfirmation = false;
        this.callApi("/apis/personnes/del", this.currentPersonne).then(this.refresh);
    };
    PersonnesScreen.prototype.cancelDelete = function () {
        this.displayDeleteConfirmation = false;
        this.showList();
    };
    PersonnesScreen = __decorate([
        vue_class_component_1.default({
            filters: {
                frenchDate: function (dateISO) {
                    return moment_1.default(dateISO, 'YYYY-MM-DD').format("DD/MM/YYYY");
                }
            }
        })
    ], PersonnesScreen);
    return PersonnesScreen;
}(diapslib_1.default));
;
window.onload = function () {
    new PersonnesScreen({ el: 'main' });
};
//# sourceMappingURL=personnes.js.map